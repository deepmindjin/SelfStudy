package kr.co.fastcampus.android.chat;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by HongSeok on 2016-11-07.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    public static final String TAG = "MyFirebaseIIDService";
    public static final String TOPIC = "update";

    public MyFirebaseInstanceIDService() {
    }

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);
    }
}
