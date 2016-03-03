package com.mobilemonkeysoftware.testapp.bus;

import android.support.annotation.NonNull;

import com.mobilemonkeysoftware.testapp.model.ui.UserItem;

import lombok.Data;

/**
 * Created by AR on 03.03.2016.
 */
@Data
public final class UserRemovedEvent {

    private UserItem user;

    public UserRemovedEvent(@NonNull UserItem user) {
        this.user = user;
    }

}
