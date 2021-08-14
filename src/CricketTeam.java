
public class CricketTeam {
	
	int id;
	String teamName;
	int[] members = new int[3];
	
	int teamJumps;
	int teamDistance;
	
	public CricketTeam(int id) {
		this.id = id;
		teamName = String.format("%x",(int)(Math.random()*1000000000)) + " - " +
				   String.format("%x",(int)(Math.random()*1000000000)) + " - " +
				   String.format("%x",(int)(Math.random()*1000000000));	
		
	}
	
}
