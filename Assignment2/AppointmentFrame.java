/* Name: Sarah Rostami
 * Student Number: 500750485
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class creates a custom Frame by inheriting the JFrame class and sets up certain components
 * in order to create an appointment calendar, which can add, remove and find appointments at any date.
 * @author sarah.rostami
 *
 */
@SuppressWarnings("serial")
public class AppointmentFrame extends JFrame
{
	private Contacts contacts;
	private JLabel currentDate;
	private JLabel currentMonth;
	private Calendar calendar;
	private SimpleDateFormat sdf;
	private SimpleDateFormat sdfMonth;
	private ArrayList<Appointment> appointments;
	private Stack<Appointment> stackedAppointments;
	private JTextArea allAppointments;
	private JTextField inputDay; 
	private JTextField inputMonth ;
	private JTextField inputYear ;
	private JTextField inputHour ;
	private JTextField inputMinute ;
	private JTextField desBox ;
	private JTextField inputLastName;
	private JTextField inputFirstName;
	private JTextField inputTelephone;
	private JTextField inputEmail;
	private JTextField inputAddress;
	private JPanel dates;
	private JPanel restOfCalendar;
	private JPanel gridCalendar;
	private JButton day;
	
	/**
	 * Default constructor for AppointmentFrame
	 */
	public AppointmentFrame()
	{
		appointments = new ArrayList<Appointment>();
		stackedAppointments = new Stack<Appointment>();
		contacts = new Contacts();
		sdf = new SimpleDateFormat("EEE, MMM d, yyyy");
		sdfMonth = new SimpleDateFormat("MMM");
		calendar = new GregorianCalendar();
		calendar.setTime(calendar.getTime());
		inputDay = new JTextField(3);
		inputMonth = new JTextField(3);
		inputYear = new JTextField(5);
		inputHour = new JTextField(5);
		inputMinute = new JTextField(5);
		inputLastName = new JTextField(10);
		inputFirstName = new JTextField(10);
		inputTelephone = new JTextField(10);
		inputEmail = new JTextField(10);
		inputAddress = new JTextField(10);
		desBox = new JTextField();
		allAppointments = new JTextArea();
		day = new JButton();
		createComponents();
		setSize(800,800);
	}
	
	/** Creates all basic components to AppointmentFrame */
	public void createComponents()
	{
		contacts.readContactsFile();
		currentDate = new JLabel(sdf.format(calendar.getTime()));
		currentMonth = new JLabel(sdfMonth.format(calendar.getTime()));	
		setLayout(new BorderLayout());
		dates = new JPanel();
		dates.setLayout(null);
		dates.setPreferredSize(new Dimension(1000, 20));
		restOfCalendar = new JPanel();
		restOfCalendar.setLayout(new GridLayout(3, 2));
		dates.add(currentDate);
		dates.add(currentMonth);
		currentDate.setLocation(0, 0);
		currentMonth.setLocation(400, 0);
		currentDate.setSize(200, 20);
		currentMonth.setSize(100, 20);
		add(dates, BorderLayout.NORTH);
		add(restOfCalendar, BorderLayout.CENTER);
		setTextArea();
		setPanels();			
	}
	
