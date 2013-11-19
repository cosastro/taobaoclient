package com.android.taobaoclient.activity;

import android.content.Context;
import android.widget.Toast;

public class Utilities {
	public static void showMessage(Context context, String messageString){
		Toast.makeText(context, messageString, Toast.LENGTH_LONG).show();
	}
}
