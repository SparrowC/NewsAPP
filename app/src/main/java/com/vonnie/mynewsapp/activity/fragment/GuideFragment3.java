package com.vonnie.mynewsapp.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.vonnie.mynewsapp.R;
import com.vonnie.mynewsapp.activity.MainActivity;

/**
 * Created by vonnie on 2016/2/3.
 */
public class GuideFragment3 extends Fragment {
    private Button btn_start;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.guide_fragment3_layout,container,false);
        Log.d("Fragment1", "进入fragment3");
        btn_start= (Button) root.findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return root;
    }
}
