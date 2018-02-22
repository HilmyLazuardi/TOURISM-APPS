package com.example.firmanvsly.login;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.firmanvsly.login.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Pesan extends AppCompatActivity {

    ProgressDialog pDialog;
    Button btn_pesan,btn_total;
    EditText nama;
    EditText jum;
    TextView total;
    CheckBox wisata1, wisata2, wisata3;


    int success;
    ConnectivityManager conMgr;

    private String url = Server.URL + "create.php";

    private static final String TAG = Pesan.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesan);


        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        nama = (EditText) findViewById(R.id.txtNama);
        jum = (EditText) findViewById(R.id.txtJum);
        wisata1 = (CheckBox) findViewById(R.id.checkSong);
        wisata2 = (CheckBox) findViewById(R.id.checkBia);
        wisata3 = (CheckBox) findViewById(R.id.checkMut);
        total = (TextView) findViewById(R.id.txtTot);
        btn_pesan = (Button) findViewById(R.id.btn_pesan);
        btn_total = (Button) findViewById(R.id.btnTot);

        btn_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wisa1 = 20000;
                int wisa2 = 480000;
                int wisa3 = 35000;
                int tottal = 0;
                String juml = jum.getText().toString();
                int jumla = Integer.parseInt(juml);
                if(wisata1.isChecked() && wisata2.isChecked() && wisata3.isChecked()){
                    tottal=(wisa1+wisa2+wisa3)*jumla;
                }else if(wisata1.isChecked() && wisata2.isChecked()){
                    tottal=(wisa1+wisa2)*jumla;
                }else if(wisata1.isChecked() && wisata3.isChecked()){
                    tottal=(wisa1+wisa3)*jumla;
                }else if(wisata2.isChecked() && wisata3.isChecked()){
                    tottal=(wisa2+wisa3)*jumla;
                }else if(wisata1.isChecked()){
                    tottal=wisa1*jumla;
                }else if(wisata2.isChecked()){
                    tottal=wisa2*jumla;
                }else if(wisata3.isChecked()){
                    tottal=wisa3*jumla;
                }
                else {
                    Toast.makeText(getApplicationContext(), "isi Checkbox",
                            Toast.LENGTH_LONG).show();
                }
                total.setText(kursIndonesia.format(tottal));
            }
        });
        btn_pesan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int wisa1 = 20000;
                int wisa2 = 480000;
                int wisa3 = 35000;
                int totaal = 0;
                String nm = nama.getText().toString();
                String juml = jum.getText().toString();
                int jumla = Integer.parseInt(juml);
                String wisataa= "";
                if(wisata1.isChecked() && wisata2.isChecked() && wisata3.isChecked()){
                    totaal=(wisa1+wisa2+wisa3)*jumla;
                    wisataa="Karangsong, Pulau Biawak, Mutiara Bangsa";
                }else if(wisata1.isChecked() && wisata2.isChecked()){
                    totaal=(wisa1+wisa2)*jumla;
                    wisataa="Karangsong, Pulau Biawak";
                }else if(wisata1.isChecked() && wisata3.isChecked()){
                    totaal=(wisa1+wisa3)*jumla;
                    wisataa="Karangsong, Mutiara Bangsa";
                }else if(wisata2.isChecked() && wisata3.isChecked()){
                    totaal=(wisa2+wisa3)*jumla;
                    wisataa="Pulau Biawak, Mutiara Bangsa";
                }else if(wisata1.isChecked()){
                    totaal=wisa1*jumla;
                    wisataa="Karangsong";
                }else if(wisata2.isChecked()){
                    totaal=wisa2*jumla;
                    wisataa="Pulau Biawak";
                }else if(wisata3.isChecked()){
                    totaal=wisa3*jumla;
                    wisataa="Mutiara Bangsa";
                }
                else{
                    Toast.makeText(getApplicationContext(), "isi Checkbox",
                            Toast.LENGTH_LONG).show();
                }
                String wis=wisataa;
                int ftotal=totaal;
                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkPesan(nm, wis, ftotal, jumla);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkPesan(final String nm, final String wis, final int ftotal, final int jumla) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Tunggu Sebentar...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Pesan Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully Pesan!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        nama.setText("");
                        jum.setText(null);
                        wisata1.setChecked(false);
                        wisata2.setChecked(false);
                        wisata3.setChecked(false);
                        total.setText(null);

                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("nama", nm);
                params.put("wisata", wis);
                params.put("total", String.valueOf(ftotal));
                params.put("jumlah", String.valueOf(jumla));

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
