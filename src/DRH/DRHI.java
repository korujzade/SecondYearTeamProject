package DRH;

import java.util.ArrayList;

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

public class DRHI
{

	protected static Shell shell;
	private Text ID_textField;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			DRHI window = new DRHI();
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

					// checkDriverHoliday(ID, start, end);
					ArrayList<String> messages = new ArrayList<String>();
					messages = DriverInfo.checkDriverHoliday(ID, start, end);

					if (DriverInfo.errorsuc == 0)
						for (int i = 0; i < messages.size(); i++)
						{
							MessageDialog.openInformation(shell, "Error ",
									messages.get(i));
						}
					else
					{
						for (int i = 0; i < messages.size(); i++)
						{
							MessageDialog.openInformation(shell, "Success ",
									messages.get(i));
						}
					}

				} catch (Exception e2)
				{
					MessageDialog.openError(shell, "ERROR",
							"Something went wrong !" + e2.getMessage());

				}
			}
		});
		submit_btn.setBounds(60, 133, 88, 29);
		submit_btn.setText("Submit");

	}
}
