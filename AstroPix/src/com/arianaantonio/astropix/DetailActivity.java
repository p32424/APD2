package com.arianaantonio.astropix;

import java.util.ArrayList;
import java.util.HashMap;

import Fragments.DetailFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class DetailActivity extends FragmentActivity {
	private static int NUM_PAGES;
	private ViewPager viewPager;
	private PagerAdapter pagerAdapter;
	ArrayList<HashMap<String, String>> data1 = new ArrayList<HashMap<String, String>>();
	int clickedPosition;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle arg0) {
		
		super.onCreate(arg0);
		setContentView(R.layout.viewpager);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("Grid bundle");
		
		data1 = (ArrayList<HashMap<String, String>>)bundle.getSerializable("Grid Images");
		NUM_PAGES = data1.size();
		clickedPosition = intent.getIntExtra("Position", 0); 

		//Log.i("Detail Activity", "Clicked image: " +data1.get(clickedPosition));
		
		Log.i("Detail Activity", "Position: " +clickedPosition);
		viewPager= (ViewPager) findViewById(R.id.pager);
		pagerAdapter = new ScreenPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);
		viewPager.setCurrentItem(clickedPosition );  
	
		 
	}

	private class ScreenPagerAdapter extends FragmentStatePagerAdapter {
		
		public ScreenPagerAdapter(FragmentManager manager) {
			super(manager);
		}

		@Override
		public Fragment getItem(int position) {
			//viewPager.setCurrentItem(clickedPosition); 
			DetailFragment fragment = new DetailFragment();
			Log.i("Detail Activity", "getItem position: " +position);
			Bundle bundle2 = new Bundle();
			bundle2.putSerializable("clicked data", data1.get(position));
			fragment.setArguments(bundle2);
			return fragment;  
		}

		@Override
		public int getCount() {
			
			return NUM_PAGES; 
		}
	}

}
