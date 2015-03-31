package com.imasdroid.hanoi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    EditText jumlahPiringan;

    public final static String NUMBER_DISK = "com.imasdriod.hanoi.DISK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jumlahPiringan = (EditText) findViewById(R.id.editTextJumlahPiringan);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void proses(View view)
    {
        Intent intent = new Intent(this, HanoiGameActivity.class);
        try
        {
            intent.putExtra(NUMBER_DISK, jumlahPiringan.getText().toString());
            startActivity(intent);
        }
        catch(NumberFormatException ex)
        {
            ex.printStackTrace();
            Toast.makeText(getApplicationContext(), "Input Number Not String", Toast.LENGTH_LONG).show();
        }
    }
}
