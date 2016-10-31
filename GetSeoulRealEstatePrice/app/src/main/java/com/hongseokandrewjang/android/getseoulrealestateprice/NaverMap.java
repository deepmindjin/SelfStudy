package com.hongseokandrewjang.android.getseoulrealestateprice;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.nmapmodel.NMapPlacemark;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;

public class NaverMap extends NMapActivity{

    private static final String LOG_TAG = "NMAP";
    private static final String clientId = "12mLgH2Z2BVqUKmol67U";
    private static final String clientSecret = "aLvBuSPvLs";

    NMapView mMapView;
    NMapController mMapController;
    NGeoPoint currentPosition;
    NMapLocationManager mNMapLocationManager;
    NMapViewerResourceProvider mNMapViewerResourceProvider;

    String sigungu = "";
    boolean isSeoul = false;
    double pressed_position_x = 0;
    double pressed_position_y = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set data provider listener
        super.setMapDataProviderListener(new OnDataProviderListener() {
            @Override
            public void onReverseGeocoderResponse(NMapPlacemark nMapPlacemark, NMapError nMapError) {
                sigungu = nMapPlacemark.siName;
                if (nMapPlacemark.doName.equals("서울특별시")){
                    isSeoul = true;
                }
            }
        });

// create map view
        mMapView = new NMapView(this);
        mMapView.setBuiltInZoomControls(true, null);

// set Client ID for Open MapViewer Library
        mMapView.setClientId(clientId);

// set the activity content to the map view
        setContentView(mMapView);

// initialize map view
        mMapView.setClickable(true);

// register listener for map state changes
        mMapView.setOnMapStateChangeListener(new NMapView.OnMapStateChangeListener() {
            @Override
            public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {
                if (nMapError == null) { // success
                    mNMapLocationManager = new NMapLocationManager(getBaseContext());
                    mNMapLocationManager.enableMyLocation(true);
                    mMapController.setMapCenter(new NGeoPoint(126.978371, 37.5666091), 12);
                    currentPosition = mNMapLocationManager.getMyLocation();
//                    mMapController.animateTo(currentPosition);
                } else { // fail
                    Log.e(LOG_TAG, "onMapInitHandler: error=" + nMapError.toString());
                }
            }

            @Override
            public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {

            }

            @Override
            public void onMapCenterChangeFine(NMapView nMapView) {

            }

            @Override
            public void onZoomLevelChange(NMapView nMapView, int i) {

            }

            @Override
            public void onAnimationStateChange(NMapView nMapView, int i, int i1) {

            }
        });
        mMapView.setOnMapViewTouchEventListener(new NMapView.OnMapViewTouchEventListener() {
            @Override
            public void onLongPress(NMapView nMapView, MotionEvent motionEvent) {
                pressed_position_x = motionEvent.getX();
                pressed_position_y = motionEvent.getY();
                NMapOverlayItem item = new NMapOverlayItem(new NGeoPoint(pressed_position_x,pressed_position_y),"집값을 알고싶은 지역",null,null);
                item.setVisibility(NMapOverlayItem.VISIBLE);
            }

            @Override
            public void onLongPressCanceled(NMapView nMapView) {

            }

            @Override
            public void onTouchDown(NMapView nMapView, MotionEvent motionEvent) {

            }

            @Override
            public void onTouchUp(NMapView nMapView, MotionEvent motionEvent) {

            }

            @Override
            public void onScroll(NMapView nMapView, MotionEvent motionEvent, MotionEvent motionEvent1) {

            }

            @Override
            public void onSingleTapUp(NMapView nMapView, MotionEvent motionEvent) {

            }
        });

        // register callout overlay listener to customize it.
//        mOverlayManager.setOnCalloutOverlayListener(onCalloutOverlayListener);

// use map controller to zoom in/out, pan and set map center, zoom level etc.
        mMapController = mMapView.getMapController();
        mMapView.setBuiltInZoomControls(true,null);
    }

    public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {
        if (errorInfo == null) { // success
            mNMapLocationManager = new NMapLocationManager(getBaseContext());
            mNMapLocationManager.enableMyLocation(true);
            mMapController.setMapCenter(new NGeoPoint(126.978371, 37.5666091), 11);
            currentPosition = mNMapLocationManager.getMyLocation();
            Log.i("CURRENTPOSITION",currentPosition.toString());
                if (currentPosition!=null)
                    mMapController.animateTo(currentPosition);

        } else { // fail
            Log.e(LOG_TAG, "onMapInitHandler: error=" + errorInfo.toString());
        }
    }

    public void onReverseGeocoderResponse(NMapPlacemark placeMark, NMapError errorInfo) {

        if (errorInfo != null) {
            Log.e(LOG_TAG, "Failed to findPlacemarkAtLocation: error=" + errorInfo.toString());
            return;
        }

        Log.i(LOG_TAG, "onReverseGeocoderResponse: placeMark=" + placeMark.toString());
    }

    private class  CallOutOverlay extends NMapCalloutOverlay{

        public CallOutOverlay(NMapOverlay nMapOverlay, NMapOverlayItem nMapOverlayItem, Rect rect) {
            super(nMapOverlay, nMapOverlayItem, rect);
        }

        @Override
        protected boolean isTitleTruncated() {
            return false;
        }

        @Override
        protected int getMarginX() {
            return 0;
        }

        @Override
        protected Rect getBounds(NMapView nMapView) {
            return null;
        }

        @Override
        protected PointF getSclaingPivot() {
            return null;
        }

        @Override
        protected void drawCallout(Canvas canvas, NMapView nMapView, boolean b, long l) {

        }

        @Override
        protected Drawable getDrawable(int i, int i1) {
            return null;
        }
    }

}
