package org.mobilesynergies.epic.sdk;


import org.mobilesynergies.android.epic.service.interfaces.IEpicServiceApplicationInterface;
import org.mobilesynergies.android.epic.service.interfaces.IServiceStatusChangeCallback;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * 
 * Abstract class that handles interaction with the epic service. 
 * Activities that are want to interact with the epic service can extend this Activity. 
 * @author Peter
 */

public abstract class ApplicationActivity extends Activity {
	private static String LOG_TAG = "EpicSDK"; 


	/** 
	 * The application interface to call methods of the {@link EpicService} 
	 */
	protected IEpicServiceApplicationInterface mEpicService = null;
	
	/** 
	 * True if the android.os.service (the EpicService) is bound successfully.
	 * This is a prerequisite to communicate with the epic service
	 */
	protected boolean mIsBound = false;
	
	/** 
	 * Called when the activity is first created. 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(!bindService()){
			Log.e(LOG_TAG, "couldn't bind to the epic-service");
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(mIsBound){
	        // Detach our existing connection.
	        unbindService(mServiceConnection);
	        mIsBound = false;
		}
	}

	/**
	 * Try to bind to the application interface of the service
	 */
	private boolean bindService() {

		if(mIsBound){
			return true;
		}
		Intent intent = new Intent("org.mobilesynergies.EPIC_SERVICE");
		if(bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)){
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * This method has to be implemented to get informed when the activity successfully connected to the application interface of the epic service.
	 * It is called when the service is ready to interact with the activity. 
	 */
	protected abstract void onConnected();

	/**
	 * This method has to be implemented to get informed when the connection to the application interface of the epic service is lost.
	 * It is called when the service crashed.
	 */
	protected abstract void onDisconnected();
	
	/**
	 * This method has to be implemented to get informed when the service connected to the epic network (i.e., it is ready to handle messages).
	 */
	protected abstract void onConnectedToEpicNetwork();
	
	/**
	 * A service connection object used to get informed about changes to the service connection
	 */
	private ServiceConnection mServiceConnection = new ServiceConnection() {
		

		public void onServiceConnected(ComponentName className, IBinder service) {
			// This is called when the connection with the service has been
			// established, giving us the service object we can use to
			// interact with the service.  We are communicating with our
			// service through an IDL interface, so get a client-side
			// representation of that from the raw service object.

			mEpicService = IEpicServiceApplicationInterface.Stub.asInterface(service);
			mIsBound = true;
			try {
				Log.d(LOG_TAG, "registerServiceStatusChangeCallback");
				mEpicService.registerServiceStatusChangeCallback(mServiceStatusChangeCallback);
				int state = mEpicService.getState();
				Log.d(LOG_TAG, "The state received in the app activity is "+state);

			} catch (RemoteException e) {
				e.printStackTrace();
			}
			onConnected();
		}

		public void onServiceDisconnected(ComponentName className) {
			// This is called when the connection with the service has been
			// unexpectedly disconnected -- that is, its process crashed.
			Log.d(LOG_TAG, "Service disconnected.");
			mEpicService = null;
			mIsBound=false;
			onDisconnected();
		}
	};
	
	private IServiceStatusChangeCallback.Stub mServiceStatusChangeCallback = new IServiceStatusChangeCallback.Stub(){

		@Override
		public void onServiceStatusChanged(int status)
				throws RemoteException {
			
			Log.d(LOG_TAG, "received new state: "+status);
			if(status==StateObject.EPICNETWORKCONNECTION) {
				onConnectedToEpicNetwork();
			} 
		}
		
	};

}
