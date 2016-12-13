/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/


package com.example.softwarecontrolleddrone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListDataAdapter extends ArrayAdapter {

    List list = new ArrayList<>();

    public ListDataAdapter(Context context, int resource) {
        super(context, resource);

    }

    static class LayoutHandler
    {
        TextView DATE, FLIGHTDURATION;
    }

    @Override
    public void add(Object object)
    {
        super.add(object);

        list.add(object);
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        LayoutHandler layoutHandler;

        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.list_view_layout, parent, false);

            layoutHandler = new LayoutHandler();
            layoutHandler.DATE = (TextView) row.findViewById(R.id.displayDate);
            layoutHandler.FLIGHTDURATION = (TextView) row.findViewById(R.id.displayFlightDuration);
            row.setTag(layoutHandler);
        }
        else
        {
            layoutHandler = (LayoutHandler) row.getTag();
        }
        DataProvider dataProvider = (DataProvider)this.getItem(position);
        layoutHandler.DATE.setText(dataProvider.getDate());
        layoutHandler.FLIGHTDURATION.setText(dataProvider.getFlightDuration());

        return row;
    }
}