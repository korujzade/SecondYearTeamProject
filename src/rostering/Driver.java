package rostering;
import java.util.ArrayList;
import DRH.*;

//Makes a driver object to be assigned to a bus and service
public class Driver {

	private int driverID;
	public ArrayList<Services> services = new ArrayList<Services>();//store the services this driver is assigned to
	public ArrayList<Bus> buses = new ArrayList<Bus>();//store the corresponding buses 
	
	
	public Driver(int driverID) {
		this.setDriverID(driverID);
	}
	
	//Add a journey to the drivers work and what bus he will be driving. Also update his hours.
	public void AddServiceBus(Services service, Bus bus) {
        services.add(service);
        buses.add(bus);
        int driveTime = service.getTo() - service.getFrom(); //length of time for this journey
        //update the driver's hours accordingly
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
	
	
	
	
}