package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages =
            {
                    {"Uprise-03 1000IU Capsule", "", "", "", "50"},
                    {"HealthVit Chromium Picolinate 200mcg Capsule", "", "", "", "305"},
                    {"Vitamin B Complex Capsule", "", "", "", "450"},
                    {"Inlife Vitamin E Wheat Germ Oil Capsule", "", "", "", "535"},
                    {"Dolo 650 Tablet", "", "", "", "30"},
                    {"Crocin 650 Advance Tablet", "", "", "", "50"},
                    {"Feronia -XT Tablet", "", "", "", "130"},

            };
private String[] package_details =
        {
            "Building and keeping the bones &teeth strong.\n"+
            "Reducing Fatigue/stress and muscular pains.\n"+
            "Boosting immunity and increasing resistance against infection.",
            "Help a chromium deficiency, control blood sugar levels, lower cholesterol, or lose weight.",
            "Provides relief from vitamin B deficiencies.\n"+
            "Help in formation of red blood cells.\n"+
            "Maintains health nervous system.",
            "It promotes health as well as skin benefits.\n"+
            "Help reduce skin blemish and pigmentation.\n"+
            "It act as safeguard the skin from the harsh UVA UVB sun ray.",
            "It is used to relieve pain and reduce fever.\n"+
            "It is used to treat many conditions such as headaches, body aches, toothaches, and the common cold.",
            "effective relief from tension headache pain, migraine pain, toothache, cold and flu symptoms, muscle aches, arthritis and osteoarthritis.",
            "It is used to treat anemia (lack of blood) that occurs mainly due to poor diet, poor absorption of food, or increased folate use in the body (in pregnancy)."

        };

       HashMap<String,String> item;
       ArrayList list;
       SimpleAdapter sa;
       ListView lst;
       Button btnBack, btnGoToCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.listviewBM);
        btnGoToCart = findViewById(R.id.buttonBMGoToCard);
        btnBack = findViewById(R.id.buttonBMBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class));

            }
        });

        list = new ArrayList();
        for(int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost"+packages[i][4]+"/-");
            item.put("line6", "");
            list.add( item );

        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "Line5","line6"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e, R.id.line_n});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });
    }
}