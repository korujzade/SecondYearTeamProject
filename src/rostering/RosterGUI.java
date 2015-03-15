package rostering;



import org.eclipse.swt.SWT; 
import org.eclipse.swt.events.SelectionEvent; 
import org.eclipse.swt.events.SelectionListener; 
import org.eclipse.swt.widgets.Button; 
import org.eclipse.swt.widgets.Display; 
import org.eclipse.swt.widgets.Event; 
import org.eclipse.swt.widgets.Label; 
import org.eclipse.swt.widgets.Listener; 
import org.eclipse.swt.widgets.Shell; 
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class RosterGUI  { 
  private class DriverShell { 
	 protected Shell shell;
	 private Text text;
	 private Button btnNewButton;
	 private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
     
	 public DriverShell(Display display) { 
		final Display driverShellDisplay = display;
    	shell = new Shell(display, SWT.CLOSE);
 		shell.setSize(450, 300);
 		shell.setText("SWT Application");
 		shell.open();
		 
 		Label lblNewLabel = new Label(shell, SWT.NONE);
 		lblNewLabel.setBounds(95, 115, 76, 17);
 		lblNewLabel.setText("Enter yor ID : ");
 		
 		text = new Text(shell, SWT.BORDER);
		text.setBounds(234, 115, 75, 27);
		formToolkit.adapt(text, true, true);
		
		btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String driverID = text.getText();
				new DriverInfoShell(driverShellDisplay);
			}
		});
		btnNewButton.setBounds(187, 203, 55, 27);
		formToolkit.adapt(btnNewButton, true, true);
		btnNewButton.setText("OK");
     } 
  } 

  private class DriverInfoShell { 
	  protected Shell shell;
	  private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	     
		 public DriverInfoShell(Display display) { 
			final Display driverInfoShellDisplay = display;
	    	shell = new Shell(display, SWT.CLOSE);
	    	shell.setSize(619, 428);
			shell.setText("SWT Application");
			shell.open();
			
			Label lblNewLabel = new Label(shell, SWT.NONE);
			lblNewLabel.setBounds(23, 24, 58, 31);
			lblNewLabel.setText("Monday");
			
			Label lblNewLabel_1 = new Label(shell, SWT.NONE);
			lblNewLabel_1.setBounds(23, 97, 58, 17);
			lblNewLabel_1.setText("Tuesday");
			
			Label lblNewLabel_2 = new Label(shell, SWT.NONE);
			lblNewLabel_2.setBounds(23, 140, 65, 17);
			lblNewLabel_2.setText("Wednesday");
			
			Label lblThursday = new Label(shell, SWT.NONE);
			lblThursday.setText("Thursday");
			lblThursday.setBounds(23, 186, 58, 17);
			
			Label lblFriday = new Label(shell, SWT.NONE);
			lblFriday.setText("Friday");
			lblFriday.setBounds(23, 231, 58, 17);
			
			Label lblSaturday = new Label(shell, SWT.NONE);
			lblSaturday.setText("Saturday");
			lblSaturday.setBounds(23, 273, 58, 17);
			
			Label lblSunday = new Label(shell, SWT.NONE);
			lblSunday.setText("Sunday");
			lblSunday.setBounds(23, 316, 58, 17);
			
			Button btnNewButton = new Button(shell, SWT.NONE);
			btnNewButton.setBounds(138, 24, 77, 31);
			formToolkit.adapt(btnNewButton, true, true);
			btnNewButton.setText("New Button");
			
			Button button = new Button(shell, SWT.NONE);
			button.setText("New Button");
			button.setBounds(234, 24, 77, 31);
			formToolkit.adapt(button, true, true);
			
			Button button_1 = new Button(shell, SWT.NONE);
			button_1.setText("New Button");
			button_1.setBounds(332, 24, 77, 31);
			formToolkit.adapt(button_1, true, true);
			
			Button button_2 = new Button(shell, SWT.NONE);
			button_2.setText("New Button");
			button_2.setBounds(433, 24, 77, 31);
			formToolkit.adapt(button_2, true, true);
	     } 
  } 
  
  public RosterGUI() 
  { 
     final Display display = Display.getDefault(); 

     final Shell mainWindowShell = new Shell(display, SWT.CLOSE); 

     mainWindowShell.setText("Main Shell"); 

     
     Button spawn = new Button(mainWindowShell, SWT.PUSH); 
     spawn.setText("Driver"); 
     spawn.setBounds(67, 116, 150, 30); 
     spawn.addSelectionListener(new SelectionListener() 
     { 
        public void widgetSelected(SelectionEvent e) 
        { 
           
           new DriverShell(display);
        } 

        public void widgetDefaultSelected(SelectionEvent e) 
        { 
           widgetSelected(e); 
        } 
     }); 

     
     mainWindowShell.addListener(SWT.Close, new Listener() 
     { 
        public void handleEvent(Event event) 
        { 
           display.dispose(); 
        } 
     }); 

     
     mainWindowShell.setSize(507, 272); 
     mainWindowShell.open(); 

     
     while (!display.isDisposed()) 
     { 
        
        try 
        { 
           if (!display.readAndDispatch()) 
           { 
              display.sleep(); 
           } 
        } 
        catch (Exception e) 
        { 
           e.printStackTrace(); 
        } 
     } 

  } 

  public static void main(String[] args) 
  { 
     new RosterGUI(); 
  } 
} 