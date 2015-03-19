package rostering;




import java.util.Date;

import DRH.*;
import rostering.*;

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

public class RosterGUI {
  
  private static Bus[][][] rosterBuses;
  private static Driver[][][] rosterDrivers;
  private static String[] driverNames;
  private static int[] driverIDs, DriverIDs;
  private static int sz;
  public static Date datedf;
  
  
  public static void main(String[] args) { 
      database.openBusDatabase();
      driverIDs = DriverInfo.getDrivers();
      driverNames = new String [driverIDs.length];
      
      String date = "2015-02-05"; 
	  LocalDate datejd = new LocalDate(date);
	  datedf = datejd.toDate();
      
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
	shell.setSize(507, 272); 
	shell.setText("Main Shell"); 

	Button spawn = new Button(shell, SWT.PUSH);
	spawn.setText("Driver"); 
	spawn.setBounds(85, 163, 93, 30); 
	spawn.addSelectionListener(new SelectionListener() { 
		
	  public void widgetSelected(SelectionEvent e) {  
	    new DriverShell(display);
	  } 

	  
	  public void widgetDefaultSelected(SelectionEvent e) { 
	    widgetSelected(e); 
	  } 
	}); 

	shell.addListener(SWT.Close, new Listener() { 
		
	  public void handleEvent(Event event) { 
	    display.dispose(); 
	  } 
	}); 
	     
	Label lblNewLabel_3 = new Label(shell, SWT.NONE);
	lblNewLabel_3.setBounds(154, 117, 197, 17);
	lblNewLabel_3.setText("What function do you want to use?");
	     
	Button btnNewButton_1 = new Button(shell, SWT.NONE);
	btnNewButton_1.addSelectionListener(new SelectionAdapter() {
	  @Override
	  public void widgetSelected(SelectionEvent e) {
		new ControllerDriverHoursView(display);
	  }
	});
	btnNewButton_1.setBounds(219, 162, 77, 31);
	btnNewButton_1.setText("Controller");
	     
	Button btnNewButton_2 = new Button(shell, SWT.NONE);
	btnNewButton_2.setEnabled(false);
	btnNewButton_2.setBounds(328, 162, 77, 31);
	btnNewButton_2.setText("Customer");
	
	Button btnNewButton = new Button(shell, SWT.NONE);
	btnNewButton.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			//rosterBuses = Rostering.assignBuses();
			rosterDrivers = Rostering.assignDrivers(datedf);
		}
	});
	btnNewButton.setBounds(141, 55, 104, 31);
	btnNewButton.setText("Generate Roster");
	
	Text text = new Text(shell, SWT.BORDER);
	text.setBounds(291, 55, 75, 27);
	
	shell.open(); 
	     
	while (!display.isDisposed()) { 
	  try { 
		if ((rosterDrivers != null) || (rosterBuses != null)) {
	      text.setText("Done !");
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
		tblclmnNewColumn_2.setWidth(236);
		tblclmnNewColumn_2.setText("Service");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(230);
		tblclmnNewColumn_3.setText("Bus");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(94);
		tblclmnNewColumn.setText("Times");
		
		// Kamil's method
		int rows = 30;
		for (int i = 0; i < rows; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
		    item.setText(0, new Integer(i + 1).toString());
		    
		    item.setText(1, "2");
		    item.setText(2, "3");
		}
		
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
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(85, 21, 129, 29);
		
		//combo.add(rosterDrivers[0][0][0]);
		combo.add("2015");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(21, 21, 58, 17);
		lblNewLabel.setText("Driver ID :");
		
		Label lblDay = new Label(shell, SWT.NONE);
		lblDay.setText("Day :");
		lblDay.setBounds(255, 21, 34, 17);
		
		Combo combo_1 = new Combo(shell, SWT.NONE);
		combo_1.setBounds(295, 21, 129, 29);
		combo_1.add("Monday");
		combo_1.add("Tuesday");
		combo_1.add("Wednesday");
		combo_1.add("Thursday");
		combo_1.add("Friday");
		combo_1.add("Saturday");
		combo_1.add("Sunday");
		
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
			shell.setSize(700, 800);
		  shell.setText("Driver Hours View");
          		  
		  ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			scrolledComposite.setBounds(10, 61, 678, 661);
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setExpandVertical(true);
			
			table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_1.setWidth(164);
			tblclmnNewColumn_1.setText("Day");
			
			TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn.setWidth(162);
			tblclmnNewColumn.setText("Times");
			
			TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_3.setWidth(175);
			tblclmnNewColumn_3.setText("Hours Worked");
			
			TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_2.setWidth(88);
			tblclmnNewColumn_2.setText("No. of Breaks");
			scrolledComposite.setContent(table);
			scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			
			// Kamil's method
			int rows = 30;
			for (int i = 0; i < rows; i++) {
				TableItem item = new TableItem(table, SWT.NONE);
			    item.setText(0, "1");
			    item.setText(1, "2");
			    item.setText(2, "3");
			}
			
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
			button.setBounds(307, 732, 77, 31);

			// Kamil's method
			combo.add("2014");
			combo.add("2015");
			
			Label lblView = new Label(shell, SWT.NONE);
			lblView.setText("View :");
			lblView.setBounds(255, 21, 34, 17);
			
			final Combo combo_1 = new Combo(shell, SWT.NONE);
			combo_1.addSelectionListener(new SelectionAdapter() {
				
				public void widgetSelected(SelectionEvent e) {
					switch(combo_1.getText()) {
					case "Bus Hours View":
						  shell.dispose();
						  new ControllerBusHoursView(display);
						break;
					case "Driver Services View":
						  new ControllerDriverServicesView(display);
						break;
					case "Bus Services View":
					      new ControllerBusServicesView(display);
					    break;
					case "Days View":
						  new ControllerDaysView(display);
						break;
					}
				}
			});
			combo_1.setBounds(295, 21, 129, 29);
			combo_1.add("Bus Hours View");
			combo_1.add("Driver Services View");
			combo_1.add("Bus Services View");
		    combo_1.add("Days View");
		    		
		    		
		   shell.open();
	      shell.layout();
		  
		  while (!shell.isDisposed()) {
		    if (!display.readAndDispatch()) {
	          display.sleep();
			}
		  }
		} 
	  }
  
    private class ControllerBusHoursView { 
	    protected Shell shell;
		private Table table;
		     
		public ControllerBusHoursView(final Display display) { 
		  final Display driverInfoShellDisplay = display;
		  shell = new Shell();
			shell.setSize(700, 800);
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
			tblclmnNewColumn_1.setText("Days");
			
			TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_3.setWidth(204);
			tblclmnNewColumn_3.setText("Times");
			
			TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_2.setWidth(132);
			tblclmnNewColumn_2.setText("Hours Used");
			scrolledComposite.setContent(table);
			scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			
			// Kamil's method
			int rows = 30;
			for (int i = 0; i < rows; i++) {
				TableItem item = new TableItem(table, SWT.NONE);
			    item.setText(0, "1");
			    item.setText(1, "2");
			    item.setText(2, "3");
			}
			
			Label lblBusId = new Label(shell, SWT.NONE);
			lblBusId.setText("Bus ID :");
			lblBusId.setBounds(21, 21, 58, 17);
			
			// Kamil's method
			final Combo combo = new Combo(shell, SWT.NONE);
			combo.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					
				}
			});
			combo.setBounds(85, 21, 129, 29);
			
			Button button = new Button(shell, SWT.NONE);
			button.setText("OK");
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.dispose();
				}
			});
			button.setBounds(307, 732, 77, 31);

			// Kamil's method
			combo.add("2014");
			combo.add("2015");
			
			Label label = new Label(shell, SWT.NONE);
			label.setText("View :");
			label.setBounds(255, 21, 34, 17);
			
			final Combo combo_1 = new Combo(shell, SWT.NONE);
			combo_1.addSelectionListener(new SelectionAdapter() {
				
				public void widgetSelected(SelectionEvent e) {
					switch(combo_1.getText()) {
					case "Driver Hours View":
						  shell.dispose();
						  new ControllerDriverHoursView(display);
						break;
					case "Driver Services View":
						  new ControllerDriverServicesView(display);
						break;
					case "Bus Services View":
					      new ControllerBusServicesView(display);
					    break;
					case "Days View":
						  new ControllerDaysView(display);
						break;
					}
				}
			});
			combo_1.setBounds(295, 21, 129, 29);
			combo_1.add("Driver Hours View");
			combo_1.add("Driver Services View");
			combo_1.add("Bus Services View");
		    combo_1.add("Days View");
			
		  shell.open();
	      shell.layout();
		  
		  while (!shell.isDisposed()) {
		    if (!display.readAndDispatch()) {
	          display.sleep();
			}
		  }
		} 
	  }
    
    private class ControllerDriverServicesView { 
	    protected Shell shell;
		private Table table;
		     
		public ControllerDriverServicesView(final Display display) { 
		  final Display driverInfoShellDisplay = display;
		  shell = new Shell();
			shell.setSize(457, 614);
			shell.setText("Controller Driver");
			
			ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			scrolledComposite.setBounds(10, 10, 431, 530);
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setExpandVertical(true);
			
			table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_1.setWidth(142);
			tblclmnNewColumn_1.setText("Driver");
			
			TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_2.setWidth(141);
			tblclmnNewColumn_2.setText("Service");
			
			TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn.setWidth(109);
			tblclmnNewColumn.setText("Times");
			scrolledComposite.setContent(table);
			scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			
			int rows = 30;
			for (int i = 0; i < rows; i++) {
				TableItem item = new TableItem(table, SWT.NONE);
			    item.setText(0, "1");
			    item.setText(1, "2");
			    item.setText(2, "3");
			}
			
			Label label = new Label(shell, SWT.NONE);
			label.setText("Switch to :");
			label.setBounds(10, 546, 58, 17);
			
			final Combo combo = new Combo(shell, SWT.NONE);
			combo.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (combo.getText().equals("Overall View")) {
						shell.dispose();
						//new ControllerOverallView(display);
				    } 
					else {
						shell.dispose();
						//new ControllerBusView(display);
					}
				}
			});
			combo.setBounds(95, 546, 103, 29);
			
			Button button = new Button(shell, SWT.NONE);
			button.setText("OK");
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.dispose();
				}
			});
			button.setBounds(256, 546, 77, 31);
			
			Button button_1 = new Button(shell, SWT.NONE);
			button_1.setText("Back");
			button_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.dispose();
					//new ControllerInfoShell(display);
				}
			});
			button_1.setBounds(364, 546, 77, 31);

			combo.add("Overall View");
			combo.add("Bus View");
			
		  shell.open();
	      shell.layout();
		  
		  while (!shell.isDisposed()) {
		    if (!display.readAndDispatch()) {
	          display.sleep();
			}
		  }
		} 
	  }
  
    private class ControllerBusServicesView { 
	    protected Shell shell;
		private Table table;
		     
		public ControllerBusServicesView(final Display display) { 
		  final Display driverInfoShellDisplay = display;
		  shell = new Shell();
			shell.setSize(457, 614);
			shell.setText("Controller Driver");
			
			ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			scrolledComposite.setBounds(10, 10, 431, 530);
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setExpandVertical(true);
			
			table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_1.setWidth(142);
			tblclmnNewColumn_1.setText("Driver");
			
			TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_2.setWidth(141);
			tblclmnNewColumn_2.setText("Service");
			
			TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn.setWidth(109);
			tblclmnNewColumn.setText("Times");
			scrolledComposite.setContent(table);
			scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			
			int rows = 30;
			for (int i = 0; i < rows; i++) {
				TableItem item = new TableItem(table, SWT.NONE);
			    item.setText(0, "1");
			    item.setText(1, "2");
			    item.setText(2, "3");
			}
			
			Label label = new Label(shell, SWT.NONE);
			label.setText("Switch to :");
			label.setBounds(10, 546, 58, 17);
			
			final Combo combo = new Combo(shell, SWT.NONE);
			combo.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (combo.getText().equals("Overall View")) {
						shell.dispose();
						//new ControllerOverallView(display);
				    } 
					else {
						shell.dispose();
						//new ControllerBusView(display);
					}
				}
			});
			combo.setBounds(95, 546, 103, 29);
			
			Button button = new Button(shell, SWT.NONE);
			button.setText("OK");
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.dispose();
				}
			});
			button.setBounds(256, 546, 77, 31);
			
			Button button_1 = new Button(shell, SWT.NONE);
			button_1.setText("Back");
			button_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.dispose();
					//new ControllerInfoShell(display);
				}
			});
			button_1.setBounds(364, 546, 77, 31);

			combo.add("Overall View");
			combo.add("Bus View");
			
		  shell.open();
	      shell.layout();
		  
		  while (!shell.isDisposed()) {
		    if (!display.readAndDispatch()) {
	          display.sleep();
			}
		  }
		} 
	  }
    
    private class ControllerDaysView { 
	    protected Shell shell;
		private Table table;
		     
		public ControllerDaysView(final Display display) { 
		  final Display driverInfoShellDisplay = display;
		  shell = new Shell();
			shell.setSize(457, 614);
			shell.setText("Controller Driver");
			
			ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			scrolledComposite.setBounds(10, 10, 431, 530);
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setExpandVertical(true);
			
			table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_1.setWidth(142);
			tblclmnNewColumn_1.setText("Driver");
			
			TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_2.setWidth(141);
			tblclmnNewColumn_2.setText("Service");
			
			TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn.setWidth(109);
			tblclmnNewColumn.setText("Times");
			scrolledComposite.setContent(table);
			scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			
			int rows = 30;
			for (int i = 0; i < rows; i++) {
				TableItem item = new TableItem(table, SWT.NONE);
			    item.setText(0, "1");
			    item.setText(1, "2");
			    item.setText(2, "3");
			}
			
			Label label = new Label(shell, SWT.NONE);
			label.setText("Switch to :");
			label.setBounds(10, 546, 58, 17);
			
			final Combo combo = new Combo(shell, SWT.NONE);
			combo.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (combo.getText().equals("Overall View")) {
						shell.dispose();
						//new ControllerOverallView(display);
				    } 
					else {
						shell.dispose();
						//new ControllerBusView(display);
					}
				}
			});
			combo.setBounds(95, 546, 103, 29);
			
			Button button = new Button(shell, SWT.NONE);
			button.setText("OK");
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.dispose();
				}
			});
			button.setBounds(256, 546, 77, 31);
			
			Button button_1 = new Button(shell, SWT.NONE);
			button_1.setText("Back");
			button_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.dispose();
					//new ControllerInfoShell(display);
				}
			});
			button_1.setBounds(364, 546, 77, 31);

			combo.add("Overall View");
			combo.add("Bus View");
			
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