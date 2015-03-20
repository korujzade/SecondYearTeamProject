package DRH;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


public class test
{

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			test window = new test();
			window.open();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open()
	{
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents()
	{
		shell = new Shell();
		shell.setSize(700, 794);
		shell.setText("SWT Application");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 61, 678, 661);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Table table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
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
		
		int rows = 30;
		for (int i = 0; i < rows; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
		    item.setText(0, "1");
		    item.setText(1, "2");
		    item.setText(2, "3");
		}
		
		Label lblDriver = new Label(shell, SWT.NONE);
		lblDriver.setText("Day :");
		lblDriver.setBounds(21, 18, 26, 20);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(202, 18, 40, 20);
		lblNewLabel.setText("Route :");
		
		Label lblView = new Label(shell, SWT.NONE);
		lblView.setText("View :");
		lblView.setBounds(396, 18, 40, 17);
		
		final Combo combo = new Combo(shell, SWT.NONE);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		combo.setBounds(53, 15, 129, 29);
		combo.add("2014");
		combo.add("2015");
		
		Combo combo_2 = new Combo(shell, SWT.NONE);
		combo_2.setBounds(248, 15, 109, 23);
		
		Combo combo_1 = new Combo(shell, SWT.NONE);
		combo_1.setBounds(439, 15, 129, 29);
		combo_1.add("");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
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
	}
}
