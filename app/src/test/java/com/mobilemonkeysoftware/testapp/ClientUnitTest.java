package com.mobilemonkeysoftware.testapp;

import com.mobilemonkeysoftware.testapp.model.GitHubUser;
import com.mobilemonkeysoftware.testapp.rest.Client;
import com.mobilemonkeysoftware.testapp.rest.response.DailymotionUsersResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static android.test.MoreAsserts.assertNotEmpty;
import static org.junit.Assert.assertNotNull;

/**
 * Created by AR on 03.03.2016.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ClientUnitTest {

    @Test public void servicesInstances() throws Exception {

        assertNotNull(Client.getGitHubService());
        assertNotNull(Client.getDailymotionService());
    }

    @Test public void gitHubService_isDataLoaded() throws Exception {

        Observable<List<GitHubUser>> observable = Client.getGitHubService().getUsers();
        TestSubscriber<List<GitHubUser>> subscriber = new TestSubscriber<>();
        observable.subscribe(subscriber);

        subscriber.assertCompleted();
        subscriber.assertNoErrors();
        List<List<GitHubUser>> users = subscriber.getOnNextEvents();
        assertNotNull(users);
        assertNotNull(users.get(0));
        assertNotEmpty(users);
        assertNotEmpty(users.get(0));
    }

    @Test public void dailymotionService_isDataLoaded() throws Exception {

        Observable<DailymotionUsersResponse> observable = Client.getDailymotionService().getUsers();
        TestSubscriber<DailymotionUsersResponse> subscriber = new TestSubscriber<>();
        observable.subscribe(subscriber);

        subscriber.assertCompleted();
        subscriber.assertNoErrors();
        List<DailymotionUsersResponse> responses = subscriber.getOnNextEvents();
        assertNotNull(responses);
        assertNotNull(responses.get(0));
        assertNotEmpty(responses);
        assertNotNull(responses.get(0).getList());
        assertNotEmpty(responses.get(0).getList());
    }

}
