package com.mobilemonkeysoftware.testapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mobilemonkeysoftware.testapp.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AR on 03.03.2016.
 */
public abstract class BaseActivity extends AppCompatActivity implements View {

    @Nullable @Bind(R.id.toolbar) Toolbar toolbar;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResID());
        bind();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void bind() {
        ButterKnife.bind(this);
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
