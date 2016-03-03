package com.mobilemonkeysoftware.testapp.activity.fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.mobilemonkeysoftware.testapp.R;
import com.mobilemonkeysoftware.testapp.adapter.UserListAdapter;
import com.mobilemonkeysoftware.testapp.bus.EmptyAdapterEvent;
import com.mobilemonkeysoftware.testapp.bus.RefreshDataEvent;
import com.mobilemonkeysoftware.testapp.bus.UserRemovedEvent;
import com.mobilemonkeysoftware.testapp.model.ui.UserItem;
import com.mobilemonkeysoftware.testapp.rx.RxHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AR on 03.03.2016.
 */
public class UserListFragment extends BaseListFragment<UserItem, UserListAdapter> {

    private static final String TAG = UserListFragment.class.getName();

    @Override public int getSpanResId() {
        return R.integer.user_list_span_count;
    }

    @Override public int getEmptyListTextResId() {
        return R.string.empty_list_users;
    }

    @Override public boolean canRefreshOnSwipe() {
        return true;
    }

    @Override public void refreshOnSwipe() {
        startItemsLoad();
    }

    @Override public void startItemsLoad() {

        Log.d(TAG, "startItemsLoad");
        refresh(true);
        final List<UserItem> items = new ArrayList<>();
        Observable<List<UserItem>> userItemsObservable = RxHelper.createGetUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        userItemsObservable.subscribe(new Subscriber<List<UserItem>>() {
            @Override public void onCompleted() {

                Log.d(TAG, "onCompleted");
                clearItems();
                addItems(items);
                refresh(false);
            }

            @Override public void onError(Throwable e) {

                Log.e(TAG, "Users download error", e);
                updateEmptyInfo();
                refresh(false);
            }

            @Override public void onNext(List<UserItem> userItems) {

                Log.d(TAG, "onNext=" + userItems.toString());
                items.addAll(userItems);
            }
        });
    }

    @NonNull @Override public UserListAdapter createEmptyAdapter() {
        return new UserListAdapter(UserListFragment.this, new ArrayList<UserItem>());
    }

    /**
     * Handler for {@link com.mobilemonkeysoftware.testapp.bus.RefreshDataEvent}
     *
     * @param event Event {@link com.mobilemonkeysoftware.testapp.bus.RefreshDataEvent}
     */
    @Subscribe public void onEventMainThread(RefreshDataEvent event) {
        startItemsLoad();
    }

    /**
     * Handler for {@link com.mobilemonkeysoftware.testapp.bus.EmptyAdapterEvent}
     *
     * @param event Event {@link com.mobilemonkeysoftware.testapp.bus.EmptyAdapterEvent}
     */
    @Subscribe public void onEventMainThread(EmptyAdapterEvent event) {
        updateEmptyInfo();
    }

    /**
     * Handler for {@link com.mobilemonkeysoftware.testapp.bus.UserRemovedEvent}
     *
     * @param event Event {@link com.mobilemonkeysoftware.testapp.bus.UserRemovedEvent}
     */
    @Subscribe public void onEventMainThread(UserRemovedEvent event) {

        if (isAdded() && getView() != null) {
            UserItem user = event.getUser();
            String text = getString(R.string.user_activity_user_removed, user.getName(), user.getType());
            Snackbar.make(getView(), text, Snackbar.LENGTH_LONG).show();
        }
    }

}
