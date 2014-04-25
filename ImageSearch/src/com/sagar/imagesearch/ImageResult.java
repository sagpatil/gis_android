package com.sagar.imagesearch;




import java.io.Serializable;
import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




//serialize for passing through intents
public class ImageResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3655930718215155243L;
	private String fullUrl;
	private String tbUrl;
	
	//constructor to define a new object from JSONobject 
	public ImageResult(JSONObject json){
		try
		{
			this.fullUrl= json.getString("url");
			this.tbUrl=json.getString("tbUrl");
			
		}catch (JSONException e)
		{
			this.fullUrl=null;
			this.tbUrl=null;
		}
	}
	
	public String getFullUrl()
	{
		return fullUrl;
	}
	public String getThumbUrl()
	{
		return tbUrl;
	}
	public String toString(){
		return this.tbUrl;
	}

	
	//convert JSONArray to imageResults array by converting one by one
	public static ArrayList<ImageResult> fromJSONArray(
			JSONArray imageJsonResults) {
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		
		for (int i=0;i<imageJsonResults.length();i++)
		{
			try {
				//get a single json object, create a new ImageResultObject by our defined constructor
				// and add it to the array
				results.add(new ImageResult(imageJsonResults.getJSONObject(i)));
			} catch (JSONException e) {e.printStackTrace();}
		}
		
		return results;
	}
	
}
