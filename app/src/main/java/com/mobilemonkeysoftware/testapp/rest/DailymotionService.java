package com.mobilemonkeysoftware.testapp.rest;

import com.mobilemonkeysoftware.testapp.rest.response.DailymotionUsersResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by AR on 03.03.2016.
 */
public interface DailymotionService {

    @GET("users?fields=avatar_360_url,username") Observable<DailymotionUsersResponse> getUsers();

}
