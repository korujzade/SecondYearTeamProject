package rostering;

import java.util.ArrayList;

public class Bus
{

	ArrayList<Integer> endTimes = new ArrayList();
	int BusID;
	ArrayList busStops = new ArrayList();
	
	public Bus(int busID)
	{
		BusID = busID;
		endTimes.add(0);
	}

	public ArrayList getBusStops()
	{
		return busStops;
	}

	public void setBusStops(ArrayList busStops)
	{
		this.busStops = busStops;
	}



	public ArrayList getEndTimes()
	{
		return endTimes;
	}

	public void setTimes(ArrayList times)
	{
		this.endTimes = endTimes;
	}
	
	
	
}
