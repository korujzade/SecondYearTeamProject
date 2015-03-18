package rostering;

public class ConstraintsForDriver
{
	//1. The maximum driving time for any driver in any one day is 10 hours.
	public static boolean maxDriveTimePerDay (int minsToday, int upTillNow)
	{
		if((minsToday + upTillNow)<= 600)
			return true;
		else
			return false;
	}

	//2. There can be no more than 50 hours driven by any one driver in any one week.
	public static boolean maxDriveTimePerWeek(int minsWeek,int upTillNow)
	{
		if((minsWeek + upTillNow) <= 3000)
			return true;
		else
			return false;
	}

	//3. A driver can drive for a maximum of 5 hours at any one time and must have
	// a break of at least one hour. Breaks can only be taken at the bus depot.
	public static boolean continuousWork(int minsToday, int upTillNow, Boolean breakTime)
	{
		if((minsToday + upTillNow) <= 300)
			return true;
    
		else if((minsToday + upTillNow) > 300 && breakTime == false)
		{
			breakTime = true;
			return false;
		}	
		else if((minsToday + upTillNow) > 300  && breakTime == true)
			return true;
		else 
			return false;
	}   

}