	/** Sets the properties of the description text area */
	public void setTextArea()
	{
		JScrollPane scroll = new JScrollPane(allAppointments, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		allAppointments.setEditable(false);
		restOfCalendar.add(scroll);
	}
	
	/** Sets up subpanels and adds them to the control panel along with the other components */
	public void setPanels()
	{
		gridCalendar = new JPanel();
		JPanel dateSubpanel = new JPanel();
		JPanel appointmentSubpanel = new JPanel();
		JPanel contactSubpanel = new JPanel();
		JPanel descriptionSubpanel = new JPanel();
		setGridCalendarSubpanel(gridCalendar);
		setDateSubpanel(dateSubpanel);
		setContactSubpanel(contactSubpanel);
		setAppointmentSubpanel(appointmentSubpanel);
		setDescriptionBox(descriptionSubpanel);
		restOfCalendar.add(gridCalendar);
		restOfCalendar.add(dateSubpanel);
		restOfCalendar.add(contactSubpanel);
		restOfCalendar.add(appointmentSubpanel);
		restOfCalendar.add(descriptionSubpanel);
	}
	
	/**
	 * Sets up the month view for the calendar
	 * @param calendarView the panel to modify
	 */
	public void setGridCalendarSubpanel(JPanel calendarView)
	{	
		JPanel monthView = new JPanel();
		monthView.removeAll();
		calendarView.setLayout(new BorderLayout());
		JPanel daysOfWeek = new JPanel();
		monthView.setLayout(new GridBagLayout());
		daysOfWeek.setLayout(new GridLayout(1,7));
		JLabel sunLabel = new JLabel("Sun");
		JLabel monLabel = new JLabel("Mon");
		JLabel tueLabel = new JLabel("Tue");
		JLabel wedLabel = new JLabel("Wed");
		JLabel thuLabel = new JLabel("Thu");
		JLabel friLabel = new JLabel("Fri");
		JLabel satLabel = new JLabel("Sat");
		daysOfWeek.add(sunLabel);
		daysOfWeek.add(monLabel);
		daysOfWeek.add(tueLabel);
		daysOfWeek.add(wedLabel);
		daysOfWeek.add(thuLabel);
		daysOfWeek.add(friLabel);
		daysOfWeek.add(satLabel);
		calendarView.add(daysOfWeek, BorderLayout.NORTH);
		
		GridBagConstraints c = new GridBagConstraints();
		int numOfWeeks = calendar.getMaximum(Calendar.WEEK_OF_MONTH);
		int numOfDay = 1;
		int startingDay = 0;
		Calendar temp = new GregorianCalendar();
		temp.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		int maxDayOfWeek = temp.getActualMaximum(Calendar.DAY_OF_WEEK);
		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		for (int week = 1; week <= numOfWeeks; week++)
		{
			if (week == 1)
			{
				startingDay = temp.get(Calendar.DAY_OF_WEEK);
			}
			else
			{
				startingDay = calendar.getActualMinimum(Calendar.DAY_OF_WEEK);
			}
			for (int weekDay = startingDay; weekDay <= maxDayOfWeek; weekDay++)
			{
				if (weekDay >= startingDay && numOfDay <= maxDays)
				{
					day = new JButton(Integer.toString(numOfDay));
					day.setPreferredSize(new Dimension(20, 80));
					day.setActionCommand(Integer.toString(numOfDay));
					day.addActionListener(new ClickListener());
					c.fill = GridBagConstraints.HORIZONTAL;
					c.weightx = 0.5;
					c.weighty = 0.5;
					c.gridx = weekDay;
					c.gridy = week;
					monthView.add(day, c);
					numOfDay++;
				}
				if (numOfDay-1 == calendar.get(Calendar.DAY_OF_MONTH))
				{
					day.setBackground(Color.RED);
					day.revalidate();
					day.repaint();
				}
			}
		}
		
		calendarView.revalidate();
		calendarView.add(monthView, BorderLayout.CENTER);
		monthView.repaint();
		monthView.revalidate();
	}
	
	/**
	 * Sets up contact panel for creating contacts
	 * @param contact the panel to modify
	 */
	public void setContactSubpanel(JPanel contact)
	{
		contact.setLayout(new BorderLayout());
		JPanel inputContactInfo = new JPanel();
		JPanel forContactButtons = new JPanel();
		JPanel forAddressText = new JPanel();
		forAddressText.setLayout(new BorderLayout());
		inputContactInfo.setLayout(new GridLayout(4,4));
		JLabel lastNameLabel = new JLabel("Last Name");
		JLabel firstNameLabel = new JLabel("First Name");
		JLabel telephoneLabel = new JLabel("Telephone");
		JLabel emailLabel = new JLabel("Email");
		JLabel addressLabel = new JLabel("Address");
		inputContactInfo.add(lastNameLabel);
		inputContactInfo.add(firstNameLabel);
		inputContactInfo.add(inputLastName);
		inputContactInfo.add(inputFirstName);
		inputContactInfo.add(telephoneLabel);
		inputContactInfo.add(emailLabel);
		inputContactInfo.add(inputTelephone);
		inputContactInfo.add(inputEmail);
		forAddressText.add(addressLabel, BorderLayout.NORTH);
		forAddressText.add(inputAddress, BorderLayout.CENTER);	
		JButton findContacts = new JButton();
		setUpButtons(findContacts, "Find", "Find Contact", 75, 25);
		JButton clearTextBox = new JButton();
		setUpButtons(clearTextBox, "Clear", "Clear", 75, 25);
		forContactButtons.add(findContacts);
		forContactButtons.add(clearTextBox);
		contact.add(inputContactInfo, BorderLayout.NORTH);
		contact.add(forAddressText, BorderLayout.CENTER);
		contact.add(forContactButtons, BorderLayout.SOUTH);
		contact.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		contact.setBorder(BorderFactory.createTitledBorder("Contact"));
	}
	
	/** Sets up the date subpanel in the Control Panel 
	 * @param date the date subpanel
	 */
	public void setDateSubpanel(JPanel date)
	{
		JPanel thruDates = new JPanel();
		JPanel chooseDate = new JPanel();
		JPanel pForShow = new JPanel();
		JButton nextDay = new JButton();
		setUpButtons(nextDay, ">", "Next Date", 125, 30);
		JButton prevDay = new JButton();
		setUpButtons(prevDay, "<", "Prev Date", 125, 30);
		JButton showDay = new JButton();
		setUpButtons(showDay, "Show", "Show Date", 75, 25);
		JLabel timeDay = new JLabel("Day");
		JLabel timeMonth = new JLabel("Month");
		JLabel timeYear = new JLabel("Year");	
		date.setLayout(new BorderLayout());
		date.add(thruDates, BorderLayout.NORTH); 
		date.add(chooseDate, BorderLayout.CENTER); 
		date.add(pForShow, BorderLayout.SOUTH);
		date.setPreferredSize(new Dimension(470, 150));
		date.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		date.setBorder(BorderFactory.createTitledBorder("Date"));
		thruDates.add(prevDay);
		thruDates.add(nextDay);
		chooseDate.add(timeDay);
		chooseDate.add(inputDay);
		chooseDate.add(timeMonth);
		chooseDate.add(inputMonth);
		chooseDate.add(timeYear);
		chooseDate.add(inputYear);
		pForShow.add(showDay);
	}
	/** Sets up the action subpanel in the Control Panel
	 * @param action the action subpanel
	 */
	public void setAppointmentSubpanel(JPanel action)
	{
		JPanel chooseHAndM = new JPanel();
		JPanel pforCreateAndCancel = new JPanel();
		JButton create = new JButton();
		setUpButtons(create, "CREATE", "Create Appointment", 85, 25);
		JButton cancel = new JButton();
		setUpButtons(cancel, "CANCEL", "Cancel Appointment", 85, 25);
		JButton recall = new JButton();
		setUpButtons(recall, "RECALL", "Recall Appointment", 85, 25);
		JLabel hourTime = new JLabel("Hour");
		JLabel minuteTime = new JLabel("Minute");
		action.setLayout(new BorderLayout());
		action.add(chooseHAndM, BorderLayout.NORTH); 
		action.add(pforCreateAndCancel, BorderLayout.SOUTH);
		action.setPreferredSize(new Dimension(470, 140));
		action.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		action.setBorder(BorderFactory.createTitledBorder("Appointment"));
		chooseHAndM.add(hourTime);
		chooseHAndM.add(inputHour);
		chooseHAndM.add(minuteTime);
		chooseHAndM.add(inputMinute);
		pforCreateAndCancel.add(create);
		pforCreateAndCancel.add(cancel);
		pforCreateAndCancel.add(recall);
	}
	
	/**
	 * Set up the description subpanel in the Control Panel
	 * @param description The description subpanel
	 */
	public void setDescriptionBox(JPanel description)
	{
		JPanel pForDes = new JPanel();		
		description.setLayout(new BorderLayout());	
		description.setPreferredSize(new Dimension(470, 140));
		pForDes.setPreferredSize(new Dimension(470,75));
		description.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		description.setBorder(BorderFactory.createTitledBorder("Description"));
		desBox.setPreferredSize(new Dimension(300, 75));
		description.add(pForDes);
		pForDes.add(desBox);
	}
	
	/**
	 * Set all the necessary settings for the buttons
	 * @param button the JButton that was created
	 * @param name the text to be set in the JButton
	 * @param actionComm the String which will determine which action to perform
	 * @param width the preferred width of the button
	 * @param height the preferred height of the button
	 */
	public void setUpButtons(JButton button, String name, String actionComm, int width, int height)
	{
		button.setText(name);
		button.setActionCommand(actionComm);
		button.addActionListener(new ClickListener());
		button.setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Creates an appointment with user input
	 */
	public void createAppointment()
	{
		boolean occurs = false;
		
		int year, month, day, hour, minute;
		String desText = "";
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = Integer.parseInt(inputHour.getText().trim());
		if (!inputMinute.getText().isEmpty())
		{
		minute = Integer.parseInt(inputMinute.getText().trim());
		}
		else
		{
		minute = 0;
		}
		if (!desBox.getText().isEmpty())
		{
			desText = desBox.getText();
		}
		if (hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59)
		{
			for(Appointment a : appointments)
			{
				if (a.occursOn(year, month, day, hour, minute))
				{
					occurs = true;
					break;
				}
			}
		}
		else
		{
			desBox.setText("Wrong number format!");
			return;
		}
		
		
		if(occurs)
		{
			desBox.setText("CONFLICT!!");
		}
		else
		{
			if (contacts.findPersonName(inputFirstName.getText(), inputLastName.getText()) != null || contacts.findPersonEmail(inputEmail.getText()) != null || contacts.findPersonTelephone(inputTelephone.getText()) != null)
			{
					appointments.add(new Appointment(year, month, day, hour, minute, desText, inputLastName.getText(), inputFirstName.getText(), inputTelephone.getText(), inputAddress.getText(), inputEmail.getText()));
					stackedAppointments.push(new Appointment(year, month, day, hour, minute, desText, inputLastName.getText(), inputFirstName.getText(), inputTelephone.getText(), inputAddress.getText(), inputEmail.getText()));
					Collections.sort(appointments);
			}
			else if (!inputLastName.getText().isEmpty() && !inputFirstName.getText().isEmpty())
			{
				appointments.add(new Appointment(year, month, day, hour, minute, desText, inputLastName.getText(), inputFirstName.getText(), inputTelephone.getText(), inputAddress.getText(), inputEmail.getText()));
				stackedAppointments.push(new Appointment(year, month, day, hour, minute, desText, inputLastName.getText(), inputFirstName.getText(), inputTelephone.getText(), inputAddress.getText(), inputEmail.getText()));
				Collections.sort(appointments);
			}
			else
			{
				appointments.add(new Appointment(year, month, day, hour, minute, desText));
				stackedAppointments.push(new Appointment(year, month, day, hour, minute, desText));
				Collections.sort(appointments);
			}
		}
	}
	
	/**
	 * Cancels an appointment which already exists
	 */
	public void cancelAppointment()
	{
		boolean occurs = false;
		int count = -1;
		int year, month, day, hour, minute;
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = Integer.parseInt(inputHour.getText().trim());
		minute = Integer.parseInt(inputMinute.getText().trim());
		
		for(Appointment a : appointments)
		{
			count++;
			if (a.occursOn(year, month, day, hour, minute))
			{
				occurs = true;
				break;
			}
		}
		
		if(occurs)
		{
			Appointment temp = appointments.get(count);
			appointments.remove(appointments.get(count));
			Collections.sort(appointments);
			ListIterator<Appointment> it = stackedAppointments.listIterator();
			while (it.hasNext())
			{
				if (it.next().occursOn(year, month, day, hour, minute))
				{
					it.remove();
				}
			}
		}
		else
		{
			desBox.setText("Does not exist");
		}
	}
	
	/**
	 * Prints the appoints of the current day in description text area
	 */
	public void printAppointment()
	{
		String dates = "";
		if (appointments.isEmpty())
		{
			allAppointments.setText(dates);
			return;
		}
		for (Appointment a : appointments)
		{
			if (a.dayOccursOn(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))) {
				dates += a.print(a) + "\n";
				allAppointments.setText(dates);
			}
			else
			{
				allAppointments.setText(dates);
			}
		}
	}
	
	/**
	 * Finds an appointment based on the year, month and day it occurs
	 */
	public void findAppointment()
	{
		if (!appointments.isEmpty())
		{
			int year, month, day;
			year = Integer.parseInt(inputYear.getText().trim());
			month = Integer.parseInt(inputMonth.getText().trim());
			month--;
			day = Integer.parseInt(inputDay.getText().trim());
			Calendar temp = new GregorianCalendar(year, month, 1);
			int max = temp.getActualMaximum(Calendar.DAY_OF_MONTH);
			int min = temp.getActualMinimum(Calendar.DAY_OF_MONTH);
		
			if (day > max || day < min)
			{
				desBox.setText("Numbers out of bounds");
				return;
			}
			
			if (month <= 11 && month >= 0)
			{
				for (Appointment a : appointments)
				{
					if (a.dayOccursOn(year, month, day))
					{
						calendar.set(year, month, day);
						currentDate.setText(sdf.format(calendar.getTime()));
					}
				}
			}
			else
			{
				desBox.setText("Numbers out of bounds");
			}
		}
	}
	
	/**
	 * Check if the month has changed to update calendar
	 * @param previous the previous date
	 */
	public void hasMonthChanged(Calendar previous)
	{
		int prevDateMonth = previous.get(Calendar.MONTH);
		int currentDateMonth = calendar.get(Calendar.MONTH);
		if (prevDateMonth != currentDateMonth)
		{
			gridCalendar.removeAll();
			gridCalendar.revalidate();
			gridCalendar.repaint();   
			setGridCalendarSubpanel(gridCalendar);
		}
		else
		{
			gridCalendar.removeAll();
			gridCalendar.revalidate();
			gridCalendar.repaint();   
			setGridCalendarSubpanel(gridCalendar);
		}
	}
	
	/**
	 * Fills in the textboxes once it finds a match contact in linked list
	 * @param parameter the string by which it search for the person
	 */
	public void fillInTextBoxes(String parameter)
	{
		if (parameter.equals("name"))
		{
			Person nameFound = contacts.findPersonName(inputFirstName.getText(), inputLastName.getText());
			if (nameFound != null)
			{
				inputLastName.setText(nameFound.getLastName());
				inputFirstName.setText(nameFound.getFirstName());
				inputTelephone.setText(nameFound.getTelephone());
				inputEmail.setText(nameFound.getEmail());
				inputAddress.setText(nameFound.getAddress());
			}
			else
			{
				desBox.setText("Does not exist");
			}
		}
		if (parameter.equals("email"))
		{
			Person emailFound = contacts.findPersonEmail(inputEmail.getText());
			if (emailFound != null)
			{
				inputLastName.setText(emailFound.getLastName());
				inputFirstName.setText(emailFound.getFirstName());
				inputTelephone.setText(emailFound.getTelephone());
				inputEmail.setText(emailFound.getEmail());
				inputAddress.setText(emailFound.getAddress());
			}
			else
			{
				desBox.setText("Does not exist");
			}
		}
		if (parameter.equals("telephone"))
		{
			Person telephoneFound = contacts.findPersonTelephone(inputTelephone.getText());
			if (telephoneFound != null)
			{
				inputLastName.setText(telephoneFound.getLastName());
				inputFirstName.setText(telephoneFound.getFirstName());
				inputTelephone.setText(telephoneFound.getTelephone());
				inputEmail.setText(telephoneFound.getEmail());
				inputAddress.setText(telephoneFound.getAddress());
			}
			else
			{
				desBox.setText("Does not exist");
			}
		}
	}
	
	/**
	 * This helper class determines what action to perform based on what button is clicked
	 * @author sarah.rostami
	 *
	 */
	class ClickListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) 
		{
			String command = event.getActionCommand();
			Object source = event.getSource();
			
