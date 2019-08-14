package com.github.zahileoo.gittest;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.BaseKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.zahileoo.widget.signview.SignCalendarView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

        et = findViewById(R.id.et);

        SignCalendarView scvSign = findViewById(R.id.scv);
        scvSign.showWeekTitle(true);
        scvSign.setIsRetroactiveSignStatus(false);
        scvSign.setDataAndDate("2019-02-18", null);
    }

    private void testMethod(String testText) {
        Toast.makeText(this, testText, Toast.LENGTH_SHORT).show();
    }

    public void button1(View view) {
        et.setKeyListener(null);
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "input not available", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void button2(View view) {
        et.setKeyListener(new BaseKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_CLASS_NUMBER;
            }
        });
        et.setOnClickListener(null);
    }
}
