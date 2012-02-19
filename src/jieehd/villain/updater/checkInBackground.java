package jieehd.villain.updater;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class checkInBackground extends BroadcastReceiver {

	 NotificationManager nm;
		HttpClient client;
		JSONObject json;
		JSONObject device;
		final static String URL = "http://dl.dropbox.com/u/44265003/update.json";
		boolean available = true;
		
		
	    public JSONObject getVersion() 
	    		throws ClientProtocolException, IOException, JSONException{
	    	StringBuilder url = new StringBuilder(URL);
	    	HttpGet get = new HttpGet(url.toString());
	    	HttpResponse r = client.execute(get);
	    	int status = r.getStatusLine().getStatusCode();
	    	if (status == 200){
	    		HttpEntity e = r.getEntity();
	    		String data = EntityUtils.toString(e);
	    		JSONObject stream = new JSONObject(data);
	    		JSONObject rom = stream.getJSONObject("villain-roms");
	    		return rom;
	    	}else{
	    		return null;
	    	}
	    }
	    
	    
	    public class Display {
	    	public String mRom;
	    	public String mChange;
	    	public String mUrl;
	    	public String mBuild;
	    	
	    	public Display (String rom, String changelog, String downurl, String build) {
	    		mRom = rom;
	    		mChange = changelog;
	    		mUrl = downurl;
	    		mBuild = build;
	    	
	    	
	    	}
	    }
	 
	 
	 public class Read extends AsyncTask<String, Integer, Display> {
	    	

			@Override
			protected Display doInBackground(String... params) {
				String buildDevice = android.os.Build.DEVICE.toUpperCase();
				// TODO Auto-generated method stub
				try {
					if (buildDevice.equals("PYRAMID")) {
					json = getVersion();
					String rom = json.getJSONObject("device").getJSONArray("PYRAMID").getJSONObject(0).getString("version");
					String changelog = json.getJSONObject("device").getJSONArray("PYRAMID").getJSONObject(0).getString("changelog");
					String downurl = json.getJSONObject("device").getJSONArray("PYRAMID").getJSONObject(0).getString("url");
					String build = json.getJSONObject("device").getJSONArray("PYRAMID").getJSONObject(0).getString("rom");
					return new Display(rom, changelog, downurl, build);
					} else if (buildDevice.equals("MAGURO")) {
						json = getVersion();
						String rom = json.getJSONObject("device").getJSONArray("MAGURO").getJSONObject(0).getString("version");
						String changelog = json.getJSONObject("device").getJSONArray("MAGURO").getJSONObject(0).getString("changelog");
						String downurl = json.getJSONObject("device").getJSONArray("MAGURO").getJSONObject(0).getString("url");
						String build = json.getJSONObject("device").getJSONArray("MAGURO").getJSONObject(0).getString("rom");
						return new Display(rom, changelog, downurl, build);
					} else if (buildDevice.equals("I9100")) {
						json = getVersion();
						String rom = json.getJSONObject("device").getJSONArray("I9100").getJSONObject(0).getString("version");
						String changelog = json.getJSONObject("device").getJSONArray("I9100").getJSONObject(0).getString("changelog");
						String downurl = json.getJSONObject("device").getJSONArray("I9100").getJSONObject(0).getString("url");
						String build = json.getJSONObject("device").getJSONArray("I9100").getJSONObject(0).getString("rom");
						return new Display(rom, changelog, downurl, build);
					} else if (buildDevice.equals("PASSION")) {
						json = getVersion();
						String rom = json.getJSONObject("device").getJSONArray("PASSION").getJSONObject(0).getString("version");
						String changelog = json.getJSONObject("device").getJSONArray("PASSION").getJSONObject(0).getString("changelog");
						String downurl = json.getJSONObject("device").getJSONArray("PASSION").getJSONObject(0).getString("url");
						String build = json.getJSONObject("device").getJSONArray("PASSION").getJSONObject(0).getString("rom");
						return new Display(rom, changelog, downurl, build);
					} else if (buildDevice.equals("GENERIC")) {
						json = getVersion();
						String rom = json.getJSONObject("device").getJSONArray("GENERIC").getJSONObject(0).getString("version");
						String changelog = json.getJSONObject("device").getJSONArray("GENERIC").getJSONObject(0).getString("changelog");
						String downurl = json.getJSONObject("device").getJSONArray("GENERIC").getJSONObject(0).getString("url");
						String build = json.getJSONObject("device").getJSONArray("GENERIC").getJSONObject(0).getString("rom");
						return new Display(rom, changelog, downurl, build);
					} else if (buildDevice.equals(null)) {
						String rom = "Could not retrieve device information";
						String changelog = "Could not retrieve information from server";
						String downurl = "Could not retrieve information from server";
						String build = "Could not retrieve information from server";
						return new Display(rom, changelog, downurl, build);}
							
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return null;
				}
			
			@Override
			public void onPostExecute(final Display result) {
				String buildVersion = android.os.Build.ID;
						if (buildVersion.equals(result.mRom)) {
							available = false;
						}else{
							available = true;
						}
			;}
	 }
	 


	 @Override
	 public void onReceive(Context context, Intent intent) {
	  if (available == true) {
		  nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		  CharSequence from = "Villain Updater";
		  CharSequence message = "New Updates!";
		  PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(), 0);
		  Notification notif = new Notification(R.drawable.updates, "New updates!", (3 * AlarmManager.INTERVAL_DAY));
		  notif.setLatestEventInfo(context, from, message, contentIntent);
		  nm.notify(1, notif);
	  } else {

	  }

}
}

	
