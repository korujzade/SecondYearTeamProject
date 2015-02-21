import java.util.ArrayList;
import java.util.Date;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Class which represents information about drivers and their availability. It
 * also allows the application to get and set the number of hours a driver has
 * worked over time periods of a week or a year, and the holidays taken by a
 * driver in the current calendar year.<br>
 * <br>
 * 
 * As well as an ID, drivers (like buses) have numbers which are traditionally
 * used to identify them. You can also get the name of a specified driver<br>
 * <br>
 * 
 * The methods contain checks for invalid queries, which will result in
 * InvalidQueryExceptions being thrown, but it does not enforce "business rules"
 * such as checking for dates in the past.
 */
public class DriverInfo
{

	public static int errorsuc;

	// This class is not intended to be instantiated
	private DriverInfo()
	{
	}

	/**
	 * Get the IDs of all the drivers in the database
	 */
	public static int[] getDrivers()
	{
		return database.busDatabase.select_ids("driver_id", "driver", "name");
	}

	// get drivers from availability table *kamil*
	public static int[] getAvailabilityDrivers()
	{
		return database.busDatabase.select_ids("driver_availability_id",
				"driver_availability", "driver");
	}

	/**
	 * Find the driver with the specified driver number
	 */
	public static int findDriver(String number)
	{
		return database.busDatabase.find_id("driver", "number", number);
	}

	/**
	 * Get the real name of a driver
	 */
	public static String getName(int driver)
	{
		if (driver == 0)
			throw new InvalidQueryException("Nonexistent driver");
		return database.busDatabase.get_string("driver", driver, "name");
	}

	/**
	 * Get the number of days holiday taken, or planned to be taken, in the
	 * current calendar year
	 */
	public static int getHolidaysTaken(int driver)
	{
		if (driver == 0)
			throw new InvalidQueryException("Nonexistent driver");
		return database.busDatabase.get_int("driver", driver, "holidays_taken");
	}

	/**
	 * Set the number of days holiday taken, or planned to be taken, in the
	 * current calendar year.
	 */
	public static void setHolidaysTaken(int driver, int value)
	{
		if (driver == 0)
			throw new InvalidQueryException("Nonexistent driver");
		database.busDatabase.set_value("driver", driver, "holidays_taken",
				value);
	}

	// Set the number of days holiday taken, or planned to be taken, or planned
	// to be taken, in the next calendar year.
	public static void setHolidaysTakenNextYear(int driver, int value)
	{
		if (driver == 0)
			throw new InvalidQueryException("Nonexistent driver");
		database.busDatabase.set_value("driver", driver,
				"holidays_taken_next_year", value);
	}

	/**
	 * Get the number of hours worked by a driver so far this calandar year
	 */
	public static int getHoursThisYear(int driver)
	{
		if (driver == 0)
			throw new InvalidQueryException("Nonexistent driver");
		return database.busDatabase
				.get_int("driver", driver, "hours_this_year");
	}

	/**
	 * Set the number of hours worked by a driver so far this calandar year
	 */
	public static void setHoursThisYear(int driver, int value)
	{
		if (driver == 0)
			throw new InvalidQueryException("Nonexistent driver");
		database.busDatabase.set_value("driver", driver, "hours_this_year",
				value);
	}

	/**
	 * Get the number of hours worked by a driver during the period of a weekly
	 * roster
	 */
	public static int getHoursThisWeek(int driver)
	{
		if (driver == 0)
			throw new InvalidQueryException("Nonexistent driver");
		return database.busDatabase
				.get_int("driver", driver, "hours_this_week");
	}

	/**
	 * Set the number of hours worked by a driver during the period of a weekly
	 * roster
	 */
	public static void setHoursThisWeek(int driver, int value)
	{
		if (driver == 0)
			throw new InvalidQueryException("Nonexistent driver");
		database.busDatabase.set_value("driver", driver, "hours_this_week",
				value);
	}

	/**
	 * Get the identification number of a driver
	 */
	public static String getNumber(int driver)
	{
		if (driver == 0)
			throw new InvalidQueryException("Nonexistent driver");
		return database.busDatabase.get_string("driver", driver, "number");
	}

	/**
	 * Determine whether a driver is available on a given date
	 */
	public static boolean isAvailable(int driver, Date date)
	{
		if (date == null)
			throw new InvalidQueryException("Date is null");
		if (driver == 0)
			throw new InvalidQueryException("Nonexistent driver");
		database db = database.busDatabase;
		if (db.select_record("driver_availability", "driver", driver, "day",
				date))
			return (Integer) db.get_field("available") != 0;
		else
			return true;
	}

	/**
	 * Determine whether a driver is available today
	 */
	public static boolean isAvailable(int driver)
	{
		return isAvailable(driver, database.today());
	}

