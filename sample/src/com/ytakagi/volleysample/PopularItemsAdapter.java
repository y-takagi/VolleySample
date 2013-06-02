
package com.ytakagi.volleysample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.arnx.jsonic.JSON;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.ytakagi.volleysample.model.Item;
import com.ytakagi.volleysample.network.response.PaginatedItems;

public class PopularItemsAdapter extends BaseAdapter {
    private static final String TAG = PopularItemsAdapter.class.getName();
    private List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;

    public PopularItemsAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Item.popularItems(1, 100,
                new Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        PaginatedItems paginatedItems = JSON.decode(response, PaginatedItems.class);
                        items.addAll(Arrays.asList(paginatedItems.getItems()));
                        notifyDataSetChanged();
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.toString());
                    }
                });
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView itemTitleView;

        public ViewHolder(View convertView) {
            itemTitleView = (TextView) convertView.findViewById(R.id.item_title);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.items_row, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Item item = (Item) getItem(position);
        holder.itemTitleView.setText(item.getTitle());
        return convertView;
    }
}
