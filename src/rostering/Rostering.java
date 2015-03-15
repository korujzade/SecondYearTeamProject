package rostering;

import DRH.BusInfo;
import DRH.BusStopInfo;
import DRH.TimetableInfo.timetableKind;
import DRH.database;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.joda.time.LocalDate;

public class Rostering
{
	private static int counter;

	public static void assignBuses()
	{
		//database.openBusDatabase();

		// Buses ArrayList
		ArrayList<Bus> busesToAssign = new ArrayList();
		// initially add one bus to the list
		counter = 0;
		int[] BusIDs = BusInfo.getBuses();
		Bus bus1 = new Bus(BusIDs[counter]);
		busesToAssign.add(bus1);

		// get route ids
		int[] routeIDs = BusStopInfo.getRoutes();

		// three dimensional array keeps assigned buses for each weekdays,
		// routes and services
		Object[][][] rosterBus = new Object[7][4][200];

		// for weekdays, assign buses to services
		for (int i = 0; i <= 4; i++)
		{
			System.out.println("----------------------------");	
			System.out.println("Day: " + i);
			// for each routes
			for (int j = 0; j <= 3; j++)
			{
				System.out.println("--------------------------");
				System.out.println("Route" + j);
				Routes route1 = new Routes(routeIDs[j]);
				route1.setTheNumberOfServices(timetableKind.weekday);
				for (int k = 0; k < route1.getTheNumberOfServices(); k++)
				{
					// send first route and the service to service class to
					// get service 1 object
					Services service1 = new Services(routeIDs[j], timetableKind.weekday, k);
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
							busesToAssign.get(bus).setEndTimes(service1.getTo());
							
							has = true;
							System.out.print("busID: " + busesToAssign.get(bus).BusID + " ");
							System.out.print("from: " + service1.getFrom() + " to: " + service1.getTo());
							System.out.println(" endtimes: " + busesToAssign.get(bus).getEndTimes());
							break;
						}
					}
					// not found any bus suitable to service in list
					if (!has)
					{
						counter++;
						Bus bus = new Bus(BusIDs[counter]);
						busesToAssign.add(bus);
						rosterBus[i][j][k] = bus;
						// set the end time of bus to "to" time of service1
						bus.setEndTimes(service1.getTo());

						System.out.print("busID: " + busesToAssign.get(busesToAssign.size() - 1).BusID + " ");
						System.out.print("from: " + service1.getFrom() + " to: " + service1.getTo());
						System.out.println("busendtimes: " + bus.getEndTimes());
					}
				}
				System.out.println("size for route: " + busesToAssign.size());
			}
			
			System.out.println("size: " + busesToAssign.size());
			busesToAssign.clear();
			counter = 0;
			Bus bus2 = new Bus(BusIDs[counter]);
			busesToAssign.add(bus2);
			
			
		}

		// Saturday
		int i = 5;

		// for Saturdays
		while (i == 5)
		{
			for (int j = 0; j <= 3; j++)
			{
				Routes route1 = new Routes (routeIDs[j]);				
				route1.setTheNumberOfServices(timetableKind.saturday);
				for (int k = 0; k < route1.getTheNumberOfServices(); k++)
				{
					// rosterBus[0][0][0] = bus1;// assign bus to services
				}
			}
			i++;
		}

		// for Sundays
		while (i == 6)
		{
			for (int j = 0; j <= 3; j++)
			{
				Routes route1 = new Routes(routeIDs[j]);
				route1.setTheNumberOfServices(timetableKind.sunday);
				for (int k = 0; k < route1.getTheNumberOfServices(); k++)
				{
					// rosterBus[0][0][0] = bus1;// assign bus to services
				}
		
			}
			i++;
		}

	}

	// // check if any available bus suitable to assign to work
	// public static Boolean checkAvBuses()
	// {
	// BusIDs = BusInfo.getAvailabilityBuses();
	// for (int i = 0; i < BusIDs.length; i++)
	// {
	// database db = database.busDatabase;
	// if (db.select_record("bus_availability", "bus_availability_id",
	// BusIDs[i], "available", 0))
	// {
	// return true;
	// }
	// else
	// return false;
	// }
	// return false;
	// }
}
