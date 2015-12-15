package game;

public class Player {
	public static int coinage = 2000,health = 10;
	public static int killed = 0,killToWin = 0,level = 1,maxLevel = 3;
	
	public Player() {

	}
	
	//player health
	public static void loseHealth() {
		health--;
		countKill();
		
	}
	
	public static void countKill(){
		killed++;
	}
	
	public static void getMoney(int mobID){
		coinage += Value.deathReward[mobID] ;
	}
}
