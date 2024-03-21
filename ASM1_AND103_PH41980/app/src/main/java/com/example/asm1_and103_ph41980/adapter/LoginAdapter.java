package com.example.asm1_and103_ph41980.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.asm1_and103_ph41980.Fragment.LoginFragment;
import com.example.asm1_and103_ph41980.Fragment.SignupFragment;

public class LoginAdapter extends FragmentPagerAdapter {
    private Context context;
    private int totalTab;
    public LoginAdapter(@NonNull FragmentManager fm, Context context, int totalTab) {
        super(fm);
        this.context = context;
        this.totalTab = totalTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new LoginFragment();
        } else {
            return new SignupFragment();
        }
    }

    @Override
    public int getCount() {
        return totalTab;
    }
}
