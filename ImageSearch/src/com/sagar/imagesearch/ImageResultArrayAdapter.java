package com.sagar.imagesearch;

import java.util.List;

import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {

	public ImageResultArrayAdapter(Context context, List<ImageResult> images) {
		super(context, R.layout.item_image_result, images);
		
	}

	
	//its the translator takes an item n converts into view
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//remove super
		//return super.getView(position, convertView, parent);
		
		ImageResult imageInfo = this.getItem(position);
		SmartImageView ivImage;
		
		// convertview is existsing view which we will translate. 
		//if there isnt any convertview we have to create a new version of subitem
		if(convertView == null)
		{
			//layoutInflator is component of framewk which takes a file and converts into a inmemory object view 
			LayoutInflater inflator = LayoutInflater.from(getContext());
			ivImage= (SmartImageView) inflator.inflate(R.layout.item_image_result, parent,false);
		}else // existing imageView
		{
			ivImage = (SmartImageView)convertView;
			//clear the data by changing color to white
			ivImage.setImageResource(android.R.color.transparent);
		}
		ivImage.setImageUrl(imageInfo.getThumbUrl());
		return ivImage;
	}


}
