package com.smitsworks.redlo.hottours.lists.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smitsworks.redlo.hottours.R;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;
import com.smitsworks.redlo.hottours.lists.mealtypes.MealTypesFragment;

import java.util.List;

/**
 * Created by redlongcity on 21.10.2017.
 * adapter for creating list element from Meal_Type object
 */

public class MealTypesAdapter extends BaseAdapter {

    private List<Meal_Type> types;
    private MealTypesFragment.MealTypesItemListener listener;

    public MealTypesAdapter(List<Meal_Type> types,
                            MealTypesFragment.MealTypesItemListener listener) {
        this.types = types;
        this.listener = listener;
    }

    public void replaceData(List<Meal_Type> types){
        setTypes(types);
        notifyDataSetChanged();
    }

    public void setTypes(List<Meal_Type> types) {
        this.types = types;
    }

    @Override
    public int getCount() {
        return types.size();
    }

    @Override
    public Object getItem(int position) {
        return types.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowView = inflater.inflate(R.layout.list_item, parent, false);
        }
            final Meal_Type type = (Meal_Type) getItem(position);

            TextView textView = (TextView) rowView.findViewById(R.id.li_text_view);
            textView.setText(type.getName_full());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMealTypesClick(type);
                }
            });
            return rowView;

    }
}
