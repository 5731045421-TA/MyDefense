package game;
import java.util.Scanner;

import exception.LoadMissionException;

public class LoadMap {
	private Scanner loadScanner;

	public void loadSave(String loadPath)throws LoadMissionException{
		try {
			ClassLoader loader = LoadMap.class.getClassLoader();
			loadScanner = new Scanner(loader.getResourceAsStream(loadPath));
			while(loadScanner.hasNext()){
				GameScreen.killToWin = loadScanner.nextInt();
				Mob.mobHealth = loadScanner.nextInt();
				GameScreen.mobType = loadScanner.nextInt();
				for(int y = 0;y<GameScreen.room.block.length;y++){
					for(int x = 0;x<GameScreen.room.block[0].length;x++){
						GameScreen.room.block[y][x].groundID = loadScanner.nextInt();
					}
				}
				for(int y = 0;y<GameScreen.room.block.length;y++){
					for(int x = 0;x<GameScreen.room.block[0].length;x++){
						GameScreen.room.block[y][x].airID = loadScanner.nextInt();
					}
				}
			}
			loadScanner.close();
			Resource.soundTrack.play();
		} catch (Exception e) {
			throw new LoadMissionException(e);
		}
	}
}
