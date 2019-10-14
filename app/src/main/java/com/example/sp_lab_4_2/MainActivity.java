package com.example.sp_lab_4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyThread.MyInterface {

    TextView textView;
    Button button;
    int counter;
    ArrayList<MyThread> threads = new ArrayList<MyThread>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.start_button);
        button.setOnClickListener(this);
        textView = findViewById(R.id.text_view);
        textView.append("Ok lets start...");
    }


    @Override
    public void onClick(View v) {
        createThread();
    }

    @Override
    public void updateStatus(final int progress,final int id) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(progress < 100){
                    textView.append("\nThread " + id + " on Progress: " + progress + "%");
                }
                if (progress == 100) {
                    textView.append("\nThread " + id +  " On_Complete");
                }
            }
        });
    }

    public void createThread(){
        MyThread thread = new MyThread(this, counter);
        threads.add(thread);
        thread.start();
        textView.append("\nNew thread created with id: " + counter);
        counter++;
    }
}
