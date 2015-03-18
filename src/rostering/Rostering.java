package rostering;

import DRH.*;
import DRH.TimetableInfo.timetableKind;
import java.util.*;

public class Rostering
{
	private static int counter;

	public static void assignBuses()
	{
		// database.openBusDatabase();

		// Buses ArrayList
		ArrayList<Bus> busesToAssign = new ArrayList<Bus>();
		// initially add one bus to the list
		counter = 0;
		// int[] BusIDs = BusInfo.getBuses();
		// two dimensional array, one for bus ids, and one for the number of
		// times buses used
		int[][] BusIDs = new int[BusInfo.getBuses().length][2];

		for (int i = 0; i < BusInfo.getBuses().length; i++)
		{
			BusIDs[i][0] = BusInfo.getBuses()[i];
			BusIDs[i][1] = 0;
		}

		// Bus bus1 = new Bus(BusIDs[counter]);
		// busesToAssign.add(bus1);

		Bus bus1 = new Bus(BusIDs[counter][0]);
		busesToAssign.add(bus1);

		// get route ids
		int[] routeIDs = BusStopInfo.getRoutes();

		// three dimensional array keeps assigned buses for each weekdays,
		// routes and services
		Object[][][] rosterBus = new Object[7][4][200];

		// for weekdays, assign buses to services
		for (int i = 0; i <= 6; i++)
		{
			System.out.println("----------------------------");
			System.out.println("Day: " + (i + 1));
			// for each routes
			for (int j = 0; j <= 3; j++)
			{
				System.out.println("---------------------------");
				System.out.println("Route: " + (j + 1));
				Routes route1 = new Routes(routeIDs[j]);
				timetableKind kind = null;
				if (i <= 4)
					kind = timetableKind.weekday;
				if (i == 5)
					kind = timetableKind.saturday;
				if (i == 6)
					kind = timetableKind.sunday;

				route1.setTheNumberOfServices(kind);
				for (int k = 0; k < route1.getTheNumberOfServices(); k++)
				{
					// send first route and the service to service class to
					// get service 1 object
					Services service1 = new Services(routeIDs[j], kind, k);
					// get this service's "from" time
					int serviceFrom = service1.getFrom();
					// get the number of the busses on the list to check if
					// bus suitable to assign to service
					int busLength = busesToAssign.size();
					boolean has = false;
					for (int bus = 0; bus < busLength; bus++)
					{

						// if suitable
						if (serviceFrom > busesToAssign.get(bus).endTimes
								.get(busesToAssign.get(bus).endTimes.size() - 1))
						{
							// assign bus
							rosterBus[i][j][k] = busesToAssign.get(bus);
							// set the end time of bus to "to" time of
							// service1
							busesToAssign.get(bus)
									.setEndTimes(service1.getTo());

							// get the id of the just assigned to service
							// increase the number of the times this bus used
							int id = busesToAssign.get(bus).getBusID();
							for (int l = 0; l < BusIDs.length; l++)
							{
								if (BusIDs[l][0] == id)
								{
									BusIDs[l][1]++;
								}
							}

							has = true;
							System.out.print("busID: "
									+ busesToAssign.get(bus).BusID + " ");
							System.out.print("from: " + service1.getFrom()
									+ " to: " + service1.getTo());
							System.out.println(" endtimes: "
									+ busesToAssign.get(bus).getEndTimes());
							break;
						}
					}
					// not found any bus suitable to service in list
					if (!has)
					{
						counter++;
						Bus bus = new Bus(BusIDs[counter][0]);
						busesToAssign.add(bus);
						rosterBus[i][j][k] = bus;
						// set the end time of bus to "to" time of service1
						bus.setEndTimes(service1.getTo());

						// increase the number of the times this bus used
						int id = bus.getBusID();
						for (int l = 0; l < BusIDs.length; l++)
						{
							if (BusIDs[l][0] == id)
							{
								BusIDs[l][1]++;
							}
						}

						System.out
								.print("busID: "
										+ busesToAssign.get(busesToAssign
												.size() - 1).BusID + " ");
						System.out.print("from: " + service1.getFrom()
								+ " to: " + service1.getTo());
						System.out.println(" endtimes: " + bus.getEndTimes());
					}
				}
				System.out
						.println("No of buses used in total (end of the route): "
								+ busesToAssign.size());
			}

			System.out.println("No of buses used for this day : "
					+ busesToAssign.size());
			busesToAssign.clear();

			// sort array based on the number of times they used
			for (int f = 0; f < BusIDs.length - 1; f++)
			{
				int h = 1;

				while (h < (BusIDs.length - 1 - f))
				{
					if (BusIDs[f][1] > BusIDs[f + h][1])
					{
						int temp = BusIDs[f][1];
						BusIDs[f][1] = BusIDs[f + h][1];
						BusIDs[f + h][1] = temp;

						temp = BusIDs[f][0];
						BusIDs[f][0] = BusIDs[f + h][0];
						BusIDs[f + h][0] = temp;
					}
					h++;
				}

			}
			counter = 0;
			Bus bus2 = new Bus(BusIDs[counter][0]);
			busesToAssign.add(bus2);
		}
	}// assign buses

