package com.example.ultrarapidbuild;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

public class LeagueData extends Activity {
	protected String choice;
	protected String name;

	private ProgressDialog dialog;
	private URL fullURL;
	private Context context;
	private Champion champ;
	private Long item0;
	private Long item1;
	private Long item2;
	private Long item3;
	private Long item4;
	private Long item5;
	private Long item6;
	private ArrayList<Long> items;

	public LeagueData(Champion champ, Context context) {
		this.context = context;
		this.champ = champ;
		items = new ArrayList<Long>();
		dialog = new ProgressDialog(context);
		fullURL = null;
		choice = null;
		name = null;
		item0 = (long) -1;
		item1 = (long) -1;
		item2 = (long) -1;
		item3 = (long) -1;
		item4 = (long) -1;
		item5 = (long) -1;
		item6 = (long) -1;
	}

	/*
	 * Get API key stored in another file
	 */
	private String getKey() {
		String key = null;
		try {
		    InputStream inputStream = context.getAssets().open("apikey.txt");
		    
		    StringBuffer stream = new StringBuffer();
		    byte[] b = new byte[4096];
		    for (int n; (n = inputStream.read(b)) != -1;)
		        stream.append(new String(b, 0, n));
		 
		    key = stream.toString().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return key;
	}
	
	/*
	 * Checks the Internet connection and starts loading screen if has Internet
	 * else a pop up will indicate no Internet connection
	 */
	public void checkNetwork() {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setMessage("Loading...");
			dialog.setIndeterminate(true);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
		} else {
			internetPopUp();
		}
	}

	/*
	 * Gets description of the champion
	 */
	public void getDesc(String champName) throws JSONException, IOException {
		String APIKey = getKey();
		StringBuilder uriBuilder = new StringBuilder(
				"//global.api.pvp.net/api/lol/static-data/na/v1.2/champion?champData=tags&api_key=");
		uriBuilder.append(APIKey);
		choice = "desc";
		name = champName;
		getData(uriBuilder);
	}

