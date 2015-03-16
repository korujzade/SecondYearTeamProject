package DRH;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import rostering.Bus;
import rostering.Rostering;

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
    public static void main(String[] args){
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
        
       Rostering.assignBuses();
        

//
//        
//        int a = TimetableInfo.getNumberOfServices(66, timetableKind.weekday);
//        System.out.println("services :" + a);
//        
//        int b[] = TimetableInfo.getServiceTimes(65,timetableKind.weekday ,20);
//        for (int i = 0; i < b.length; i++)
//        	System.out.println("times: " + b[i]);
//        
    }
}