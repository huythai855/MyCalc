package com.example.mycalc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.IOException;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.net.URL;


public class Main extends AppCompatActivity {

    static SharedPreferences Items, ItemName, ItemAcc, ItemPass;
    Integer CountLongClick = 0;
    Integer LastClick = -1;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Main.this, Menu.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btn_add = findViewById(R.id.btn_add);
        Button btn_change = findViewById(R.id.btn_change);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, AddNew.class);
                startActivity(intent);
            }
        });

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, ChangePass.class);
                startActivity(intent);
            }
        });

        Context context = getApplicationContext();

        Items = context.getSharedPreferences("Number",context.MODE_PRIVATE);
        ItemName = context.getSharedPreferences("ItemName",context.MODE_PRIVATE);
        ItemAcc = context.getSharedPreferences("ItemAcc",context.MODE_PRIVATE);
        ItemPass = context.getSharedPreferences("ItemPass",context.MODE_PRIVATE);

        int NumberOfItems =Items.getInt("Number", 0);
        String ItemName1[] = new String[NumberOfItems];
        String ItemAcc1[] = new String[NumberOfItems];
        String ItemPass1[] = new String[NumberOfItems];

        for(int i=1; i<=NumberOfItems; i++){
            String key = Integer.toString(i);
            ItemName1[i-1] = ItemName.getString(key, "null");
            ItemAcc1[i-1] = ItemAcc.getString(key, "null");
            ItemPass1[i-1] = ItemPass.getString(key, "null");
        }

        ListView itemlist = findViewById(R.id.itemlist);
        Adapter adapter = new Adapter(context, ItemName1, ItemAcc1, ItemPass1);
        itemlist.setAdapter((ListAdapter) adapter);

        itemlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==LastClick)
                    CountLongClick++;
                else{
                    LastClick = i;
                    CountLongClick = 1;
                }

                if(CountLongClick == 3){
                    SharedPreferences.Editor editor;
                    for(int j=i; j<=NumberOfItems-2; j++){
                        editor = ItemName.edit();
                        editor.putString(Integer.toString(j), ItemName1[j+1]);
                        editor.commit();

                        editor = ItemAcc.edit();
                        editor.putString(Integer.toString(j), ItemAcc1[j+1]);
                        editor.commit();

                        editor = ItemPass.edit();
                        editor.putString(Integer.toString(j), ItemAcc1[j+1]);
                        editor.commit();
                    }

                    editor = Items.edit();
                    editor.putInt("Number", NumberOfItems-1);
                    editor.commit();

                    Intent intent = new Intent(Main.this, Main.class);
                    startActivity(intent);
                }
            }
        });
    }


    class Adapter extends ArrayAdapter<String> {
        Context context;
        String ItemName1[];
        String ItemAcc1[];
        String ItemPass1[];
        ImageView ItemImage1[];

        Adapter (Context c, String name[], String acc[], String pass[]){
            super(c, R.layout.item, R.id.txt_ItemName, name);
            this.context = c;
            this.ItemName1 = name;
            this.ItemAcc1 = acc;
            this.ItemPass1 = pass;
            ///this.ItemImage1 =
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = layoutInflater.inflate(R.layout.item, parent, false);
            TextView name = row.findViewById(R.id.txt_ItemName);
            TextView acc = row.findViewById(R.id.txt_ItemAccount);
            TextView pass = row.findViewById(R.id.txt_ItemPassword);
            ImageView image = row.findViewById(R.id.img_Item);

            name.setText(ItemName1[position]);
            acc.setText(ItemAcc1[position]);
            pass.setText(ItemPass1[position]);

            //Resources res = getResources();
            //Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.icon_app_background, null);
            //image.setImageDrawable(drawable);

            String wordlist[] = {"agribank", "bidv", "codeforces", "courses", "discord",
                                "facebook", "github", "gmail", "hackerrank", "instagram",
                                "linkedin", "mbbank", "microsoft", "momo", "notion",
                                "snapchat", "tpbank", "vietcombank", "viettelpay", "vnoj",
                                "zalo"};

            String temp = ItemName1[position];
            boolean flag = false;

            for(int i=0; i<=20; i++)
                if(temp.equals(wordlist[i]))
                    flag = true;

            if(flag == true){
                int resID = context.getResources().getIdentifier(temp, "drawable", context.getPackageName());
                image.setImageDrawable(ContextCompat.getDrawable(context,resID));
            }
            else{
                int resID = context.getResources().getIdentifier("meow", "drawable", context.getPackageName());
                image.setImageDrawable(ContextCompat.getDrawable(context,resID));
            }



            return row;
        }
    }

}