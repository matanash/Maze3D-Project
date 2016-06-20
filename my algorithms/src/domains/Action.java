package domains;

/**
 * This Class represents Action to do in searchable problem
 * @author Matan Ashkenazi and Noee Cohen
 * @version - 1.0
 */
public class Action 
{
	private String description;
	private double cost;
	
	/**
	 * Action Constructor
	 * @param description - the action description to set
	 * @param cost - the action cost to set
	 */
	public Action(String description, double cost) 
	{		
		this.description = description;
		this.cost = cost;
	}
	
	/**
	 * This method get Action description
	 * @return Action description
	 */
	public String getDescription() 
	{
		return description;
	}
	
	/**
	 * This method set Action description
	 * @param description - the action description to set
	 */
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	/**
	 * This method get the Action cost
	 * @return Action cost
	 */
	public double getCost() 
	{
		return this.cost;
	}
	
	
	/**
	 * This method set the Action cost
	 * @param cost - The Action cost to set
	 */
	public void setCost(double cost) 
	{
		this.cost = cost;
	}
	
	@Override
	public int hashCode() 
	{
		return description.hashCode();
	}	
	
	
}
