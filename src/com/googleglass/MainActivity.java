package com.googleglass;

import java.lang.reflect.Method;

import com.google.android.glass.media.Sounds;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.view.WindowUtils;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import android.util.Log;
import com.googleglass.R;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	
	
	private static String TAG = MainActivity.class.getSimpleName();
	
	
	public GestureDetector mGestureDetector;

	/**
	 * scroller list view, GDK
	 */
	  private CardScrollView _scrollView;
	  
	  /**
	   * list of items in the view
	   */
	  private MenuScrollAdapter _adapter;
	  
	  /**
	   * transition effect
	   */
	  protected Method _animate;
	  
	  
		// items from the Menu, first one is empty because we display the logo
		 private String [] _items = new String [] {"" , "Find nearest dockers", "Get direction"};


	  

public class MenuScrollAdapter extends AScrollAdapter {
    
	/**
	 * constructor 
	 * @param modules list of modules to load
	 * @param context current context = activity
	 */
	MenuScrollAdapter(String[] modules, Context context)
	{
		super(modules, context);
	}
  		
	/**
	 * view of each items
	 */
    @Override
    public View getView(int num, View v, ViewGroup parent) {   
      if (v == null) {
        TextView tv = new TextView(getApplicationContext());        
        if (num == 0)
        	tv.setBackgroundResource(R.drawable.tsglasslogo); // display logo, first item
        tv.setText(_modules[num]);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(50); 
        v = tv;
      } 
      return v;
    }

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    
    // switch on crashLytics
    //Crashlytics.start(this);
    setContentView(R.layout.mmenu);
    _scrollView = (CardScrollView) findViewById(R.id.scrollview);
    
    // scroll view
    _adapter = new MenuScrollAdapter(_items, getApplicationContext()); 
    _scrollView.setAdapter(_adapter);
    _scrollView.activate();
    
    _scrollView.setHorizontalScrollBarEnabled(true);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    
    
    mGestureDetector = createGestureDetector(this);
    
    getWindow().requestFeature(WindowUtils.FEATURE_VOICE_COMMANDS);
      
    
    _scrollView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			TextView a = (TextView) arg1;
			 // launch activity
		}
	});
  }	  
  
  
  

	public GestureDetector createGestureDetector(Context context) {
	    GestureDetector gestureDetector = new GestureDetector(context);
	        //Create a base listener for generic gestures
	        gestureDetector.setBaseListener( new GestureDetector.BaseListener() {
	            @Override
	            public boolean onGesture(Gesture gesture) {	            	            		            	
	                return gesturesManagement(gesture);
	            }
	        });
	        
	        return gestureDetector;
	    }
	
	
	
	public boolean gesturesManagement(Gesture gesture)
	  {
       
         if (gesture == Gesture.SWIPE_RIGHT) {
        	Log.e(TAG, "Gesture.SWIPE_RIGHT");
            return true;
        } else if (gesture == Gesture.SWIPE_LEFT) {
        	Log.e(TAG, "Gesture.SWIPE_LEFT");
            return true;
        } 
        else if (gesture == Gesture.SWIPE_DOWN)
      	  finish();
        return true;
	  }
  
    /**
     * Builds a Glass styled "Hello World!" view using the {@link CardBuilder} class.
     */
    private View buildView() {
        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.TEXT);

        card.setText("TFL");
        return card.getView();
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS ||
                featureId == Window.FEATURE_OPTIONS_PANEL) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        // Pass through to super to setup touch menu.
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS ||
                featureId == Window.FEATURE_OPTIONS_PANEL) {
            switch (item.getItemId()) {
                case R.id.nearestdocks:
                	// display page to get nearest docks
                    break;
                case R.id.direction:
                	// get direction
                    break;
                default:
                    return true;
            }
            return true;
        }
        // Good practice to pass through to super if not handled
        return super.onMenuItemSelected(featureId, item);
    }


}
