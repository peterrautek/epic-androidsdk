/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Peter\\workspaces\\epicgit\\epic-androidservice\\src\\org\\mobilesynergies\\android\\epic\\service\\interfaces\\IEpicServiceApplicationInterface.aidl
 */
package org.mobilesynergies.android.epic.service.interfaces;
/**
* The administration interface of the EpicService
*/
public interface IEpicServiceApplicationInterface extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.mobilesynergies.android.epic.service.interfaces.IEpicServiceApplicationInterface
{
private static final java.lang.String DESCRIPTOR = "org.mobilesynergies.android.epic.service.interfaces.IEpicServiceApplicationInterface";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.mobilesynergies.android.epic.service.interfaces.IEpicServiceApplicationInterface interface,
 * generating a proxy if needed.
 */
public static org.mobilesynergies.android.epic.service.interfaces.IEpicServiceApplicationInterface asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.mobilesynergies.android.epic.service.interfaces.IEpicServiceApplicationInterface))) {
return ((org.mobilesynergies.android.epic.service.interfaces.IEpicServiceApplicationInterface)iin);
}
return new org.mobilesynergies.android.epic.service.interfaces.IEpicServiceApplicationInterface.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getVersion:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getVersion();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_registerServiceStatusChangeCallback:
{
data.enforceInterface(DESCRIPTOR);
org.mobilesynergies.android.epic.service.interfaces.IServiceStatusChangeCallback _arg0;
_arg0 = org.mobilesynergies.android.epic.service.interfaces.IServiceStatusChangeCallback.Stub.asInterface(data.readStrongBinder());
this.registerServiceStatusChangeCallback(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_sendMessage:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
android.os.Bundle _arg2;
if ((0!=data.readInt())) {
_arg2 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg2 = null;
}
this.sendMessage(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_getState:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getState();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.mobilesynergies.android.epic.service.interfaces.IEpicServiceApplicationInterface
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
		* check version of service implementation
		*/
public int getVersion() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getVersion, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
		* register a callback that receives updates if the state of the service changes
		*/
public void registerServiceStatusChangeCallback(org.mobilesynergies.android.epic.service.interfaces.IServiceStatusChangeCallback callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerServiceStatusChangeCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
		* register a callback to receive messages
		* currently not implemented 
		
		* void registerMessageCallback(in String application, in IIncomingMessageCallback messageCallback);
		* 
		
		*
		* unregister a callback to receive messages
		* currently not implemented 
		*	
		void unregisterMessageCallback(in String application);
		*//**
		* send a message 
		*/
public void sendMessage(java.lang.String action, java.lang.String sessionid, android.os.Bundle data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(action);
_data.writeString(sessionid);
if ((data!=null)) {
_data.writeInt(1);
data.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_sendMessage, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
		* get the current state of the service
		*/
public int getState() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getState, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getVersion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_registerServiceStatusChangeCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_sendMessage = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
/**
		* check version of service implementation
		*/
public int getVersion() throws android.os.RemoteException;
/**
		* register a callback that receives updates if the state of the service changes
		*/
public void registerServiceStatusChangeCallback(org.mobilesynergies.android.epic.service.interfaces.IServiceStatusChangeCallback callback) throws android.os.RemoteException;
/**
		* register a callback to receive messages
		* currently not implemented 
		
		* void registerMessageCallback(in String application, in IIncomingMessageCallback messageCallback);
		* 
		
		*
		* unregister a callback to receive messages
		* currently not implemented 
		*	
		void unregisterMessageCallback(in String application);
		*//**
		* send a message 
		*/
public void sendMessage(java.lang.String action, java.lang.String sessionid, android.os.Bundle data) throws android.os.RemoteException;
/**
		* get the current state of the service
		*/
public int getState() throws android.os.RemoteException;
}
