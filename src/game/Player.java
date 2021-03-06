package game;

import render.GameScreen;

public class Player {
	public static int coinage = 100,health = 10;
	public static int killed = 0,killToWin = 0,level = 1,maxLevel = 10;


	
	
	//player health
	public static void loseHealth() {
		health--;
		countKill();
		GameScreen.gameoverSoundTrigger=true;
		//System.out.println("gameoversoundtrigger  true");
	}
	
	public static void countKill(){
		killed++;
		hasWon();
	}
	
	public static void getMoney(int mobID){
		coinage += Value.deathReward[mobID] ;
	}
	
	public  static void hasWon(){
		if(Player.killed == Player.killToWin){
			GameScreen.isWin = true;
			killed = 0;
			coinage = 200;
			health = 10; 
		}
	}
}
