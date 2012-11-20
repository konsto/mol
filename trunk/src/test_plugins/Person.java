package test_plugins;


public class Person 
{
	private String name;
	private String surname;
	
	public Person(String name, String surname)
	{
		this.name = name;
		this.surname = surname;
	}
	
	public Person()
	{
		this("Undefinned", "Undefinned");
	}
	
	public void setName(String name)
	{
		this.name  = name;
	}
	
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	
	public static String concatenate(String first, String second) {
	    return first + second;
	}

	public double test(int a) {
	    return Math.sqrt(a);
	}
	
	public String toString()
	{
		String result =  this.name + " " + this.surname + " "; 
		return result;
	}
	
	public String saySomething(String thingToSay)
	{
		String result = this.toString() + "said: \"" + thingToSay;
		return result;
	}
	
	public static int demoFunctionWithParams(String a)
	{
		return 5;
	}
	
	public static int demoFunctionWithoutParams()
	{
		return 6;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getSurname()
	{
		return this.surname;
	}
}
