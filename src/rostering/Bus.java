package rostering;

import java.util.ArrayList;

public class Bus
{

	ArrayList<Integer> endTimes = new ArrayList();
	Integer BusID;
	int time;
//	int from;
//	int to;
//	int ServiceID:
	ArrayList<Integer> busStops = new ArrayList();
	
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

	public void setEndTimes(int time)
	{
		endTimes.add(time);
	}
	
	public Integer getBusID()
	{
		return this.BusID;
	}
	
	
}
