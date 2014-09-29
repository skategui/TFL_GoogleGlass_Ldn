package com.googleglass;

import java.util.HashMap;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.glass.widget.CardScrollAdapter;

public class AScrollAdapter extends CardScrollAdapter{

	protected HashMap<Object, String> mMap;
	// list of modules
	protected String[] _modules;
	
	// current context
	protected Context _context;
	// current activity
    
	/**
	 * constructor
	 * @param modules list of modules, string format
	 * @param context current context
	 */
	public AScrollAdapter(String[] modules, Context context)
	{
		this._modules = modules;
		this._context = context;
	}
	
	
    @Override
    public int getCount() {
      return _modules.length;
    }

    @Override
    public Object getItem(int num) {
      return _modules[num];
    }

    /**
     * view of the current module displayed
     */
    @Override
    public View getView(int num, View v, ViewGroup parent) {   
      if (v == null) {
        TextView tv = new TextView(this._context); 
        tv.setText(_modules[num]);
        // if num == 0 , display logo
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(50); 
        v = tv;
      } 
      return v;
    }

	@Override
	public int getPosition(Object key) {
		return (Integer) key;
	}

}