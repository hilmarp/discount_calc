package com.hilmarp.discountcalculator2;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	// Constants used when saving and restoring
	private static final String FULL_PRICE = "FULL_PRICE";
	private static final String DISCOUNT_AMOUNT = "DISCOUNT_AMOUNT";
	
	// 100%
	private static final int FULL_PRICE_PERCENT = 100;
	
	// Price without discount
	private double priceWithoutDiscount;
	
	// Discount amount
	private double discountAmount;
	
	// EditTexts
	EditText fullPriceET;
	EditText discountAmountET;
	
	// TextViews
	TextView priceWithDiscountTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Check if app just started, or if it is being restored
		if (savedInstanceState == null) {
			priceWithoutDiscount = 0.0;
			discountAmount = 0.0;
		} else {
			priceWithoutDiscount = savedInstanceState.getDouble(FULL_PRICE);
			discountAmount = savedInstanceState.getDouble(DISCOUNT_AMOUNT);
		}
		
		// Initialize EditTexts
		fullPriceET = (EditText) findViewById(R.id.fullPriceEditText);
		discountAmountET = (EditText) findViewById(R.id.discountAmountEditText);
		
		// Initialize TextViews
		priceWithDiscountTV = (TextView) findViewById(R.id.priceWithDiscountTextView);
		
		// Add change listener to full price change
		fullPriceET.addTextChangedListener(fullPriceListener);
		
		// Add change listener to discount amount change
		discountAmountET.addTextChangedListener(discountAmountListener);
	}
	
	private TextWatcher fullPriceListener = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			try {
				priceWithoutDiscount = Double.parseDouble(s.toString());
			} catch (NumberFormatException e) {
				priceWithoutDiscount = 0.0;
			}
			
			updatePriceWithDiscount();
		}
	};
	
	private TextWatcher discountAmountListener = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			try {
				discountAmount = Double.parseDouble(s.toString());
			} catch (NumberFormatException e) {
				discountAmount = 0.0;
			}
			
			updatePriceWithDiscount();
		}
	};
	
	// Update priceWithDiscount
	private void updatePriceWithDiscount() {
		// Price with discount added
		double priceWithDiscount;
		
		// % of regular price
		double salePercentToMultiplyWith = FULL_PRICE_PERCENT - discountAmount;
		
		// Final sale price
		priceWithDiscount = salePercentToMultiplyWith * priceWithoutDiscount;
		
		// Add to TextView on screen
		priceWithDiscountTV.setText(String.valueOf(priceWithDiscount));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
