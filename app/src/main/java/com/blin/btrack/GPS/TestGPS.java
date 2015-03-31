package com.blin.btrack.GPS;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.blin.btrack.R;

public class TestGPS extends ActionBarActivity implements CurLoc.OnCurSendScuessListener {
    CurLoc CurrentLocation;
private LocationInfo LocInfo=new LocationInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_gps);
        CurrentLocation=new CurLoc(this);
        CurrentLocation.InitLoc();
        CurrentLocation.setOnCurSendScuessListener(this);
        /*LocationInfo a9=CurrentLocation.getLocInfo();
        if(a9!=null){
        Log.i("CurrentLocation",Double.toString(a9.getLatitude()));}*/

    /*    Intent i = new Intent(this, CurLoc.class);
        startActivityForResult(i, 1);
*/

    }

    @Override
    public void  CurLocsendScuess(LocationInfo a1)
    {
        Toast.makeText(getApplicationContext(),"Latitude:"+Double.toString(a1.getLatitude()),
                Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        }

        Toast.makeText(getApplicationContext(), Double.toString(data.getDoubleExtra("result",0)),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_g, menu);
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
}
