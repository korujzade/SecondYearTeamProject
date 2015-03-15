package rostering;

import DRH.TimetableInfo;
import DRH.TimetableInfo.timetableKind;

public class Services
{
	private int from;
	private int to;
	private int kind;
	private int route;
	private int serviceNumber;

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

	public int getFrom()
	{
		return from;
	}

	public int getTo()
	{
		return to;
	}

}
