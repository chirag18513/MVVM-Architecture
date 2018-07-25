package com.cygnet.projecttemplate.views.fragments;

import android.app.Dialog;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cygnet.framework.model.APIError;
import com.cygnet.framework.utils.AppLog;
import com.cygnet.framework.utils.CommonUtils;
import com.cygnet.framework.utils.StringUtils;
import com.cygnet.framework.utils.network.NetworkRetryCallback;
import com.cygnet.projecttemplate.mvvm.views.BaseView;


/**
 * Name : BaseFragment
 *<br> Created by 1618 on 10/30/2017
 *<br> Modified by 1618 on 10/30/2017
 *<br> Purpose :
 *  This class is extended by all the fragments.
 *      it contains the method which are implemented by the base view as well as
 *      get current context etc...
 */
public class BaseFragment extends Fragment implements BaseView {

    String TAG = "BaseFragment";
    private Dialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppLog.e(TAG,"onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        AppLog.e(TAG,"onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        AppLog.e(TAG,"onDestroy");
        super.onDestroy();
    }

    public <T extends ViewModel> T getViewModel(Class<T> viewModelClass){
        return ViewModelProviders.of(this).get(viewModelClass);
    }
/*
    public void setSelectedNavItem(NavItemModel sNavItemModel) {
        AppLog.d(TAG, "setSelectedNavItem():" + sNavItemModel.getFragment().getSimpleName());
        FragmentTransaction mFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        loadFragment(sNavItemModel, mFragmentTransaction);
        mFragmentTransaction.commit();
    }

    public void loadFragment(NavItemModel aNavItemModel, FragmentTransaction aFragmentTransaction) {
        Fragment fragment;

        if (prevTag != null && prevTag != aNavItemModel.getTag()) {
            fragment = getActivity().getSupportFragmentManager().findFragmentByTag(prevTag);
            if (fragment != null) {
                aFragmentTransaction.detach(fragment);
            }
        }

        fragment = getActivity().getSupportFragmentManager().findFragmentByTag(aNavItemModel.getTag());

        if (fragment == null) {
            fragment = Fragment.instantiate(getContext(), aNavItemModel.getFragment().getName(), aNavItemModel.getBundle());
            aFragmentTransaction.replace(R.id.flContainer, fragment, aNavItemModel.getTag());
        } else if (fragment.isDetached()) {
            fragment = Fragment.instantiate(getContext(), aNavItemModel.getFragment().getName(), aNavItemModel.getBundle());
            aFragmentTransaction.replace(R.id.flContainer, fragment, aNavItemModel.getTag());
        } else {
            fragment = Fragment.instantiate(getContext(), aNavItemModel.getFragment().getName(), aNavItemModel.getBundle());
            aFragmentTransaction.replace(R.id.flContainer, fragment, aNavItemModel.getTag());
        }

        aFragmentTransaction.addToBackStack(aNavItemModel.getTag());
        prevTag = aNavItemModel.getFragment().getName();
    }*/



    /**
     * Name : BaseFragment getCurrentContext
     *<br> Created by 1618 on 10/30/2017
     *<br> Modified by 1618 on 10/30/2017
     *<br> Purpose :
     *  This method is used to get current context of the application.
     *
     * @return  : {@link Context} of current fragment/activity.
     */
    @Override
    public Context getCurrentContext() {
        return getActivity();
    }


    /**
     * Name : BaseFragment onStart
     *<br> Created by 1618 on 10/30/2017
     *<br> Modified by 1618 on 10/30/2017
     *<br> Purpose : onStart of the fragment register the event bus.
     */
    @Override
    public void onStart() {
        AppLog.e(TAG, "onStart");
        super.onStart();
    }


    /**
     * Name : BaseFragment onStop
     *<br> Created by 1618 on 10/30/2017
     *<br> Modified by 1618 on 10/30/2017
     *<br> Purpose : OnStop of the fragment unregister eventbus.
     */
    @Override
    public void onStop() {
        AppLog.e(TAG, "onStop");
        super.onStop();
    }



    /**
     * Name : BaseFragment showLoader
     *<br> Created by 1618 on 10/30/2017
     *<br> Modified by 1618 on 10/30/2017
     *<br> Purpose :This method is used to show loader when any API calls. or in any other place
     * in fragment.
     * @param aMessage  : message which we want to display in loaders.
     */
    @Override
    public void showLoader(String... aMessage) {
        mProgressDialog = CommonUtils.showProgressDialog(getCurrentContext(), aMessage);
        mProgressDialog.show();
    }


    /**
     * Name : BaseFragment hideLoader
     *<br> Created by 1618 on 10/30/2017
     *<br> Modified by 1618 on 10/30/2017
     *<br> Purpose :This method will used by fragment to hide loaders.
     */
    @Override
    public void hideLoader() {
        CommonUtils.hideProgressDialog(mProgressDialog);
    }


    /**
     * Name : BaseFragment noInternetConnection
     *<br> Created by 1618 on 10/30/2017
     *<br> Modified by 1618 on 10/30/2017
     *<br> Purpose :
     * This method will handle the no InternetConnection. before making
     * any Network API Call.
     *
     * @param aNetworkRetryCallback : callback to handle the retry mechanism. when internet comes back.

     */
    @Override
    public void noInternetConnection(NetworkRetryCallback aNetworkRetryCallback) {
        CommonUtils.showNetworkDialog(getCurrentContext(), aNetworkRetryCallback);
    }



    /**
     * Name : BaseFragment apiError
     *<br> Created by 1618 on 10/30/2017
     *<br> Modified by 1618 on 10/30/2017
     *<br> Purpose :This method is used to show the error message
     *
     * @param aError Object of {@link APIError } which contains the error message and ErrorCode.
     */
    @Override
    public void apiError(APIError aError) {
        if (aError != null && !StringUtils.isTrimmedEmpty(aError.getStatusMessage()))
            Toast.makeText(getCurrentContext(), aError.getStatusMessage(), Toast.LENGTH_LONG).show();
    }


}
