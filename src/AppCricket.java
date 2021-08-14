import java.util.ArrayList;
import java.io.*;
import java.util.Collections;

public class AppCricket {
	public static int CRICKETNUMBER;
	public static int GOAL;
	
	public static void main(String[] args) {
		
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
	System.out.println("              _______  _______ _________ _______  _        _______ _________\r\n"
			+ "             (  ____ \\(  ____ )\\__   __/(  ____ \\| \\    /\\(  ____ \\\\__   __/\r\n"
			+ "             | (    \\/| (    )|   ) (   | (    \\/|  \\  / /| (    \\/   ) (   \r\n"
			+ "             | |      | (____)|   | |   | |      |  (_/ / | (__       | |   \r\n"
			+ "             | |      |     __)   | |   | |      |   _ (  |  __)      | |   \r\n"
			+ "             | |      | (\\ (      | |   | |      |  ( \\ \\ | (         | |   \r\n"
			+ "             | (____/\\| ) \\ \\_____) (___| (____/\\|  /  \\ \\| (____/\\   | |   \r\n"
			+ "             (_______/|/   \\__/\\_______/(_______/|_/    \\/(_______/   )_(   \r\n"
			+ "                                                                            \r\n"
			+ "                            _______  _______  _______  _______ \r\n"
			+ "                           (  ____ )(  ___  )(  ____ \\(  ____ \\\r\n"
			+ "                           | (    )|| (   ) || (    \\/| (    \\/\r\n"
			+ "                           | (____)|| (___) || |      | (__    \r\n"
			+ "                           |     __)|  ___  || |      |  __)   \r\n"
			+ "                           | (\\ (   | (   ) || |      | (      \r\n"
			+ "                           | ) \\ \\__| )   ( || (____/\\| (____/\\\r\n"
			+ "                           |/   \\__/|/     \\|(_______/(_______/\r\n"
			+ "                                                               \r\n"
			+ "     _______ _________ _______           _        _______ _________ _______  _______ \r\n"
			+ "    (  ____ \\\\__   __/(       )|\\     /|( \\      (  ___  )\\__   __/(  ___  )(  ____ )\r\n"
			+ "    | (    \\/   ) (   | () () || )   ( || (      | (   ) |   ) (   | (   ) || (    )|\r\n"
			+ "    | (_____    | |   | || || || |   | || |      | (___) |   | |   | |   | || (____)|\r\n"
			+ "    (_____  )   | |   | |(_)| || |   | || |      |  ___  |   | |   | |   | ||     __)\r\n"
			+ "          ) |   | |   | |   | || |   | || |      | (   ) |   | |   | |   | || (\\ (   \r\n"
			+ "    /\\____) |___) (___| )   ( || (___) || (____/\\| )   ( |   | |   | (___) || ) \\ \\__\r\n"
			+ "    \\_______)\\_______/|/     \\|(_______)(_______/|/     \\|   )_(   (_______)|/   \\__/");
	
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
		crickets[i] = new Cricket(i, GOAL, podium);
		crickets[i].start();
	}
	
	for (int i = 0; i < crickets.length; i++) {
		try {
			crickets[i].join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Collections.sort(podium);
	
	for(int i = 0; i < podium.size(); i++) 
	{
		System.out.print((i + 1) + "st Place: Cricket " + podium.get(i).id + " [TOTAL JUMPS:" + podium.get(i).jumpNumber + 
				" / DISTANCE: " + podium.get(i).totalDist +"cm]\n");
	}
	
	}
}
