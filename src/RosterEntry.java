import java.util.*;

//The RosterEntry class, this combines a bus, driver and specific journey into one roster entry
//To create a roster entry supply the IDs of the bus, driver and service.
//Ben Davis
public class RosterEntry {
    private int busID;
    private int driverID;
    private int serviceID;
    private Date day;
	private int hoursThisRoute; //ungettable
	
	//constructor
	public RosterEntry(int busID, int driverID, int serviceID, Date day) {
		this.busID = busID;
		this.driverID = driverID;
	    this.serviceID = serviceID;
	    this.day = day;
	    
	    //update the db accordingly
	    hoursThisRoute = 0; //calc this from db
	    //update the hours of the driver
	    DriverInfo.setHoursThisWeek(driverID, hoursThisRoute + DriverInfo.getHoursThisWeek(driverID));
	    DriverInfo.setHoursThisYear(driverID, hoursThisRoute + DriverInfo.getHoursThisYear(driverID));
	    
	}
	
	//getters
	public int getBusID() {
		return this.busID;
	}
	public int getDriverID() {
	    return this.driverID;
	}
	public int getServiceID() {
       return this.serviceID;
	}
    public Date getDay() {
        return this.day;
    }
	//return finish time maybe?
    public int getFinishTime(){
		return 0;
	}
	//toString method - future changes to print service name/time	
	public String toString() {
		return "Driver ID: " + DriverInfo.getName(driverID) + 
				 " is driving bus ID: " + busID + 
				   " on service ID: " + serviceID;
	}


		
		
}
