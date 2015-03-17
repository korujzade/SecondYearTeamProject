import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT; 
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent; 
import org.eclipse.swt.events.SelectionListener; 
import org.eclipse.swt.widgets.Button; 
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

public class RosterGUI  {
  
  public static void main(String[] args) { 
    new RosterGUI(); 
  } 
	
  public RosterGUI() {	  
    final Display display = Display.getDefault(); 
	final Shell mainWindowShell = new Shell(display, SWT.CLOSE); 
	mainWindowShell.setText("Main Shell"); 

	Button spawn = new Button(mainWindowShell, SWT.PUSH); 
	spawn.setText("Driver"); 
	spawn.setBounds(85, 116, 93, 30); 
	spawn.addSelectionListener(new SelectionListener() { 
	  @Override 
	  public void widgetSelected(SelectionEvent e) {  
	    new DriverInfoShell(display);
	  } 

	  @Override 
	  public void widgetDefaultSelected(SelectionEvent e) { 
	    widgetSelected(e); 
	  } 
	}); 

	mainWindowShell.addListener(SWT.Close, new Listener() { 
	  @Override 
	  public void handleEvent(Event event) { 
	    display.dispose(); 
	  } 
	}); 

	mainWindowShell.setSize(507, 272); 
	     
	Label lblNewLabel_3 = new Label(mainWindowShell, SWT.NONE);
	lblNewLabel_3.setBounds(162, 66, 197, 17);
	lblNewLabel_3.setText("What function do you want to use?");
	     
	Button btnNewButton_1 = new Button(mainWindowShell, SWT.NONE);
	btnNewButton_1.addSelectionListener(new SelectionAdapter() {
	  @Override
	  public void widgetSelected(SelectionEvent e) {
		new ControllerInfoShell(display);
	  }
	});
	btnNewButton_1.setBounds(214, 116, 77, 31);
	btnNewButton_1.setText("Controller");
	     
	Button btnNewButton_2 = new Button(mainWindowShell, SWT.NONE);
	btnNewButton_2.setBounds(325, 115, 77, 31);
	btnNewButton_2.setText("Bus");
	mainWindowShell.open(); 
	     
	while (!display.isDisposed()) { 
	  try { 
	    if (!display.readAndDispatch()) { 
	      display.sleep(); 
	    } 
	  } 
	  catch (Exception e) { 
	    e.printStackTrace(); 
	  } 
	} 
  } 
	
  private class DriverInfoShell { 
    protected Shell shell;
	private Text text;
	private Text text_1;
	private Button btnNewButton;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
     
