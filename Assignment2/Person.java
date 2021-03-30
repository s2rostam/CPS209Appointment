/* Name: Sarah Rostami
 * Student Number: 500750485
 */
import java.util.Comparator;

/**
 * This class creates a person to correspond with appointments with a name, telephone, email and address
 * @author Sarah
 *
 */
public class Person implements Comparable<Person>
{
	private String lastName;
	private String firstName;
	private String telephone;
	private String address;
	private String email;
	
	/**
	 * The constructor for the person class
	 * @param pSurname the person's last name
	 * @param pFirstName the person's first name
	 * @param pTelephone the person's telephone number
	 * @param pAddress the person's Address
	 * @param pEmail the person's email
	 */
	public Person(String pSurname, String pFirstName, String pTelephone, String pAddress, String pEmail)
	{
		firstName = pFirstName;
		lastName = pSurname;
		telephone = pTelephone;
		address = pAddress;
		email = pEmail;
	}
	
	/**
	 * Get the last name of a person object
	 * @return the last name of the person
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Get the first name of a person object
	 * @return the first name of the person
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Get the telephone of a person object
	 * @return the telephone of the person
	 */
	public String getTelephone()
	{
		return telephone;
	}
	
	/**
	 * Get the address of a person object
	 * @return the address of the person
	 */
	public String getAddress()
	{
		return address;
	}
	
	/**
	 * Get the email of a person object
	 * @return the email of the person
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * Compares two person objects based on name
	 * @param the other person object to compare
	 */
	public int compareTo(Person other) 
	{	
		if ( this.lastName.equals(other.lastName))
		{
			return this.firstName.compareTo(other.firstName);
		}
		else 
		{
			return this.lastName.compareTo(other.lastName);
		}
		
	}
	
	/**
	 * Convert the person object into a string of information according to available information
	 */
	public String toString()
	{
		if (telephone.equals("") && email.equals(""))
		{
			return " WITH: " + firstName + " " + lastName;
		}
		else if (telephone.equals(""))
		{
			return " WITH: " + firstName + " " + lastName + " " + email;
		}
		else if (email.equals(""))
		{
			return " WITH: " + firstName + " " + lastName + " " + telephone;
		}
		else
		{
			return " WITH: " + firstName + " " + lastName + " " + telephone + " " + email;
		}
		
	}

}

/**
 * This class compares person's email to find if a contact exists
 *
 */
class CompareEmail implements Comparator<Person>
{
	/**
	 * Compares two person objects based on email
	 */
	public int compare(Person per1, Person per2) 
	{
		return per1.getEmail().compareTo(per2.getEmail());
	}
	
}

/**
 * This class compares person's telephone to find if a contact exists
 * @author Sarah
 *
 */
class CompareTelephone implements Comparator<Person>
{
	/**
	 * Compares two person objects based on telephone
	 */
	public int compare(Person per1, Person per2) 
	{
		return per1.getTelephone().compareTo(per2.getTelephone());
	}
	
}
