package cn.dalelegamalek.demo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import cn.dalelegamalek.demo.Adapter.RecyclerViewAdapter;
import cn.dalelegamalek.demo.model.content_profile;
import cn.simonlee.demo.xcodescanner.R;


public class FragmentImage extends DialogFragment {

    List<content_profile> profile ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_fragment_image, container, false);

        profile = new ArrayList<>();
        profile.add(new content_profile(R.drawable.aaaa));
        profile.add(new content_profile(R.drawable.bbbb));
        profile.add(new content_profile(R.drawable.cccc));
        profile.add(new content_profile(R.drawable.dddd));
        profile.add(new content_profile(R.drawable.eeee));
        profile.add(new content_profile(R.drawable.ffff));
        profile.add(new content_profile(R.drawable.gggg));
        profile.add(new content_profile(R.drawable.hhhh));
        profile.add(new content_profile(R.drawable.iiii));
        profile.add(new content_profile(R.drawable.jjjj));
        profile.add(new content_profile(R.drawable.kkkk));
        profile.add(new content_profile(R.drawable.llll));
        profile.add(new content_profile(R.drawable.mmmm));
        profile.add(new content_profile(R.drawable.nnnn));
        profile.add(new content_profile(R.drawable.oooo));
        profile.add(new content_profile(R.drawable.pppp));
        profile.add(new content_profile(R.drawable.qqqq));


        RecyclerView myrv = (RecyclerView) view.findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getContext(),profile,getDialog());
        myrv.setLayoutManager(new GridLayoutManager(getActivity(),3));
        myrv.setAdapter(myAdapter);


        return view;
    }


}
