package jieehd.villain.updater;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceClickListener;
 
public class villainsettings extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);
            // Get the custom preference
            Preference clean_up = (Preference) findPreference("clean_up");
            clean_up.setOnPreferenceClickListener(new OnPreferenceClickListener() {
                                    public boolean onPreferenceClick(Preference preference) {
                                            SharedPreferences customSharedPreference = getSharedPreferences("myCustomSharedPrefs", Activity.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = customSharedPreference.edit();
                                            editor.putString("clean_up","User would like to clean old files");
                                            editor.commit();
                                            return true;
                                    }

                            });
            
            Preference update_notify = (Preference) findPreference("update_notify");
            update_notify.setOnPreferenceClickListener(new OnPreferenceClickListener() {
                                    public boolean onPreferenceClick(Preference preference) {
                                            SharedPreferences customSharedPreference = getSharedPreferences("myCustomSharedPrefs", Activity.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = customSharedPreference.edit();
                                            editor.putString("update_notify","User would like push notifications of updates");
                                            editor.commit();
                                            return true;
                                    }

                            });
    }
    
}
