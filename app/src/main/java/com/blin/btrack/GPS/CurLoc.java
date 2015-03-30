package com.blin.btrack.GPS;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

/**
 * Created by Lin on 2015/3/29.
 * return Location Value
 */
public class CurLoc extends Activity implements OnGetGeoCoderResultListener {
    private LatLng CurPOI;
    private GeoCoder mSearch;
    public LocationClient mLocationClient;
    public GeofenceClient mGeofenceClient;
    public MyLocationListener mMyLocationListener;
    public String  mLocationResult;
    public String LocationResult;
    public LocationInfo Curloc;
    private Context mcontext;
    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
    public LocationInfo Curlocation;
    private boolean GetLocaFlag=false;
    public CurLoc(Context mcontext) {
        this.mcontext=mcontext;

    }

    public void InitLoc()
    {
        mLocationClient = new LocationClient(this.getApplicationContext());
        mMyLocationListener = new MyLocationListener();
        mGeofenceClient = new GeofenceClient(getApplicationContext());
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
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
    }

    public LocationInfo ReturnLocInfo()
    {
        if(GetLocaFlag==false) return null;
        return Curloc;

    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        mLocationClient.stop();
        mLocationClient=null;
        mMyLocationListener=null;
        super.onStop();
    }


    /**
     * 妗珋妗弇隙覃潼泭
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            Curloc.setLocationTime(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            Curloc.setLatitude(location.getLatitude());
            Curloc.setLongitude(location.getLongitude());
            Curloc.setLocationType(location.getLocType());
            Curloc.setRadius(location.getRadius());
            Curloc.setErrorCode(location.getLocType());
           /* CurPOI=new LatLng(location.getLatitude(),location.getLongitude()) ;
            mSearch.reverseGeoCode(new ReverseGeoCodeOption()
                    .location( CurPOI));
            LocationResult=sb.toString();*/
            mLocationClient.stop();
            GetLocaFlag=!GetLocaFlag;
//            Log.i("BaiduLocationApiDem", sb.toString());
        }


    }


    /**
     * 珆尨趼睫揹
     * @param str
     */
   public void logMsg(String str) {
        try {
            if (mLocationResult != null)
                mLocationResult=str;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(this, "onGetGeoCodeResult抱歉", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        String strInfo = String.format("?度：%f ?度：%f",
                result.getLocation().latitude, result.getLocation().longitude);
        Toast.makeText(this, strInfo, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(this, "Sorry! Not found!", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        LocationResult+="\n"+result.getAddress();
        Toast.makeText(this, result.getAddress(),
                Toast.LENGTH_LONG).show();

    }
    /**
     * 詢儕僅華燴峓戲隙覃
     * @author jpren
     *
     */
}
