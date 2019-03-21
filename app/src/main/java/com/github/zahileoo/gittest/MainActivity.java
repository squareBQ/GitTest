package com.github.zahileoo.gittest;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_group);

        Group group = findViewById(R.id.ctt_group);
        group.setVisibility(View.GONE);

        testMethod("Just test a lot!");

        IntentService intentService = new IntentService("test") {
            @Override
            protected void onHandleIntent(@Nullable Intent intent) {

            }
        };
        // intentService.onStartCommand(new Intent(), IntentService.START_FLAG_RETRY, IntentService.START_STICKY);

        // getSharedPreferences("test", ContextWrapper.MODE_PRIVATE);

        //获取应用分配内存
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int memoryClass = activityManager.getMemoryClass();
        Log.d(TAG, String.format("应用分配内存：%1$sM", memoryClass));
    }

    private void testMethod(String testText) {
        Toast.makeText(this, testText, Toast.LENGTH_SHORT).show();
    }
}
