package com.github.zahileoo.gittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testMethod("Just test a lot!");

        /**测试git stash切换分支*/
        Log.d("git", "stash");
    }

    private void testMethod(String testText) {
        Toast.makeText(this, testText, Toast.LENGTH_SHORT).show();
    }
}
