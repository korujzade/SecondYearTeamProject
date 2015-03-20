package rostering;



import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import DRH.*;
import DRH.TimetableInfo.timetableKind;
import rostering.*;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT; 
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent; 
import org.eclipse.swt.events.SelectionListener; 
import org.eclipse.swt.widgets.Button; 
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display; 
import org.eclipse.swt.widgets.Event; 
import org.eclipse.swt.widgets.Label; 
import org.eclipse.swt.widgets.Listener; 
import org.eclipse.swt.widgets.Shell; 
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;
import org.joda.time.LocalDate;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;

public class RosterGUI {
  
  private static Driver[][][] rosterDrivers;
  private static Bus[][][] rosterBuses;
  private static String[] driverNames;
  private static int[] driverIDs, DriverIDs;
  private static int sz;
  public static Date datedf;
  

  public static void main(String[] args) { 
	  database.openBusDatabase();
      driverIDs = DriverInfo.getDrivers();
      driverNames = new String [driverIDs.length];
      
//      String date = "2015-02-05"; 
//	  LocalDate datejd = new LocalDate(date);
//	  datedf = datejd.toDate();
      
      DriverIDs = DriverInfo.getDrivers();
      sz = DriverIDs.length;
      
      //reset hours this week
      for(int i=0; i<sz; i++){
   	    DriverInfo.setHoursThisWeek(DriverIDs[i], 0);
      }
	  rosterDrivers = null;
	  rosterBuses = null;
	  
	  new RosterGUI(); 
  } 
	
  public RosterGUI() {	  
    final Display display = Display.getDefault(); 
	final Shell shell = new Shell(display, SWT.CLOSE);
	shell.setSize(507, 302); 
	shell.setText("Main Shell"); 

	final Button spawn = new Button(shell, SWT.PUSH);
	spawn.setText("Driver"); 
	spawn.setBounds(84, 221, 93, 30); 
	spawn.addSelectionListener(new SelectionListener() { 
	  @Override 
	  public void widgetSelected(SelectionEvent e) {  
	    new DriverShell(display);
	  } 

	  @Override 
	  public void widgetDefaultSelected(SelectionEvent e) { 
	    widgetSelected(e); 
	  } 
	}); 

	shell.addListener(SWT.Close, new Listener() { 
	  @Override 
	  public void handleEvent(Event event) { 
	    display.dispose(); 
	  } 
	}); 
	     
	Label lblNewLabel_3 = new Label(shell, SWT.NONE);
	lblNewLabel_3.setBounds(130, 182, 234, 17);
	lblNewLabel_3.setText("What function do you want to use?");
	     
	final Button btnNewButton_1 = new Button(shell, SWT.NONE);
	btnNewButton_1.addSelectionListener(new SelectionAdapter() {
	  @Override
	  public void widgetSelected(SelectionEvent e) {
		new ControllerDriverHoursView(display);
	  }
	});
	btnNewButton_1.setBounds(219, 220, 77, 31);
	btnNewButton_1.setText("Controller");
	     
	Button btnNewButton_2 = new Button(shell, SWT.NONE);
	btnNewButton_2.setEnabled(false);
	btnNewButton_2.setBounds(330, 220, 77, 31);
	btnNewButton_2.setText("Customer");
	
	Text text = new Text(shell, SWT.BORDER);
	text.setBounds(276, 121, 131, 27);
	
	Label lblNewLabel = new Label(shell, SWT.NONE);
	lblNewLabel.setBounds(178, 10, 152, 17);
	lblNewLabel.setText("Select date for Roster :");
	
	final Text text_1 = new Text(shell, SWT.BORDER);
	text_1.setBounds(113, 33, 277, 27);
	
	final Button btnNewButton = new Button(shell, SWT.NONE);
	btnNewButton.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) 
		{
			  //reset hours this week
			
     	       int[] DriverIDs = DriverInfo.getDrivers();
  	           int sz = DriverIDs.length;
		       for(int i=0; i<sz; i++){
		    	   DriverInfo.setHoursThisWeek(DriverIDs[i], 0);
		       }
			
			// UNCOMMENT
			String DATE_FORMAT_NOW = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			try {
				datedf = sdf.parse(text_1.getText());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			rosterDrivers = Rostering.assignDrivers(datedf);
			rosterBuses = Rostering.assignBuses();
			
//		    // COMMENT
//			String date = "2015-02-05"; 
//		    LocalDate datejd = new LocalDate(date);
//   		    datedf = datejd.toDate();
//			rosterDrivers = Rostering.assignDrivers(datedf);
//			rosterBuses = Rostering.assignBuses();
		}
	});
	btnNewButton.setBounds(113, 121, 129, 31);
	btnNewButton.setText("Generate Roster");
	
	spawn.setEnabled(false);
	btnNewButton_1.setEnabled(false);
	btnNewButton.setEnabled(false);
	
	Button btnNewButton_3 = new Button(shell, SWT.NONE);
	btnNewButton_3.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			// UNCOMMENT
			String datestr = text_1.getText();
			String DATE_FORMAT_NOW = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			try {
				Date date2 = sdf.parse(datestr);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date2);
				boolean monday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
				if (monday) {
					spawn.setEnabled(true);
					btnNewButton_1.setEnabled(true);
					btnNewButton.setEnabled(true);
				}
				else {
					text_1.setText("Day needs to be Monday");
				}
			
			}
			catch(ParseException ex) {
				MessageDialog.openError(shell, "ERROR",
						"Date format : yyyy-mm-dd !");
			}
			
		    // COMMENT
