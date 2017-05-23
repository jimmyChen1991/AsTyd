package com.hhyg.TyClosing.ui.view;

import com.hhyg.TyClosing.R;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class BaseFlashView extends RelativeLayout{
	protected ImageView mImageView;
	protected TextView mBrandName;
	protected TextView mName;
	protected ImageView mNoStock;
	protected TextView mActiviteIndicator;
	protected RelativeLayout mPriceLayout,mImgLayout;
	protected RelativeLayout.LayoutParams mImgParams,mBrandNameParams,mNameParams,mPricePairParams,mNoStockImgParams,mImgLayoutParams,mActiviteIndicatorParams;
	public BaseFlashView(Context context) {
		super(context);
		initImgLayout(context);
		initBrandName(context);
		initName(context);
		initPriceParam(context);
		initActiviteIndicator(context);
	}
	private void initActiviteIndicator(Context context) {
		mActiviteIndicator = new TextView(context);
		mActiviteIndicator.setTextColor(Color.WHITE);
		mActiviteIndicator.setBackgroundResource(R.drawable.rect_activebg);
		mActiviteIndicatorParams = new RelativeLayout.LayoutParams(100,RelativeLayout.LayoutParams.WRAP_CONTENT);
		mActiviteIndicatorParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		mActiviteIndicator.setTextSize(12);
		mActiviteIndicator.setGravity(Gravity.CENTER);
		mActiviteIndicator.setLayoutParams(mActiviteIndicatorParams);
		mActiviteIndicator.setVisibility(View.GONE);
		mImgLayout.addView(mActiviteIndicator);
	}
	private void initNoStock(Context context) {
		mNoStock = new ImageView(context);
		mNoStockImgParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		mNoStockImgParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		mNoStock.setLayoutParams(mNoStockImgParams);
		mNoStock.setBackgroundResource(R.drawable.allshop_nostock);
		mNoStock.setVisibility(View.GONE);
		mImgLayout.addView(mNoStock);
	}
	public void setNoStockImgVisible(){
		mNoStock.setVisibility(View.VISIBLE);
	}
	public ImageView getImagewView(){
		return mImageView;
	}
	public void setBrandName(String text){
		mBrandName.setText(text);
	}
	public void setName(String text){
		String name = "";
		if(text.length()>10){
			name = text.substring(0, 10)+"...";
		}else{
			name = text;
		}
		mName.setText(name);
	}
	private void initImgLayout(Context context){
		mImgLayout = new RelativeLayout(context);
		mImgLayoutParams = new RelativeLayout.LayoutParams(240,240);
		mImgLayoutParams.setMargins(0, 20, 0, 0);
		mImgLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		mImgLayout.setId(100);
		mImgLayout.setLayoutParams(mImgLayoutParams);
		initImgView(context);
		initNoStock(context);
		addView(mImgLayout);
	}
	private void initImgView(Context context){
		mImageView = new ImageView(context);
		mImgParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		mImageView.setLayoutParams(mImgParams);
		mImgLayout.addView(mImageView);
	}
	private void initBrandName(Context context){
		mBrandName = new TextView(context);
		mBrandName.setTextColor(Color.rgb(102, 102, 102));
		mBrandName.setTextSize(12);
		mBrandName.setId(101);
		mBrandNameParams =  new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		mBrandNameParams.addRule(RelativeLayout.BELOW, 100);
		mBrandNameParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		mBrandName.setLayoutParams(mBrandNameParams);
		addView(mBrandName);
	}
	private void initName(Context context){
		mName = new TextView(context);
		mName.setTextColor(Color.rgb(102, 102, 102));
		mName.setTextSize(12);
		mName.setId(102);
		mNameParams =  new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		mNameParams.addRule(RelativeLayout.BELOW, 101);
		mNameParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		mName.setLayoutParams(mNameParams);
		addView(mName);
	}
	private void initPriceParam(Context context){
		mPriceLayout = new RelativeLayout(context);
		mPricePairParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		mPricePairParams.addRule(RelativeLayout.BELOW, 102);
		mPricePairParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		mPriceLayout.setLayoutParams(mPricePairParams);
		addView(mPriceLayout);
	}
}
