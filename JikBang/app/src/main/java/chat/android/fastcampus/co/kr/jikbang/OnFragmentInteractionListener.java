package chat.android.fastcampus.co.kr.jikbang;

import android.support.v4.app.Fragment;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by HongSeok on 2016-11-08.
 */

public interface OnFragmentInteractionListener {
    void onFragmentInteraction(Fragment fragment);
    DatabaseReference getRoomReference();
}