package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCAdditionalInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener;


public class OrderDetailsActivity extends AppCompatActivity implements SSLCTransactionResponseListener{

    private String[][] order_details = {};

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    String order;
    Button btn;

    private SSLCommerzInitialization sslCommerzInitialization;
    private SSLCAdditionalInitializer additionalInitialization;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_order_details);

        btn = findViewById(R.id.buttonODBack);
        lst = findViewById(R.id.listViewOD);
        order = "Unpaid";

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this,HomeActivity.class));
            }
        });

        Database db = new Database(getApplicationContext(),"healthcare",null,1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        ArrayList dbData = db.getOrderData(username);

        order_details = new String[dbData.size()][];
        for (int i=0;i<order_details.length;i++){
            order_details[i] = new String[6];
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            order_details[i][0] = strData[0];
            order_details[i][1] = strData[1];//+" "+strData[3];
            if (strData[7].compareTo("medicine")==0){
                order_details[i][3] = "Est. Del: "+strData[4];
            }
            if (strData[7].compareTo("appointment")==0){
                order_details[i][3] = "App. Date: "+strData[4];
            }else {
                order_details[i][3] = "Est. del: "+strData[4]+" Time: "+strData[5];
            }
            order_details[i][2] = strData[6];
            order_details[i][4] = strData[7];
            order_details[i][5] = "Contact No: " +strData[2];
        }

        list = new ArrayList();
        for (int i=0;i<order_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", order_details[i][0]);
            item.put("line2", "Address: " +order_details[i][1]);
            item.put("line3", "Taka: "+order_details[i][2]);
            item.put("line4", order_details[i][3]);
            item.put("line6", "Order Type: " +order_details[i][4]);
            item.put("line5", order);
            item.put("line7",order_details[i][5]);
            list.add( item );
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5","line6","line7"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e,R.id.line_n,R.id.line_m});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String totalamount = order_details[i][2]; // Replace with total amount
                if (totalamount.isEmpty()) {
                    Toast.makeText(OrderDetailsActivity.this, "Payment Error", Toast.LENGTH_SHORT).show();

                } else {

                    sslSetUp(totalamount);
                }
            }
        });
    }

    private void sslSetUp(String amount) {
        sslCommerzInitialization = new SSLCommerzInitialization(
                "green64e3a7b92fabe",
                "green64e3a7b92fabe@ssl"

                ,
                Double.parseDouble(amount),
                SSLCCurrencyType.BDT,
                amount,
                "eshop",
                SSLCSdkType.TESTBOX
        );

        additionalInitialization = new SSLCAdditionalInitializer();
        additionalInitialization.setValueA("Value Option 1");
        additionalInitialization.setValueB("Value Option 2");
        additionalInitialization.setValueC("Value Option 3");
        additionalInitialization.setValueD("Value Option 4");

        IntegrateSSLCommerz.getInstance(OrderDetailsActivity.this)
                .addSSLCommerzInitialization(sslCommerzInitialization)
                .addAdditionalInitializer(additionalInitialization)
                .buildApiCall(OrderDetailsActivity.this);
    }

    @Override
    public void transactionSuccess(SSLCTransactionInfoModel sslcTransactionInfoModel) {
        order = "Paid";
        Toast.makeText(getApplicationContext(), "Payment success", Toast.LENGTH_SHORT).show();
        Log.i("DONE", "Payment Done");
    }

    @Override
    public void transactionFail(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closed(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}