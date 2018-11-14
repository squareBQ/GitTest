package com.github.zahileoo.livedatademo;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.zahileoo.livedatademo.observer.CustomLifecycleObserver;
import com.github.zahileoo.livedatademo.model.Person;
import com.github.zahileoo.livedatademo.viewmodel.TextViewModel;

public class MainActivity extends AppCompatActivity {

    private TextView tvText;
    private boolean toggle;
    private TextViewModel<Person> mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**添加LifeCycleObserver，可以非UI业务逻辑放到Observer*/
        getLifecycle().addObserver(new CustomLifecycleObserver());

        initViews();

        /**注册订阅*/
        LiveDataBus.get().getChannel("key_test", Boolean.class)
                .observe(this, aBoolean ->
                        Toast.makeText(MainActivity.this, "收到->" + aBoolean, Toast.LENGTH_SHORT).show());
    }

    private void initViews() {
        mViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(TextViewModel.class);
        tvText = findViewById(R.id.tv_text);
        mViewModel.getData().observe(this, o -> {
            if (o != null) {
                Person o1 = o;
                tvText.append("\n name:" + o1.getName() + ", age:" + o1.getAge() + ", 性别:" + (o1.isMale() ? "男" : "女"));
            }
        });

        Intent intent = new Intent();
        intent.putExtra("data", new Person("Linda", 24, false));
    }

    public void doPost(View view) {
        /**发送消息*/
        LiveDataBus.get().getChannel("key_test").setValue(toggle);
        toggle = !toggle;
    }

    public void doChange(View view) {
        mViewModel.setData(new Person("Linda", 24, false));
    }
}
