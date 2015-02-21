import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class DRHI{

	protected static Shell shell;
	private Text ID_textField;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DRHI window = new DRHI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	
//    public static ArrayList	<String> checkDriverHoliday(int id, String fromdate, String todate)
//    {
//        
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
//        int fromcheck  = from.getYear();
//        int tocheck = to.getYear();
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
//        if (tocheck != 2015 || fromcheck != 2015)
//        {
//        	errors.add("You can only take holiday for this year.");
//        }
//        else if(holidays >= 25)
//        {
//            errors.add("You have already chosen 25 days for holidays this year");
//        } 
//        else if (atleastTen <= 10)
//        {
//            errors.add("There must be 10 days between today and the day you want to take holiday.");
//        }
//        else if(diff <= 0)
//        {
//            errors.add("You have chosen dates in a wrong way!");
//        }    
//        else
//        {  
//        	LocalDate tofor = to.plusDays(1);
//        	// use casted date for iterating between "from" date and "to" date
//            for (LocalDate date = from; date.isBefore(tofor); date = date.plusDays(1))
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
//        	return errors;
////        	for(int i = 0; i < errors.size(); i++)
////			{
////				MessageDialog.openError(shell, "ERROR", errors.get(i));
////			}
//        }
//        else
//        {	
//        	LocalDate tofor = to.plusDays(1);
//            for (LocalDate date = from; date.isBefore(tofor); date = date.plusDays(1))
//            {
//                Date datedf = date.toDate();
//                database.busDatabase.new_record("driver_availability", new Object[][]{{"available", 0}, {"day", datedf}, {"driver", id}});      
//            }  
//            success.add("Enjoy your holiday! You have chosen " + (holidays + diff) + " days as holiday for this year");
//            DriverInfo.setHolidaysTaken(id, (holidays + diff));
//            return success;
//           // MessageDialog.openConfirm(shell, "Confirmation", success.get(0));
//            
//            
//            //DriverInfo.setAvailable(diff, true);
//           
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
////          if (db.select_record("driver_availability", "driver", driverIDs[i], "day", date)) {
////            if (db.select_record("driver_availability", "driver", driverIDs[i], "available", 0)) {
////              holidayDrivers++;
////           }
////          }
//            if (db.select_record("driver_availability", "driver_availability_id", driverIDs[i], "available", 0, "day" , date)) 
//            {
//              holidayDrivers++;
//            }
////          if (db.select_record("driver_availability", "available", 0,"day", date))
////          {
////              holidayDrivers++;
////          }
////          
//        }
//        if (holidayDrivers >= 10) {
//          holidayAvailability = false;
//        }
//        // System.out.println("\nNot available drivers: " + holidayDrivers);
//        return holidayAvailability;
//    }
//	
	
	

	protected void createContents() 
	{
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("DRH Interface");
		
		Label ID_label = new Label(shell, SWT.NONE);
		ID_label.setBounds(10, 10, 68, 17);
		ID_label.setText("Driver ID");
		
		Label start_label = new Label(shell, SWT.NONE);
		start_label.setBounds(10, 51, 68, 17);
		start_label.setText("Start Date");
		
		Label end_label = new Label(shell, SWT.NONE);
		end_label.setBounds(10, 90, 68, 17);
		end_label.setText("End Date");
		
		final DateTime start_date = new DateTime(shell, SWT.BORDER);
		start_date.setBounds(100, 40, 116, 29);
		
		final DateTime end_date = new DateTime(shell, SWT.BORDER);
		end_date.setBounds(100, 78, 116, 29);
		
		ID_textField = new Text(shell, SWT.BORDER);
		ID_textField.setBounds(100, 0, 116, 27);
		
		Button submit_btn = new Button(shell, SWT.NONE);
		submit_btn.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				try
				{
					int ID = Integer.parseInt(ID_textField.getText());

					int day = start_date.getDay();
					int month = start_date.getMonth() + 1;
					int year = start_date.getYear();
					StringBuilder sb = new StringBuilder();
					sb.append(year);				
					sb.append("-");
					sb.append(month);
					sb.append("-");
					sb.append(day);
					String start = sb.toString();	
					day = end_date.getDay();
					month = end_date.getMonth() + 1;
					year = end_date.getYear();
					sb.setLength(0);
					sb.append(year);				
					sb.append("-");
					sb.append(month);
					sb.append("-");
					sb.append(day);
					String end = sb.toString();

					//checkDriverHoliday(ID, start, end);
					ArrayList<String> messages = new ArrayList<String>();
					messages = DriverInfo.checkDriverHoliday(ID, start, end);
					
					if (DriverInfo.errorsuc == 0)
						for(int i = 0; i < messages.size(); i++)
						{
							MessageDialog.openInformation(shell, "Error ", messages.get(i));
						}
					else
					{						
						for(int i = 0; i < messages.size(); i++)
						{
							MessageDialog.openInformation(shell, "Success ", messages.get(i));
						}			
					}
					
					
				}
				catch (Exception e2)
				{
					MessageDialog.openError(shell, "ERROR", "Something went wrong !" + e2.getMessage());
					
				}
			}
		});
		submit_btn.setBounds(60, 133, 88, 29);
		submit_btn.setText("Submit");

	}
}
