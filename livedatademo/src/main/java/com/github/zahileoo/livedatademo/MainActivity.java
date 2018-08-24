package com.github.zahileoo.livedatademo;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**注册订阅*/
        LiveDataBus.get().getChannel("key_test", Boolean.class)
                .observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        Toast.makeText(MainActivity.this, "收到->" + aBoolean, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void doPost(View view) {
        /**发送消息*/
        LiveDataBus.get().getChannel("key_test").setValue(toggle);
        toggle = !toggle;
    }
}
