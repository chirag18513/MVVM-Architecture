package com.cygnet.projecttemplate.mvvm.views;

/**
 * Name : LoginView
 *<br> Created by 1618 on 10/30/2017
 *<br> Modified by 1618 on 10/30/2017
 *<br> Purpose :
 * This Interface will contains all the methods which
 * are implemented by the {@link com.cygnet.projecttemplate.views.fragments.LoginFragment}
 * on API Responses.
 */
public interface DetailView extends BaseView {

    void onAlbumUpdate();
    void onAlbumDelete();
    void onUserUpdate();
    void onUserDelete();

}
