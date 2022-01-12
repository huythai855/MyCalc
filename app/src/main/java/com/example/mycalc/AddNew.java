package com.example.mycalc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
//import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddNew extends AppCompatActivity {
    static SharedPreferences Items, ItemName, ItemAcc, ItemPass;
    String NUL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew);

        Context context = getApplicationContext();

        Button btn_Save = findViewById(R.id.btn_SaveNew);
        EditText edt_NewName = findViewById(R.id.edt_NewName);
        EditText edt_NewAcc = findViewById(R.id.edt_NewAcc);
        EditText edt_NewPass = findViewById(R.id.edt_NewPass);
        TextView txt_Warning = findViewById(R.id.txtWarning2);

        Items = context.getSharedPreferences("Number",context.MODE_PRIVATE);
        ItemName = context.getSharedPreferences("ItemName",context.MODE_PRIVATE);
        ItemAcc = context.getSharedPreferences("ItemAcc",context.MODE_PRIVATE);
        ItemPass = context.getSharedPreferences("ItemPass",context.MODE_PRIVATE);

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NewName = edt_NewName.getText().toString();
                String NewAcc = edt_NewAcc.getText().toString();
                String NewPass = edt_NewPass.getText().toString();
                Integer Number = Items.getInt("Number", 0);
                SharedPreferences.Editor editor;

                if(NewName.equals(NUL) || NewAcc.equals(NUL) || NewPass.equals(NUL)){
                    txt_Warning.setVisibility(View.VISIBLE);
                }
                else{
                    txt_Warning.setText("Added successfully!");
                    txt_Warning.setTextColor(getResources().getColor(R.color.teal_200));
                    txt_Warning.setVisibility(View.VISIBLE);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    editor = Items.edit();
                    editor.putInt("Number", Number+1);
                    editor.commit();

                    editor = ItemName.edit();
                    editor.putString(Integer.toString(Number+1), NewName);
                    editor.commit();

                    editor = ItemAcc.edit();
                    editor.putString(Integer.toString(Number+1), NewAcc);
                    editor.commit();

                    editor = ItemPass.edit();
                    editor.putString(Integer.toString(Number+1), NewPass);
                    editor.commit();

                    Intent intent = new Intent(AddNew.this, Main.class);
                    startActivity(intent);
                }
            }
        });




    }
}