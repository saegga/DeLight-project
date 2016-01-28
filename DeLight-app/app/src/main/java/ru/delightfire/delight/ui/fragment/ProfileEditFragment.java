package ru.delightfire.delight.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.lang.ref.WeakReference;

import ru.delightfire.delight.R;
import ru.delightfire.delight.util.UserAccount;

/**
 * Created by sergei on 26.01.2016.
 */
public class ProfileEditFragment  extends Fragment {

    private AppCompatEditText userFirstName;
    private AppCompatEditText userLastName;
    private AppCompatEditText userPhone;
    private Button saveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile_edit, container, false);
        userFirstName = (AppCompatEditText) view.findViewById(R.id.acet_fragment_profile_edit_first_name);
        userLastName = (AppCompatEditText) view.findViewById(R.id.acet_fragment_profile_edit_last_name);
        userPhone = (AppCompatEditText) view.findViewById(R.id.acet_fragment_profile_edit_phone);

        saveButton = (Button) view.findViewById(R.id.btn_fragment_profile_save_info);

        loadUserInfo();
        saveButton.setOnClickListener(new View.OnClickListener() {
            int[] res = new int[]{
                    R.id.acet_fragment_profile_edit_first_name,
                    R.id.acet_fragment_profile_edit_last_name,
                    R.id.acet_fragment_profile_edit_phone
            };

            @Override
            public void onClick(View v) {
                boolean flag = true;
                for (int i = 0; i < 3; i++) {
                    AppCompatEditText checkField = ((AppCompatEditText) view.findViewById(res[i]));
                    String data = checkField.getText().toString();
                    if ("".equals(data)) {
                        checkField.setError("заполни поле");
                        flag = false;
                    }
                }
                if (flag) {
                    updateUserInfo();
                }
            }
        });
        return view;
    }
    private void loadUserInfo(){
        String id = String.valueOf(UserAccount.getInstance().getUser(getActivity()).getUserId());

        final MaterialDialog materialDialog = new MaterialDialog.Builder(getActivity())
                .title(R.string.loading)
                .progressIndeterminateStyle(true)
                .backgroundColorRes(R.color.mainBackground)
                .progress(true, 0)
                .show();
        Ion.with(getActivity())
                .load("POST", "http://delightfire-sunteam.rhcloud.com/app/androidQueries/get/get_user")
                .setBodyParameter("user_id", id)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        materialDialog.dismiss();
                        if (e != null) {
                            new MaterialDialog.Builder(getActivity())
                                    .title(R.string.error)
                                    .content(R.string.check_connection)
                                    .backgroundColorRes(R.color.mainBackground)
                                    .positiveColorRes(R.color.white)
                                    .positiveText(R.string.ok)
                                    .show();
                            return;
                        }
                        if (result.get("success").getAsInt() == 1) {

                            String firstNameStr = result.get("user").getAsJsonObject().get("first_name").isJsonNull() ? "" : result.get("user").getAsJsonObject().get("first_name").getAsString();
                            String lastNameStr = result.get("user").getAsJsonObject().get("last_name").isJsonNull() ? "" : result.get("user").getAsJsonObject().get("last_name").getAsString();
                            String phoneNumber = result.get("user").getAsJsonObject().get("phone").isJsonNull() ? "" : result.get("user").getAsJsonObject().get("phone").getAsString();

                            userFirstName.setText(firstNameStr);
                            userLastName.setText(lastNameStr);
                            userPhone.setText(phoneNumber);
                            // // TODO: 26.01.2016 сделать переход на фрагмент редактирования
                        }

                    }
                });

    }
    private void updateUserInfo(){
        String id = String.valueOf(UserAccount.getInstance().getUser(getActivity()).getUserId());
        final MaterialDialog materialDialog = new MaterialDialog.Builder(getActivity())
                .title(R.string.saving)
                .progressIndeterminateStyle(true)
                .backgroundColorRes(R.color.mainBackground)
                .progress(true, 0)
                .show();
        Ion.with(getActivity())
                .load("POST", "http://delightfire-sunteam.rhcloud.com/app/androidQueries/update/update_user")
                .setBodyParameter("user_id", id)
                .setBodyParameter("first_name", userFirstName.getText().toString())
                .setBodyParameter("last_name", userLastName.getText().toString())
                .setBodyParameter("phone", userPhone.getText().toString())
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        materialDialog.dismiss();
                        if(e != null){
                            new MaterialDialog.Builder(getActivity())
                                    .title(R.string.error)
                                    .content(R.string.check_connection)
                                    .backgroundColorRes(R.color.mainBackground)
                                    .positiveColorRes(R.color.white)
                                    .positiveText(R.string.ok)
                                    .show();
                            return;
                        }
                        if (result.get("success").getAsInt() == 1) {
                            Log.d("result update", result.toString());
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
                                            Fragment profileFragment = new MyProfileFragment();
                                            FragmentManager manager = getFragmentManager();
                                            manager.beginTransaction()
                                                    .replace(R.id.fl_activity_main_content, profileFragment)
                                                    .commit();
                                        }
                                    })
                                    .show();
                        }else{
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

}
