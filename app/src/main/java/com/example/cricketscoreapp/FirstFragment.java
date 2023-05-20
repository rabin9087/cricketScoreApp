package com.example.cricketscoreapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cricketscoreapp.databinding.FragmentFirstBinding;

import java.util.Random;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    public static final Random RANDOM = new Random();
    private ImageView coin;

    EditText teamA, teamB;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        initView();

        return binding.getRoot();

    }

    private void initView() {
        teamA = getView().findViewById(R.id.teamA);
        teamB = getView().findViewById(R.id.teamB);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        coin = coin.findViewById(R.id.tossCoin);
        coin.setOnClickListener(view1 -> flipCoin());

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
                
            }
        });
    }

    private void flipCoin(){
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1500);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                coin.setImageResource(RANDOM.nextFloat() > 0.5f ? R.drawable.tails : R.drawable.heads);

                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(1500);
                fadeIn.setFillAfter(true);

                coin.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        coin.startAnimation(fadeOut);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}