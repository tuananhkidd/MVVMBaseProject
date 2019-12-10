package com.base.mvvmbaseproject.network.repository;

import com.base.mvvmbaseproject.base.ListResponse;
import com.base.mvvmbaseproject.entity.SearchResponse;
import com.base.mvvmbaseproject.network.ApiInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    private final ApiInterface apiInterface;

    @Inject
    Repository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Single<ListResponse<SearchResponse>> search(int pageIndex) {
        return apiInterface.search("h",pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
