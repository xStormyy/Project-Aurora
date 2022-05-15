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
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.View;
import android.animation.Animator;

public class LauncherActivity extends Activity {
	
	private Timer _timer = new Timer();
	
	private boolean hider = false;
	
	private LinearLayout linear7;
	private ImageView imageview9;
	private LinearLayout linear8;
	private ImageView imageview8;
	
	private TimerTask kill;
	private ObjectAnimator fadeout = new ObjectAnimator();
	private TimerTask time;
	private ObjectAnimator move = new ObjectAnimator();
	private ObjectAnimator fadeout2 = new ObjectAnimator();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.launcher);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear7 = findViewById(R.id.linear7);
		imageview9 = findViewById(R.id.imageview9);
		linear8 = findViewById(R.id.linear8);
		imageview8 = findViewById(R.id.imageview8);
		
		imageview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				move.setTarget(imageview9);
				move.setPropertyName("translationY");
				move.setDuration((int)(500));
				move.setFloatValues((float)(90));
				move.setInterpolator(new DecelerateInterpolator());
				move.start();
				fadeout2.setTarget(imageview8);
				fadeout2.setPropertyName("alpha");
				fadeout2.setDuration((int)(300));
				fadeout2.setFloatValues((float)(0));
				fadeout2.setInterpolator(new DecelerateInterpolator());
				fadeout2.start();
			}
		});
		
		fadeout.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator _param1) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator _param1) {
				imageview8.setAlpha((float)(1.0d));
				imageview9.setTranslationY((float)(0));
			}
			
			@Override
			public void onAnimationCancel(Animator _param1) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animator _param1) {
				
			}
		});
		
		move.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator _param1) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator _param1) {
				_mamamo();
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
		.setSystemUiVisibility(
		View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
		);
	}
	
	public void _mamamo() {
		if (!android.provider.Settings.canDrawOverlays(LauncherActivity.this)){
					Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
				    startActivity(intent);
				    }
		    else{
				Intent startMain = new Intent(Intent.ACTION_MAIN); startMain.addCategory(Intent.CATEGORY_HOME); //startMain.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS); 
				startActivity(startMain);
				
				 int LAYOUT_FLAG;
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
						    LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
				} else {
						    LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
				}
				
				final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
				    
				    LAYOUT_FLAG,
				      
				    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
				     , 
				
				    PixelFormat.TRANSPARENT);
				
				  final  WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
				    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
				    //View myView = inflater.inflate(R.layout.floating, null);
				
				final View myView = (View) getLayoutInflater().inflate(R.layout.viewer, null); 
				
					time = new TimerTask() {
					@Override
					public void run() {
							runOnUiThread(new Runnable() {
									@Override
									public void run() {
											fadeout.setTarget(myView);
											fadeout.setPropertyName("alpha");
											fadeout.setFloatValues((float)(1.0d), (float)(0.0d));
											fadeout.setDuration((int)(900));
											fadeout.setInterpolator(new AccelerateInterpolator());
											fadeout.start();
									}
							});
					}
			};
			_timer.schedule(time, (int)(5000));
			
			kill = new TimerTask() {
					@Override
					public void run() {
							runOnUiThread(new Runnable() {
									@Override
									public void run() {
							             wm.removeView(myView);
									}
							});
					}
			};
			_timer.schedule(kill, (int)(7000));
			
				
				    linear7.setOnTouchListener(new OnTouchListener() {
						private int x;
						private int y;
						       @Override
						       public boolean onTouch(View v, MotionEvent event) {
								
								 switch (event.getAction()) { 
										case MotionEvent.ACTION_DOWN: 
										x = (int) event.getRawX(); 
										y = (int) event.getRawY(); 
										break;
										
										case MotionEvent.ACTION_MOVE: int nowX = (int) event.getRawX(); 
										int nowY = (int) event.getRawY(); 
										int movedX = nowX - x; 
										int movedY = nowY - y; 
										x = nowX; y = nowY; 
										params.x = params.x + movedX; 
										params.y = params.y + movedY; wm.updateViewLayout(myView, params); 
										break; 
										default: 
										break; 
								}
								 return false;
								 }
						 });
				
				   //Specify the view position
				       params.gravity = Gravity.TOP | Gravity.LEFT;        //Initially view will be added to top-left corner
				       params.x = 0;
				       params.y = 0;
				
				Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.mojang.minecraftpe");
				if (launchIntent != null) {
						   startActivity(launchIntent);
				} else {
						   Toast.makeText(LauncherActivity.this, "Please install Minecraft", Toast.LENGTH_LONG).show();
				}
				
				wm.addView(myView, params);
			
		}
		
	}
}
