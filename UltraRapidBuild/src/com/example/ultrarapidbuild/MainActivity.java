package com.example.ultrarapidbuild;

import java.io.IOException;

import org.json.JSONException;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class MainActivity extends ActionBarActivity {
	private Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		ImageView background = (ImageView) ((ActionBarActivity) context)
				.findViewById(R.id.bg);
		background.setAlpha((float) 0.5);

		GridView gridview = (GridView) findViewById(R.id.gridView1);
		gridview.setAdapter(new ImageAdapter(this));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
						try {
							Champion champion = new Champion(context);
							champion.goToChamp(v, position, id);
						} catch (JSONException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
			}
		});
		
		final Button aboutBtn = (Button) findViewById(R.id.button1);
		aboutBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				goToAboutPage(v);
			}
		});
	}

	public void goToAboutPage(View view) {
		setContentView(R.layout.activity_about);
		ImageView background = (ImageView) ((ActionBarActivity) context)
				.findViewById(R.id.bgabout);
		background.setAlpha((float) 0.5);
	}
	
	public void goToHomePage(View view) {
		setContentView(R.layout.activity_main);	
		ImageView background = (ImageView) ((ActionBarActivity) context)
				.findViewById(R.id.bg);
		background.setAlpha((float) 0.5);
		
		GridView gridview = (GridView) findViewById(R.id.gridView1);
		gridview.setAdapter(new ImageAdapter(this));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
						try {
							Champion champion = new Champion(context);
							champion.goToChamp(v, position, id);
						} catch (JSONException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
			}});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
