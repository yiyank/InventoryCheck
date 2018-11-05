package com.example.kyy.inventorycheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AddActivity extends Activity implements View.OnClickListener {

    private Button HomeButton2;
    private static final String TAG = "AddActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        HomeButton2 = findViewById(R.id.HomeButton2);
        HomeButton2.setOnClickListener(AddActivity.this);
    }




    @Override
    public void onClick (View view){
        Intent go = new Intent(AddActivity.this, HomePage.class);
        startActivity(go);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater optionMenuInflater = getMenuInflater();
        optionMenuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.Home:
                Intent go1 = new Intent(this, HomePage.class);
                this.startActivity(go1);
                return true;
            case R.id.Add:
                //Intent go2 = new Intent(this, AddActivity.class);
                //this.startActivity(go2);
                return true;
            case R.id.Check:
                Intent go3 = new Intent(this, CheckActivity.class);
                this.startActivity(go3);
                return true;

            default:
                return false;
        }
    }
}
