package rostering;

import java.util.ArrayList;

import DRH.TimetableInfo;
import DRH.TimetableInfo.timetableKind;

public class Services
{
	public Integer from;
	public Integer to;
	private int kind;
	private int route;
	private int serviceNumber;
	
//	ArrayList<Bus> assignedBusses = new ArrayList<Bus>();
//	ArrayList<Driver> assignedDrivers= new ArrayList<Driver>();

	public Services(int route, timetableKind kind, int serviceNumber)
	{
		this.route = route;
		this.serviceNumber = serviceNumber;

		kind = kind;
		from = TimetableInfo.getServiceTimes(route, kind, serviceNumber)[0];
		to = TimetableInfo.getServiceTimes(route, kind, serviceNumber)[TimetableInfo
				.getServiceTimes(route, kind, serviceNumber).length - 1];
	}

	public int getKind()
	{
		return kind;
	}

	public void setKind(int kind)
	{
		this.kind = kind;
	}

	public Integer getFrom()
	{
		return this.from;
	}

	public Integer getTo()
	{
		return this.to;
	}
	
	public int getServiceTime()
	{
		return to - from;
	}

}
