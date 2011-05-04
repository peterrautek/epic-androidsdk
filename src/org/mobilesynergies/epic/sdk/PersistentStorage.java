package org.mobilesynergies.epic.sdk;


import android.content.Context;
import android.content.SharedPreferences;

public final class PersistentStorage {
 
	
	public static int getNotificationId(Context context) {
		SharedPreferences store = context.getSharedPreferences("notificationStore", 0);
		int id = store.getInt("notificationId", 0);
		return id;
	}

	
	public static void setNotificationId(Context context, int id) {
		SharedPreferences store = context.getSharedPreferences("notificationStore", 0);
		SharedPreferences.Editor editor = store.edit();
		editor.putInt("notificationId", id);
		editor.commit();
	}
}