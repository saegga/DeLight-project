package ru.delightfire.delight.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import ru.delightfire.delight.R;
import ru.delightfire.delight.utils.DelightContext;

/**
 * Created by sergei on 04.11.2015.
 */

public class RegistrationFragmentFirstStep extends Fragment {

    private DelightContext context = DelightContext.getInstance();

    private static final String TAG = RegistrationFragmentFirstStep.class.getSimpleName();
    private EditText inpKeyValue;
    private Button btnNextStep;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_key_check, container, false);
        inpKeyValue = (EditText)view.findViewById(R.id.edit_key_value);
        btnNextStep = (Button)view.findViewById(R.id.btnNextStep);

        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = inpKeyValue.getText().toString();
                try {
                    if(context.keyCheck(key)){
                        //// TODO: 09.11.2015 SecondStep
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }



}
