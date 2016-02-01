package ru.delightfire.delight.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import ru.delightfire.delight.R;
import ru.delightfire.delight.entity.subject.DelightEvent;
import ru.delightfire.delight.entity.support.DelightPageInfo;
import ru.delightfire.delight.ui.activity.DetailEventActivity;
import ru.delightfire.delight.ui.activity.MainActivity;
import ru.delightfire.delight.util.UserAccount;

/**
 * Created by scaredChatsky on 21.01.2016.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<DelightPageInfo> pages = new ArrayList<>();

    private Context context;

    private MainActivity activity;

    public ViewPagerAdapter(List<DelightPageInfo> pages, Context context) {
        this.pages = pages;
        this.context = context;
        activity = (MainActivity) context;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.element_schedule_page, container, false);
        final ListView listView = (ListView) itemView.findViewById(R.id.lv_element_schedule_page);

        listView.setDivider(context.getResources().getDrawable(android.R.color.transparent));
        listView.setDividerHeight(0);
        listView.setLongClickable(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("LongClick: ", position + "");
                final DelightEvent event = ((EventAdapter) pages.get(activity
                        .getCurrentViewPagerPosition()).getAdapter()).getEvents().get(position);

                if (UserAccount.getInstance().hasDeleteEventPermission(event)) {
                    new MaterialDialog.Builder(context)
                            .title("Удалить?")
                            .backgroundColorRes(R.color.mainBackground)
                            .positiveText(R.string.delete)
                            .positiveColorRes(R.color.white)
                            .negativeText(R.string.cancel)
                            .negativeColorRes(R.color.white)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                    final MaterialDialog materialDialog = new MaterialDialog.Builder(activity)
                                            .title(R.string.loading)
                                            .progressIndeterminateStyle(true)
                                            .backgroundColorRes(R.color.mainBackground)
                                            .widgetColorRes(R.color.white)
                                            .progress(true, 0)
                                            .show();

                                    String table;
                                    if (activity.getCurrentViewPagerPosition() == 0) {
                                        table = "current_trainings";
                                    } else if (activity.getCurrentViewPagerPosition() == 1) {
                                        table = "shows";
                                    } else {
                                        table = "meetings";
                                    }

                                    Ion.with(context)
                                            .load("POST", "http://delightfire-sunteam.rhcloud.com/app/androidQueries/delete/delete_event")
                                            .setBodyParameter("table", table)
                                            .setBodyParameter("event_id", String.valueOf(event.getEventId()))
                                            .asJsonObject()
                                            .setCallback(new FutureCallback<JsonObject>() {
                                                @Override
                                                public void onCompleted(Exception e, JsonObject result) {
                                                    materialDialog.dismiss();
                                                    if (result != null && e == null) {
                                                        if (result.get("success").getAsInt() == 1) {
                                                            new MaterialDialog.Builder(context)
                                                                    .title(R.string.success)
                                                                    .content(R.string.success_deleted)
                                                                    .backgroundColorRes(R.color.mainBackground)
                                                                    .positiveColorRes(R.color.white)
                                                                    .dismissListener(new DialogInterface.OnDismissListener() {
                                                                        @Override
                                                                        public void onDismiss(DialogInterface dialog) {
                                                                            activity.setHardReloadDrawer(true);
                                                                            activity.getDrawer()
                                                                                    .setSelectionAtPosition(activity.getCurrentDrawerPosition());
                                                                        }
                                                                    })
                                                                    .positiveText(R.string.ok)
                                                                    .show();
                                                        }
                                                    } else {
                                                        new MaterialDialog.Builder(context)
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
                            })
                            .show();
                }
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Click ", "click");
                        Intent i = new Intent(context.getApplicationContext(), DetailEventActivity.class);
                        context.startActivity(i);
            }
        });

        listView.setAdapter(pages.get(position).getAdapter());

        container.addView(itemView);
        return itemView;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pages.get(position).getTitle();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
