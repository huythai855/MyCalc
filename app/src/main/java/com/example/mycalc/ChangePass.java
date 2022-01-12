package com.example.mycalc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
//import androidx.appcompat.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePass extends AppCompatActivity {

    static SharedPreferences password;

    boolean Check(String x){
        int len = x.length();
        if(len <= 6) return false;

        for(int i=0; i<=(len-1); i++)
            if((x.charAt(i)<'0' || x.charAt(i)>'9') && x.charAt(i)!='+' && x.charAt(i)!='-' && x.charAt(i)!='*' && x.charAt(i)!='/')
                return false;

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        password = getSharedPreferences("password", Context.MODE_PRIVATE);

        Button btn_Change1 = findViewById(R.id.btnChange1);
        EditText OldPass = findViewById(R.id.pt_OldPass);
        EditText NewPass = findViewById(R.id.pt_NewPass);
        TextView Warning = findViewById(R.id.txtWarning);

        btn_Change1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Current = password.getString("password", "25/12/2003");
                String Old = OldPass.getText().toString();
                String New = NewPass.getText().toString();

                System.out.println(Current);
                System.out.println(Old);
                System.out.println(New);

                if(Old.equals(Current))
                    System.out.println("ok dk1");

                if(Check(New)==true)
                    System.out.println("ok dk2");




                if(Old.equals(Current) && Check(New)==true){
                    SharedPreferences.Editor it = password.edit();
                    it.putString("password", New);
                    it.commit();

                    Warning.setText("Password changed successfully!");
                    Warning.setTextColor(getResources().getColor(R.color.teal_200));
                    Warning.setVisibility(View.VISIBLE);

                    //Toast.makeText(getApplicationContext(), "Password changed Successfully", Toast.LENGTH_SHORT).show();

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(ChangePass.this, Menu.class);
                    startActivity(intent);
                }
                else{
                    Warning.setVisibility(View.VISIBLE);
                }



            }
        });

    }
}