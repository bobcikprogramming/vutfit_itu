package com.bobcikprogramming.genertorhesla.view;

/**
 * Soubor:      RecyclerViewPattern
 * Autor:       Pavel Bobčík (xbobci03)
 * Předmět:     ITU - Tvorba uživatelských rozhraní
 * Organizace:  Vysoké učení technické v Brně
 */

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bobcikprogramming.genertorhesla.R;
import com.bobcikprogramming.genertorhesla.model.PatternEntity;

import java.util.List;

public class RecyclerViewPattern extends RecyclerView.Adapter<RecyclerViewPattern.ViewHolder>{

    private List<PatternEntity> dataList;
    private View.OnClickListener myClickListener;

    public RecyclerViewPattern(View.OnClickListener myClickListener){
        this.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_pattern, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PatternEntity pattern = dataList.get(position);


        holder.tvName.setText(pattern.name);
        holder.tvPatternExample.setText(pattern.example);

        if(position < (getItemCount() - 1)){
            holder.underline.setVisibility(View.VISIBLE);
        }else{
            holder.underline.setVisibility(View.GONE);
        }

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setPatternData(List<PatternEntity> dataList){
        this.dataList = dataList;
    }

    public void dataListChange(List<PatternEntity> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvPatternExample;
        private LinearLayout underline, item;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvPatternExample = itemView.findViewById(R.id.tvPatternExample);
            item = itemView.findViewById(R.id.layoutPattern);
            underline = itemView.findViewById(R.id.underline);

            itemView.setOnClickListener(myClickListener);
        }
    }
}