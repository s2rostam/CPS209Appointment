/* Name: Sarah Rostami
 * Student Number: 500750485
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import javax.swing.JOptionPane;
/**
 *This class creates a LinkeList of person people called contacts and uses a file to load contacts into 
 *the linked list 
 * @author Sarah
 */
public class Contacts 
{
	private LinkedList<Person> contacts;
	
	/**
	 * Default constructor for contacts
	 */
	public Contacts()
	{
		contacts = new LinkedList<Person>();
	}
	
	/**
	 * This class finds a contact in the linked list with their first and last name
	 * @param firstName the first name of person to find
	 * @param lastName the last name of the person to find
	 * @return The matching person object if it exists or null if it doesn't
	 */
	public Person findPersonName(String firstName, String lastName)
	{
		if (!contacts.isEmpty())
		{
			ListIterator<Person> it = contacts.listIterator();
			
			Person look = new Person(lastName, firstName, null, null, null);
			while (it.hasNext())
			{
				Person match = it.next();
				
				int test = match.compareTo(look);
				if (test == 0)
				{
					return match;
				}
			}
		}
		return null;
	}
	
	/**
	 * This class finds a contact in the linked list with their email
	 * @param email The email to search the linked list with
	 * @return The matching person object if it exists or null if it does not
	 */
	public Person findPersonEmail(String email)
	{
		if (!contacts.isEmpty())
		{
			ListIterator<Person> it = contacts.listIterator();
	
			while (it.hasNext())
			{
				Person look = new Person(null, null, null, null, email);
				Person match = it.next();
				CompareEmail emails = new CompareEmail();
				int test = emails.compare(look, match);
				if (test == 0)
				{
					return match;
				}
			}
		}
		return null;
	}
	
	/**
	 * This class finds a contact in the linked list with their telephone
	 * @param telephone The telephone to search the linked list with
	 * @return The matching person object if it exists or null if it does not
	 */
	public Person findPersonTelephone(String telephone)
	{
		if (!contacts.isEmpty())
		{
			ListIterator<Person> it = contacts.listIterator();

			while (it.hasNext())
			{
				Person look = new Person(null, null, telephone, null, null);
				Person match = it.next();
				CompareTelephone telephones = new CompareTelephone();
				int test = telephones.compare(look, match);
				if (test == 0)
				{
					return match;
				}
			}
		}
		return null;
	}
	
	/**
	 * This method reads a contacts file and loads the data into the linked list 
	 */
	public void readContactsFile()
	{
				
		int numOfContacts = 0;
		int lineNum = 1;
		try 
		{
			File file = new File("contacts.txt");
			if (!file.exists())
			{
				contacts.clear();
				throw new FileNotFoundException("No contacts file exists");
			}
			
			Scanner sc = new Scanner(file);
			sc.useDelimiter("[^A-za-z0-9]+");
			
			int count = 0;
			if (count == 0 && sc.hasNextInt())
			{
				count++;
				numOfContacts = sc.nextInt();
				sc.nextLine();
				while(sc.hasNextLine() && count > 0)
				{
					count++;
					String value = sc.nextLine();
					if (value.equals("") && lineNum ==1 || value.equals("") && lineNum == 5)
					{
						contacts.clear();
						throw new Exception("Missing contact Info");
					}
					else
					{
						lineNum++;
						if (lineNum > 5)
						{
							lineNum = 0;
						}
					}
				}
				if ((count-1)%5 != 0)
				{
					contacts.clear();
					throw new Exception("Uneven contact entries");
				}
				if ((count-1)/5 != numOfContacts)
				{
					contacts.clear();
					throw new Exception("Integer value does not match number of contacts");
				}
			}
			else
			{
				contacts.clear();
				throw new Exception("First line is not a valid integer");
			}
			
			sc.close();
			sc = new Scanner(file);
			sc.nextLine();
			lineNum = 1; 
			String lastName, firstName, address, telephone, email;
			lastName = firstName = address = telephone = email = "";
			
			while (sc.hasNextLine())
			{			
				if (lineNum == 1)
				{
					lastName = sc.nextLine();
					lineNum++;
				}
				else if (lineNum == 2)
				{
					firstName = sc.nextLine();
					lineNum++;
				}
				else if (lineNum == 3)
				{
					address = sc.nextLine();
					lineNum++;
				}
				else if (lineNum == 4)
				{
					telephone = sc.nextLine();
					lineNum++;
				}
				else if (lineNum == 5)
				{
					email = sc.nextLine();
					contacts.add(new Person(lastName, firstName, telephone, address, email));
					lineNum = 1;
				}
			}
			Collections.sort(contacts);
			sc.close();
		} 
		catch (Exception e) 
		{	
			JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}