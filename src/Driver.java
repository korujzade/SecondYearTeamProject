import java.util.ArrayList;

//Makes a driver object to be assigned to a bus and service
public class Driver {

	private int DriverID;
	public ArrayList<Service> services = new ArrayList<Service>();//store the services this driver is assigned to
	public ArrayList<Bus> buses = new ArrayList<Bus>();//store the corresponding buses 
	
	
	public Driver(int DriverID) {
		this.setDriverID(DriverID);
	}
	
	public void AddServiceBus(Service service, Bus bus) {
        services.add(service);
        buses.add(bus);
	}

	public int getDriverID() {
		return DriverID;
	}

	public void setDriverID(int driverID) {
		DriverID = driverID;
	}
	
	
	
	
}