	private static boolean constraintsPassed(Driver d, Services s)
	{
		boolean breakTime = d.getBreaking();
		int minsWeek = d.getHoursThisWeek();
		int minsToday = d.getMinsToday();
		int upTillNow = s.getServiceTime();
		
		if (ConstraintsForDriver.maxDriveTimePerDay(minsToday, upTillNow)
				&& ConstraintsForDriver
						.maxDriveTimePerWeek(minsWeek, upTillNow)
				&& ConstraintsForDriver.continuousWork(minsToday, upTillNow,
						breakTime))
			return true;
		else
			return false;
	}

	public static void assignDrivers(Date date)
	{

		ArrayList<Driver> driversToAssign = new ArrayList<Driver>();
		// Start with one driver
		counter = 0;
		int[] DriverIDs = DriverInfo.getDrivers();
		while(!(DriverInfo.isAvailable(DriverIDs[counter], date)))
		{
			counter++;
		}
		
		Driver driver1 = new Driver(DriverIDs[counter]);
		driversToAssign.add(driver1);

		int[] routeIDs = BusStopInfo.getRoutes();

		// three dimensional array keeps assigned buses for each weekdays,
		// routes and services - same as buses
		Object[][][] rosterDriver = new Object[7][4][200];

		// for each day assign drivers to services
		// i - day, j - route, k - service
		for (int i = 0; i <= 6; i++)
		{
			System.out.println("----------------------------");
			System.out.println("Day: " + (i + 1));
			for (int j = 0; j <= 3; j++)
			{
				System.out.println("--------------------------");
				System.out.println("Route" + (j + 1));
				Routes route1 = new Routes(routeIDs[j]);
				if (i <= 4)
					route1.setTheNumberOfServices(timetableKind.weekday);
				if (i == 5)
					route1.setTheNumberOfServices(timetableKind.saturday);
				if (i == 6)
					route1.setTheNumberOfServices(timetableKind.sunday);

				for (int k = 0; k < route1.getTheNumberOfServices(); k++)
				{
					// get kth service of current (jth) route
					Services service1 = new Services(routeIDs[j],
							timetableKind.weekday, k);
					// get this service's "from" (start) time
					int serviceFrom = service1.getFrom();
					boolean has = false;
					// check each driver in assignment list
					for (int driver = 0; driver < driversToAssign.size(); driver++)
					{
						// if suitable
						// last element in this drivers' endtimes list
						int element = (driversToAssign.get(driver).endTimes
								.size()) - 1;
						int a = driversToAssign.get(driver).endTimes
								.get(element);
						if (constraintsPassed(driversToAssign.get(driver), service1))
						{
							// assign driver
							rosterDriver[i][j][k] = driversToAssign.get(driver);
							// set the end time of bus to "to" time of
							// service1
							driversToAssign.get(driver).setEndTimes(
									service1.getTo());

							has = true;
							System.out.print("DriverID: "
									+ driversToAssign.get(driver).getDriverID()
									+ " ");
							// System.out.print("busID: " +
							// busesToAssign.get(bus).BusID + " ");
							System.out.print("from: " + service1.getFrom()
									+ " to: " + service1.getTo());
							System.out
									.println(" endtimes: "
											+ driversToAssign.get(driver)
													.getEndTimes());
							break;
						}
					}

					// not found any bus suitable to service in list
					if (!has)
					{
						counter++;
						Driver driver = new Driver(DriverIDs[counter]);
						driversToAssign.add(driver);
						rosterDriver[i][j][k] = driver;
						// set the end time of bus to "to" time of service1
						driver.setEndTimes(service1.getTo());

						System.out.print("DriverID: "
								+ driversToAssign.get(
										driversToAssign.size() - 1)
										.getDriverID() + " ");
						System.out.print("from: " + service1.getFrom()
								+ " to: " + service1.getTo());
						System.out.println(" driverendtimes: "
								+ driver.getEndTimes());
					}
				}
				System.out.println("size for route: " + driversToAssign.size());
			}

			System.out.println("size: " + driversToAssign.size());
			driversToAssign.clear();
			counter = 0;
			Driver driver2 = new Driver(DriverIDs[counter]);
			driversToAssign.add(driver2);

		}
	}

}// class
