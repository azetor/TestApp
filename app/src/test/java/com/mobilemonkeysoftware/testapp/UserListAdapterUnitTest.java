package com.mobilemonkeysoftware.testapp;

import com.mobilemonkeysoftware.testapp.activity.fragment.UserListFragment;
import com.mobilemonkeysoftware.testapp.adapter.UserListAdapter;
import com.mobilemonkeysoftware.testapp.model.ui.UserItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static android.test.MoreAsserts.assertEmpty;
import static android.test.MoreAsserts.assertNotEmpty;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.assertTrue;

/**
 * Created by AR on 03.03.2016.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class UserListAdapterUnitTest {

    private UserListFragment fragment;
    private List<UserItem> items;

    @Before public void setUp() {

        fragment = new UserListFragment();
        items = new ArrayList<>();
        items.add(new UserItem("1", "1", UserItem.Type.GITHUB));
        items.add(new UserItem("2", "2", UserItem.Type.DAILYMOTION));
        items.add(new UserItem("3", "3", UserItem.Type.GITHUB));
    }

    @Test public void createAdapter_isNotNull() throws Exception {

        UserListAdapter adapter = new UserListAdapter(fragment, new ArrayList<UserItem>());
        assertNotNull(adapter);
    }

    @Test public void createAdapter_operations() throws Exception {

        UserListAdapter adapter = new UserListAdapter(fragment, items);
        assertEquals(items.size(), adapter.getItemCount());
        assertNotNull(adapter.getItems());
        assertNotEmpty(adapter.getItems());
        adapter.remove(items.remove(0));
        assertEquals(items.size(), adapter.getItemCount());
        assertNotNull(adapter.getItems());
        assertNotEmpty(adapter.getItems());
        adapter.clear();
        assertEmpty(adapter.getItems());
        assertEquals(0, adapter.getItemCount());
        assertTrue(adapter.isEmpty());
    }

    @Test public void createAdapter_sameItems() throws Exception {

        UserListAdapter sameAdapter = new UserListAdapter(fragment, items);
        assertSame(items, sameAdapter.getItems());

        UserListAdapter notSameAdapter = new UserListAdapter(fragment, new ArrayList<UserItem>());
        notSameAdapter.addAll(items);
        assertNotSame(items, notSameAdapter.getItems());
    }

}
