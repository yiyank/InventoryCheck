package com.example.kyy.inventorycheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CheckActivity extends Activity implements View.OnClickListener {

    private Button HomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        HomeButton = findViewById(R.id.HomeButton);
        HomeButton.setOnClickListener(CheckActivity.this);
    }



    @Override
    public void onClick (View view){
        Intent go2 = new Intent(CheckActivity.this, HomePage.class);
        startActivity(go2);
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
                startActivity(go1);
                return true;
            case R.id.Add:
                Intent go2 = new Intent(this, AddActivity.class);
                startActivity(go2);
                return true;
            case R.id.Check:
                //Intent go3 = new Intent(this, CheckActivity.class);
                //this.startActivity(go3);
                return true;

            default:
                return false;
        }
    }
}
