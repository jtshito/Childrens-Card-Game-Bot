package com.yugioh.bot;

import java.util.List;
import java.util.prefs.Preferences;

public class PrefController {
	private Preferences prefs = Preferences.userRoot().node(this.getClass().getName());
	String key1 = "deckX", key2 = "deckY", key3 = "yesX", key4 = "yesY", key5 = "flagX", key6 = "flagY", key7 = "borderX", key8 = "borderY", key9 = "flagRed",
			key10 = "flagGreen", key11 = "flagBlue", key12 = "borderRed", key13 = "borderGreen", key14 = "borderBlue";
	PrefController(){

	}
	public String getPref(String key) {
		return prefs.get(key, "0");
	}
	public void updatePrefs(List<String> coords) {
		prefs.put(key1, coords.remove(0));
		prefs.put(key2, coords.remove(0));
		prefs.put(key3, coords.remove(0));
		prefs.put(key4, coords.remove(0));
		prefs.put(key5, coords.remove(0));
		prefs.put(key6, coords.remove(0));
		prefs.put(key7, coords.remove(0));
		prefs.put(key8, coords.remove(0));
		prefs.put(key9, coords.remove(0));
		prefs.put(key10, coords.remove(0));
		prefs.put(key11, coords.remove(0));
		prefs.put(key12, coords.remove(0));
		prefs.put(key13, coords.remove(0));
		prefs.put(key14, coords.remove(0));
		
	}
}
