package com.anhq.slidemenu.activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

import com.anhq.slidemenu.R;
import com.anhq.slidemenu.fgrt.CurrentSlideMenuFrgt;
import com.anhq.slidemenu.fgrt.LeftSlideMenuFrgt;
import com.anhq.slidemenu.fgrt.RightSlideMenuFrgt;
import com.anhq.slidemenu.utils.StatusBarUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


/**
 * 当前主类
 * @author ahq
 *
 */
public class MainActivity extends SlidingFragmentActivity implements OnClickListener{
	protected SlidingMenu mSlidingMenu;
	private ImageButton mTitleLeftIB;
	private ImageButton mTitleRightIB;
	private Fragment mContent;
	private View mStatusBarView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		StatusBarUtils.initStatusBar(this, R.color.completely_transparent);
		initSlidingMenu();
		setContentView(R.layout.activity_main);
		initView();
		StatusBarUtils.setStatusBarViewVisibility(mStatusBarView);

	}
	
	
	private void initView() {
		mStatusBarView = this.findViewById(R.id.status_bar_view);
		mTitleLeftIB = (ImageButton)this.findViewById(R.id.title_Left_ib);
		mTitleLeftIB.setOnClickListener(this);
		mTitleRightIB = (ImageButton)this.findViewById(R.id.title_right_ib);
		mTitleRightIB.setOnClickListener(this);
	}

	private void initSlidingMenu() {
		mContent = new CurrentSlideMenuFrgt();
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, mContent).commit();
		setBehindContentView(R.layout.main_left_layout);
		FragmentTransaction leftFragementTransaction = getSupportFragmentManager().beginTransaction();
		Fragment leftFrag = new LeftSlideMenuFrgt();
		leftFragementTransaction.replace(R.id.main_left_fragment, leftFrag);
		leftFragementTransaction.commit();
		// customize the SlidingMenu
		mSlidingMenu = getSlidingMenu();
		mSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);// 设置是左滑还是右滑，还是左右都可以滑，我这里只做了左滑
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset_left);// 设置左边菜单宽度
		mSlidingMenu.setRightMenuOffsetRes(R.dimen.slidingmenu_offset_right);// 设置右边菜单宽度
		mSlidingMenu.setFadeDegree(0.35f);// 设置淡入淡出的比例
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置手势模式
		mSlidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置手势模式
		mSlidingMenu.setShadowDrawable(R.color.completely_transparent);// 设置左菜单阴影图片
		mSlidingMenu.setFadeEnabled(true);// 设置滑动时菜单的是否淡入淡出
		mSlidingMenu.setBehindScrollScale(0.333f);// 设置滑动时拖拽效果
		mSlidingMenu.setBackgroundImage(R.drawable.ic_bg);
		mSlidingMenu.setSecondaryMenu(R.layout.main_right_layout);
		FragmentTransaction rightFragementTransaction = getSupportFragmentManager().beginTransaction();
		Fragment rightFrag = new RightSlideMenuFrgt();
		leftFragementTransaction.replace(R.id.main_right_fragment, rightFrag);
		rightFragementTransaction.commit();

		mSlidingMenu.setBehindCanvasTransformer(new SlidingMenu.CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
//				float scale = (float)(percentOpen * 0.25+0.75);
//				canvas.scale(scale, scale, - canvas.getWidth() / 2,
//						canvas.getHeight() / 2);
			}
		});

		mSlidingMenu.setAboveCanvasTransformer(new SlidingMenu.CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
//				float scale = (float) (1 - percentOpen * 0.25);
//				canvas.scale(scale, scale, 0, canvas.getHeight() / 2);
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_Left_ib:
			mSlidingMenu.showMenu();
			break;
		case R.id.title_right_ib:
			mSlidingMenu.showSecondaryMenu(true);
			break;
		default:
			break;
		}
		
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if(mSlidingMenu.isMenuShowing())
			break;
		default:
			break;

		}
		return super.onKeyDown(keyCode, event);

	}

 

	/**
	 *    左侧菜单点击切换首页的内容
	 */
	
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, fragment)
		.commit();
		getSlidingMenu().showContent();
	}

	/**
	 * 侧边栏滑动时通知
	 * @param percentOpen
	 *
	 */
	public void onLeftSlidingCanvasTranformed(float percentOpen) {
		if(mTitleLeftIB == null) {
			return;
		}

		float alphaPoint = 1 - percentOpen;
		if(alphaPoint < 0.0f) {
			alphaPoint = 0.0f;
		} else if(alphaPoint > 1.0f) {
			alphaPoint = 1.0f;
		}

		int alpha = (int) (alphaPoint * 255);

		mTitleLeftIB.getBackground().setAlpha(alpha);
	}



}
