package com.cygnet.framework.utils;

/**
 * Created by hrdudhat on 3/2/2016.
 * <p/>
 * Contains all constants use in Model Module.
 */
public class Constants {
    public static final String BASE_URL = "http://reqres.in/api/";
    public static final java.lang.String DATABASE_NAME = "template_db";

    public static class ApiHeader {
    }

    public static class ApiCodes {
        public static final int SUCCESS = 1;
        public static final int ERROR = 0;

        public static final String ERROR_KEY = "code";
        public static final String ERROR_MESSAGE_KEY = "error";
    }

    public static class ApiMethods {
        public static final String REGISTER_URL = "register";
        public static final String LOGIN_URL = "login";
        public static final String USER_LIST_URL = "users";
        public static final String ALBUM_LIST_URL = "https://jsonplaceholder.typicode.com/albums";
    }
}
