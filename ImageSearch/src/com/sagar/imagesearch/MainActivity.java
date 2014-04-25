package com.sagar.imagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MainActivity extends ActionBarActivity {

	EditText etQuery;
	GridView gvResults;
	Button btSearch;
	ImageResultArrayAdapter imageAdapter;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupViews();
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		//click small to bring full screen activity	
		gvResults.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,int position, long id) {
			Intent i = new Intent(getApplicationContext(),ImageDisplayActivity.class);
			ImageResult imageResult = imageResults.get(position);
			// insert key value in intent
			i.putExtra("result", imageResult);
			
			startActivity(i);
			}
		});
	}

	private void setupViews() {
		etQuery = (EditText)findViewById(R.id.etQuery);
		gvResults = (GridView)findViewById(R.id.gvResults);
		btSearch = (Button)findViewById(R.id.btnSearch);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	 public void onImageSearch(View v) {
	    	String query = etQuery.getText().toString();
	    	Toast.makeText(this, "Searching for " + query, Toast.LENGTH_SHORT).show();
	    	Log.d("DEBUG",query);
	    	
	    	AsyncHttpClient client = new AsyncHttpClient();

	    	
	    	client.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" + 
	    			"start=" + 0 + "&v=1.0&q=" + Uri.encode(query), 
	    			new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(JSONObject response) {
	    			JSONArray imageJsonResults = null;
	    			try {
	    				imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
	    				imageResults.clear();
	    				imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
	    				
	    				Log.d("DEBUG", imageResults.toString());
	    			} catch (JSONException e) {
	    				e.printStackTrace();
	    			}
	    		}
	    	});
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
