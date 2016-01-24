package ru.delightfire.delight.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import ru.delightfire.delight.R;

/**
 * Created by scaredChatsky on 24.01.2016.
 */
public class LoadingFragment extends Fragment{

    private MaterialDialog materialDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;

        rootView = inflater.inflate(R.layout.fragment_loading, container, false);

        materialDialog = new MaterialDialog.Builder(getActivity())
                .title(R.string.loading)
                .backgroundColorRes(R.color.mainBackground)
                .progressIndeterminateStyle(true)
                .widgetColorRes(R.color.white)
                .progress(true, 0)
                .show();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        materialDialog.dismiss();
    }
}