//			spawn.setEnabled(true);
//			btnNewButton_1.setEnabled(true);
//			btnNewButton.setEnabled(true);
			
			
		}
	});
	btnNewButton_3.setBounds(208, 65, 88, 29);
	btnNewButton_3.setText("Done");
	
	shell.open(); 
	     
	while (!display.isDisposed()) { 
	  try { 
		if ((rosterDrivers != null) /*&& (rosterBuses != null)*/) {
	      text.setText("Rostering Done !");
	      spawn.setEnabled(true);
		  btnNewButton_1.setEnabled(true);
		}
		else {
			spawn.setEnabled(false);
			btnNewButton_1.setEnabled(false);
		}
	    if (!display.readAndDispatch()) { 
	      display.sleep(); 
	    } 
	  } 
	  catch (Exception e) { 
	    e.printStackTrace(); 
	  } 
	} 
  } 
  
  Driver mynigga;
  Services myservice;
  Bus mybus;
  
  private class DriverShell { 
    protected Shell shell;
    private Table table;
	     
	public DriverShell(final Display display) { 
	  final Display driverInfoShellDisplay = display;
	  shell = new Shell(display, SWT.CLOSE);
	  shell.setText("Driver");
			
		shell.setSize(700, 800);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 61, 678, 661);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(202);
		tblclmnNewColumn_2.setText("Service");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(174);
		tblclmnNewColumn_3.setText("Bus");
		
		TableColumn tblclmnFrom = new TableColumn(table, SWT.NONE);
		tblclmnFrom.setWidth(150);
		tblclmnFrom.setText("From");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(94);
		tblclmnNewColumn.setText("To");
		
		// Kamil's method
//		int rows = 1; 
//		for (int i = 0; i < rows; i++) {
//			TableItem item = new TableItem(table, SWT.NONE);
//		    item.setText(0, "" );
//		    
//		    item.setText(1, "");
//		    item.setText(2, "");
//		}
		
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnNewButton.setBounds(307, 732, 77, 31);
		btnNewButton.setText("OK");
		
		final Text text = new Text(shell, SWT.BORDER);
		text.setBounds(103, 21, 100, 27);
		
//		final Combo combo = new Combo(shell, SWT.NONE);
//		combo.setBounds(85, 21, 129, 29);
//		
//		for (int i = 0; i < DriverInfo.getDrivers().length; i++) 
//		{
//			int temp = DriverInfo.getDrivers()[i];
//			StringBuilder sb = new StringBuilder();
//			sb.append("");
//			sb.append(temp);
//			String number = sb.toString();
//			combo.add(number);
//		}
		
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(25, 21, 72, 17);
		lblNewLabel.setText("Driver ID :");
		
		Label lblDay = new Label(shell, SWT.NONE);
		lblDay.setText("Day :");
		lblDay.setBounds(255, 21, 34, 17);
		
		final Combo combo_1 = new Combo(shell, SWT.NONE);
		combo_1.setBounds(295, 21, 129, 29);
		combo_1.add("Monday");
		combo_1.add("Tuesday");
		combo_1.add("Wednesday");
		combo_1.add("Thursday");
		combo_1.add("Friday");
		combo_1.add("Saturday");
		combo_1.add("Sunday");

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String selectionStr = combo_1.getText();
				int selectionint = 0;
				switch(selectionStr) {
					case "Monday" :
						selectionint = 0;
						break;
					case "Tuesday" :
						selectionint = 1;
						break;
					case "Wednesday" :
						selectionint = 2;
						break;
					case "Thursday" :
						selectionint = 3;
						break;
					case "Friday" :
						selectionint = 4;
						break;
					case "Saturday" :
						selectionint = 5;
						break;
					case "Sunday" :
						selectionint = 6;
						break;
				}
				String did = text.getText();
				mynigga = Rostering.assignedDriverID(did, selectionint);

				
				table.setRedraw(true);
				
				// Kamil's method
				int rows = mynigga.getServiceNumbersAssigned().size(); 
				
				for (int i = 0; i < rows; i++)
				{
					
					mybus = Rostering.returnBus();
					myservice = new Services(mynigga.getRoutesAssigned().get(i), timetableKind.weekday, i );
					TableItem item = new TableItem(table, SWT.NONE);
				    item.setText(0, mynigga.getServiceIDsAssigned().get(i).toString());
				    item.setText(1, mybus.getBusID().toString());
				    item.setText(2, myservice.getFrom().toString());
				    item.setText(3, myservice.getTo().toString());
				}
				
			}
		});
		btnNewButton_1.setBounds(464, 21, 88, 29);
		btnNewButton_1.setText("Show");
		
		shell.open();
		shell.layout();
		
	  while (!shell.isDisposed()) {
	    if (!display.readAndDispatch()) {
          display.sleep();
		}
	  }
	} 
  }
 
  private class ControllerDriverHoursView { 
	    protected Shell shell;
		private Table table;
		     
		public ControllerDriverHoursView(final Display display) { 
		  final Display driverInfoShellDisplay = display;
		  shell = new Shell(display, SWT.CLOSE);
			shell.setSize(700, 852);
		  shell.setText("Driver Hours View");
          		  
		  ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			scrolledComposite.setBounds(10, 61, 678, 661);
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setExpandVertical(true);
			
			table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_1.setWidth(222);
			tblclmnNewColumn_1.setText("Drivers");
			
			TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_3.setWidth(200);
			tblclmnNewColumn_3.setText("Minutes Worked; Limits: 3000");
			

			scrolledComposite.setContent(table);
			
			// Kamil's method
//			int rows = 1;
//			for (int i = 0; i < rows; i++) {
//				TableItem item = new TableItem(table, SWT.NONE);
//			    item.setText(0, "");
//			    item.setText(1, "");
//			    item.setText(2, "");
//			}
			
			Label lblDriver = new Label(shell, SWT.NONE);
			lblDriver.setText("Driver ID :");
			lblDriver.setBounds(21, 21, 58, 17);
			
			// Kamil's method
			final Combo combo = new Combo(shell, SWT.NONE);
			combo.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					
				}
			});
			combo.setBounds(85, 21, 129, 29);
			
			Button button = new Button(shell, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.dispose();
				}
			});
			button.setText("OK");
			button.setBounds(295, 781, 77, 31);

			// Kamil's method
			for (int i = 0; i < DriverInfo.getDrivers().length; i++)  {
				int temp = DriverInfo.getDrivers()[i];
				StringBuilder sb = new StringBuilder();
				sb.append("");
				sb.append(temp);
				String number = sb.toString();
				combo.add(number);
			}
			
			Label lblView = new Label(shell, SWT.NONE);
			lblView.setText("View :");
			lblView.setBounds(255, 21, 34, 17);
			
			final Combo combo_1 = new Combo(shell, SWT.NONE);
			combo_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					switch(combo_1.getText()) {
					case "Bus Hours View":
						  shell.dispose();
						  new ControllerBusHoursView(display);
						break;
					case "Services View":
						  shell.dispose();
					      new ControllerServicesView(display);
					    break;
					}
				}
			});
			combo_1.setBounds(295, 21, 129, 29);
			combo_1.add("Bus Hours View");
			combo_1.add("Services View");
