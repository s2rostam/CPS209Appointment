/*
*Name: Sarah Rostami
*Student Number: 500750485
*/
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class creates an appointment containing a date, time and description of what and when it occurs
 * @author sarah.rostami
 *
 */
public class Appointment implements Comparable<Appointment>
{
	private Calendar date; 
	private String description;
	private SimpleDateFormat sdf;
	private Person person;
	
	/**
	 * Appointment constructor for a calendar
	 * @param year The year the appointment will occur
	 * @param month The month the appointment will occur
	 * @param day The day the appointment will occur
	 * @param hour The hour the appointment will occur
	 * @param minute The minute the appointment will occur
	 * @param newDescription The description of the appointment
	 */
	public Appointment(int year, int month, int day, int hour, int minute, String newDescription)
	{
		date = new GregorianCalendar();
		date.set(year, month, day, hour, minute);
		description = newDescription;
		sdf = new SimpleDateFormat("kk:mm");
	}
	
	public Appointment(int year, int month, int day, int hour, int minute, String newDescription, String lastName, String firstName, String telephone, String address, String email)
	{
		date = new GregorianCalendar();
		date.set(year, month, day, hour, minute);
		description = newDescription;
		sdf = new SimpleDateFormat("kk:mm");
		person = new Person(lastName, firstName, telephone, address, email);
	}
	
	/**
	 * Get the description of the appointment
	 * @return the description of the appointment
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Get the month of the appointment
	 * @return the month of the appointment
	 */
	public int getMonth()
	{
		return date.get(Calendar.MONTH);
	}
	
	/**
	 * Get the year of the appointment
	 * @return the year of the appointment
	 */
	public int getYear()
	{
		return date.get(Calendar.YEAR);
	}
	
	/**
	 * Get the day of the appointment
	 * @return the day of the appointment
	 */
	public int getDay()
	{
		return date.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Get the hour of the appointment
	 * @return the hour of the appointment
	 */
	public int getHour()
	{
		return date.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * Get the minute of the appointment
	 * @return the minute of the appointment
	 */
	public int getMinute()
	{
		return date.get(Calendar.MINUTE);
	}
	
	/**
	 * Creates a string which contains the time and description of the appointment
	 * @return String containing time, description and person
	 */
	public String print(Appointment a)
	{
		if (a.person != null)
		{
			String value = sdf.format(date.getTime()) + " " + getDescription() +  " " + person.toString() + "\n";
			return value;
		}
		if (a.person == null)
		{
			String value = sdf.format(date.getTime()) + " " + getDescription() + "\n";
			return value;
		}
		return "";
	}
	
	/**
	 * Checks to see whether there is an appointment which occurs on the same day and time as another
	 * @param year The year the appointment occurs
	 * @param month The month the appointment occurs
	 * @param day The day the appointment occurs
	 * @param hour The hour the appointment occurs
	 * @param minute The minute the appointment occurs
	 * @return true if occurs or false if does not occur
	 */
	public boolean occursOn(int year, int month, int day, int hour, int minute)
	{
		int dateYear, dateMonth, dateDay, dateHour, dateMinute;
		dateYear = date.get(Calendar.YEAR);
		dateMonth = date.get(Calendar.MONTH);
		dateDay = date.get(Calendar.DAY_OF_MONTH);
		dateHour = date.get(Calendar.HOUR_OF_DAY);
		dateMinute= date.get(Calendar.MINUTE);
		
		if (year == dateYear && month == dateMonth
			&& day == dateDay && hour == dateHour
			&& minute == dateMinute)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	 
	/**
	  * Checks to see if an appointment takes place on the same day as another
	  * @param year The year the appointment occurs
	  * @param month The month the appointment occurs
	  * @param day The day the appointment occurs
	  * @return true if occurs or false if does not occur
	  */
	public boolean dayOccursOn(int year, int month, int day)
	{
		if (year == date.get(Calendar.YEAR) && month == date.get(Calendar.MONTH)
				&& day == date.get(Calendar.DAY_OF_MONTH))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Check to see whether an appointment occur before or after another appointment
	 * @param other The second appointment to compare to
	 * @return 1 if after or -1 if before
	 */
	public int compareTo(Appointment other)
	{
		if (this.date.before(other.date)) 
		return -1;
		else
		return 1;
	}
}
