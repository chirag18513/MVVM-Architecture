package com.cygnet.projecttemplate.mvvm.views;

import com.cygnet.model.entities.response.LoginResponse;



/**
 * Name : LoginView
 *<br> Created by 1618 on 10/30/2017
 *<br> Modified by 1618 on 10/30/2017
 *<br> Purpose :
 * This Interface will contains all the methods which
 * are implemented by the {@link com.cygnet.projecttemplate.views.fragments.LoginFragment}
 * on API Responses.
 */
public interface LoginView extends BaseView {

    void  onLoginSuccess(LoginResponse mResponse);
}