//			combo_1.add("Bus Services View");
		    combo_1.add("Days View");
		    		
//		    Button btnNewButton_1 = new Button(shell, SWT.NONE);
//			btnNewButton_1.addSelectionListener(new SelectionAdapter() {
//				@Override
//				public void widgetSelected(SelectionEvent e) {
//					switch (combo_1.getText()) {
//					case "Bus Hours View" :
//						
//						break;
//					case "Driver Services View" :
//						
//						break;
//					case "Bus Services View" :
//	
//						break;
//					case "Days View" :
//	
//						break;
//					default:
//						break;
//					}
//				}
//			});
//			btnNewButton_1.setBounds(464, 21, 88, 29);
//			btnNewButton_1.setText("Show");
		    
		    Label lblNewLabel1 = new Label(shell, SWT.NONE);
			lblNewLabel1.setBounds(259, 741, 183, 17);
			lblNewLabel1.setText("Total hours the Bus worked :");
			
			final Text text_2 = new Text(shell, SWT.BORDER);
			text_2.setBounds(454, 741, 75, 27);
		    		
			
			Driver newDriver;
			Driver driverRoster[][][] = Rostering.rosterDriver;
			

			int[] drivers = DriverInfo.getDrivers();
			Integer[] newArray = new Integer[drivers.length];
			int i = 0;
			for (int value : drivers) {
			    newArray[i++] = Integer.valueOf(value);
			}
			
			for (int j = 0; j < drivers.length; j++)
			{
				Integer a = DriverInfo.getHoursThisWeek(drivers[j]);
				
				TableItem item = new TableItem(table, SWT.NONE);
			    item.setText(0, newArray[j].toString());
			    item.setText(1, a.toString());
			}
			
		   shell.open();
	      shell.layout();
		  
		  while (!shell.isDisposed()) {
		    if (!display.readAndDispatch()) {
	          display.sleep();
			}
		  }
		} 
	  }
  
