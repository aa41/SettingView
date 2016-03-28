package com.example.mydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SettingView settingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingView= (SettingView) findViewById(R.id.test);
        String[] textArrays={"111111111","2222222222222","33333333333"};
        int[] iconArrays={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        settingView.setSettingList(MainActivity.this,textArrays,iconArrays,R.mipmap.arrow);
        settingView.setOnItemClickListner(new SettingView.OnItemClickListner() {
            @Override
            public void clickListner(View v, int position) {
               switch (position){
                   case  0:
                       Toast.makeText(MainActivity.this,"0000000000000",Toast.LENGTH_SHORT).show();
                       break;
                   case  1:
                       Toast.makeText(MainActivity.this,"11111111111111111111",Toast.LENGTH_SHORT).show();
                       break;
                   case  2:
                       Toast.makeText(MainActivity.this,"222222222222222222222",Toast.LENGTH_SHORT).show();
                       break;
               }
            }
        });
    }
}
