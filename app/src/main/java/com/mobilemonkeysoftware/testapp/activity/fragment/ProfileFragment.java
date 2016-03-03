package com.mobilemonkeysoftware.testapp.activity.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobilemonkeysoftware.testapp.BuildConfig;
import com.mobilemonkeysoftware.testapp.R;
import com.mobilemonkeysoftware.testapp.image.ImageHelper;

import butterknife.Bind;

/**
 * Created by AR on 03.03.2016.
 */
public class ProfileFragment extends BaseFragment {

    @Bind(R.id.image) ImageView image;
    @Bind(R.id.version) TextView version;

    @Override public int getLayoutResID() {
        return R.layout.fragment_profile;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageHelper.loadUrl(image, BuildConfig.PROFILE_IMAGE_URL);
        version.setText(getString(R.string.version, getString(R.string.app_name), BuildConfig.VERSION_NAME));
    }

}
