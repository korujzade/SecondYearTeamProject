package rostering;

import java.util.TimerTask;

import DRH.BusStopInfo;
import DRH.TimetableInfo;
import DRH.TimetableInfo.timetableKind;

public class Routes
{
	String routeName;
	private int theNumberOfServices;
	public int routeID;
	public TimetableInfo.timetableKind kind;

	public Routes(int routeID)
	{
		super();
		this.kind = kind;
		this.routeID = routeID;
		
		this.routeName = BusStopInfo.getRouteName(routeID);

	}

	public Routes(int routeID, TimetableInfo.timetableKind kind)
	{
		super();
		this.kind = kind;
		this.routeID = routeID;

		this.theNumberOfServices = TimetableInfo.getNumberOfServices(routeID,
				kind);
		this.routeName = BusStopInfo.getRouteName(routeID);

	}
	
	

	
	public void setTheNumberOfServices(timetableKind kind)
	{
		
		this.theNumberOfServices = TimetableInfo.getNumberOfServices(routeID, kind);
		
	}
	
	public int getTheNumberOfServices()
	{
		return theNumberOfServices;
	}

	
	public String getRouteName()
	{
		return this.routeName;
	}
	
	public void setKind(timetableKind kind)
	{
		this.kind = kind;
	}
	
	public timetableKind setKind()
	{
		  return kind;
	}

}
