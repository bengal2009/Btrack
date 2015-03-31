package com.blin.btrack.GPS;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.blin.btrack.R;

public class NotifyActivity extends Activity{

    private Button startNotify;
    private Vibrator mVibrator;
    private LocationClient mLocationClient;
    private NotiftLocationListener listener;
    private double longtitude,latitude;
    private NotifyLister mNotifyLister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        listener = new NotiftLocationListener();
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);

        startNotify = (Button)findViewById(R.id.notifystart);
        mLocationClient  = new LocationClient(this);
        mLocationClient.registerLocationListener(listener);
        startNotify.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(startNotify.getText().toString().equals("羲弇离枑倳")){
                    mLocationClient.start();
                    startNotify.setText("壽敕弇离枑倳");
                }else{
                    if(mNotifyLister!=null){
                        mLocationClient.removeNotifyEvent(mNotifyLister);
                        startNotify.setText("羲弇离枑倳");
                    }

                }


            }
        });
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        mLocationClient.removeNotifyEvent(mNotifyLister);
        mLocationClient = null;
        mNotifyLister= null;
        listener = null;

    }

    private Handler notifyHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            mNotifyLister = new NotifyLister();
            mNotifyLister.SetNotifyLocation(latitude,longtitude, 3000,"gcj02");//4跺統杅測桶猁弇离枑倳腔萸腔釴梓ㄛ撿极漪砱甡棒峈ㄩ帠僅ㄛ冪僅ㄛ擒燭毓峓ㄛ釴梓炵濬倰(gcj02,gps,bd09,bd09ll)
            mLocationClient.registerNotify(mNotifyLister);
        }

    };
    public class NotiftLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            longtitude = location.getLongitude();
            latitude = location.getLatitude();
            notifyHandler.sendEmptyMessage(0);
        }
    }
    public class NotifyLister extends BDNotifyListener{
        public void onNotify(BDLocation mlocation, float distance){
            mVibrator.vibrate(1000);//淥雄枑倳眒善扢隅弇离蜇輪
            Toast.makeText(NotifyActivity.this, "涾雄枑倳", Toast.LENGTH_SHORT).show();
        }
    }
}

