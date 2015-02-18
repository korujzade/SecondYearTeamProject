
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/*
 * A very simple application illustrating how to use the interface.
 * Prints the names of all the drivers in the database.
 * @author John Sargeant
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        database.openBusDatabase();
        int[] driverIDs = DriverInfo.getDrivers();
        String[] driverNames = new String [driverIDs.length];
//        for (int i=0; i<driverIDs.length; i++)
//            System.out.println(DriverInfo.getName(driverIDs[i]) + ", " + DriverInfo.getNumber(driverIDs[i]));
//        
//        String from = "2015-02-15";
//        String to = "2015-02-20";
//        
//        LocalDate from1  = new LocalDate(from);
//        LocalDate to1 = new LocalDate(to);
//        
//        int a = Days.daysBetween(from1, to1).getDays() + 1;
//        System.out.println(a);

        System.out.println(DRHI.checkDriverHoliday(2025, "2015-12-12", "2015-12-21"));
        
    }

//    public static ArrayList<String> checkDriverHoliday(int id, String fromdate, String todate)
//    {
//        database.openBusDatabase();
//             
//        // getNumber method return string driver Number
//        String driverNumberString;
//        driverNumberString = DriverInfo.getNumber(id);
//        
//        // parse driver number to integer
//        int driverNumber = Integer.parseInt(driverNumberString);
//        int holidays;
//        
//        ArrayList<String> errors = new ArrayList();
//        ArrayList<String> success = new ArrayList();
//        
//        // cast fromdate and to date which come as string from GUI to localdate
//        // of Joda library
//        LocalDate from  = new LocalDate(fromdate);
//        LocalDate to = new LocalDate(todate);
//        
//        
//        holidays = DriverInfo.getHolidaysTaken(id);
//        
//        // the number of days driver want to take holiday
//        // + 1 needed as daysBetween calculate days between these dates
//        int diff = Days.daysBetween(from, to).getDays() + 1;
//        
//        
//        // new method! check if from date is at least 10 days later after today
//        LocalDate todaysDate = new LocalDate();
//        int atleastTen = Days.daysBetween(todaysDate, from).getDays();
//
//        if(holidays >= 25)
//        {
//            errors.add("You have already chosen 25 or more days for holidays this year");
//        } 
//        else if (atleastTen <= 10)
//        {
//            errors.add("You have to choose days for holiday at least for ten day later");
//        }
//        else if(diff <= 0)
//        {
//            errors.add("You have choosen dates in a wrong way!");
//        }    
//        else
//        {    // use casted date for iterating between "from" date and "to" date
//            for (LocalDate date = from; date.isBefore(to); date = date.plusDays(1))
//            {
//               // check if current date is chosen more than 10 drivers
//               DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
//               String dateforcheck = formatter.print(date);
//               if(checkHolidayAvailability(dateforcheck) == false)
//               {
//                   errors.add(date + " has already been chosen the limited number of times by drivers ");
//               }
//               
//               // cast localdate format to date format for using in isAvailable method
//               Date datedf = date.toDate();
//
//               // check if driver already chosen current date for holiday means s/he choose 
//               // that date again by mistake
//               if(DriverInfo.isAvailable(id, datedf) == false)
//               {
//                   errors.add("You have already chosen " + date + " date for holiday");
//               }
//            }
//            if((holidays + diff) > 25)
//            {
//                errors.add("The number of days you chose and previous taken holidays are more than 25");
//            }       
//        } // else if 
//        
//        
//        if(!errors.isEmpty())
//        {   
//            return errors;
//        }
//        else
//        {
//            for (LocalDate date = from; date.isBefore(to); date = date.plusDays(1))
//            {
//                Date datedf = date.toDate();
//                database.busDatabase.new_record("driver_availability", new Object[][]{{"available", 0}, {"day", datedf}, {"driver", id}});      
//            }     
//            success.add("Enjoy your holiday! You have chosen " + (holidays + diff) + " days as holiday for this year");
//            DriverInfo.setHolidaysTaken(id, (holidays + diff));
//            
//            //DriverInfo.setAvailable(diff, true);
//            return success;
//        }
//    }
//
//    public static boolean checkHolidayAvailability(String date) 
//    {
//        if (date == null) throw new InvalidQueryException("Date is null");
//        int holidayDrivers = 0;
//        boolean holidayAvailability = true;
//        int[] driverIDs = DriverInfo.getAvailabilityDrivers();
//        for (int i=0; i<driverIDs.length; i++) {
//          database db = database.busDatabase;
//            if (db.select_record("driver_availability", "driver_availability_id", driverIDs[i], "available", 0, "day" , date)) 
//            {
//              holidayDrivers++;
//            }     
//        }
//        if (holidayDrivers >= 5) {
//          holidayAvailability = false;
//        }
//        // System.out.println("\nNot available drivers: " + holidayDrivers);
//        return holidayAvailability;
//    }
}