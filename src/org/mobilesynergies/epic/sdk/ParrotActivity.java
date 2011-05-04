package org.mobilesynergies.epic.sdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;


import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Browser;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;

/**
 * The parrot activity implements the org.epic.action.TalkBack action.
 * It talks back! Not very useful but demonstrating that everything works and is in place! 
 * 
 * @author Peter
 */

public class ParrotActivity extends ApplicationActivity{

	private static final String CLASS_TAG = ParrotActivity.class.getSimpleName();
	private static final String EPIC_ACTION = "org.epic.action.TalkBack";
	
	/** The session id identifies the caller.
	 */
	private String mSessionId = "";
	
	/** The data that is sent with the request
	 * 
	 */
	private Bundle mData = null;


	/** 
	 * Called when the activity is first created. 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.parrot);
		Intent callingIntent = getIntent();
		Uri uri = callingIntent.getData();
		mSessionId = uri.getLastPathSegment();
		mData = callingIntent.getExtras();
		
	}

	

	/** 
	 * This method is called when the activity connected to the running android-service.
	 */
	@Override
	protected void onConnected() {
		try {
			// we try to get information about the state of the service 
			int state = mEpicService.getState();
			if(state == StateObject.EPICNETWORKCONNECTION){
				// if it is connected to the epic network we will immediately send a message to the node that started this activity
				// if it is not connected to the epic service, we do nothing untill we get informed, that it is connected (see onConnectedToEpicNetwork)
				sendMessage();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/** 
	 * This method is called when the epic service just connected to the epic network
	 */
	@Override
	protected void onConnectedToEpicNetwork() {
		sendMessage();
	}


	@Override
	protected void onDisconnected() {
		
	}

	
	void sendMessage(){
		//here we could do something really sophisticated
		//like retrieve some information from the phones storage (think of contacts, browser history, etc.)
		//or get some state information about the phone (think of battery status, up-time, etc.)
		//or get some sensor measurements (think of camera, gps, compass, light sensor, accellerometer, etc.)
		//or get some information from a connected device (think of bluetooth connected devices, or the Android IOIO) 
		
		//instead of doing something really fancy - we simply respond (TalkBack) with the data we got from the caller 
		try {			  
			mEpicService.sendMessage(EPIC_ACTION, mSessionId, mData);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		// after some time we close the activity.
		// note that we also could close it immediately (however, in this case we want to show the user the ui for a few seconds)
		TimerTask t = new TimerTask(){

			@Override
			public void run() {
				handleFinish.sendEmptyMessage(0);
			}
			
		};
		Timer timer = new Timer();
		timer.schedule(t, 2500);
		
	}
	
	Handler handleFinish = new Handler(){
		public void handleMessage(android.os.Message msg) {
			finish();
		};
	};



}