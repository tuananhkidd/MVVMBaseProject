package com.base.mvvmbaseproject.ui.splash;

import android.os.Handler;

import androidx.lifecycle.ViewModelProviders;

import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.base.BaseFragment;
import com.base.mvvmbaseproject.databinding.SplashFragmentBinding;
import com.base.mvvmbaseproject.ui.home.HomeFragment;


public class SplashFragment extends BaseFragment<SplashFragmentBinding> {

    private SplashViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.splash_fragment;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void initView() {
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(SplashViewModel.class);
    }

    @Override
    public void initData() {
        new Handler().postDelayed(()->{
            mViewController.addFragment(HomeFragment.class,null);
        },2000);
    }

}