	/**
	 * Set whether a driver is available on a given date
	 */
	public static void setAvailable(int driver, Date date, boolean available)
	{
		if (date == null)
			throw new InvalidQueryException("Date is null");
		if (driver == 0)
			throw new InvalidQueryException("Nonexistent driver");
		if (available && !isAvailable(driver, date))
			database.busDatabase.delete_record("driver_availability", "driver",
					driver, "day", date);
		else if (!available && isAvailable(driver, date))
			database.busDatabase.new_record("driver_availability",
					new Object[][]
					{
					{ "available", false },
					{ "day", date },
					{ "driver", driver } });
	}

	/**
	 * Set whether a driver is available today
	 */
	public static void setAvailable(int driver, boolean available)
	{
		setAvailable(driver, database.today(), available);
	}

	public static ArrayList<String> checkDriverHoliday(int id, String fromdate,
			String todate)
	{

		database.openBusDatabase();
		// getNumber method return string driver Number
		// String driverNumberString;
		// driverNumberString = DriverInfo.getNumber(id);

		// parse driver number to integer
		// int driverNumber = Integer.parseInt(driverNumberString);
		int holidays;

		ArrayList<String> errors = new ArrayList<String>();
		ArrayList<String> success = new ArrayList<String>();

		// cast fromdate and to date which come as string from GUI to localdate
		// of Joda library
		LocalDate from = new LocalDate(fromdate);
		LocalDate to = new LocalDate(todate);

		int fromcheck = from.getYear();
		int tocheck = to.getYear();
		holidays = DriverInfo.getHolidaysTaken(id);

		// the number of days driver want to take holiday
		// + 1 needed as daysBetween calculate days between these dates
		int diff = Days.daysBetween(from, to).getDays() + 1;

		// new method! check if from date is at least 10 days later after today
		LocalDate todaysDate = new LocalDate();
		int atleastTen = Days.daysBetween(todaysDate, from).getDays();

		if (tocheck != 2015 || fromcheck != 2015)
		{
			errors.add("You can only take holiday for this year.");
		} else if (holidays >= 25)
		{
			errors.add("You have already chosen 25 days for holidays this year");
		} else if (atleastTen <= 10)
		{
			errors.add("There must be 10 days between today and the day you want to take holiday.");
		} else if (diff <= 0)
		{
			errors.add("You have chosen dates in a wrong way!");
		} else
		{
			LocalDate tofor = to.plusDays(1);
			// use casted date for iterating between "from" date and "to" date
			for (LocalDate date = from; date.isBefore(tofor); date = date
					.plusDays(1))
			{
				// check if current date is chosen more than 10 drivers
				DateTimeFormatter formatter = DateTimeFormat
						.forPattern("yyyy-MM-dd");
				String dateforcheck = formatter.print(date);
				if (checkHolidayAvailability(dateforcheck) == false)
				{
					errors.add(date
							+ " has already been chosen the limited number of times by drivers ");
				}

				// cast localdate format to date format for using in isAvailable
				// method
				Date datedf = date.toDate();

				// check if driver already chosen current date for holiday means
				// s/he choose
				// that date again by mistake
				if (DriverInfo.isAvailable(id, datedf) == false)
				{
					errors.add("You have already chosen " + date
							+ " date for holiday");
				}
			}
			if ((holidays + diff) > 25)
			{
				errors.add("The number of days you chose and previous taken holidays are more than 25");
			}
		} // else if

		if (!errors.isEmpty())
		{
			errorsuc = 0;
			database.busDatabase.close();
			return errors;
		} else
		{
			errorsuc = 1;
			LocalDate tofor = to.plusDays(1);
			for (LocalDate date = from; date.isBefore(tofor); date = date
					.plusDays(1))
			{
				Date datedf = date.toDate();
				database.busDatabase.new_record("driver_availability",
						new Object[][]
						{
						{ "available", 0 },
						{ "day", datedf },
						{ "driver", id } });
			}
			success.add("Enjoy your holiday! You have chosen "
					+ (holidays + diff) + " days as holiday for this year");
			DriverInfo.setHolidaysTaken(id, (holidays + diff));
			database.busDatabase.close();
			return success;

		}
	}

	public static boolean checkHolidayAvailability(String date)
	{
		if (date == null)
			throw new InvalidQueryException("Date is null");
		int holidayDrivers = 0;
		boolean holidayAvailability = true;
		int[] driverIDs = DriverInfo.getAvailabilityDrivers();
		for (int i = 0; i < driverIDs.length; i++)
		{
			database db = database.busDatabase;
			if (db.select_record("driver_availability",
					"driver_availability_id", driverIDs[i], "available", 0,
					"day", date))
			{
				holidayDrivers++;
			}
		}
		if (holidayDrivers >= 10)
		{
			holidayAvailability = false;
		}
		return holidayAvailability;
	}

}
