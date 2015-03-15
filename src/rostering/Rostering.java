package rostering;
import DRH.BusInfo;
import DRH.BusStopInfo;
import DRH.database;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import org.joda.time.LocalDate;


public class Rostering
{
	
	private static final Routes Routes = null;
	public static int[] BusIDs;

	public static void assignBuses(int from, int to, Date date)
	{
		database.openBusDatabase();
		
		// if there is no available bus to assign to work
		// add new bus to available
		int checker = 0;
		if (!(checkAvBuses()))
		{
			database.busDatabase.new_record("bus_availability",
					new Object[][]
					{
					{ "available", 1 },
					{ "day", date },
					{ "bus", BusIDs[checker] } });
			checker++;
		}
	
		// get route ids
		int[] routeIDs = BusStopInfo.getRoutes();
		
		Routes route1 = new Routes(routeIDs[0]);
		//route1.setTheNumberOfServices();

		
		// three dimensional array keeps assigned buses for each weekdays, routes and services
		Object[][][] rosterBus = new Object[6][3][200];
		
		// for weekdays, assign buses to services
		for (int i = 0; i <= 4; i++)
		{
			for (int j = 0; j <= 3; j++)
			{				
				for (int k = 0; k < route1.getTheNumberOfServices(); k++)
				{
				
				}
			}					
		}
		
		int i = 5;
		
		// for saturday
		while(i == 5)
		{
			for (int j = 0; j <= 3; j++)
			{				
				for (int k = 0; k <= 200; k++)
				{
				
				}
			}
			i++;
		}
		
		// for sundays
		while(i == 6)
		{
			for (int j = 0; j <= 3; j++)
			{				
				for (int k = 0; k <= 200; k++)
				{
				
				}
			}
			i = 0;
		}


		
		
	}

	// check if any available bus suitable to assign to work
	public static Boolean checkAvBuses()
	{
		BusIDs = BusInfo.getAvailabilityBuses();
		for (int i = 0; i < BusIDs.length; i++)
		{
			database db = database.busDatabase;
			if (db.select_record("bus_availability", "bus_availability_id", BusIDs[i], "available", 0))
			{	
				return true;
			}
			else
				return false;
		}
		return false;
	}
}
