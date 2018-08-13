package com.github.zahileoo.gittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testMethod("Just test a lot!");
    }

    private void testMethod(String testText) {
        Toast.makeText(this, testText, Toast.LENGTH_SHORT).show();
    }
}
