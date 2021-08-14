import java.lang.Thread;
import java.util.ArrayList;

public class Cricket extends Thread implements Comparable{
	// [ATRIBUTOS]
	public int id;
	private int goal;
	private ArrayList<Cricket> podium;
	
	public Cricket(int id, int goal, ArrayList<Cricket> podium) 
	{
		this.id = id;
		this.goal = goal;
		this.podium = podium;
		System.out.println("Cricket" + id + " initialized.");
	}
	
	public int jumpNumber; //Total de Pulos que o Grilo deu
	public int jumpDist; //Distância que pulou no pulo atual
	public int totalDist; //Distância total percorrida
	public boolean checkGoal = false;
	
	public int randomJump() {return (int)(1 + Math.random() * 20);}
	
	
	public void doJump() {
		this.jumpNumber++;
		this.jumpDist = randomJump();
		this.totalDist += this.jumpDist;
		System.out.println("   [ " + this.currentThread().getName() + " ]\n" +
				"Cricket " + this.id + " jumped " + this.jumpDist + "cm. \n"
				+ "Cricket " + this.id + " total distance: " + this.totalDist+ "cm.\n"
				+ "Cricket " + this.id + " jumped " + this.jumpNumber + " times. \n");
		
	}
	
	@Override
	public void run() 
	{
		
		System.out.println("\nCricket" + id+ " is running.");
		
		while(totalDist < goal) 
		{
			doJump();
		}
		System.out.println("\n == Cricket" + id + " Completed the race with " + jumpNumber + " jumps. ==\n");
		podium.add(this);
	}


	@Override
	public int compareTo(Object c) {
		// TODO Auto-generated method stub
		int compareJumps = ((Cricket) c).jumpNumber;
		return this.jumpNumber - compareJumps;
	}

}
