package ru.delightfire.delight.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.subject.DelightUser;
import ru.delightfire.delight.ui.activity.MainActivity;
import ru.delightfire.delight.util.UserAccount;

/**
 * Created by sergei on 26.01.2016.
 */
public class EditProfileFragment extends Fragment {

    private AppCompatEditText firstName;
    private AppCompatEditText lastName;
    private AppCompatEditText phone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        firstName = (AppCompatEditText) view.findViewById(R.id.acet_fragment_profile_edit_first_name);
        lastName = (AppCompatEditText) view.findViewById(R.id.acet_fragment_profile_edit_last_name);
        phone = (AppCompatEditText) view.findViewById(R.id.acet_fragment_profile_edit_phone);

        final DelightUser user = UserAccount.getInstance().getUser(getActivity());

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        phone.setText(user.getPhone());

        AppCompatButton cancelButton = (AppCompatButton) view.findViewById(R.id.acb_fragment_edit_profile_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToProfile();
            }
        });

        AppCompatButton saveButton = (AppCompatButton) view.findViewById(R.id.acb_fragment_edit_profile_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstName.getText().toString().equals(user.getFirstName()) ||
                        !lastName.getText().toString().equals(user.getLastName()) ||
                        !phone.getText().toString().equals(user.getPhone())) {
                    updateUserInfo();
                } else {
                    backToProfile();
                }
            }
        });

        return view;
    }

    private void updateUserInfo() {

        String id = String.valueOf(UserAccount.getInstance().getUser(getActivity()).getUserId());

        final MaterialDialog materialDialog = new MaterialDialog.Builder(getActivity())
                .title(R.string.saving)
                .progressIndeterminateStyle(true)
                .backgroundColorRes(R.color.mainBackground)
                .widgetColorRes(R.color.white)
                .progress(true, 0)
                .show();

        Ion.with(getActivity())
                .load("POST", "http://delightfire-sunteam.rhcloud.com/app/androidQueries/update/update_user")
                .setBodyParameter("user_id", id)
                .setBodyParameter("first_name", firstName.getText().toString())
                .setBodyParameter("last_name", lastName.getText().toString())
                .setBodyParameter("phone", phone.getText().toString())
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        materialDialog.dismiss();

                        if (e != null && result == null) {
                            new MaterialDialog.Builder(getActivity())
                                    .title(R.string.error)
                                    .content(R.string.check_connection)
                                    .backgroundColorRes(R.color.mainBackground)
                                    .positiveColorRes(R.color.white)
                                    .positiveText(R.string.ok)
                                    .show();
                        } else if (result.get("success").getAsInt() == 1) {
                            new MaterialDialog.Builder(getActivity())
                                    .title(R.string.success)
                                    .content(R.string.success_update_user_info)
                                    .positiveText(R.string.ok)
                                    .backgroundColorRes(R.color.mainBackground)
                                    .positiveColorRes(R.color.white)
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(MaterialDialog dialog, DialogAction which) {
                                            dialog.dismiss();
                                            ((MainActivity) getActivity()).setHardReloadProfile(true);
                                            backToProfile();
                                        }
                                    })
                                    .show();
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
    }

    public void backToProfile() {
        Fragment profileFragment = new ProfileFragment();
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fl_activity_main_content, profileFragment)
                .commit();
    }

}
