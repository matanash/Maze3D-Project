package domains;

/**
 * This class represents State in searchable problem
 * @author MatanA
 */
public class State implements Comparable<State> 
{
	private String description;
	private double cost;
	private State cameFromState;
	private boolean visited;
	
	/**
	 * This method get the state description
	 * @return State description
	 */
	public String getDescription() 
	{
		return this.description;
	}
	
	/**
	 * This method set the state description
	 * @param description - description to set
	 */
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	/**
	 * This method get the state cost
	 * @return State cost
	 */
	public double getCost() 
	{
		return this.cost;
	}
	
	/**
	 * This method set the state cost
	 * @param cost - cost to set in state
	 */
	public void setCost(double cost) 
	{
		this.cost = cost;
	}
	
	/**
	 * This method get the came from state
	 * @return the cameFromState state
	 */
	public State getCameFrom() 
	{
		return this.cameFromState;
	}
	
	/**
	 * This method set the came from state
	 * @param cameFromState - came from state to set
	 */
	public void setCameFrom(State cameFromState) {
		this.cameFromState = cameFromState;
	}
	/**
	 * This method check if state isVisited
	 * @return true or false
	 */
	public boolean isVisited() 
	{
		return this.visited;
	}
	
	/**
	 * This method set true or false to visited flag of state
	 * @param visited flag
	 */
	public void setVisited(boolean visited) 
	{
		this.visited = visited;
	}
	
	@Override
	public int compareTo(State state) 
	{
		return (int)(this.cost - state.cost);
	}
	@Override
	public int hashCode() 
	{
		return description.hashCode();
	}
	@Override
	public boolean equals(Object arg) 
	{
		State state = (State)arg;
		return description.equals(state.description);
	}
	@Override
	public String toString()
	{
		String str = "State Description: " + this.description ;
		if (this.cost!=0.0)
			str+= ",State Cost: " + this.cost;
		return str;
	}
	
}
