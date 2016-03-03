package com.mobilemonkeysoftware.testapp.rest;

import com.mobilemonkeysoftware.testapp.model.GitHubUser;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by AR on 03.03.2016.
 */
public interface GitHubService {

    @GET("users") Observable<List<GitHubUser>> getUsers();

}
