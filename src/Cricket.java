import java.lang.Thread;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Cricket extends Thread {
	// [ATRIBUTOS]
	public int id;
	private int goal;
	private static Semaphore semaphore;
	private CricketTeam team;
	public int getTeam(){return this.team.id;}
	ArrayList<Cricket> podium;
	
	
	public Cricket(int id, int goal, Semaphore semaphore, CricketTeam team, ArrayList<Cricket> podium) 
	{
		this.id = id;
		this.goal = goal;
		this.semaphore = semaphore;
		this.team = team;
		this.podium = podium;
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
		
		this.team.teamJumps++;
		this.team.teamDistance += this.jumpDist;
		
	}
	
	private void processor() {
		//NON CRITICAL REGION
	    try {
	    	//CRITICAL REGION
	        semaphore.acquire();
	        doJump();
	        semaphore.release();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }    
	}
	
    private void waitFor() {
    	//NON CRITICAL REGION
        try {
        	//CRITICAL REGION
            Thread.sleep(100 + id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
	public void victoryCheck() {
		//NON CRITICAL REGION
	        try {
	        	//CRITICAL REGION
	            semaphore.acquire();
	            if (totalDist >= goal) {
		    		System.out.println("\n == Cricket" + id + " Completed the race with " + jumpNumber + " jumps. ==\n");
		    		podium.add(this);
		    		semaphore.release();
	            }
	            semaphore.release();
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	@Override
	public void run() {
		 while(totalDist < goal) {
			processor();
			victoryCheck();
			waitFor();
		}
	}

}
