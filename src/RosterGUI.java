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
	btnNewButton_2.setEnabled(false);
	btnNewButton_2.setBounds(325, 115, 77, 31);
	btnNewButton_2.setText("Customer");
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
			
		shell.setSize(421, 514);
		
	  ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 10, 400, 425);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(131);
		tblclmnNewColumn_2.setText("Service");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(133);
		tblclmnNewColumn_3.setText("Bus");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(94);
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
	 	  shell.setText("Select Day & Route");
	 	  shell.open();
	 	  shell.layout();
	 	  
	 	 Composite composite = new Composite(shell, SWT.NONE);
			composite.setBounds(10, 10, 428, 253);
			
			Label lblNewLabel = new Label(composite, SWT.NONE);
			lblNewLabel.setBounds(98, 88, 118, 17);
			lblNewLabel.setText("Select day of week :");
			
			text = new Text(composite, SWT.BORDER);
			text.setBounds(265, 88, 75, 27);
			
			Button btnNewButton = new Button(composite, SWT.NONE);
			btnNewButton.setBounds(176, 189, 77, 31);
			btnNewButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String driverID = text.getText();
					shell.dispose();
				    new ControllerOverallView(driverShellDisplay);
				}
			});
			btnNewButton.setText("OK");
			
			Label lblChooseARoute = new Label(composite, SWT.NONE);
			lblChooseARoute.setBounds(98, 135, 118, 17);
			lblChooseARoute.setText("Chose a Route :");
			
			Combo combo = new Combo(composite, SWT.NONE);
			combo.setBounds(265, 135, 75, 29);
	       
			combo.add("383");
			combo.add("384");
			combo.add("358out");
			combo.add("358back");
	 	  
		  while (!shell.isDisposed()) {
		    if (!display.readAndDispatch()) {
			  display.sleep();
			}
		  }
	    } 
  } 
  
  private class ControllerOverallView { 
	    protected Shell shell;
		private Table table;
		     
		public ControllerOverallView(final Display display) { 
		  final Display driverInfoShellDisplay = display;
		  shell = new Shell(display, SWT.CLOSE);
		  shell.setSize(482, 556);
		  shell.setText("Controller Overall");
          		  
			ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			scrolledComposite.setBounds(10, 10, 460, 473);
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setExpandVertical(true);
			
			table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_3.setWidth(103);
			tblclmnNewColumn_3.setText("Service");
			
			TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_2.setWidth(106);
			tblclmnNewColumn_2.setText("Bus");
			
			TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_1.setWidth(125);
			tblclmnNewColumn_1.setText("Driver");
			
			TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn.setWidth(57);
			tblclmnNewColumn.setText("Times");
			scrolledComposite.setContent(table);
			scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			
			int rows = 20;
			for (int i = 0; i < rows; i++) {
				TableItem item = new TableItem(table, SWT.NONE);
			    item.setText(0, "1");
			    item.setText(1, "2");
			    item.setText(2, "3");
			    item.setText(3, "4");
			}
			
			Button btnNewButton = new Button(shell, SWT.NONE);
			btnNewButton.setBounds(266, 489, 77, 31);
			btnNewButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.dispose();
				}
			});
			btnNewButton.setText("OK");
			
			Button btnNewButton_1 = new Button(shell, SWT.NONE);
			btnNewButton_1.setBounds(374, 489, 77, 31);
			btnNewButton_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.dispose();
					new ControllerInfoShell(display);
				}
			});
			btnNewButton_1.setText("Back");

			final Combo combo = new Combo(shell, SWT.NONE);
			combo.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (combo.getText().equals("Bus View")) {
						shell.dispose();
						new ControllerBusView(display);
				    } 
					else {
						shell.dispose();
						new ControllerDriverView(display);
					}
				}
			});
			combo.setBounds(105, 489, 103, 29);
			
			Label lblNewLabel = new Label(shell, SWT.NONE);
			lblNewLabel.setBounds(20, 489, 58, 17);
			lblNewLabel.setText("Switch to :");
	       
			combo.add("Bus View");
			combo.add("Driver View");
			
		  shell.open();
	      shell.layout();
		  
		  while (!shell.isDisposed()) {
		    if (!display.readAndDispatch()) {
	          display.sleep();
			}
		  }
		} 
	  }
  
    private class ControllerBusView { 
	    protected Shell shell;
		private Table table;
		     
		public ControllerBusView(final Display display) { 
		  final Display driverInfoShellDisplay = display;
		  shell = new Shell();
			shell.setSize(457, 614);
			shell.setText("Controller Bus");
			
			ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
			scrolledComposite.setBounds(10, 10, 431, 530);
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setExpandVertical(true);
			
			table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn_1.setWidth(142);
			tblclmnNewColumn_1.setText("Bus");
			
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
						new ControllerOverallView(display);
				    } 
					else {
						shell.dispose();
						new ControllerDriverView(display);
						
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
					new ControllerInfoShell(display);
				}
			});
			button_1.setBounds(364, 546, 77, 31);

			combo.add("Overall View");
			combo.add("Driver View");
			
		  shell.open();
	      shell.layout();
		  
		  while (!shell.isDisposed()) {
		    if (!display.readAndDispatch()) {
	          display.sleep();
			}
		  }
		} 
	  }
    
    private class ControllerDriverView { 
	    protected Shell shell;
		private Table table;
		     
		public ControllerDriverView(final Display display) { 
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
						new ControllerOverallView(display);
				    } 
					else {
						shell.dispose();
						new ControllerBusView(display);
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
					new ControllerInfoShell(display);
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