package ru.delightfire.delight.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.subject.DelightUser;
import ru.delightfire.delight.ui.activity.LaunchActivity;
import ru.delightfire.delight.util.UserAccount;

/**
 * Created by scaredChatsky on 21.01.2016.
 */
public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        final Button registerBtn = (Button) rootView.findViewById(R.id.btn_fragment_login_registration_start);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();

                Fragment registrationFragment = new RegistrationFragment();

                manager.beginTransaction()
                        .replace(R.id.fl_activity_launch_content_frame, registrationFragment)
                        .commit();
            }
        });

        final EditText inputEmail = (EditText) rootView.findViewById(R.id.acet_fragment_login_input_login);
        final EditText inputPassword = (EditText) rootView.findViewById(R.id.acet_fragment_login_input_password);

        Button loginBtn = (Button) rootView.findViewById(R.id.btn_fragment_login_auth);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final MaterialDialog materialDialog = new MaterialDialog.Builder(getActivity())
                        .title(R.string.loading)
                        .progressIndeterminateStyle(true)
                        .backgroundColorRes(R.color.mainBackground)
                        .widgetColorRes(R.color.white)
                        .progress(true, 0)
                        .show();

                final String login = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                Ion.with(getActivity())
                        .load("POST", "http://delightfire-sunteam.rhcloud.com/app/androidQueries/auth/db_user_check")
                        .setBodyParameter("login", login)
                        .setBodyParameter("password", password)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (result != null) {
                                    if (e != null) {
                                        Log.d("Login::", e.getMessage());
                                        return;
                                    }

                                    Log.d("Response:: ", result.toString());

                                    if (result.get("success").getAsInt() == 1) {
                                        int userId = result.get("user").getAsJsonObject().get("user_id").getAsInt();
                                        DelightUser user = new DelightUser(userId, login, password);
                                        UserAccount.getInstance().saveUser(getActivity().getApplicationContext(), user);
                                        ((LaunchActivity) getActivity()).redirectToMain();
                                    } else {
                                        new MaterialDialog.Builder(getActivity())
                                                .title(R.string.error)
                                                .content(R.string.wrong_auth)
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
                                materialDialog.dismiss();
                            }
                        });
            }
        });

        return rootView;
    }
}
