package com.mobilemonkeysoftware.testapp.activity.fragment;

import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.mobilemonkeysoftware.testapp.adapter.BaseAdapter;

/**
 * Created by AR on 03.03.2016.
 */
public interface ListView<AT extends BaseAdapter> {

    @IntegerRes int getSpanResId();
    @StringRes int getEmptyListTextResId();
    boolean canRefreshOnSwipe();
    void refreshOnSwipe();
    void startItemsLoad();
    @NonNull AT createEmptyAdapter();

}
