package com.example.predator.lab2t1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoadAsyncTask.OnLoadingDone {

    TextView textView = null;
    EditText editText = null;
    LoadAsyncTask task = new LoadAsyncTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String address = editText.getText().toString();
                task.setAddress(address);
                task.setCallBack(MainActivity.this);
                task.execute();
            }
        });
        textView = findViewById(R.id.tw);
        editText = findViewById(R.id.et);
    }

    @Override
    public void loadingDone(String stringToPass) {
        textView.setText(stringToPass);
    }
}
