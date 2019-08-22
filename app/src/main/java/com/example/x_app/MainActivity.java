package com.example.x_app;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import io.flutter.facade.Flutter;
import io.flutter.view.FlutterView;


public class MainActivity extends AppCompatActivity {

    private FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        setContentView(R.layout.activity_main);
        fl = findViewById(R.id.flutter_container);
        View flutterView = Flutter.createView(
                MainActivity.this,
                getLifecycle(),
                "flutter_page"
        );
        fl.addView(flutterView);
//        ViewGroup.LayoutParams layout2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        addContentView(flutterView, layout2);

        showFlutterView((FlutterView) flutterView);


    }

    private void showFlutterView(final FlutterView flutterView) {
        flutterView.addFirstFrameListener(new FlutterView.FirstFrameListener() {
            @Override
            public void onFirstFrame() {
                flutterView.removeFirstFrameListener(this);
                fl.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        View mDecorView = getWindow().getDecorView();

        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }
}
