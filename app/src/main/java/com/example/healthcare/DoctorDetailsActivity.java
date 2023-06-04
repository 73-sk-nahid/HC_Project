package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 =
            {
                    {"Doctor Name : Mahbubur Rahman", "Apollo Hospital, Dhaka", "Exp : 5yrs.", "Mobile No : 01900000000", "500", "19:00 - 22:00" },
                    {"Doctor Name : Tavvir Ahmed", "BRB Hospital, Dhaka", "Exp : 4yrs.", "Mobile No : 01700000000", "500", "19:00 - 22:00" },
                    {"Doctor Name : Susmita Parvin", "MAG Usmani Medical College, Sylhet", "Exp : 10yrs.", "Mobile No : 01800000000", "900", "19:00 - 22:00" },
                    {"Doctor Name : Rezaul Karim", "Tangail Medical College Tangail", "Exp : 8yrs.", "Mobile No : 01300000000", "600" , "19:00 - 22:00"},
                    {"Doctor Name : Jesmin Akter", "Al-Rafi Hospital, Jashor", "Exp : 15yrs.", "Mobile No : 01600000000", "1000" , "19:00 - 22:00"}

            };

    private String[][] doctor_details2 =
            {
                    {"Doctor Name : Mahbubur Rahman", "Hospital Address : Dhaka", "Exp : 5yrs.", "Mobile No : 01900000000", "500" },
                    {"Doctor Name : Tavvir Ahmed", "Hospital Address : Narsingdi", "Exp : 4yrs.", "Mobile No : 01700000000", "500" },
                    {"Doctor Name : Susmita Parvin", "Hospital Address : Rajshahi", "Exp : 10yrs.", "Mobile No : 01800000000", "900" },
                    {"Doctor Name : Rezaul Karim", "Hospital Address : Dhaka", "Exp : 8yrs.", "Mobile No : 01300000000", "600" },
                    {"Doctor Name : Jesmin Akter", "Hospital Address : Jashor", "Exp : 15yrs.", "Mobile No : 01600000000", "1000" }

            };

    private String[][] doctor_details3 =
            {
                    {"Doctor Name : Mahbubur Rahman", "Hospital Address : Dhaka", "Exp : 5yrs.", "Mobile No : 01900000000", "500" },
                    {"Doctor Name : Tavvir Ahmed", "Hospital Address : Narsingdi", "Exp : 4yrs.", "Mobile No : 01700000000", "500" },
                    {"Doctor Name : Susmita Parvin", "Hospital Address : Rajshahi", "Exp : 10yrs.", "Mobile No : 01800000000", "900" },
                    {"Doctor Name : Rezaul Karim", "Hospital Address : Dhaka", "Exp : 8yrs.", "Mobile No : 01300000000", "600" },
                    {"Doctor Name : Jesmin Akter", "Hospital Address : Jashor", "Exp : 15yrs.", "Mobile No : 01600000000", "1000" }

            };

    private String[][] doctor_details4 =
            {
                    {"Doctor Name : Mahbubur Rahman", "Hospital Address : Dhaka", "Exp : 5yrs.", "Mobile No : 01900000000", "500" },
                    {"Doctor Name : Tavvir Ahmed", "Hospital Address : Narsingdi", "Exp : 4yrs.", "Mobile No : 01700000000", "500" },
                    {"Doctor Name : Susmita Parvin", "Hospital Address : Rajshahi", "Exp : 10yrs.", "Mobile No : 01800000000", "900" },
                    {"Doctor Name : Rezaul Karim", "Hospital Address : Dhaka", "Exp : 8yrs.", "Mobile No : 01300000000", "600" },
                    {"Doctor Name : Jesmin Akter", "Hospital Address : Jashor", "Exp : 15yrs.", "Mobile No : 01600000000", "1000" }

            };

    private String[][] doctor_details5 =
            {
                    {"Doctor Name : Mahbubur Rahman", "Hospital Address : Dhaka", "Exp : 5yrs.", "Mobile No : 01900000000", "500" },
                    {"Doctor Name : Tavvir Ahmed", "Hospital Address : Narsingdi", "Exp : 4yrs.", "Mobile No : 01700000000", "500" },
                    {"Doctor Name : Susmita Parvin", "Hospital Address : Rajshahi", "Exp : 10yrs.", "Mobile No : 01800000000", "900" },
                    {"Doctor Name : Rezaul Karim", "Hospital Address : Dhaka", "Exp : 8yrs.", "Mobile No : 01300000000", "600" },
                    {"Doctor Name : Jesmin Akter", "Hospital Address : Jashor", "Exp : 15yrs.", "Mobile No : 01600000000", "1000" }

            };

    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_doctor_details);

        btn = findViewById(R.id.buttonDDBack);
        tv = findViewById(R.id.textViewDDTitle);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Physicians")==0)
            doctor_details = doctor_details1;
        else
        if (title.compareTo("Dietician")==0)
            doctor_details = doctor_details1;
        else
        if (title.compareTo("Dentist")==0)
            doctor_details = doctor_details1;
        else
        if (title.compareTo("Surgeon")==0)
            doctor_details = doctor_details1;
        else
            doctor_details = doctor_details1;


            btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

    list = new ArrayList();
    for (int i=0;i<doctor_details.length;i++){
        item = new HashMap<String,String>();
        item.put("line1", doctor_details[i][0]);
        item.put("line2", doctor_details[i][1]);
        item.put("line3", doctor_details[i][2]);
        item.put("line4", doctor_details[i][3]);
        item.put("line5", "Cons Fees:"+doctor_details[i][4]+"/-");
        item.put("line6", "Available Time: "+doctor_details[i][5]);
        list.add(item);
    }

    sa = new SimpleAdapter(this,list,
            R.layout.multi_lines,
            new String[]{"line1","line2","line3","line4","line5", "line6"},
            new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e, R.id.line_n}
            );

        ListView lst = findViewById(R.id.listviewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                it.putExtra("text6",doctor_details[i][5]);
                startActivity(it);

            }
        });

    }
}