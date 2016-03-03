package com.mobilemonkeysoftware.testapp;

import com.mobilemonkeysoftware.testapp.model.ui.UserItem;
import com.mobilemonkeysoftware.testapp.rx.RxHelper;

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
public class RxHelperUnitTest {

    @Test public void createGetUsers_isDataLoaded() throws Exception {

        Observable<List<UserItem>> observable = RxHelper.createGetUsers();
        TestSubscriber<List<UserItem>> subscriber = new TestSubscriber<>();
        observable.subscribe(subscriber);

        subscriber.assertCompleted();
        subscriber.assertNoErrors();
        List<List<UserItem>> items = subscriber.getOnNextEvents();
        assertNotNull(items);
        assertNotNull(items.get(0));
        assertNotNull(items.get(1));
        assertNotEmpty(items.get(0));
        assertNotEmpty(items.get(1));
    }

}
