package ru.delightfire.delight.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
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

import java.lang.ref.WeakReference;

import ru.delightfire.delight.R;

/**
 * Created by sergei on 26.01.2016.
 */
public class ProfileEditFragment  extends Fragment {

    private AppCompatEditText userFirstName;
    private AppCompatEditText userLastName;
    private AppCompatEditText userPhone;
    private Button saveButton;
    private TextInputLayout inpFirstName, inpLastName, inpPhone;
    public static final int MIN_LENGTH_TEXT = 1;
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
        inpFirstName = (TextInputLayout) view.findViewById(R.id.inp_first_name);
        inpLastName = (TextInputLayout) view.findViewById(R.id.inp_last_name);
        inpPhone = (TextInputLayout) view.findViewById(R.id.inp_phone);

        saveButton.setOnClickListener(new View.OnClickListener() {
            int[] res = new int[]{
                    R.id.acet_fragment_profile_edit_first_name,
                    R.id.acet_fragment_profile_edit_last_name,
                    R.id.acet_fragment_profile_edit_phone
            };
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 3; i++) {
                    AppCompatEditText checkField = ((AppCompatEditText) view.findViewById(res[i]));
                    String data = checkField.getText().toString();
                    if("".equals(data)){
                        checkField.setError("заполни поле");
                    }
                }
            }
        });
        return view;
    }
    private boolean shouldShowError(AppCompatEditText edit){
        int length = edit.getText().length();
        if(length < MIN_LENGTH_TEXT){
            return true;
        }
        return false;
    }


}
