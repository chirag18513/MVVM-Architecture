package com.cygnet.projecttemplate.utils;

/**
 * @Author : CMPatel
 * @Created_On : 10/6/2017
 * @Purpose : Constants are delclared here.
 */
public class Constants {

    public interface IntentKey {
        String KEY_USERID = "UserId";
        String KEY_ALBUMID = "AlbumId";
    }

    public interface Database {
        int INSERT_USERLIST = 1;
        int INSERT_ALBUMLIST = 2;
        int GET_ALBUM = 3;
        int GET_USER = 4;
        int UPDATE_ALBUM = 5;
        int DELETE_ALBUM = 6;
        int UPDATE_USER = 7;
        int DELETE_USER = 8;
    }
}
