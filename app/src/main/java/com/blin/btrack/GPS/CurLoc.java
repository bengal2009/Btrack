package com.blin.btrack.GPS;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCoder;

/**
 * Created by Lin on 2015/3/29.
 * return Location Value
 */
public class CurLoc extends Activity {
    private LatLng CurPOI;
    private GeoCoder mSearch;
    public LocationClient mLocationClient;
    public GeofenceClient mGeofenceClient;
    public MyLocationListener mMyLocationListener;
    public String  mLocationResult;
    public String LocationResult;
    public LocationInfo LocInfo;
    private Context mcontext;
    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
    public LocationInfo Curlocation;
    private boolean GetLocaFlag=false;
    private OnCurSendScuessListener mListener;
    public interface OnCurSendScuessListener {
        public  void CurLocsendScuess(LocationInfo msg);
    }
    public void setOnCurSendScuessListener(OnCurSendScuessListener listener) {
        this.mListener = listener;
    }
    public CurLoc(Context mcontext)
    {
        this.mcontext=mcontext;
    }
    public void InitLoc()
    {
        mLocationClient = new LocationClient(mcontext);
        mMyLocationListener = new MyLocationListener(  );
        mGeofenceClient = new GeofenceClient(mcontext);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(tempMode);
        option.setOpenGps(true);// 打?gps
        option.setCoorType("bd09ll"); // 置坐??型
        option.setScanSpan(1000);
        option.setProdName("BennyLoc");
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
        mLocationClient.registerLocationListener(mMyLocationListener);

    }

    public LocationInfo ReturnLocInfo()
    {
        if(GetLocaFlag==false) return null;
        return LocInfo;

    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        mLocationClient.stop();
        mLocationClient=null;
        mMyLocationListener=null;
        super.onStop();
    }

    public class  MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
           /* StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation){
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\ndirection : ");
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append(location.getDirection());
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //堍茠妀陓洘
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
            }
            Log.i("Latitude",Double.toString(location.getLatitude()));*/
            /*Toast.makeText(getApplicationContext(),Double.toString(location.getLatitude()),
                    Toast.LENGTH_SHORT).show();
*/
            LocationInfo a2=new LocationInfo();
            a2.setLocationTime(location.getTime());
            a2.setLatitude(location.getLatitude());
            a2.setLongitude(location.getLongitude());
            setLocInfo(a2);
            GetLocaFlag=!GetLocaFlag;
            mListener.CurLocsendScuess(a2);
            Log.i("getLatitude",Double.toString(location.getLatitude()));
            mLocationClient.stop();
        }
    }

    public LocationInfo getLocInfo() {
       if(GetLocaFlag) {
           return LocInfo;
       }else
       {
           return null;
       }

    }

    public void setLocInfo(LocationInfo locInfo) {
        LocInfo = locInfo;
    }

    /**
     * 詢儕僅華燴峓戲隙覃
     * @author jpren
     *
     */
}
