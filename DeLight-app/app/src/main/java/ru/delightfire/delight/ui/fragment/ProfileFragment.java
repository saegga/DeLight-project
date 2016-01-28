package ru.delightfire.delight.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.subject.DelightUser;
import ru.delightfire.delight.ui.activity.MainActivity;
import ru.delightfire.delight.util.DelightUserDeserializer;
import ru.delightfire.delight.util.UserAccount;

/**
 * Created by sergei on 12.11.2015.
 */
public class ProfileFragment extends Fragment {

    private TextView firstName;
    private TextView lastName;
    private TextView phone;
    private TextView role;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        setHasOptionsMenu(true);

        firstName = (TextView) view.findViewById(R.id.profile_text_first_name);
        lastName = (TextView) view.findViewById(R.id.profile_text_last_name);
        phone = (TextView) view.findViewById(R.id.profile_text_phone);
        role = (TextView) view.findViewById(R.id.profile_text_status);

        if (((MainActivity) getActivity()).isHardReloadProfile()) {
            loadUser();
        } else {
            setUserData();
        }

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar toolbar = ((MainActivity) getActivity()).getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(R.string.profile);
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_profile_toolbar, menu);
        menu.getItem(0).setIcon(new IconicsDrawable(getActivity())
                .icon(FontAwesome.Icon.faw_pencil_square_o)
                .colorRes(R.color.white)
                .sizeDp(20));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager manager = getFragmentManager();
        Fragment editProfile = new EditProfileFragment();
        manager.beginTransaction()
                .replace(R.id.fl_activity_main_content, editProfile)
                .commit();

        return super.onOptionsItemSelected(item);
    }

    private void loadUser() {

        final String userId = String.valueOf(UserAccount.getInstance().getUser(getActivity()).getUserId());

        final MaterialDialog materialDialog = new MaterialDialog.Builder(getActivity())
                .title(R.string.loading)
                .progressIndeterminateStyle(true)
                .backgroundColorRes(R.color.mainBackground)
                .widgetColorRes(R.color.white)
                .progress(true, 0)
                .show();

        Ion.with(getActivity())
                .load("POST", "http://delightfire-sunteam.rhcloud.com/app/androidQueries/get/get_user")
                .setBodyParameter("user_id", userId)
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

                            Gson gson = new GsonBuilder()
                                    .registerTypeAdapter(DelightUser.class, new DelightUserDeserializer(getActivity()))
                                    .create();

                            UserAccount.getInstance().setUser(gson.fromJson(result.get("user"), DelightUser.class));

                            ((MainActivity) getActivity()).setHardReloadProfile(false);

                            setUserData();
                        }
                    }
                });
    }

    public void setUserData() {
        DelightUser user = UserAccount.getInstance().getUser(getActivity());

        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        phone.setText(user.getPhone());
        role.setText(user.getRole().toString());
    }
}
