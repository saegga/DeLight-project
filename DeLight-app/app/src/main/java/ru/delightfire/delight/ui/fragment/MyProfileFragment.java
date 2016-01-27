package ru.delightfire.delight.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import ru.delightfire.delight.R;
import ru.delightfire.delight.ui.activity.MainActivity;
import ru.delightfire.delight.util.UserAccount;

/**
 * Created by sergei on 12.11.2015.
 */
public class MyProfileFragment extends Fragment {

    private TextView login;
    private TextView firstName;
    private TextView lastName;
    private TextView phone;
    private TextView status;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);
        login = (TextView) view.findViewById(R.id.profile_text_login);
        firstName = (TextView) view.findViewById(R.id.profile_text_first_name);
        lastName = (TextView) view.findViewById(R.id.profile_text_last_name);
        phone = (TextView) view.findViewById(R.id.profile_text_phone);
        status = (TextView) view.findViewById(R.id.profile_text_status);

        loadingUser();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Профиль");

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_profile_toolbar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        FragmentManager manager = getFragmentManager();
        Fragment profileEdit = new ProfileEditFragment();
        manager.beginTransaction()
                .replace(R.id.fl_activity_main_content, profileEdit)
                .commit();
        return super.onOptionsItemSelected(item);
    }

    private void loadingUser(){
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
                        if(result.get("success").getAsInt() == 1){
                            Log.d("Response useeer:: ", result.toString());

                            String firstNameStr = result.get("user").getAsJsonObject().get("first_name").isJsonNull() ? "" : result.get("user").getAsJsonObject().get("first_name").getAsString();
                            String lastNameStr =  result.get("user").getAsJsonObject().get("last_name").isJsonNull() ? "" : result.get("user").getAsJsonObject().get("last_name").getAsString();
                            String phoneNumber =  result.get("user").getAsJsonObject().get("phone").isJsonNull() ? "" : result.get("user").getAsJsonObject().get("phone").getAsString();
                            login.setText(result.get("user").getAsJsonObject().get("login").getAsString());
                            firstName.setText(firstNameStr);
                            lastName.setText(lastNameStr);
                            phone.setText(phoneNumber);
                            status.setText(result.get("user").getAsJsonObject().get("role").getAsString());
                            // // TODO: 26.01.2016 сделать переход на фрагмент редактирования + кнопку в тулбаре редактир-я
                        }

                    }
                });
    }
}
