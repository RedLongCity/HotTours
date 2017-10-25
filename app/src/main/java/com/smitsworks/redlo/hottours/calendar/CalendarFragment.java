package com.smitsworks.redlo.hottours.calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.tourfiltering.TourFilteringPresenter;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 24.10.2017.
 * this view shows calendar for choosing date of begin tour
 */

public class CalendarFragment extends Fragment implements CalendarContract.View {

    private CalendarContract.Presenter presenter;

    private TextView year;

    private TextView date;

    private CalendarView calendar;

    private Button ok;

    public CalendarFragment() {
    }

    public CalendarFragment newInstance(){
        return new CalendarFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.calendar_frag,container,false);

        year = (TextView) root.findViewById(R.id.year_tv);
        date = (TextView) root.findViewById(R.id.date_tv);
        calendar = (CalendarView) root.findViewById(R.id.calendar_view);
        Date date = new Date();
        calendar.setDate(date.getTime());
        calendar.setMinDate(date.getTime());
        calendar.setMaxDate(date.getTime()+365*24*3600*1000);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                cal.set(year,month,dayOfMonth);
                Resources res = getResources();
                String[] daysOfWeek = res.getStringArray(R.array.days_of_week);
                String[] monthsOfYear = res.getStringArray(R.array.months_of_year);

                String dayOfWeek = daysOfWeek[cal.get(Calendar.DAY_OF_WEEK)];
                String req_month = monthsOfYear[month];

                showYear(year);
                showDate(dayOfWeek+", "+req_month+" "+dayOfMonth);

            }
        });

        ok = (Button) root.findViewById(R.id.but_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.chooseDate(new Timestamp(calendar.getDate()));
            }
        });
        return root;
    }

    @Override
    public void chooseDateUI(Timestamp date) {
        Intent intent = new Intent();
        intent.putExtra(TourFilteringPresenter.DATE_EXTRA, date);
        getActivity().setResult(Activity.RESULT_OK,intent);
        getActivity().finish();
    }

    @Override
    public void showYear(Integer yearValue) {
        year.setText(yearValue);
    }

    @Override
    public void showDate(String dateValue) {
        date.setText(dateValue);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(CalendarContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }


}
