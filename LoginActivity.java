package com.mc.eenadunap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mc.eenadunap.views.CheckNetwork;
import com.mc.eenadunap.views.Helper;
import com.mc.eenadunap.views.SharedPreferenceData;

/**
 * Created by root on 7/12/17.
 */
public class LoginActivity extends AppCompatActivity {
    private Button btn_login;
    private TextView txt_loginhere;
    private EditText edt_employeeid,edt_passwor;
    private String getempid,getpassword;
    private SharedPreferenceData session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getLayoutInflater().inflate(R.layout.activity_login, frag_frame);
        setContentView(R.layout.activity_login);

        session = new SharedPreferenceData(LoginActivity.this);
        btn_login = (Button)findViewById(R.id.btn_login);
        txt_loginhere = (TextView)findViewById(R.id.txt_loginhere);
        edt_employeeid = (EditText)findViewById(R.id.edt_employeeid);
        edt_passwor = (EditText)findViewById(R.id.edt_passwor);

       /* txt_loginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainint = new Intent(LoginActivity.this,Registration.class);
                startActivity(mainint);
                finish();
            }
        });*/
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String getempid = edt_employeeid.getText().toString();
                String getpassword = edt_passwor.getText().toString();

                Intent mainint = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(mainint);
                finish();*/

                if (CheckNetwork.isInternetAvailable(LoginActivity.this)) {
                    getempid = edt_employeeid.getText().toString();
                    getpassword = edt_passwor.getText().toString();
                    if (getempid.equalsIgnoreCase("") && getpassword.equalsIgnoreCase("")) {
                        Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                    }else if (getpassword.equalsIgnoreCase("")) {
                        Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    } else {
                        getempid = edt_employeeid.getText().toString();
                        getpassword = edt_passwor.getText().toString();
                        Log.e("dsbkfb", "kbkfb===>" + getempid + "  " + getpassword);
                        //postRegDetails(ApiController.login + "&uname=" + pname + "&pwd=" + ppwd);
                        //postRegDetails(ApiController.login);
                    }


                } else {
                    Helper.getNetworkError(LoginActivity.this);
                }
            }
        });
    }
   /* private void postRegDetails(String url) {
        Log.e("url", "url==>" + url);


        final Map<String, String> postParameters = new HashMap<String, String>();
        postParameters.put("empid", getempid);
        postParameters.put("pwd", getpassword);


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParameters),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.e("Response", "Response===>" + jsonObject.toString());
                        try {

                            JSONArray array = jsonObject.getJSONArray("login");

                            if (array != null) {
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject job = array.getJSONObject(i);
                                    String id = job.getString("message");
                                    if (id.equalsIgnoreCase("success")) {
                                        session.setLogged(true);
                                        session.setUser(getempid.toString().trim());
                                        Intent logint = new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(logint);

                                    } else {
                                        Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }


                        } catch (Exception e) {
                            // TODO Auto-generated catch block

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "error===>" + error.toString());

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(LoginActivity.this, "Uh.. TimeOut\", \"please check your network once and try again", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(LoginActivity.this, "Uh.. authentication problem\", \"please check your network once and try again", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(LoginActivity.this, "Uh.. content missing\", \"currently our server is too slow please try again after some time", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(LoginActivity.this, "Uh.. authentication problem\", \"please check your network once and try again", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(LoginActivity.this, "Uh.. content missing\", \"currently our server is too slow please try again", Toast.LENGTH_SHORT).show();
                }

            }
        });


        req.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(req);
    }*/
}
