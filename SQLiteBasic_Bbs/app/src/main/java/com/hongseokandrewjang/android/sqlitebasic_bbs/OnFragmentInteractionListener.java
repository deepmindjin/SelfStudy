package com.hongseokandrewjang.android.sqlitebasic_bbs;

public interface OnFragmentInteractionListener {
                // 일반 Action을 처리하는 함수
                void onFragmentInteraction(int actionFlag, int bbsno);

        void addData(String title, String name, String contents);
}
