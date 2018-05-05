package com.sample.shivam;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class WifiBasedHomeAutomation extends AppCompatActivity {

    Button btnOnL1, btnOffL1,btnOnL2, btnOffL2,btnOnL3, btnOffL3;

    String server_url = ">>> Supply Your URL Here <<<";

    int key[] = {0};
    int z = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_control);

        getSupportActionBar().setTitle("WiFi Based Home Automation");

        if(!isNetworkConnected()){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));
            builder1.setMessage("Internet Access Needed!");
            builder1.setCancelable(false);
            builder1.setPositiveButton(
                    "Open Settings",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.setClassName("com.android.settings",
                                    "com.android.settings.Settings$DataUsageSummaryActivity");
                            startActivity(intent);

                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

        }

        final int[] i = {0};
        final int[] j = {0};
        final int[] k = {0};

        //call the widgtes
        btnOnL1 = findViewById(R.id.button2);
        btnOffL1 = findViewById(R.id.button3);
        btnOnL2 = findViewById(R.id.button21);
        btnOffL2 = findViewById(R.id.button31);
        btnOnL3 = findViewById(R.id.button22);
        btnOffL3 = findViewById(R.id.button32);

        //new  resultsucess(this).execute();

        btnOnL1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                i[0] = 0;
                key[0] = 4*i[0] + 2*j[0] + k[0];
                done();
                btnOnL1.setVisibility(View.GONE);
                btnOffL1.setVisibility(View.VISIBLE);
            }
        });

        btnOffL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                i[0] = 1;
                key[0] = 4*i[0] + 2*j[0] + k[0];
                done();
                btnOffL1.setVisibility(View.GONE);
                btnOnL1.setVisibility(View.VISIBLE);
            }
        });

        btnOnL2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                j[0] = 0;
                key[0] = 4*i[0] + 2*j[0] + k[0];
                done();
                btnOnL2.setVisibility(View.GONE);
                btnOffL2.setVisibility(View.VISIBLE);
            }
        });

        btnOffL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                j[0] = 1;
                key[0] = 4*i[0] + 2*j[0] + k[0];
                done();
                btnOffL2.setVisibility(View.GONE);
                btnOnL2.setVisibility(View.VISIBLE);
            }
        });

        btnOnL3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                k[0] = 0;
                key[0] = 4*i[0] + 2*j[0] + k[0];
                done();
                btnOnL3.setVisibility(View.GONE);
                btnOffL3.setVisibility(View.VISIBLE);
            }
        });

        btnOffL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                k[0] = 1;
                key[0] = 4*i[0] + 2*j[0] + k[0];
                done();
                btnOffL3.setVisibility(View.GONE);
                btnOnL3.setVisibility(View.VISIBLE);
            }
        });
    }

    private void  done(){

        /*not needed:
        new resultsucess(this).execute();*/


        StringRequest request = new StringRequest(Request.Method.PUT, server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("x",Integer.toString(key[0]));
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToReqQ(request);

    }

    /**private class resultsucess extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;
        private Context mcontext;

        public resultsucess(Context activity) {
            this.mcontext = activity;

        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(mcontext);
            dialog.setMessage("Please wait.");
            dialog.show();
            super.onPreExecute();
        }

        protected Void doInBackground(Void... args) {
            StringRequest request1 = new StringRequest(Request.Method.DELETE, server_url + "/2",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            MySingleton.getInstance(getApplicationContext()).addToReqQ(request1);
            return null;
        }

        protected void onPostExecute(Void result) {

        //provide a delay
            StringRequest request = new StringRequest(Request.Method.PUT, server_url + "/1",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }},
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }}){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("x",Integer.toString(key[0]));
                    return params;
                }
            };
            MySingleton.getInstance(getApplicationContext()).addToReqQ(request);

            dialog.dismiss();
        }
    }*/


    private class resultsucess extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;
        private Context mcontext;

        public resultsucess(Context activity) {
            this.mcontext = activity;

        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(mcontext);
            dialog.setMessage("Please wait.");
            dialog.show();
            super.onPreExecute();
        }

        protected Void doInBackground(Void... args) {
            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, server_url, null,
                    new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(JSONObject response) {
                            // display response
                            try {
                                z = Integer.parseInt(response.getString("x"));
                                Log.d("Response", String.valueOf(z));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error.Response", String.valueOf(error));
                        }
                    }
            );

            MySingleton.getInstance(getApplicationContext()).addToReqQ(getRequest);
            return null;
        }

        protected void onPostExecute(Void result) {

            //provide a delay

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                        switch (z) {
                            case 1:
                                btnOffL3.callOnClick();
                                break;
                            case 2:
                                btnOffL2.callOnClick();
                                break;
                            case 3:
                                btnOffL2.callOnClick();
                                btnOffL3.callOnClick();
                                break;
                            case 4:
                                btnOffL1.callOnClick();
                                break;
                            case 5:
                                btnOffL3.callOnClick();
                                btnOffL1.callOnClick();
                                break;
                            case 6:
                                btnOffL2.callOnClick();
                                btnOffL1.callOnClick();
                                break;
                            case 7:
                                btnOffL1.callOnClick();
                                btnOffL2.callOnClick();
                                btnOffL3.callOnClick();
                                break;
                        }
                    }
                    dialog.dismiss();
                }
            }, 2000);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_led_control, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Info")
                    .setMessage("\nHTTP GET REQUEST URL:\n\n" + server_url)
                    .setCancelable(false)
                    .setIcon(R.drawable.info512)
                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        public void onClick (DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog disc = builder.create();
            disc.show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        return cm.getActiveNetworkInfo() != null;
    }

    protected void onPause() {
        super.onPause();
        finish();
    }

    protected void onStop() {
        super.onStop();
        finish();
    }

}
