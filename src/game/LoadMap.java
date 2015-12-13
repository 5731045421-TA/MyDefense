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
				Screen.killToWin = loadScanner.nextInt();
				Mob.mobHealth = loadScanner.nextInt();
				Screen.mobType = loadScanner.nextInt();
				for(int y = 0;y<Screen.room.block.length;y++){
					for(int x = 0;x<Screen.room.block[0].length;x++){
						Screen.room.block[y][x].groundID = loadScanner.nextInt();
					}
				}
				for(int y = 0;y<Screen.room.block.length;y++){
					for(int x = 0;x<Screen.room.block[0].length;x++){
						Screen.room.block[y][x].airID = loadScanner.nextInt();
					}
				}
			}
			loadScanner.close();
		} catch (Exception e) {
			throw new LoadMissionException(e);
		}
	}
}
