package com.example.ultrarapidbuild;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Champion {
	
	private Context context;
	private String champName;
	private LeagueData leagueData;
	private ArrayList<Long> itemsNeeded;

	public Champion(Context context) {
		this.context = context;
		leagueData = new LeagueData(this, context);
		itemsNeeded = new ArrayList<Long>();
		champName = null;
	}

	/*
	 * Sets the new layout to the champion display layout
	 */
	public void goToChamp(View v, int position, long id) throws JSONException,
			IOException {
		((ActionBarActivity) context).setContentView(R.layout.activity_champ);;
		ImageView background = (ImageView) ((ActionBarActivity) context)
				.findViewById(R.id.champbg);
		background.setAlpha((float) 0.5);
		update(v, position, id);
	}

	/*
	 * Sets the champion picture and starts getting information
	 * about champion name and item infos
	 */
	public void update(View v, int position, long id) throws JSONException,
			IOException {
		leagueData.checkNetwork();
		ImageAdapter imgAdapter = new ImageAdapter(context);
		Integer[] champList = imgAdapter.getImages();

		ImageView image = (ImageView) ((ActionBarActivity) context)
				.findViewById(R.id.champPic);
		image.setBackgroundResource(champList[position]);

		champName = getChampInfo(position);
		try {
			leagueData.getDesc(champName);
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/*
	 * Changes the description text
	 */
	public void setDesc(String champName, String champDesc) {
		TextView name = (TextView) ((ActionBarActivity) context)
				.findViewById(R.id.champName);
		TextView name2 = (TextView) ((ActionBarActivity) context)
				.findViewById(R.id.champName2);
		name.setText(champName);
		name2.setText(champName);
		TextView desc = (TextView) ((ActionBarActivity) context)
				.findViewById(R.id.champDesc);
		desc.setText(champDesc);
	}

	/*
	 * Calculates the usage counts for the items
	 */
	public HashMap<Long, Integer> topSevenItems(ArrayList<Long> items) {
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		for (Long item : items) {
			if (!map.containsKey(item)) {
				map.put(item, 1);
			} else {
				Integer count = map.get(item);
				if (count != null) {
					count++;
					map.put(item, count);
				}
			}
		}
		return map;
	}

	/*
	 * Chooses the top seven most used items
	 */
	public void chooseItems(ArrayList<Long> items) throws JSONException, IOException {
		HashMap<Long, Integer> map = topSevenItems(items);
		ArrayList<Long> itemsNeeded = new ArrayList<Long>();
		while (itemsNeeded.size() != 7) {
			Long tempKey = (long) 0;
			Integer tempValue = 0;
			for (HashMap.Entry<Long, Integer> entry : map.entrySet()) {
				Long key = entry.getKey();
				Integer value = entry.getValue();
				if (value > tempValue) {
					tempValue = value;
					tempKey = key;
				}
			}
			if (tempKey != (long) 0) {
				itemsNeeded.add(tempKey);
			}
			map.remove(tempKey);
		}
		this.itemsNeeded = itemsNeeded;		
		leagueData.getItemData(itemsNeeded);
	}
	
	/* 
	 * Sets the items pictures and description in the layout
	 */
	public void setItems(String name, String desc, int count) {
		String idItemPic = "@id/itemPic" + count;		
		String drawItemPic = "@drawable/a" + itemsNeeded.get(count);
		int idItemPicInt = context.getResources().getIdentifier(idItemPic, null, context.getPackageName());
		int drawItemPicInt = context.getResources().getIdentifier(drawItemPic, null, context.getPackageName());
		ImageView itemImg = (ImageView) ((ActionBarActivity) context)
				.findViewById(idItemPicInt);
		itemImg.setBackgroundResource(drawItemPicInt);

		String idItemName = "@id/itemName" + count;
		int idItemNameInt = context.getResources().getIdentifier(idItemName, null, context.getPackageName());
		TextView itemName = (TextView) ((ActionBarActivity) context)
				.findViewById(idItemNameInt);
		itemName.setText(name);
		
		String idItemDesc = "@id/itemDesc" + count;
		int idItemDescInt = context.getResources().getIdentifier(idItemDesc, null, context.getPackageName());
		TextView itemDesc = (TextView) ((ActionBarActivity) context)
				.findViewById(idItemDescInt);
		itemDesc.setText(Html.fromHtml(desc));
	}

	/*
	 * Converts the champion grid numbers into champion names
	 */
	public String getChampInfo(int integer) {
		String champName = null;

		switch (integer) {
		case 0:
			champName = "Aatrox";
			break;
		case 1:
			champName = "Ahri";
			break;
		case 2:
			champName = "Akali";
			break;
		case 3:
			champName = "Alistar";
			break;
		case 4:
			champName = "Amumu";
			break;
		case 5:
			champName = "Anivia";
			break;
		case 6:
			champName = "Annie";
			break;
		case 7:
			champName = "Ashe";
			break;
		case 8:
			champName = "Azir";
			break;
		case 9:
			champName = "Bard";
			break;
		case 10:
			champName = "Blitzcrank";
			break;
		case 11:
			champName = "Brand";
			break;
		case 12:
			champName = "Braum";
			break;
		case 13:
			champName = "Caitlyn";
			break;
		case 14:
			champName = "Cassiopeia";
			break;
		case 15:
			champName = "Chogath";
			break;
		case 16:
			champName = "Corki";
			break;
		case 17:
			champName = "Darius";
			break;
		case 18:
			champName = "Diana";
			break;
		case 19:
			champName = "Draven";
			break;
		case 20:
			champName = "DrMundo";
			break;
		case 21:
			champName = "Elise";
			break;
		case 22:
			champName = "Evelynn";
			break;
		case 23:
			champName = "Ezreal";
			break;
		case 24:
			champName = "FiddleSticks";
			break;
		case 25:
			champName = "Fiora";
			break;
		case 26:
			champName = "Fizz";
			break;
		case 27:
			champName = "Galio";
			break;
		case 28:
			champName = "Gangplank";
			break;
		case 29:
			champName = "Garen";
			break;
		case 30:
			champName = "Gnar";
			break;
		case 31:
			champName = "Gragas";
			break;
		case 32:
			champName = "Graves";
			break;
		case 33:
			champName = "Hecarim";
			break;
		case 34:
			champName = "Heimerdinger";
			break;
		case 35:
			champName = "Irelia";
			break;
		case 36:
			champName = "Janna";
			break;
		case 37:
			champName = "JarvanIV";
			break;
		case 38:
			champName = "Jax";
			break;
		case 39:
			champName = "Jayce";
			break;
		case 40:
			champName = "Jinx";
			break;
		case 41:
			champName = "Kalista";
			break;
		case 42:
			champName = "Karma";
			break;
		case 43:
			champName = "Karthus";
			break;
		case 44:
			champName = "Kassadin";
			break;
		case 45:
			champName = "Katarina";
			break;
		case 46:
			champName = "Kayle";
			break;
		case 47:
			champName = "Kennen";
			break;
		case 48:
			champName = "Khazix";
			break;
		case 49:
			champName = "Kogmaw";
			break;
		case 50:
			champName = "Leblanc";
			break;
		case 51:
			champName = "LeeSin";
			break;
		case 52:
			champName = "Leona";
			break;
		case 53:
			champName = "Lissandra";
			break;
		case 54:
			champName = "Lucian";
			break;
		case 55:
			champName = "Lulu";
			break;
		case 56:
			champName = "Lux";
			break;
		case 57:
			champName = "Malphite";
			break;
		case 58:
			champName = "Malzahar";
			break;
		case 59:
			champName = "Maokai";
			break;
		case 60:
			champName = "MasterYi";
			break;
		case 61:
			champName = "MissFortune";
			break;
		case 62:
			champName = "Mordekaiser";
			break;
		case 63:
			champName = "Morgana";
			break;
		case 64:
			champName = "Nami";
			break;
		case 65:
			champName = "Nasus";
			break;
		case 66:
			champName = "Nautilus";
			break;
		case 67:
			champName = "Nidalee";
			break;
		case 68:
			champName = "Nocturne";
			break;
		case 69:
			champName = "Nunu";
			break;
		case 70:
			champName = "Olaf";
			break;
		case 71:
			champName = "Orianna";
			break;
		case 72:
			champName = "Pantheon";
			break;
		case 73:
			champName = "Poppy";
			break;
		case 74:
			champName = "Quinn";
			break;
		case 75:
			champName = "Rammus";
			break;
		case 76:
			champName = "RekSai";
			break;
		case 77:
			champName = "Renekton";
			break;
		case 78:
			champName = "Rengar";
			break;
		case 79:
			champName = "Riven";
			break;
		case 80:
			champName = "Rumble";
			break;
		case 81:
			champName = "Ryze";
			break;
		case 82:
			champName = "Sejuani";
			break;
		case 83:
			champName = "Shaco";
			break;
		case 84:
			champName = "Shen";
			break;
		case 85:
			champName = "Shyvana";
			break;
		case 86:
			champName = "Singed";
			break;
		case 87:
			champName = "Sion";
			break;
		case 88:
			champName = "Sivir";
			break;
		case 89:
			champName = "Skarner";
			break;
		case 90:
			champName = "Sona";
			break;
		case 91:
			champName = "Soraka";
			break;
		case 92:
			champName = "Swain";
			break;
		case 93:
			champName = "Syndra";
			break;
		case 94:
			champName = "Talon";
			break;
		case 95:
			champName = "Taric";
			break;
		case 96:
			champName = "Teemo";
			break;
		case 97:
			champName = "Thresh";
			break;
		case 98:
			champName = "Tristana";
			break;
		case 99:
			champName = "Trundle";
			break;
		case 100:
			champName = "Tryndamere";
			break;
		case 101:
			champName = "TwistedFate";
			break;
		case 102:
			champName = "Twitch";
			break;
		case 103:
			champName = "Udyr";
			break;
		case 104:
			champName = "Urgot";
			break;
		case 105:
			champName = "Varus";
			break;
		case 106:
			champName = "Vayne";
			break;
		case 107:
			champName = "Veigar";
			break;
		case 108:
			champName = "Velkoz";
			break;
		case 109:
			champName = "Vi";
			break;
		case 110:
			champName = "Viktor";
			break;
		case 111:
			champName = "Vladimir";
			break;
		case 112:
			champName = "Volibear";
			break;
		case 113:
			champName = "Warwick";
			break;
		case 114:
			champName = "MonkeyKing";
			break;
		case 115:
			champName = "Xerath";
			break;
		case 116:
			champName = "XinZhao";
			break;
		case 117:
			champName = "Yasuo";
			break;
		case 118:
			champName = "Yorick";
			break;
		case 119:
			champName = "Zac";
			break;
		case 120:
			champName = "Zed";
			break;
		case 121:
			champName = "Ziggs";
			break;
		case 122:
			champName = "Zilean";
			break;
		case 123:
			champName = "Zyra";
			break;
		default: 
			champName = null;
			break;
		}
		return champName;
	}
}