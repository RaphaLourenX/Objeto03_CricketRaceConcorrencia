import java.util.ArrayList;
import java.io.*;
import java.util.concurrent.Semaphore;

public class AppCricket {
	public static int CRICKETNUMBER;
	public static int GOAL;
	public static TitleScreen titlescreen;
	public static  Semaphore semaphore = new Semaphore(1);
	public static boolean APPRANDOM = true;
	
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
	
	Cricket[] crickets = new Cricket[CRICKETNUMBER];
	CricketTeam[] teams = new CricketTeam[(CRICKETNUMBER/3) + 1];
	
	//NEW IMPLEMENTATION
	ArrayList<Cricket> podium = new ArrayList<Cricket>();
	int winner;
	int winnerTeam;
	
	//CREATE THE CRICKET TEAMS
	for (int i = 0; i < teams.length; i++) {
		teams[i] = new CricketTeam(i);
	}
	
	//CREATE THE CRICKETS AND PUT EVERY ONE IN A TEAM
	for (int i = 0; i < crickets.length; i++) {
		crickets[i] = new Cricket(i, GOAL, semaphore, teams[i/3], podium, APPRANDOM);
		System.out.println("Cricket " + i + " joined the Team " + teams[i/3].id + " [" + teams[i/3].teamName + "]");
	}
	
	//START EVERY CRICKET THREAD
	for (int i = 0; i < crickets.length; i++) {
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
	
	for(int t = 0; t < teams.length; t++) 
	{
		System.out.println("The Team " + teams[t].id + " jumped " + teams[t].teamJumps + " times with a total distance of " + teams[t].teamDistance + "\n");
	}
	
	winner = podium.get(0).id;
	winnerTeam = podium.get(0).getTeam();
	
	System.out.println("The winner team is the Team " + winnerTeam + " with the cricket" + winner + " being the first to cross the goal.");
	
	}
}
