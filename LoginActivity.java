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

        setContentView(R.layout.activity_login);

        session = new SharedPreferenceData(LoginActivity.this);
        btn_login = (Button)findViewById(R.id.btn_login);
        txt_loginhere = (TextView)findViewById(R.id.txt_loginhere);
        edt_employeeid = (EditText)findViewById(R.id.edt_employeeid);
        edt_passwor = (EditText)findViewById(R.id.edt_passwor);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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

                    }


                } else {
                    Helper.getNetworkError(LoginActivity.this);
                }
            }
        });
    }

}
