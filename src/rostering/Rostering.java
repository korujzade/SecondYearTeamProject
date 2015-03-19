package rostering;

import DRH.*;
import DRH.TimetableInfo.timetableKind;
import java.util.*;

import org.joda.time.LocalDate;

public class Rostering
{
	private static int counter;

	public static Bus[][][] assignBuses()
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
		Bus[][][] rosterBus = new Bus[7][4][200];

		// for weekdays, assign buses to services
		for (int i = 0; i < 1; i++)
		{
			System.out.println("----------------------------");
			System.out.println("Day: " + (i + 1));
			// for each routes
			for (int j = 0; j < 4; j++)
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
							if(j != 2){
							  busesToAssign.get(bus)
									.setEndTimes(service1.getTo());
							}
							//if route 3 - allow 15 mins to get back
							else {
							  busesToAssign.get(bus)
								.setEndTimes(service1.getTo() + 15);
							}

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
						if (j != 2) {bus.setEndTimes(service1.getTo());}
						else {bus.setEndTimes(service1.getTo() + 15);}

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
		
		return rosterBus;
		
		

	}// assign buses

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// three dimensional array keeps assigned buses for each weekdays,
	// routes and services - same as buses
	public static Driver[][][] rosterDriver = new Driver[7][4][200];
	
	
	public static Driver[][][] assignDrivers(Date date)
	{
		// cast date to joda's localdate
		LocalDate datelc = new LocalDate(date);

		// arraylist of drivers, gotten from db to check whether available for
		// assigning
		ArrayList<Driver> driversToAssign = new ArrayList<Driver>();

		// all drivers from db
		int[] DriverIDs = DriverInfo.getDrivers();

		// Start with one driver
		counter = 0;
		// check if driver available for that date
		while (!(DriverInfo.isAvailable(DriverIDs[counter], date)))
		{
			counter++;
		}

		// create that driver object
		Driver driver1 = new Driver(DriverIDs[counter]);

		// set today's work time to 0
		driver1.setHoursToday(0);

		// add to arraylist
		driversToAssign.add(driver1);

		// get all routes
		int[] routeIDs = BusStopInfo.getRoutes();



		// for each day assign drivers to services
		// i - day, j - route, k - service
		for (int i = 0; i <= 0; i++)
		{
			// cast localdate to date format
			Date datedt = datelc.toDate();

			System.out.println("----------------------------");
			System.out.println("Day: " + (i + 1));

			// routes
			for (int j = 0; j <= 0; j++)
			{
				System.out.println("--------------------------");
				System.out.println("Route" + (j + 1));

				// route object for current route
				Routes route1 = new Routes(routeIDs[j]);

				// assign kind
				if (i <= 4)
					route1.setTheNumberOfServices(timetableKind.weekday);
				if (i == 5)
					route1.setTheNumberOfServices(timetableKind.saturday);
				if (i == 6)
					route1.setTheNumberOfServices(timetableKind.sunday);

				// services
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
						int lastEndTime = driversToAssign.get(driver).endTimes
								.get(element);

						// constraints for drivers (legality I)
						if (ConstraintsForDriver.checkConstraints(
								driversToAssign.get(driver), service1))
						{
							// legality II
							if (serviceFrom > lastEndTime)
							{
								// assign driver
								rosterDriver[i][j][k] = driversToAssign
										.get(driver);
								// set the end time of bus to "to" time of
								// service1
								driversToAssign.get(driver).setEndTimes(
										service1.getTo());

								// update driver's work time today
								driversToAssign.get(driver).setHoursToday(
										driversToAssign.get(driver)
												.getMinsToday()
												+ service1.getServiceTime());

								// update driver's work time this week
								driversToAssign.get(driver).setMinsThisWeek(
										driversToAssign.get(driver)
												.getHoursThisWeek()
												+ service1.getServiceTime());
								has = true;
								
								//set serviceNumberToDriver
								driver1.setServiceNumbersAssigned(k);
								
								System.out.print("DriverID: "
										+ driversToAssign.get(driver)
												.getDriverID() + " ");

								System.out.print("from: " + service1.getFrom()
										+ " to: " + service1.getTo());

								System.out.println(" endtimes: "
										+ driversToAssign.get(driver)
												.getEndTimes());
								break;
							}

						}
					} // for - search on arraylist

					// not found any driver suitable to service in list
					if (!has)
					{
						counter++;
						// get new driver from db, not has holiday and has less
						// than 50 hours
						while (!(DriverInfo.isAvailable(DriverIDs[counter],
								datedt))
								|| DriverInfo
										.getHoursThisWeek(DriverIDs[counter]) > 3000)
						{
							counter++;
						}

						Driver driver = new Driver(DriverIDs[counter]);
						driversToAssign.add(driver);
						rosterDriver[i][j][k] = driver;

						//set serviceNumberToDriver
						driver.setServiceNumbersAssigned(k);
						
						// set the end time of bus to "to" time of service1
						driver.setEndTimes(service1.getTo());

						// update driver's work time today
						driver.setHoursToday(driver.getMinsToday()
								+ service1.getServiceTime());

						// update driver's work time this week
						driver.setMinsThisWeek(driver.getHoursThisWeek()
								+ service1.getServiceTime());

						System.out.print("DriverID: "
								+ driversToAssign.get(
										driversToAssign.size() - 1)
										.getDriverID() + " ");
						System.out.print("from: " + service1.getFrom()
								+ " to: " + service1.getTo());
						System.out.println(" driverendtimes: "
								+ driver.getEndTimes());
					} // not found in arraylist

					datelc.plusDays(1);

				} // days

				System.out.println("size for route: " + driversToAssign.size());
			}
			System.out.println("size: " + driversToAssign.size());
			driversToAssign.clear();
			counter = 0;
			Driver driver2 = new Driver(DriverIDs[counter]);
			driversToAssign.add(driver2);

		}

		return rosterDriver;
	}
	
	
	public static Driver assignedDriverID(String idst, int weekday)
	{

		// get all routes
		int[] routeIDs = BusStopInfo.getRoutes();
		
		int id = Integer.parseInt(idst);
		//Driver newDriver = new Driver(id);
		
		Driver[][][] driverRoster = rosterDriver;
		
		for (int i = weekday; i < weekday; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				Routes route1 = new Routes(routeIDs[j]);
				for (int k = 0; k < route1.getTheNumberOfServices(); k++)
				{
					if(driverRoster[i][j][k].getDriverID() == id)
					{
						return driverRoster[i][j][k];
					}
				}
			}
			
		}	
		
		return null;
		
	}
	

}// class