	/*
	 * Parses the json to get champion name and title
	 */
	public void updateDesc(String str) throws JSONException, ParseException,
			IOException {
		JSONObject json = null;
		try {
			json = new JSONObject(str);
			JSONObject jsonObj = json;
			JSONObject jsonData = (JSONObject) jsonObj.get("data");
			JSONObject jsonName = (JSONObject) jsonData.get(name);
			String champDesc = jsonName.getString("title");
			String champName = jsonName.getString("name");
			int champID = jsonName.getInt("id");

			champ.setDesc(champName, champDesc);
			updateMatches(champID);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Starts parsing each match data
	 */
	public void updateMatches(int champID) throws JSONException, IOException,
			ParseException {
		for (String data : Data.matchInfo) {
			JSONObject json = null;
			try {
				json = new JSONObject(data);
				updateMatchInfo(json, champID);
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		champ.chooseItems(items);
	}

	/*
	 * Parses each match to get item info if champion played won 
	 */
	public void updateMatchInfo(JSONObject json, int champID)
			throws JSONException, ParseException, IOException {
		try {
			if (json != null) {
				JSONArray jsonPartStat = json.getJSONArray("participants");
				for (int i = 0; i < jsonPartStat.length(); i++) {
					JSONObject jsonPart = jsonPartStat.getJSONObject(i);
					int jsonChampID = (int) jsonPart.get("championId");
					if (jsonChampID == champID) {
						JSONObject jsonStat = (JSONObject) jsonPart
								.get("stats");
						boolean win = (boolean) jsonStat.get("winner");
						if (win) {
							Long item0 = jsonStat.getLong("item0");
							items.add(item0);
							Long item1 = jsonStat.getLong("item1");
							items.add(item1);
							Long item2 = jsonStat.getLong("item2");
							items.add(item2);
							Long item3 = jsonStat.getLong("item3");
							items.add(item3);
							Long item4 = jsonStat.getLong("item4");
							items.add(item4);
							Long item5 = jsonStat.getLong("item5");
							items.add(item5);
							Long item6 = jsonStat.getLong("item6");
							items.add(item6);
						}
					}
				}
			} else {
				internetPopUp();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Gets the item data
	 */
	public void getItemData(ArrayList<Long> items) throws JSONException,
			IOException {
		item0 = items.get(0);
		item1 = items.get(1);
		item2 = items.get(2);
		item3 = items.get(3);
		item4 = items.get(4);
		item5 = items.get(5);
		item6 = items.get(6);
		
		String APIKey = getKey();
		StringBuilder uriBuilder = new StringBuilder(
				"//global.api.pvp.net/api/lol/static-data/na/v1.2/item?api_key=");
		uriBuilder.append(APIKey);
		choice = "iteminfo";
		getData(uriBuilder);
	}

	/*
	 * Parses the json to get the item info
	 */
	public void updateItems(String str) {
		JSONObject json = null;
		try {
			json = new JSONObject(str);
			ArrayList<JSONObject> jsonObjs = new ArrayList<JSONObject>();
			JSONObject jsonObj = json;
			JSONObject jsonData = (JSONObject) jsonObj.get("data");
			JSONObject jsonItem0 = (JSONObject) jsonData.get(item0
					.toString());
			jsonObjs.add(jsonItem0);
			JSONObject jsonItem1 = (JSONObject) jsonData.get(item1
					.toString());
			jsonObjs.add(jsonItem1);
			JSONObject jsonItem2 = (JSONObject) jsonData.get(item2
					.toString());
			jsonObjs.add(jsonItem2);
			JSONObject jsonItem3 = (JSONObject) jsonData.get(item3
					.toString());
			jsonObjs.add(jsonItem3);
			JSONObject jsonItem4 = (JSONObject) jsonData.get(item4
					.toString());
			jsonObjs.add(jsonItem4);
			JSONObject jsonItem5 = (JSONObject) jsonData.get(item5
					.toString());
			jsonObjs.add(jsonItem5);
			JSONObject jsonItem6 = (JSONObject) jsonData.get(item6
					.toString());
			jsonObjs.add(jsonItem6);
			int count = 0;
			for (JSONObject jsonObject : jsonObjs) {
				String itemName = jsonObject.getString("name");
				String itemDesc = jsonObject.getString("description");
				champ.setItems(itemName, itemDesc, count);
				count++;
				
				if (count == 6) {
					dialog.dismiss();
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Pop-up to tell user that there is no Internet connection
	 */
	public void internetPopUp() {
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setTitle("Internet Connection Error");
		alert.setMessage("Double check your internet connection.");
		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		AlertDialog alertDiag = alert.create();
		alertDiag.show();
	}

	/*
	 * Starts getting data by starting a new asynctask connection
	 */
	public void getData(StringBuilder uriBuilder) throws JSONException,
			IOException {
		fullURL = new URL("https:" + uriBuilder.toString());
		new Connection().execute(fullURL);
	}

	/*
	 * Private connection class to get the api calls
	 */
	private class Connection extends AsyncTask<URL, Void, String> {

		@Override
		protected String doInBackground(URL... urls) {
			HttpURLConnection connection = null;
			StringBuilder builder = null;
			InputStream inputStream = null;

			try {
				URL urlIn = urls[0];
				connection = (HttpURLConnection) urlIn.openConnection();
				String line;
				builder = new StringBuilder();

				try {
					inputStream = connection.getInputStream();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} if (inputStream == null) {
					throw new Exception("InputStream is Empty");
				}

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return builder.toString();
		}
		
		@Override
		protected void onPostExecute(String result) {
			try {
				if (choice == "desc") {
					updateDesc(result);
				} else if (choice == "iteminfo") {
					updateItems(result);
				}
			} catch (JSONException | IOException | ParseException e) {
				e.printStackTrace();
			}
		}
	}
}