/////////////////////////////////////////////////////////////////////////
/////////////////////////////            //////////////////////////////////
/////////////////////////////             /////////////////////////////////
/////////////////////////////            /////////////////////////////////
/////////////////////////////            //////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
  
  
    private class ControllerBusHoursView { 
	    protected Shell shell;
		private Table table;
		     
		public ControllerBusHoursView(final Display display) { 
		  final Display driverInfoShellDisplay = display;
		  shell = new Shell();
			shell.setSize(700, 852);
			shell.setText("Bus Hours View");
			
			ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			scrolledComposite.setBounds(10, 61, 678, 661);
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setExpandVertical(true);
			
			table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_1.setWidth(233);
			tblclmnNewColumn_1.setText("Bus");
			
			TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_2.setWidth(132);
			tblclmnNewColumn_2.setText("Hours Used");
			scrolledComposite.setContent(table);
			scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			
			// Kamil's method


			
			Integer[][] buses = Rostering.BusIDs;
			int[] bullshit = BusInfo.getBuses();
			
			for (int f = 0; f < bullshit.length; f++) 
			{
				
				TableItem item = new TableItem(table, SWT.NONE);
			    item.setText(0, buses[f][0].toString());
			    item.setText(1, buses[f][1].toString());
			}
			
			Label lblBusId = new Label(shell, SWT.NONE);
			lblBusId.setText("Bus ID :");
			lblBusId.setBounds(21, 21, 58, 17);
			
			Button button = new Button(shell, SWT.NONE);
			button.setText("OK");
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.dispose();
				}
			});
			button.setBounds(295, 781, 77, 31);
			
			Label label = new Label(shell, SWT.NONE);
			label.setText("View :");
			label.setBounds(255, 21, 40, 17);
			
			final Combo combo_1 = new Combo(shell, SWT.NONE);
			combo_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					switch(combo_1.getText()) {
					case "Driver Hours View":
						  shell.dispose();
						  new ControllerDriverHoursView(display);
						break;
					case "Services View":
						  new ControllerServicesView(display);
						break;
					}
				}
			});
			combo_1.setBounds(302, 21, 129, 29);
			combo_1.add("Driver Hours View");
			combo_1.add("Services View");
