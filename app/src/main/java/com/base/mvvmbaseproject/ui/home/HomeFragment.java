package com.base.mvvmbaseproject.ui.home;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.adapter.SearchAdapter;
import com.base.mvvmbaseproject.base.BaseFragment;
import com.base.mvvmbaseproject.databinding.HomeFragmentBinding;

import java.util.List;


public class HomeFragment extends BaseFragment<HomeFragmentBinding> {

    private HomeViewModel mViewModel;
    private SearchAdapter searchAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return true;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(HomeViewModel.class);


        searchAdapter = new SearchAdapter(getContext());
        binding.rcvSearch.setListLayoutManager(LinearLayoutManager.VERTICAL);
        binding.rcvSearch.setAdapter(searchAdapter);
        binding.rcvSearch.setOnLoadingMoreListener(() -> {
            mViewModel.search(false);
        });

        binding.rcvSearch.setOnRefreshListener(() -> {
            mViewModel.search(true);
        });
        mViewModel.search(true);
        mViewModel.getSearch().observe(getViewLifecycleOwner(), searchResponseListResponse -> {
            handleLoadMoreResponse(searchResponseListResponse,searchResponseListResponse.isRefresh(),searchResponseListResponse.isCanLoadmore());
        });
    }

    @Override
    protected <K> void getListResponse(List<K> data, boolean isRefresh,boolean canLoadmore) {
        binding.rcvSearch.enableLoadmore(canLoadmore);
        binding.rcvSearch.enableRefresh(false);
        if(isRefresh){
            binding.rcvSearch.refresh(data);
        }else {
            binding.rcvSearch.addItem(data);
        }
    }
}
