package Fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.arianaantonio.astropix.CustomBaseAdapter;
import com.arianaantonio.astropix.DetailActivity;
import com.arianaantonio.astropix.ImageObject;
import com.arianaantonio.astropix.R;

public class SearchFragment extends Fragment {
	private static final String ARG_SECTION_NUMBER = "section_number";
	public static final String TAG = "SearchFragment.TAG";
	ArrayList<HashMap<String, String>> data1 = new ArrayList<HashMap<String, String>>();
	
	GridView gridView;
	List<ImageObject> imageItems;
	
	public static SearchFragment newInstance(int sectionNumber) {
		
		SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
   
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_search, container, false);
		Bundle bundle = getArguments();
		
		ArrayList<String> images = new ArrayList<String>();
		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<String> cameras = new ArrayList<String>();
		ArrayList<String> telescopes = new ArrayList<String>();
		ArrayList<String> websites = new ArrayList<String>();
		ArrayList<String> descriptions = new ArrayList<String>();
		ArrayList<String> users = new ArrayList<String>();
		
		if (bundle != null) {
			data1 = (ArrayList<HashMap<String, String>>)bundle.getSerializable("passed data");
			Log.i("Searchview", "Data: " +data1);
			
			for (int i = 0; i < data1.size(); i++) {
				String url = data1.get(i).get("url");
				if (url.equals("null")) {
					Toast toast = Toast.makeText(getActivity(), "No images found. Please try your search again.", Toast.LENGTH_SHORT);
					toast.show();
				} else {
					images.add(data1.get(i).get("url"));
					titles.add(data1.get(i).get("title"));
					cameras.add(data1.get(i).get("camera"));
					telescopes.add(data1.get(i).get("telescope"));
					websites.add(data1.get(i).get("website"));
					descriptions.add(data1.get(i).get("description"));
					users.add(data1.get(i).get("username"));
				}
				//Log.i("Gridview", "URL: " +text);
			}
			 
			gridView = (GridView) view.findViewById(R.id.grid);
			
			imageItems = new ArrayList<ImageObject>();
			imageItems.clear();
			
			
			for (int i = 0;i <images.size(); i++) {
				ImageObject item = new ImageObject(images.get(i), users.get(i), cameras.get(i), descriptions.get(i), telescopes.get(i), websites.get(i), titles.get(i));
				imageItems.add(item);
				
			}
			
			CustomBaseAdapter adapter = new CustomBaseAdapter(getActivity(), imageItems);
			adapter.notifyDataSetChanged();
			gridView.setAdapter(adapter);
			gridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					HashMap<String, ?> selectedListItem = data1.get(position);
					Log.i("Search Fragment", "Clicked: " +selectedListItem);
					Bundle bundle = new Bundle();
					bundle.putSerializable("Grid Images", data1);
					Intent intent = new Intent(getActivity(), DetailActivity.class);
					intent.putExtra("Grid bundle", bundle);
					intent.putExtra("Position", position);
					startActivity(intent);
					
				}
				
			});
		}
		return view;
	}

}
