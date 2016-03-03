package com.mobilemonkeysoftware.testapp.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobilemonkeysoftware.testapp.R;
import com.mobilemonkeysoftware.testapp.activity.UserActivity;
import com.mobilemonkeysoftware.testapp.image.ImageHelper;
import com.mobilemonkeysoftware.testapp.model.ui.UserItem;
import com.mobilemonkeysoftware.testapp.utilities.ColorUtil;

import butterknife.Bind;

/**
 * Created by AR on 03.03.2016.
 */
public class UserFragment extends BaseFragment {

    private static final String TAG = UserFragment.class.getName();

    public static final String EXTRA_ITEM = "extra_item";

    @Bind(R.id.text) TextView text;
    @Bind(R.id.image) ImageView image;

    @Nullable private UserItem mUser;

    @Override public int getLayoutResID() {
        return R.layout.fragment_user;
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            mUser = savedInstanceState.getParcelable(EXTRA_ITEM);
            Log.d(TAG, "Load data form savedInstanceState");
        } else {
            Intent activityIntent = getActivity().getIntent();
            if (activityIntent.hasExtra(UserActivity.EXTRA_USER)) {
                mUser = activityIntent.getParcelableExtra(UserActivity.EXTRA_USER);
                Log.d(TAG, "New data load");
            }
        }

        if (isAdded() && mUser != null) {
            UserItem.Type type = mUser.getType();
            text.setText(Html.fromHtml(getString(R.string.user_activity_name, mUser.getName(), type)));
            text.setTextColor(ColorUtil.getColor(getContext(), type.getColorResId()));
            ImageHelper.loadUrl(image, type.getLogoUrl());
        }
    }

    @Override public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(EXTRA_ITEM, mUser);
    }

}
