package com.mobilemonkeysoftware.testapp.image;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.mobilemonkeysoftware.testapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by AR on 03.03.2016.
 */
public final class ImageHelper {

    private static final String TAG = ImageHelper.class.getName();

    private ImageHelper() {
    }

    public static void loadUrl(@NonNull final ImageView view, @NonNull final String url) {

        Picasso.with(view.getContext()).load(url).error(R.mipmap.ic_launcher).into(view, new Callback() {
            @Override public void onSuccess() {
                Log.d(TAG, "Url load success, url=" + url);
            }

            @Override public void onError() {
                Log.d(TAG, "Url load error, url=" + url);
            }
        });
    }

}