	public DriverInfoShell(final Display display) { 
	  final Display driverShellDisplay = display;
      shell = new Shell(display, SWT.CLOSE);
 	  shell.setSize(450, 300);
 	  shell.setText("Enter ID & day");
 	  shell.open();
	 
 	 Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(120, 74, 76, 17);
		lblNewLabel.setText("Enter yor ID : ");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(263, 74, 75, 27);
		formToolkit.adapt(text, true, true);
		
		btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				new DriverShell(display);
			}
		});
		btnNewButton.setBounds(187, 203, 55, 27);
		formToolkit.adapt(btnNewButton, true, true);
		btnNewButton.setText("OK");
		
		Label lblNewLabel_1 = formToolkit.createLabel(shell, "Select day of week :", SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblNewLabel_1.setBounds(120, 136, 122, 17);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(263, 136, 75, 27);
		formToolkit.adapt(text_1, true, true);		
		
	  while (!shell.isDisposed()) {
	    if (!display.readAndDispatch()) {
		  display.sleep();
		}
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
			
	  shell.setSize(500, 512);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 10, 478, 425);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Route");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("Service");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("Bus");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Times");
		
		int rows = 20;
		for (int i = 0; i < rows; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
		    item.setText(0, "1");
		    item.setText(1, "2");
		    item.setText(2, "3");
		    item.setText(3, "4");
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
		btnNewButton.setBounds(113, 444, 77, 31);
		btnNewButton.setText("OK");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				new DriverInfoShell(display);
			}
		});
		btnNewButton_1.setBounds(265, 444, 77, 31);
		btnNewButton_1.setText("Back");

		shell.open();
		shell.layout();
		
	  while (!shell.isDisposed()) {
	    if (!display.readAndDispatch()) {
          display.sleep();
		}
	  }
	} 
  }
  
  private class ControllerInfoShell { 
	    protected Shell shell;
	    private Text text;
	     
		public ControllerInfoShell(Display display) { 
		  final Display driverShellDisplay = display;
	      shell = new Shell(display, SWT.CLOSE);
	 	  shell.setSize(450, 300);
	 	  shell.setText("Select Day");
	 	  shell.open();
	 	  shell.layout();
	 	  
	 	 Label lblNewLabel = new Label(shell, SWT.NONE);
			lblNewLabel.setBounds(101, 75, 258, 25);
			lblNewLabel.setText("Which day of the week are you interested in?");
			
			text = new Text(shell, SWT.BORDER);
			text.setBounds(181, 119, 75, 27);
			
			Button btnNewButton = new Button(shell, SWT.NONE);
			btnNewButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String driverID = text.getText();
				    new ControllerShell(driverShellDisplay);
				}
			});
			btnNewButton.setBounds(181, 167, 77, 31);
			btnNewButton.setText("OK");
	 	  
		  while (!shell.isDisposed()) {
		    if (!display.readAndDispatch()) {
			  display.sleep();
			}
		  }
	    } 
  } 
  
  private class ControllerShell { 
	    protected Shell shell;
		private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
		     
		public ControllerShell(Display display) { 
		  final Display driverInfoShellDisplay = display;
		  shell = new Shell(display, SWT.CLOSE);
		  shell.setSize(902, 380);
		  shell.setText("Controller");
          shell.setLayout(new GridLayout(8, false));
				
        Label lblNewLabel = new Label(shell, SWT.NONE);
  		lblNewLabel.setFont(SWTResourceManager.getFont("Noto Sans", 13, SWT.NORMAL));
  		lblNewLabel.setText("Monday");
  		
  		Button btnNewButton = new Button(shell, SWT.NONE);
  		btnNewButton.setFont(SWTResourceManager.getFont("Noto Sans", 13, SWT.NORMAL));
  		btnNewButton.setText("New Button");
  		
  		Button button = new Button(shell, SWT.NONE);
  		button.setFont(SWTResourceManager.getFont("Noto Sans", 13, SWT.NORMAL));
  		button.setText("New Button");
  		
  		Button button_1 = new Button(shell, SWT.NONE);
  		button_1.setFont(SWTResourceManager.getFont("Noto Sans", 13, SWT.NORMAL));
  		button_1.setText("New Button");
  		
  		Button button_2 = new Button(shell, SWT.NONE);
  		button_2.setFont(SWTResourceManager.getFont("Noto Sans", 13, SWT.NORMAL));
  		button_2.setText("New Button");
  		
  		Button button_3 = new Button(shell, SWT.NONE);
  		button_3.setFont(SWTResourceManager.getFont("Noto Sans", 13, SWT.NORMAL));
  		button_3.setText("New Button");
  		
  		Button button_4 = new Button(shell, SWT.NONE);
  		button_4.setFont(SWTResourceManager.getFont("Noto Sans", 13, SWT.NORMAL));
  		button_4.setText("New Button");
  		new Label(shell, SWT.NONE);
  		
  		Label lblTuesday = new Label(shell, SWT.NONE);
  		lblTuesday.setFont(SWTResourceManager.getFont("Noto Sans", 13, SWT.NORMAL));
  		lblTuesday.setText("Tuesday");
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		
  		Label lblWednesday = new Label(shell, SWT.NONE);
  		lblWednesday.setFont(SWTResourceManager.getFont("Noto Sans", 13, SWT.NORMAL));
  		lblWednesday.setText("Wednesday");
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		
  		Label lblThursday = new Label(shell, SWT.NONE);
  		lblThursday.setFont(SWTResourceManager.getFont("Noto Sans", 13, SWT.NORMAL));
  		lblThursday.setText("Thursday");
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		
  		Label lblFriday = new Label(shell, SWT.NONE);
  		lblFriday.setFont(SWTResourceManager.getFont("Noto Sans", 13, SWT.NORMAL));
  		lblFriday.setText("Friday");
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		
  		Label lblSaturday = new Label(shell, SWT.NONE);
  		lblSaturday.setFont(SWTResourceManager.getFont("Noto Sans", 13, SWT.NORMAL));
  		lblSaturday.setText("Saturday");
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		
  		Label lblSunday = new Label(shell, SWT.NONE);
  		lblSunday.setFont(SWTResourceManager.getFont("Noto Sans", 13, SWT.NORMAL));
  		lblSunday.setText("Sunday");
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
  		new Label(shell, SWT.NONE);
		  
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