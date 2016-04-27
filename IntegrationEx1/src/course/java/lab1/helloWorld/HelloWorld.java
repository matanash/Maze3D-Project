package course.java.lab1.helloWorld;
import course.java.lab1.data.Worker;

public class HelloWorld {

	public static void main(String[] args) {
		System.out.println("Hello World");
		Worker [] workers = new Worker [5];
		
		for (int i=0;i<workers.length;i++)
		{
			workers[i] = new Worker ();
			workers[i].setAge(25);
		}
		
		for (int i=0;i<workers.length;i++)
		{
			System.out.println("Worker " + (i+1) + " age: "+ workers[i].getAge());
		}
		
	}
	
	

}
