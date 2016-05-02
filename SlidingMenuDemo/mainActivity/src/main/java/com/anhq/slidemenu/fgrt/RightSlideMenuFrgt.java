package com.anhq.slidemenu.fgrt;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.anhq.slidemenu.R;
import com.anhq.slidemenu.activity.MainActivity;
import com.anhq.slidemenu.utils.StatusBarUtils;


/**
 * 右边界面
 * @author ahq
 *
 */
public class RightSlideMenuFrgt extends Fragment implements OnClickListener{

     @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	 View view = inflater.inflate(R.layout.main_rigth_frgt, container,false);
		StatusBarUtils.setStatusBarViewVisibility(view.findViewById(R.id.status_bar_view));
    	return view;
    }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		default:
			break;
		}
		
	}
	
	
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
			MainActivity ra = (MainActivity) getActivity();
			ra.switchContent(fragment);
		
	}
}
