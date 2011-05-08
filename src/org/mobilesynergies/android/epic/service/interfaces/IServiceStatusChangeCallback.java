/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Peter\\workspaces\\epicgit\\epic-androidservice\\src\\org\\mobilesynergies\\android\\epic\\service\\interfaces\\IServiceStatusChangeCallback.aidl
 */
package org.mobilesynergies.android.epic.service.interfaces;
/**
* Interface that must be implemented to receive updates about state changes of the EpicService
*/
public interface IServiceStatusChangeCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.mobilesynergies.android.epic.service.interfaces.IServiceStatusChangeCallback
{
private static final java.lang.String DESCRIPTOR = "org.mobilesynergies.android.epic.service.interfaces.IServiceStatusChangeCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.mobilesynergies.android.epic.service.interfaces.IServiceStatusChangeCallback interface,
 * generating a proxy if needed.
 */
public static org.mobilesynergies.android.epic.service.interfaces.IServiceStatusChangeCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.mobilesynergies.android.epic.service.interfaces.IServiceStatusChangeCallback))) {
return ((org.mobilesynergies.android.epic.service.interfaces.IServiceStatusChangeCallback)iin);
}
return new org.mobilesynergies.android.epic.service.interfaces.IServiceStatusChangeCallback.Stub.Proxy(obj);
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
case TRANSACTION_onServiceStatusChanged:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.onServiceStatusChanged(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.mobilesynergies.android.epic.service.interfaces.IServiceStatusChangeCallback
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
		* Called by the service if the status changes
		*/
public void onServiceStatusChanged(int status) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(status);
mRemote.transact(Stub.TRANSACTION_onServiceStatusChanged, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onServiceStatusChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
/**
		* Called by the service if the status changes
		*/
public void onServiceStatusChanged(int status) throws android.os.RemoteException;
}
