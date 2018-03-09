package com.jdkgroup.suryanamaskar.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdkgroup.suryanamaskar.R;
import com.jdkgroup.model.api.countrylist.ModelCountry;
import com.jdkgroup.suryanamaskar.dialog.SpDialogCountry;

import java.util.ArrayList;
import java.util.List;

public class CountrySpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private String time_format = "HH:mm";
    private List list_clone = new ArrayList<>();

    private List alList = new ArrayList<>();
    SpDialogCountry.OnItemClick itemSelect;

    public CountrySpAdapter(Context context, List<?> alList, SpDialogCountry.OnItemClick itemSelect) {

        this.context = context;
        this.alList = alList;
        this.list_clone.addAll(alList);
        this.itemSelect=itemSelect;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_sp_country, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return alList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView appTvTitle;
        int position;

        public ViewHolder(View itemView) {
            super(itemView);
            appTvTitle = itemView.findViewById(R.id.appTvTitle);

            itemView.setOnClickListener(v -> itemSelect.selectedItem(alList.get(getAdapterPosition())));
        }

        public void setData(int position) {
            this.position = position;
            if (alList.get(position) instanceof ModelCountry) {
                ModelCountry modelCountry = (ModelCountry) alList.get(position);
                appTvTitle.setText(modelCountry.getName());
            }
        }
    }
}