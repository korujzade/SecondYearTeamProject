package rostering;

import DRH.TimetableInfo;

public class Services
{
	private int from;
	private int to;
	private int kind;
	private int route;
	private int serviceNumber;
	
	public Services(int route, int serviceNumber)
	{
		this.route = route;
		this.serviceNumber = serviceNumber;
	}
	
	public int getKind()
	{
		return kind;
	}
	public void setKind(int kind)
	{
		this.kind = kind;
	}
	public int getFrom()
	{
		return from;
	}
	public void setFrom()
	{
		this.from = TimetableInfo.getServiceTimes(route, serviceNumber)[0];
	}
	public int getTo()
	{
		return to;
	}
	public void setTo(int to)
	{
		this.from = TimetableInfo.getServiceTimes(route, serviceNumber)[TimetableInfo.getServiceTimes(route, serviceNumber).length - 1];
	}
	
	
}
