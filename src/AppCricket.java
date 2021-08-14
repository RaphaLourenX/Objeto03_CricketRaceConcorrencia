import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
import java.util.concurrent.Semaphore;

public class AppCricket {
	public static int CRICKETNUMBER;
	public static int GOAL;
	public static TitleScreen titlescreen;
	public static  Semaphore semaphore = new Semaphore(1);
	
	public static void main(String[] args) {
		
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	TitleScreen titlescreen = new TitleScreen();
	titlescreen.showSpecs();
	
	System.out.println("\n \n Write the number of crickets to race:\n");
	
	try {
		CRICKETNUMBER = Integer.parseInt(br.readLine());
	} catch (NumberFormatException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
    System.out.println("Write the length of the race track:\n");
	
	try {
		GOAL = Integer.parseInt(br.readLine());
	} catch (NumberFormatException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	int add = 0;
	Cricket winner;
	boolean endRun = false;
	
	int[] winnersPodium = new int[CRICKETNUMBER + 1];
	int goalPodium = 1;
	
	Cricket[] crickets = new Cricket[CRICKETNUMBER];
	
	//NEW IMPLEMENTATION
	ArrayList<Cricket> podium = new ArrayList<>();
	
	for (int i = 0; i < crickets.length; i++) {
		crickets[i] = new Cricket(i, GOAL, podium, semaphore);
		crickets[i].start();
		try { Thread.sleep (1); } catch (InterruptedException ex) {}
	}
	
	for (int i = 0; i < crickets.length; i++) {
		try {
			crickets[i].join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	for(int i = 0; i < podium.size(); i++) 
	{
		System.out.print((i + 1) + "st Place: Cricket " + podium.get(i).id + " [TOTAL JUMPS:" + podium.get(i).jumpNumber + 
				" / DISTANCE: " + podium.get(i).totalDist +"cm]\n");
	}
	
	}
}
