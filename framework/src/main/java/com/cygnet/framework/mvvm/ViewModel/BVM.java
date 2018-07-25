package com.cygnet.framework.mvvm.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cygnet.framework.mvvm.views.MvvmView;

/**
 * Created by cmpatel on 11/9/2017.
 */

public interface BVM {

    public ViewModel inIt(Context mContext, MvvmView mvpView);
}
