package com.cygnet.projecttemplate.mvvm.viewModels;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.cygnet.framework.mvvm.ViewModel.BVM;
import com.cygnet.framework.mvvm.views.MvvmView;

/**
 * Created by cmpatel on 11/9/2017.
 */

public class BaseViewModel extends ViewModel implements BVM {

    @Override
    public ViewModel inIt(Context mContext, MvvmView mvpView) {
        return this;
    }
}