			switch(command) {
			case "Next Date":
				Calendar previous = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				currentDate.setText(sdf.format(calendar.getTime()));
				currentMonth.setText(sdfMonth.format(calendar.getTime()));
				calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
				hasMonthChanged(previous);
				printAppointment();
				break;
			case "Prev Date":
				previous = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
				currentDate.setText(sdf.format(calendar.getTime()));
				currentMonth.setText(sdfMonth.format(calendar.getTime()));
				hasMonthChanged(previous);
				printAppointment();
				break;
			case "Create Appointment":
				createAppointment();
				printAppointment();
				break;
			case "Cancel Appointment":
				if (!appointments.isEmpty())
				{
					cancelAppointment();
					printAppointment();
				}
				break;
			case "Show Date":
				findAppointment();
				printAppointment();
				setGridCalendarSubpanel(gridCalendar);
				break;
			case "Clear":
				inputLastName.setText("");
				inputFirstName.setText("");
				inputTelephone.setText("");
				inputEmail.setText("");
				inputAddress.setText("");
				break;
			case "Find Contact": 
				if (!inputLastName.getText().isEmpty() && !inputFirstName.getText().isEmpty())
				{
					fillInTextBoxes("name");
				}
				else if (!inputEmail.getText().isEmpty())
				{
					fillInTextBoxes("email");
				}
				else if (!inputTelephone.getText().isEmpty())
				{
					fillInTextBoxes("telephone");
				}
				break;
			case "Recall Appointment":
				if (!stackedAppointments.isEmpty())
				{
					Appointment latest = stackedAppointments.peek();
					calendar.set(latest.getYear(), latest.getMonth(), latest.getDay()); 
					currentDate.setText(sdf.format(calendar.getTime()));
					currentMonth.setText(sdfMonth.format(calendar.getTime())); 
					inputHour.setText(Integer.toString(latest.getHour()));
					inputMinute.setText(Integer.toString(latest.getMinute()));
					desBox.setText(latest.getDescription());
					gridCalendar.removeAll();
					gridCalendar.revalidate();
					gridCalendar.repaint();   
					setGridCalendarSubpanel(gridCalendar);
				}
				printAppointment();
				break;
			}
			
			for (int i = 1; i <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++)
			{
				String num = Integer.toString(i);
				if (num.equals(command))
				{
					calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), i);		
					currentDate.setText(sdf.format(calendar.getTime()));
					currentMonth.setText(sdfMonth.format(calendar.getTime()));
					printAppointment();
					gridCalendar.removeAll();
					gridCalendar.revalidate();
					gridCalendar.repaint();   
					setGridCalendarSubpanel(gridCalendar);
					break;
				}
			} 
		}
	}
}
