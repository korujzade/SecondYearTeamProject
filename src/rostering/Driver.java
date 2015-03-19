package rostering;
import java.util.ArrayList;
import DRH.*;

//Makes a driver object to be assigned to a bus and service
public class Driver {

	private int driverID;
	public ArrayList<Services> services = new ArrayList<Services>();//store the services this driver is assigned to
	public ArrayList<Bus> buses = new ArrayList<Bus>();//store the corresponding buses 
	ArrayList<Integer> endTimes = new ArrayList<Integer>();
	int minsToday;
	int hoursThisWeek;
	boolean breaking = false;
	int breakFrom = 0;
	int breakTo = 0;

	public ArrayList<Integer> serviceNumbersAssigned = new ArrayList<Integer>();
	
	public Driver(int driverID) {
		this.setDriverID(driverID);
		minsToday = 0;
		endTimes.add(0);
	}
	



	public void setServiceNumbersAssigned(int number)
	{
		this.serviceNumbersAssigned.add(number);
	}

	
	public ArrayList<Integer> getServiceNumbersAssigned()
	{
		return serviceNumbersAssigned;
	}



	public int getMinsToday() {
		return minsToday;
	}

	public void setHoursToday(int minsToday) {
		this.minsToday = minsToday;
	}
	
	public ArrayList<Integer> getEndTimes()
	{
		return endTimes;
	}
	
	public void setEndTimes(int time)
	{
		endTimes.add(time);
	}

	//Add a journey to the drivers work and what bus he will be driving. Also update his hours.
	public void AddServiceBus(Services service, Bus bus) {
        services.add(service);
        buses.add(bus);
        int driveTime = service.getTo() - service.getFrom(); //length of time for this journey
        //update the driver's hours accordingly
        minsToday += driveTime;
	    DriverInfo.setHoursThisWeek(driverID, driveTime + DriverInfo.getHoursThisWeek(driverID));
	    DriverInfo.setHoursThisYear(driverID, driveTime + DriverInfo.getHoursThisYear(driverID));
	}

	public int getDriverID() {
		return driverID;
	}

	//probably unneeded except in the case of replacing an ill driver or something
	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}
	
	// set the mins driver work each week to database 
	public void setMinsThisWeek(int mins)
	{
		DriverInfo.setHoursThisWeek(driverID, mins);
	}
	
	// get from database
	//does what it says on the tin, directly gets from DriverInfo method
	int getHoursThisWeek(){
		return DriverInfo.getHoursThisWeek(driverID);
	}
		
	int getHoursThisYear()
	{
		return DriverInfo.getHoursThisYear(driverID);
	}
	
	public void setBreaking(boolean breaking)
	{
		this.breaking = breaking;
	}
	
	public boolean getBreaking()
	{
		return this.breaking;
	}
	
}