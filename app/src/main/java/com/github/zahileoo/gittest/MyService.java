package com.github.zahileoo.gittest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.github.zahileoo.aidl.IMyService;

/**
 * Binder 服务端
 * Created by zahi on 2019/3/15.
 */
public class MyService extends Service {

    private static final String TAG = "MyService";
    private static final String PACKAGE_SERVER = "com.example.test";

    private final IMyService.Stub mBinder = new IMyService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void test() throws RemoteException {

        }

        /**
         * 在这里可以做权限认证，return false意味着客户端的调用就会失败
         * 比如下面，只允许包名为com.example.test的客户端通过，其他apk将无法完成调用过程
         * @param code
         * @param data
         * @param reply
         * @param flags
         * @return
         * @throws RemoteException
         */
        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String packageName = null;
            String[] packages = MyService.this.getPackageManager().
                    getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            Log.d(TAG, "onTransact: " + packageName);
            if (!PACKAGE_SERVER.equals(packageName)) {
                return false;
            }
            return super.onTransact(code, data, reply, flags);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
