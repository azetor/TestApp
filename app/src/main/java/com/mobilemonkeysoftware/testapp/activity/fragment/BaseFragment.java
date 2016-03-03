package com.mobilemonkeysoftware.testapp.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mobilemonkeysoftware.testapp.activity.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

/**
 * Created by AR on 03.03.2016.
 */
public abstract class BaseFragment extends Fragment implements View {

    @Nullable
    @Override public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        android.view.View view = inflater.inflate(getLayoutResID(), container, false);
        bind(view);

        return view;
    }

    public void bind(@NonNull android.view.View view) {
        ButterKnife.bind(this, view);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();

        unbind();
    }

    public void unbind() {
        ButterKnife.unbind(this);
    }

    @Override public void onResume() {

        EventBus.getDefault().register(this);
        super.onResume();
    }

    @Override public void onPause() {

        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Subscribe public void onEvent(Object event) {
    }

    public void post(Object event) {
        EventBus.getDefault().post(event);
    }

}
