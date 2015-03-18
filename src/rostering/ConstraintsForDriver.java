package rostering;

public class ConstraintsForDriver
{

	
	public static boolean checkConstraints(Driver newDriver, Services newService)
	{
		if(!maxDriveTimePerDay(newDriver.getMinsToday(), newService.getServiceTime()))
		{
			return false;
		}
		else if(!maxDriveTimePerWeek(newDriver.getHoursThisWeek(), newService.getServiceTime()))
		{	
			return false;
		}
		else if (!continuousWork(newDriver.getMinsToday(), newService.getServiceTime(), newDriver.getBreaking()))
			return false;
		else if (!continuousWorkWithoutBreaking(newDriver.getMinsToday(), newService.getServiceTime(), newDriver.getBreaking()))
		{
			newDriver.setBreaking(true);
			newDriver.setEndTimes(newDriver.endTimes.get(newDriver.endTimes.size() - 1) + 60);
			return false;
		}
		else
			return true;
		
	}
	
	
	
	//1. The maximum driving time for any driver in any one day is 10 hours.
	public static boolean maxDriveTimePerDay (int minsToday, int upTillNow)
	{
		if((minsToday + upTillNow)<= 600)
			return true;
		else
			return false;
	}

	//2. There can be no more than 50 hours driven by any one driver in any one week.
	public static boolean maxDriveTimePerWeek(int minsWeek, int upTillNow)
	{
		if((minsWeek + upTillNow) <= 3000)
			return true;
		else
			return false;
	}

	//3. A driver can drive for a maximum of 5 hours at any one time and must have
	// a break of at least one hour. Breaks can only be taken at the bus depot.
	public static boolean continuousWork(int minsToday, int upTillNow, boolean breaking)
	{
		if((minsToday + upTillNow) <= 300)
			return true;	
		else if((minsToday + upTillNow) > 300  && breaking == true)
			return true;
		else 
			return false;
	}
	
	 public static boolean continuousWorkWithoutBreaking(int minsToday, int upTillNow, boolean breaking)
	 {
			if((minsToday + upTillNow) > 300 && breaking == false)
			{
				return false;
			}
			else
				return true;
		 
	 }
}
