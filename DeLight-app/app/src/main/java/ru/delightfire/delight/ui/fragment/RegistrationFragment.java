package ru.delightfire.delight.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import ru.delightfire.delight.R;
import ru.delightfire.delight.util.AuthHelper;

/**
 * Created by sergei on 04.11.2015.
 */
public class RegistrationFragment extends Fragment {

    private EditText login;
    private EditText password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        login = (EditText) view.findViewById(R.id.acet_fragment_registration_input_login);
        password = (EditText) view.findViewById(R.id.acet_fragment_registration_input_password);

        AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.acb_fragment_registration_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLogin();
            }
        });

        AppCompatButton registrationConfirmButton = (AppCompatButton) view.findViewById(R.id.acb_fragment_registration_register_confirm);
        registrationConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginString = login.getText().toString();
                String passwordString = password.getText().toString();

                if (AuthHelper.checkLogin(loginString) && AuthHelper.checkPassword(passwordString)) {
                    final MaterialDialog materialDialog = new MaterialDialog.Builder(getActivity())
                            .title(R.string.loading)
                            .progressIndeterminateStyle(true)
                            .backgroundColorRes(R.color.mainBackground)
                            .widgetColorRes(R.color.white)
                            .progress(true, 0)
                            .show();

                    Ion.with(getActivity())
                            .load("POST", "http://delightfire-sunteam.rhcloud.com/app/androidQueries/create/create_user.php")
                            .setBodyParameter("login", loginString)
                            .setBodyParameter("password", passwordString)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    if (e == null && result != null) {
                                        materialDialog.dismiss();

                                        if (result.get("success").getAsInt() == 1) {
                                            new MaterialDialog.Builder(getActivity())
                                                    .title(R.string.success)
                                                    .content(R.string.registration_success)
                                                    .backgroundColorRes(R.color.mainBackground)
                                                    .positiveText(R.string.ok)
                                                    .positiveColorRes(R.color.white)
                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                        @Override
                                                        public void onClick(@NonNull MaterialDialog dialog,
                                                                            @NonNull DialogAction which) {
                                                            backToLogin();
                                                        }
                                                    })
                                                    .show();
                                        } else if (result.get("success").getAsInt() == 3) {
                                            new MaterialDialog.Builder(getActivity())
                                                    .title(R.string.error)
                                                    .content(R.string.user_exists)
                                                    .backgroundColorRes(R.color.mainBackground)
                                                    .positiveColorRes(R.color.white)
                                                    .positiveText(R.string.ok)
                                                    .show();
                                        }
                                    } else {
                                        new MaterialDialog.Builder(getActivity())
                                                .title(R.string.error)
                                                .content(R.string.check_connection)
                                                .backgroundColorRes(R.color.mainBackground)
                                                .positiveColorRes(R.color.white)
                                                .positiveText(R.string.ok)
                                                .show();
                                    }
                                }
                            });
                } else if (!AuthHelper.checkLogin(loginString)) {
                    new MaterialDialog.Builder(getActivity())
                            .title(R.string.error)
                            .content(R.string.login_format)
                            .backgroundColorRes(R.color.mainBackground)
                            .positiveColorRes(R.color.white)
                            .positiveText(R.string.ok)
                            .show();
                } else if (!AuthHelper.checkPassword(passwordString)) {
                    new MaterialDialog.Builder(getActivity())
                            .title(R.string.error)
                            .content(R.string.password_format)
                            .backgroundColorRes(R.color.mainBackground)
                            .positiveColorRes(R.color.white)
                            .positiveText(R.string.ok)
                            .show();
                }
            }
        });
        return view;
    }

    private void backToLogin() {
        FragmentManager manager = getActivity().getSupportFragmentManager();

        Fragment loginFragment = new LoginFragment();

        manager.beginTransaction()
                .replace(R.id.fl_activity_launch_content_frame, loginFragment)
                .commit();
    }
}
