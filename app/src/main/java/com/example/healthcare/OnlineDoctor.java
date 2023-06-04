package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class OnlineDoctor extends AppCompatActivity {
    private String[][] health_details =
            {
                    {"Dr. Azad Hossain", "Apollo Hospital, Dhaka", "01723429868", "", "Click to call","24/7"},
                    {"Dr. Sabbir Ahmed", "BRB Hospital, Dhaka", "01941853038", "", "Click to call","24/7"},
                    {"Dr. Azad Hossain", "Apollo Hospital, Dhaka", "01723429868", "", "Click to call","24/7"},
                    {"Dr. Sabbir Ahmed", "BRB Hospital, Dhaka", "01941853038", "", "Click to call","24/7"},
                    {"Dr. Azad Hossain", "Apollo Hospital, Dhaka", "01723429868", "", "Click to call","24/7"},
            };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnBack;
    ListView lst;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_doctor);

        btnBack = findViewById(R.id.buttonB);
        lst = findViewById(R.id.listViewHA);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnlineDoctor.this, HomeActivity.class));
            }
        });
        list = new ArrayList();
        for (int i=0;i<health_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", health_details[i][0]);
            item.put("line2", health_details[i][1]);
            item.put("line3", "Contact Number: "+health_details[i][2]);
            item.put("line4", health_details[i][3]);
            item.put("line5", "Available time: "+health_details[i][5]);
            item.put("line6", health_details[i][4]);
            list.add( item );
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line6","line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e, R.id.line_n});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String phoneNumber = health_details[i][2]; // Replace with the desired phone number
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(dialIntent);
            }
        });
    }
}