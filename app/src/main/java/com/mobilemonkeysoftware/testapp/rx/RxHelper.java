package com.mobilemonkeysoftware.testapp.rx;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.mobilemonkeysoftware.testapp.model.GitHubUser;
import com.mobilemonkeysoftware.testapp.model.User;
import com.mobilemonkeysoftware.testapp.model.ui.UserItem;
import com.mobilemonkeysoftware.testapp.rest.response.DailymotionUsersResponse;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import static com.mobilemonkeysoftware.testapp.rest.Client.getDailymotionService;
import static com.mobilemonkeysoftware.testapp.rest.Client.getGitHubService;

/**
 * Created by AR on 03.03.2016.
 */
public final class RxHelper {

    private RxHelper() {
    }

    public static Observable<List<UserItem>> createGetUsers() {

        Observable<List<UserItem>> gitHubObservable = getGitHubService().getUsers().flatMap(new Func1<List<GitHubUser>, Observable<List<UserItem>>>() {
            @Override public Observable<List<UserItem>> call(final List<GitHubUser> gitHubUsers) {
                return createUsersItems(gitHubUsers, UserItem.Type.GITHUB);
            }
        });
        Observable<List<UserItem>> dailymotionObservable = getDailymotionService().getUsers().flatMap(new Func1<DailymotionUsersResponse, Observable<List<UserItem>>>() {
            @Override public Observable<List<UserItem>> call(final DailymotionUsersResponse dailymotionUsersResponse) {
                return createUsersItems(dailymotionUsersResponse.getList(), UserItem.Type.DAILYMOTION);
            }
        });
        return Observable.merge(gitHubObservable, dailymotionObservable);
    }

    private static Observable<List<UserItem>> createUsersItems(@Nullable final List<? extends User> users, @NonNull final UserItem.Type type) {
        return Observable.create(new Observable.OnSubscribe<List<UserItem>>() {
            @Override public void call(Subscriber<? super List<UserItem>> subscriber) {

                try {
                    List<UserItem> results = new ArrayList<>();
                    for (int i = 0, size = users != null ? users.size() : 0; i < size; i++) {
                        User user = users.get(i);
                        if (!isUserEmpty(user)) {
                            results.add(new UserItem(user.getUserName(), user.getUserAvatarUrl(), type));
                        }
                    }
                    subscriber.onNext(results);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    private static boolean isUserEmpty(@Nullable User user) {
        return user == null || TextUtils.isEmpty(user.getUserName()) || TextUtils.isEmpty(user.getUserAvatarUrl());
    }

}
