package com.cygnet.projecttemplate.views.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cygnet.framework.utils.AppLog;
import com.cygnet.framework.utils.StringUtils;
import com.cygnet.model.entities.NavItemModel;
import com.cygnet.model.entities.response.RegisterResponse;
import com.cygnet.projecttemplate.R;
import com.cygnet.projecttemplate.databinding.FragmentRegisterBinding;
import com.cygnet.projecttemplate.mvvm.viewModels.RegisterViewModel;
import com.cygnet.projecttemplate.mvvm.views.RegisterView;
import com.cygnet.projecttemplate.views.activities.AppBaseActivity;


/**
 * Name : RegisterFragment
 * <br> Created by 1618 on 10/30/2017
 * <br> Modified by 1618 on 10/30/2017
 * <br> Purpose :
 * This class is used for register fragment.
 * to show the register form to create a new member on our application.
 */
public class RegisterFragment extends BaseFragment implements RegisterView {


    private View mRootView;
    private FragmentRegisterBinding mBinding;
    private RegisterViewModel mRegViewModel;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContext = getActivity();

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        mRootView = mBinding.getRoot();
        mBinding.setEvent(this);

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRegViewModel = (RegisterViewModel) getViewModel(RegisterViewModel.class)
                .inIt(mContext, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onClickRegister() {
        validateForm();
    }


    /**
     * Name : RegisterFragment validateForm
     * <br> Created by 1618 on 10/30/2017
     * <br> Modified by 1618 on 10/30/2017
     * <br> Purpose :
     * This method will validate the form locally.
     * and if any errors found then we will set errors to respected view.
     */
    private void validateForm() {
        if (StringUtils.isTrimmedEmpty(mBinding.fRegisterEdtEmail.getText().toString())) {
            mBinding.inputLayoutFirstname.setError("Please enter E-Mail Address");
        } else if (!StringUtils.isValidEmail(mBinding.fRegisterEdtEmail.getText().toString())) {
            mBinding.inputLayoutFirstname.setError("Please enter valid email address.");
        } else if (StringUtils.isTrimmedEmpty(mBinding.fRegisterEdtPassword.getText().toString())) {
            mBinding.inputLayoutPassword.setError("Please enter password");
        } else if (!StringUtils.isEquals(mBinding.fRegisterEdtPassword.getText().toString(), mBinding.fRegisterEdtRePassword.getText().toString())) {
            mBinding.inputLayoutRepassword.setError("Both password should be equal.");
        } else {
            mRegViewModel.loadRegUserResponse(mBinding.fRegisterEdtEmail.getText().toString(), mBinding.fRegisterEdtPassword.getText().toString());
        }
    }


    /**
     * Name : RegisterFragment onUserRegistered
     * <br> Created by 1618 on 10/30/2017
     * <br> Modified by 1618 on 10/30/2017
     * <br> Purpose :
     * This method will be executed by the register presenter.
     * when user registered successfully and navigates user to home page.
     *
     * @param mResponse :  Object of {@link RegisterResponse}
     */
    @Override
    public void onUserRegistered(RegisterResponse mResponse) {
        AppLog.e(TAG, "RegisterResponse : " + mResponse.getToken());
        Log.d(TAG, "User registerd successfully");
        NavItemModel mChangeAssessment = new NavItemModel(
                R.mipmap.ic_launcher,
                HomeFragment.class.getSimpleName(),
                HomeFragment.class,
                HomeFragment.class.getSimpleName(), null);

        ((AppBaseActivity) getActivity()).clearAllTopFragment();

        ((AppBaseActivity) getActivity()).setFragment(mChangeAssessment);
    }
}
