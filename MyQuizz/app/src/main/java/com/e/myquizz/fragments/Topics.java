package com.e.myquizz.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.myquizz.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class Topics extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Topics() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Topics newInstance(String param1, String param2) {
        Topics fragment = new Topics();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topics, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity context = getActivity();
        TabLayout tabLayout = context.findViewById(R.id.tab_layout);
        ViewPager viewPager = context.findViewById(R.id.viewPager);

        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(new PopularTopics(),"Popular Topics");
        pagerAdapter.addFragment(new AllTopic(),"All Topics");


        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    public static class CustomPagerAdapter extends FragmentPagerAdapter{

        public ArrayList<Fragment> mFragments = new ArrayList<>();
        public ArrayList<String> mFragmentTitles = new ArrayList<>();

        public CustomPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }


        public void addFragment(Fragment fragment,String title){
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        public CharSequence getPageTitle(int position){
            return mFragmentTitles.get(position);
        }
    }
}
