package com.example.covid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<countrymodel> {

    private Context context;
    private List<countrymodel> countrymodelList;
    private List<countrymodel> countrymodelListfiltered;





    public MyCustomAdapter(Context context, List<countrymodel> countrymodelList) {
        super(context, R.layout.list_coustomer_item,countrymodelList);

        this.context=context;
        this.countrymodelList=countrymodelList;
        this.countrymodelListfiltered=countrymodelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_coustomer_item,null,true);
        TextView tvCountryName=view.findViewById(R.id.tvCountryName);
        ImageView imageView=view.findViewById(R.id.imageFlag);
        tvCountryName.setText(countrymodelListfiltered.get(position).getCountry());
        Glide.with(context).load(countrymodelListfiltered.get(position).getFlag()).into(imageView);

        return view;
    }

    @Override
    public int getCount() {
        return countrymodelListfiltered.size();
    }

    @Nullable
    @Override
    public countrymodel getItem(int position) {
        return countrymodelListfiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = countrymodelList.size();
                    filterResults.values = countrymodelList;

                }else{
                    List<countrymodel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(countrymodel itemsModel:countrymodelList){
                        if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                countrymodelListfiltered = (List<countrymodel>) results.values;
                AffecttedCounties.countrymodelList = (List<countrymodel>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
