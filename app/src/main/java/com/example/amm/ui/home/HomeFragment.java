package com.example.amm.ui.home;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.amm.R;
import com.example.amm.databinding.FragmentHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TextView textView;
    private TextView textView3;
    private TextView textview4;

    private int index = 0;
    private NavController navController;
    private CardView cardStudent;
    private CardView ecoFriendly;
    private Button btnTrust;
    private boolean animationsStarted = false;
    private boolean animationsPaused = false;
    private int screenHeight;

    private ScrollView scrollView;
    private LinearLayout linearLayout;

    private DatabaseReference databaseReference;
    private WebView webView;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        textView = root.findViewById(R.id.text2);
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        ScrollView scrollView = root.findViewById(R.id.fragmentContainer);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (!animationsPaused) {
                        checkAndStartAnimations();
                    }
                }
            });
        }

        textView.setText(""); // Clear the text initially

        animateTextView();

        cardStudent = root.findViewById(R.id.cardStudent);
        cardStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Student Adoption
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_mainFragment_to_studentAdaptation);
            }
        });



        ecoFriendly = root.findViewById(R.id.ecoFriendly);
        ecoFriendly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Eco Friendly
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_mainFragment_to_EcoFriendly);
            }
        });

        btnTrust = root.findViewById(R.id.btnTrust);
        btnTrust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the AboutUsFragment
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_home_to_trustPage);
            }
        });

        Button btn = root.findViewById(R.id.btnhome1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the AboutUsFragment
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_mainFragment_to_aboutUsFragment);
            }
        });

        textView3 = root.findViewById(R.id.text3);
        slideInFromRightAnimation(textView3);
        textview4 = root.findViewById(R.id.text4);
        slideInFromLeftAnimation(textview4);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("webview_url");

        // Initialize the WebView
        webView = root.findViewById(R.id.webView);

        // Load URL from Firebase into WebView
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String url = dataSnapshot.getValue(String.class);
                if (url != null && !url.isEmpty()) {
                    // Load the URL into the WebView
                    webView.loadUrl(url);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error if needed
            }
        });

        // Set up WebView settings

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        return root;
    }


    private void checkAndStartAnimations() {
        startAnimationIfNeeded(textView3);
        startAnimationIfNeeded(textview4);
        // ... Add more views to animate if needed
    }

    private void startAnimationIfNeeded(View view) {
        if (!animationsStarted && isViewVisible(view)) {
            animateView(view);
            animationsStarted = true;
        }
    }

    private void animateView(View view) {
        slideInFromLeftAnimation(view);
        slideInFromRightAnimation(view);// Customize the animation as needed
    }

    private boolean isViewVisible(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int viewTop = location[1];
        int viewBottom = viewTop + view.getHeight();
        return viewTop <= screenHeight && viewBottom >= 0;
    }
    public void onResume() {
        super.onResume();
        animationsPaused = false;
        animationsStarted = false; // Reset the animationsStarted flag
        checkAndStartAnimations();
    }

    @Override
    public void onPause() {
        super.onPause();
        animationsPaused = true;
    }

    private void slideInFromLeftAnimation(View view) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        view.setTranslationX(-screenWidth);
        view.animate().translationX(0).setDuration(1000).start();
    }

    private void slideInFromRightAnimation(View view) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        view.setTranslationX(screenWidth);
        view.animate().translationX(0).setDuration(1000).start();
    }

    private void animateTextView() {
        String textToAnimate = getString(R.string.akhil_mandai_mandal); // Get text from resources

        ValueAnimator animator = ValueAnimator.ofInt(0, textToAnimate.length());
        animator.setDuration(textToAnimate.length() * 150); // Adjust the duration as needed
        animator.addUpdateListener(animation -> {
            int animatedValue = (int) animation.getAnimatedValue();
            textView.setText(textToAnimate.substring(0, animatedValue));
        });
        animator.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}