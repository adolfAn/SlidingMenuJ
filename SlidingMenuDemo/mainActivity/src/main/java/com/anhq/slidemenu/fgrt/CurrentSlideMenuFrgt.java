package com.anhq.slidemenu.fgrt;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anhq.slidemenu.R;


/**
 *  当前界面
 * @author ahq
 *
 */
public class CurrentSlideMenuFrgt extends Fragment implements OnClickListener{
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.main_current_frgt, container, false);
    	return view;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity().getApplicationContext(), "很好！", Toast.LENGTH_SHORT).show();
	}
}
