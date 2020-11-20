package MapEditor;

import java.util.ArrayList;

import Level.Map;
import Maps.BossLevel;
import Maps.TitleScreenMap;

public class EditorMaps {
	public static ArrayList<String> getMapNames() {
		return new ArrayList<String>() {
			{

				add("TitleScreen");
				add("BossLevel");
			}
		};
	}

	public static Map getMapByName(String mapName) {
		switch (mapName) {

		case "BossLevel":
			return new BossLevel();
		case "TitleScreen":
			return new TitleScreenMap();
		default:
			throw new RuntimeException("Unrecognized map name");
		}
	}
}