//			combo_1.add("Bus Services View");
//		    combo_1.add("Days View");
			
//		    Button btnNewButton_1 = new Button(shell, SWT.NONE);
//			btnNewButton_1.addSelectionListener(new SelectionAdapter() {
//				@Override
//				public void widgetSelected(SelectionEvent e) {
//				}
//			});
//			btnNewButton_1.setBounds(464, 21, 88, 29);
//			btnNewButton_1.setText("Show");
		    
		    Label lblNewLabel = new Label(shell, SWT.NONE);
			lblNewLabel.setBounds(259, 741, 183, 17);
			lblNewLabel.setText("Total hours the Bus worked :");
			
			final Text text = new Text(shell, SWT.BORDER);
			text.setBounds(454, 741, 75, 27);
			
			
		  shell.open();
	      shell.layout();
		  
		  while (!shell.isDisposed()) {
		    if (!display.readAndDispatch()) {
	          display.sleep();
			}
		  }
		} 
	  }
    
    
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////
    
    
    private class ControllerServicesView { 
	    protected Shell shell;
		private Table table;
		     
		public ControllerServicesView(final Display display) { 
		  final Display driverInfoShellDisplay = display;
		  shell = new Shell();
			shell.setSize(700, 794);
			shell.setText("Services View");
			
			ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			scrolledComposite.setBounds(10, 61, 678, 661);
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setExpandVertical(true);
			
			final Table table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_1.setWidth(172);
			tblclmnNewColumn_1.setText("Service");
			
			TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_3.setWidth(165);
			tblclmnNewColumn_3.setText("Driver");
			
			TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn.setWidth(153);
			tblclmnNewColumn.setText("Bus");
			scrolledComposite.setContent(table);
			scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			
			Label lblDriver = new Label(shell, SWT.NONE);
			lblDriver.setText("Day :");
			lblDriver.setBounds(21, 18, 26, 20);
			
			Label lblNewLabel = new Label(shell, SWT.NONE);
			lblNewLabel.setBounds(202, 18, 40, 20);
			lblNewLabel.setText("Route :");
			
			Label lblView = new Label(shell, SWT.NONE);
			lblView.setText("View :");
			lblView.setBounds(396, 18, 40, 17);
			
			// Day
			final Combo combo = new Combo(shell, SWT.NONE);
			combo.setBounds(53, 15, 129, 29);
			combo.add("Monday");
			combo.add("Tuesday");
			combo.add("Wednesday");
			combo.add("Thursday");
			combo.add("Friday");
			combo.add("Saturday");
			combo.add("Sunday");
			
			// Route
			final Combo combo_2 = new Combo(shell, SWT.NONE);
			combo_2.setBounds(248, 15, 109, 23);
			combo_2.add("358back");
			combo_2.add("358out");
			combo_2.add("383");
			combo_2.add("384");
			
			// View
			final Combo combo_1 = new Combo(shell, SWT.NONE);
			combo_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					switch(combo_1.getText()) {
					case "Driver Hours View":
						  shell.dispose();
						  new ControllerDriverHoursView(display);
						break;
					case "Bus Hours View":
						  new ControllerBusHoursView(display);
						break;
					}
				}
			});
			combo_1.setBounds(439, 15, 129, 29);
			combo_1.add("Driver Hours View");
			combo_1.add("Bus Hours View");
			
			Button btnNewButton = new Button(shell, SWT.NONE);
			btnNewButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String selectionStr = combo.getText();
					int selectionint = 0;
					switch(selectionStr) {
						case "Monday" :
							selectionint = 0;
							break;
						case "Tuesday" :
							selectionint = 1;
							break;
						case "Wednesday" :
							selectionint = 2;
							break;
						case "Thursday" :
							selectionint = 3;
							break;
						case "Friday" :
							selectionint = 4;
							break;
						case "Saturday" :
							selectionint = 5;
							break;
						case "Sunday" :
							selectionint = 6;
							break;
					}
					String route = combo_2.getText();
					int routeselect = 0;
					switch(route) {
					case "383" :
						routeselect = 3;
						break;
					case "384" :
						routeselect= 2;
						break;
					case "358out" :
						routeselect = 1;
						break;
					case "358back" :
						routeselect = 0;
						break;
				    }
					
					int a =
					0;
					if (routeselect == 3)
						a = 65;
					if (routeselect == 2)
						a = 66;
					if (routeselect == 1)
						a = 67;
					if (routeselect == 0)
						a = 68;
					
					
					// Kamil's method 

					timetableKind kind = null;
					
					if (selectionint <= 4)
					{
						kind = timetableKind.weekday;
					}
					else if (selectionint == 5)
						kind = timetableKind.saturday;
					else if (selectionint == 6)
						kind = timetableKind.sunday;
						
					Routes route1 = new Routes(a, kind);
			
					Bus[][][] rosterBus = rosterBuses;	
					Bus[] buses = rosterBus[selectionint][routeselect];
					
			       String date = "2015-01-05"; 
			       LocalDate datejd = new LocalDate(date);
				   Date datedt = datejd.toDate();
					
					Driver[][][] rosterDriver = rosterDrivers;		
					Driver[] drivers= rosterDriver[selectionint][routeselect];
					
					int[] services = TimetableInfo.getServices(a, kind);
					
					Integer[] newArray = new Integer[services.length];
					int g = 0;
					for (int value : services) 
					{
					    newArray[g++] = Integer.valueOf(value);
					}
					
					
					for (int i = 0; i < route1.getTheNumberOfServices(); i++)
					{	
						TableItem item = new TableItem(table, SWT.NONE);
					    item.setText(0, newArray[i].toString());
					    item.setText(1, buses[i].getBusID().toString()); 
					    item.setText(2, drivers[i].getDriverID().toString());
					}
					
				}
			});
			btnNewButton.setBounds(599, 13, 75, 25);
			btnNewButton.setText("Show");
			
			Button button = new Button(shell, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			button.setText("OK");
			button.setBounds(295, 728, 77, 31);
			
		  shell.open();
	      shell.layout();
		  
		  while (!shell.isDisposed()) {
		    if (!display.readAndDispatch()) {
	          display.sleep();
			}
		  }
		} 
	  }
} 
