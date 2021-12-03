package com.bobcikprogramming.genertorhesla.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bobcikprogramming.genertorhesla.R;

import java.util.ArrayList;
import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {

    private ArrayList<HelperHolder> help;
    private LayoutInflater layoutInflater;

    public ViewPagerAdapter(Context context, ArrayList<HelperHolder> help){
        this.help = help;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return help.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.help_item, container, false);
        TextView tvHelpHeadline = itemView.findViewById(R.id.tvHelperHeadline);
        TextView tvHelpText = itemView.findViewById(R.id.tvHelpText);
        tvHelpHeadline.setText(help.get(position).getHeadline());
        tvHelpText.setText(help.get(position).getText());
        Objects.requireNonNull(container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
