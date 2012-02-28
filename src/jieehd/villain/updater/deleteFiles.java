package jieehd.villain.updater;

import java.io.File;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class deleteFiles extends PreferenceActivity {
	private Context mContext;
	
	public void checkPrefs() {
	    final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext.getApplicationContext());
	    Boolean clean = sp.getBoolean("clean_up", false);
	    
	    if (clean == true) {
	    	delete();
	    }else{
	    	
	    }
	}
	private void delete() {
	
	final long MAXFILEAGE = 8035200000L; // 3 month in milliseconds
	File dir = new File("/VillainROM/ROMs/");{
	File[] files = dir.listFiles();
	

	for (File f : files ) {
	   final Long lastmodified = f.lastModified();
	   if(lastmodified + MAXFILEAGE<System.currentTimeMillis()) {
	      f.delete();
	   }
	}
}

}}

