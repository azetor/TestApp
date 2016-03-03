package com.mobilemonkeysoftware.testapp.rest.response;

import com.google.gson.annotations.SerializedName;
import com.mobilemonkeysoftware.testapp.model.DailymotionUser;

import java.util.List;

import lombok.Data;

/**
 * Created by AR on 03.03.2016.
 */
@Data
public class DailymotionUsersResponse {

    @SerializedName("list")
    private List<DailymotionUser> list;

}
