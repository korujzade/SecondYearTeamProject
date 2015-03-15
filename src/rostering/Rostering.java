package rostering;
import DRH.BusInfo;
import DRH.BusStopInfo;
import DRH.TimetableInfo.timetableKind;
import DRH.database;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import org.joda.time.LocalDate;


public class Rostering
{

	public static void assignBuses(int from, int to, Date date)
	{
		database.openBusDatabase();
		
//		// if there is no available bus to assign to work
//		// add new bus to available
//		int checker = 0;
//		if (!(checkAvBuses()))
//		{
//			database.busDatabase.new_record("bus_availability",
//					new Object[][]
//					{
//					{ "available", 1 },
//					{ "day", date },
//					{ "bus", BusIDs[checker] } });
//			checker++;
//		}
	
		// Buses ArrayList
		ArrayList<Bus> busesToAssign  = new ArrayList();
		// initially add one bus to the list
		int counter = 0;
		int[] BusIDs = BusInfo.getBuses();
		Bus bus1 = new Bus(BusIDs[counter]);
		busesToAssign.add(bus1);
		
		
		
		// get route ids
		int[] routeIDs = BusStopInfo.getRoutes();

		// create four route objects
		Routes route1 = new Routes(routeIDs[0]);
		Routes route2 = new Routes(routeIDs[1]);
		Routes route3 = new Routes(routeIDs[2]);
		Routes route4 = new Routes(routeIDs[3]);
	
		// three dimensional array keeps assigned buses for each weekdays, routes and services
		Object[][][] rosterBus = new Object[7][4][200];
		
		// for weekdays, assign buses to services
		for (int i = 0; i <= 4; i++)
		{
			// for each routes
			for (int j = 0; j <= 3; j++)
			{
				// first route
				if (j == 0)
				{
					route1.setTheNumberOfServices(timetableKind.weekday);
					for (int k = 0; k < route1.getTheNumberOfServices(); k++)
					{
						
					   //rosterBus[i][j]k]  = bus1;// assign bus to services
					}					
				}
				
				// second route
				if (j == 1)
				{	
					route2.setTheNumberOfServices(timetableKind.weekday);
					for (int k = 0; k < route2.getTheNumberOfServices(); k++)
					{
					//   rosterBus[0][0][0]  = bus1;// assign bus to services
					}					
				}
				
				// fourth route
				if (j == 2)
				{
					route3.setTheNumberOfServices(timetableKind.weekday);
					for (int k = 0; k < route3.getTheNumberOfServices(); k++)
					{
					//   rosterBus[0][0][0]  = bus1;// assign bus to services
					}					
				}
				
				// fifth route
				if (j == 3)
				{
					route4.setTheNumberOfServices(timetableKind.weekday);
					for (int k = 0; k < route4.getTheNumberOfServices(); k++)
					{
					//   rosterBus[0][0][0]  = bus1;// assign bus to services
					}					
				}
			}					
		}
		
		// Saturday
		int i = 5;		
		
		// for Saturdays
		while(i == 5)
		{
			for (int j = 0; j <= 3; j++)
			{				
				// first route
				if (j == 0)
				{
					route1.setTheNumberOfServices(timetableKind.saturday);
					for (int k = 0; k < route1.getTheNumberOfServices(); k++)
					{
					//   rosterBus[0][0][0]  = bus1;// assign bus to services
					}					
				}
				
				// second route
				if (j == 1)
				{	
					route2.setTheNumberOfServices(timetableKind.saturday);
					for (int k = 0; k < route2.getTheNumberOfServices(); k++)
					{
					//   rosterBus[0][0][0]  = bus1;// assign bus to services
					}					
				}
				
				// fourth route
				if (j == 2)
				{
					route3.setTheNumberOfServices(timetableKind.saturday);
					for (int k = 0; k < route3.getTheNumberOfServices(); k++)
					{
					//   rosterBus[0][0][0]  = bus1;// assign bus to services
					}					
				}
				
				// fifth route
				if (j == 3)
				{
					route4.setTheNumberOfServices(timetableKind.saturday);
					for (int k = 0; k < route4.getTheNumberOfServices(); k++)
					{
					//   rosterBus[0][0][0]  = bus1;// assign bus to services
					}					
				}
			}
			i++;
		}
		
		// for Sundays
		while(i == 6)
		{
			for (int j = 0; j <= 3; j++)
			{				
				// first route
				if (j == 0)
				{
					route1.setTheNumberOfServices(timetableKind.sunday);
					for (int k = 0; k < route1.getTheNumberOfServices(); k++)
					{
					//   rosterBus[0][0][0]  = bus1;// assign bus to services
					}					
				}
				
				// second route
				if (j == 1)
				{	
					route2.setTheNumberOfServices(timetableKind.sunday);
					for (int k = 0; k < route2.getTheNumberOfServices(); k++)
					{
					//   rosterBus[0][0][0]  = bus1;// assign bus to services
					}					
				}
				
				// fourth route
				if (j == 2)
				{
					route3.setTheNumberOfServices(timetableKind.sunday);
					for (int k = 0; k < route3.getTheNumberOfServices(); k++)
					{
					//   rosterBus[0][0][0]  = bus1;// assign bus to services
					}					
				}
				
				// fifth route
				if (j == 3)
				{
					route4.setTheNumberOfServices(timetableKind.sunday);
					for (int k = 0; k < route4.getTheNumberOfServices(); k++)
					{
					//   rosterBus[0][0][0]  = bus1;// assign bus to services
					}					
				}
			}
			i++;
		}		
	
	}

//	// check if any available bus suitable to assign to work
//	public static Boolean checkAvBuses()
//	{
//		BusIDs = BusInfo.getAvailabilityBuses();
//		for (int i = 0; i < BusIDs.length; i++)
//		{
//			database db = database.busDatabase;
//			if (db.select_record("bus_availability", "bus_availability_id", BusIDs[i], "available", 0))
//			{	
//				return true;
//			}
//			else
//				return false;
//		}
//		return false;
//	}
}
