package com.example.asm1_and103_ph41980.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.asm1_and103_ph41980.R;
import com.example.asm1_and103_ph41980.adapter.LoginAdapter;
import com.example.asm1_and103_ph41980.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private LoginAdapter adapter;
    float v = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Login"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Register"));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new LoginAdapter(getSupportFragmentManager(), MainActivity.this, binding.tabLayout.getTabCount());
        binding.viewPager.setAdapter(adapter);

        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        onAnimation();
    }
    private void onAnimation() {
        binding.fabFb.setTranslationY(300);
        binding.fabGoogle.setTranslationY(300);
        binding.fabTele.setTranslationY(300);
        binding.tabLayout.setTranslationY(300);

        binding.fabFb.setAlpha(v);
        binding.fabGoogle.setAlpha(v);
        binding.fabTele.setAlpha(v);
        binding.tabLayout.setAlpha(v);

        binding.fabFb.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(400).start();
        binding.fabGoogle.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(600).start();
        binding.fabTele.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(800).start();
        binding.tabLayout.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(1000).start();
    }

}