package Fragments;

import java.util.HashMap;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arianaantonio.astropix.MainActivity;
import com.arianaantonio.astropix.R;

public class DetailFragment extends Fragment {
	private static final String ARG_SECTION_NUMBER = "section_number";
	public static final String TAG = "DetailFragment.TAG";
	
	public static DetailFragment newInstance(int sectionNumber) {
		
		
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public DetailFragment() {
 
    }
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
                ARG_SECTION_NUMBER));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View view = inflater.inflate(R.layout.fragment_detail, container, false);
		Bundle bundle = getArguments();
		
		HashMap<String, String> data = (HashMap<String, String>)bundle.getSerializable("clicked data");
		Log.i("Detail Fragment", "Passed data: " +data);
		
		return view;
	}
}
