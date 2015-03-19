package DRH;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import rostering.Bus;
import rostering.Rostering;
import rostering.Routes;

import DRH.TimetableInfo.timetableKind;

/*
 * A very simple application illustrating how to use the interface.
 * Prints the names of all the drivers in the database.
 * @author John Sargeant
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        database.openBusDatabase();
        int[] driverIDs = DriverInfo.getDrivers();
        String[] driverNames = new String [driverIDs.length];

        
        // debugging
//
//        int b[] = TimetableInfo.getServices(66, timetableKind.weekday);
//        for (int i = 0; i < b.length; i++)
//		{
//        	int a[] = TimetableInfo.getServiceTimes(66,timetableKind.weekday, i);
//        	for (int j = 0; j < a.length; j++)
//			{
//				System.out.print(" "+ a[j]+" ");
//			}
//        	System.out.println();
//		}
//        
        
        //int a = TimetableInfo.getNumberOfServices(68, timetableKind.weekday);
//        /System.out.println("68: " + a);
//        
//       int[][] testArray = new int[2][2];
//       
//       testArray[0][0] = 1;
//       testArray[0][1] = 1;
//       testArray[1][0] = 2;
//       
//       int index = 0;
//       for (int i = 0; i < testArray.length; i++)
//	   {
//	     if (testArray[i][0] == 2)
//	     {
//	    	 index = i;
//	    	 testArray[0][1]++;
//	    	 break;
//	     }
//	   }
//       System.out.println("array length: " + testArray.length);
//       System.out.println("firt used number: " + testArray[0][1]);
//       System.out.println("index: " + index);
//       
       
       String date = "2015-02-05"; 
       LocalDate datejd = new LocalDate(date);
       
       Date datedf = datejd.toDate();
       int[] DriverIDs = DriverInfo.getDrivers();
       int sz = DriverIDs.length;
       
       //reset hours this week
       for(int i=0; i<sz; i++){
    	   DriverInfo.setHoursThisWeek(DriverIDs[i], 0);
       }

       int[] routeIDs = BusStopInfo.getRoutes();

       int[] services = TimetableInfo.getServices(68, timetableKind.weekday);
       
       System.out.println("service first: " + services[1] + " route first " + routeIDs[0]);
       

		Routes route1 = new Routes(routeIDs[0]);
       route1.setTheNumberOfServices(timetableKind.weekday);
       int from = TimetableInfo.getServiceTimes(68, timetableKind.weekday, 1)[0];
       
       
       
       System.out.println("from: " + from);
     // Rostering.assignDrivers(datedf);
       
    
    }
     public static void testDate(Date date )
     {
    	 LocalDate datelc = new LocalDate (date);
    	 
    	 for (int i = 0; i < 10; i++)
    	 { 
    		 Date datedt = datelc.toDate();
    		 System.out.println("date "  + datedt);
    		 datelc = datelc.plusDays(1);
    		 
    	 }
    	 
    	 
     }
    
    
}