package com.example.ultrarapidbuild;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context mContext;
	
	// references to our images
	private Integer[] mThumbIds = { R.drawable.aatrox, R.drawable.ahri,
			R.drawable.akali, R.drawable.alistar, R.drawable.amumu,
			R.drawable.anivia, R.drawable.annie, R.drawable.ashe,
			R.drawable.azir, R.drawable.bard, R.drawable.blitzcrank,
			R.drawable.brand, R.drawable.braum, R.drawable.caitlyn,
			R.drawable.cassiopeia, R.drawable.chogath, R.drawable.corki,
			R.drawable.darius, R.drawable.diana, R.drawable.draven,
			R.drawable.drmundo, R.drawable.elise, R.drawable.evelynn,
			R.drawable.ezreal, R.drawable.fiddlesticks, R.drawable.fiora,
			R.drawable.fizz, R.drawable.galio, R.drawable.gangplank,
			R.drawable.garen, R.drawable.gnar, R.drawable.gragas,
			R.drawable.graves, R.drawable.hecarim, R.drawable.heimerdinger,
			R.drawable.irelia, R.drawable.janna, R.drawable.jarvaniv,
			R.drawable.jax, R.drawable.jayce, R.drawable.jinx,
			R.drawable.kalista, R.drawable.karma, R.drawable.karthus,
			R.drawable.kassadin, R.drawable.katarina, R.drawable.kayle,
			R.drawable.kennen, R.drawable.khazix, R.drawable.kogmaw,
			R.drawable.leblanc, R.drawable.leesin, R.drawable.leona,
			R.drawable.lissandra, R.drawable.lucian, R.drawable.lulu,
			R.drawable.lux, R.drawable.malphite, R.drawable.malzahar,
			R.drawable.maokai, R.drawable.masteryi, R.drawable.missfortune,
			R.drawable.mordekaiser, R.drawable.morgana, R.drawable.nami,
			R.drawable.nasus, R.drawable.nautilus, R.drawable.nidalee,
			R.drawable.nocturne, R.drawable.nunu, R.drawable.olaf,
			R.drawable.orianna, R.drawable.pantheon, R.drawable.poppy,
			R.drawable.quinn, R.drawable.rammus, R.drawable.reksai,
			R.drawable.renekton, R.drawable.rengar, R.drawable.riven,
			R.drawable.rumble, R.drawable.ryze, R.drawable.sejuani,
			R.drawable.shaco, R.drawable.shen, R.drawable.shyvana,
			R.drawable.singed, R.drawable.sion, R.drawable.sivir,
			R.drawable.skarner, R.drawable.sona, R.drawable.soraka,
			R.drawable.swain, R.drawable.syndra, R.drawable.talon,
			R.drawable.taric, R.drawable.teemo, R.drawable.thresh,
			R.drawable.tristana, R.drawable.trundle, R.drawable.tryndamere,
			R.drawable.twistedfate, R.drawable.twitch, R.drawable.udyr,
			R.drawable.urgot, R.drawable.varus, R.drawable.vayne,
			R.drawable.veigar, R.drawable.velkoz, R.drawable.vi,
			R.drawable.viktor, R.drawable.vladimir, R.drawable.volibear,
			R.drawable.warwick, R.drawable.wukong, R.drawable.xerath,
			R.drawable.xinzhao, R.drawable.yasuo, R.drawable.yorick,
			R.drawable.zac, R.drawable.zed, R.drawable.ziggs,
			R.drawable.zilean, R.drawable.zyra };

	public ImageAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		return mThumbIds.length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			// if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(mThumbIds[position]);
		return imageView;
	}

	public Integer[] getImages() {
		return mThumbIds;
	}
}
