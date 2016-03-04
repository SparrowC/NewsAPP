package com.vonnie.mynewsapp.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vonnie.mynewsapp.R;

/**
 * Created by vonnie on 2016/2/3.
 */
public class GuideFragment2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.guide_fragment2_layout,container,false);
        Log.d("Fragment1", "进入fragment2");
        return root;
    }
}
