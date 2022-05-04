package com.white.aurora;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import com.white.aurora.R;
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import android.animation.Animator;

public class MainActivity extends Activity {

	private Timer _timer = new Timer();

	private double progress = 0;

	private LinearLayout linear1;
	private ImageView imageview2;
	private ProgressBar progressbar1;

	private ObjectAnimator animation = new ObjectAnimator();
	private ObjectAnimator animation2 = new ObjectAnimator();
	private TimerTask bartime;
	private ObjectAnimator title = new ObjectAnimator();
	private TimerTask time;
	private TimerTask time2;

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize(_savedInstanceState);
		initializeLogic();
	}

	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		imageview2 = findViewById(R.id.imageview2);
		progressbar1 = findViewById(R.id.progressbar1);

		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {

			}
		});

		animation.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator _param1) {

			}

			@Override
			public void onAnimationEnd(Animator _param1) {
				title.setTarget(imageview2);
				title.setPropertyName("translationY");
				title.setFloatValues((float) (-75));
				title.setDuration((int) (1000));
				title.setInterpolator(new AccelerateInterpolator());
				title.start();
				progress = 0;
				progressbar1.setMax((int) 100);
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								progressbar1.setVisibility(View.VISIBLE);
								animation2.setTarget(progressbar1);
								animation2.setPropertyName("alpha");
								animation2.setFloatValues((float) (0.0d), (float) (1.0d));
								animation2.setDuration((int) (1500));
								animation2.setInterpolator(new AccelerateInterpolator());
								animation2.start();
								bartime = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												progress++;
												progressbar1.setProgress((int) progress);
												if (progress == progressbar1.getMax()) {
													Intent i = new Intent(MainActivity.this, LauncherActivity.class);
													startActivity(i);
													overridePendingTransition(R.anim.f2, R.anim.f);
												}
											}
										});
									}
								};
								_timer.scheduleAtFixedRate(bartime, (int) (100), (int) (30));
							}
						});
					}
				};
				_timer.schedule(time, (int) (300));
			}

			@Override
			public void onAnimationCancel(Animator _param1) {

			}

			@Override
			public void onAnimationRepeat(Animator _param1) {

			}
		});
	}

	private void initializeLogic() {
		getWindow().getDecorView()
				.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		animation.setTarget(imageview2);
		animation.setPropertyName("alpha");
		animation.setFloatValues((float) (0.0d), (float) (1.0d));
		animation.setDuration((int) (2500));
		animation.setInterpolator(new AccelerateInterpolator());
		animation.start();
		progressbar1.setVisibility(View.INVISIBLE);
	}
}
