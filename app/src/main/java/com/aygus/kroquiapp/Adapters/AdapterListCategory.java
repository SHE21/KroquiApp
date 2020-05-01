package com.aygus.kroquiapp.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aygus.kroquiapp.Modelos.Category;
import com.aygus.kroquiapp.R;

import java.util.List;

/**
 * Created by SANTIAGO from AIGUS on 24/12/2018.
 */
public class AdapterListCategory extends RecyclerView.Adapter<AdapterListCategory.ViewHolder>{
    private List<Category> categoryList;
    private OnClickItem onClickItem;
    private int[] imgs;
    private Context context;

    public interface OnClickItem{
        void onClickItem(Category category);
    }

    public AdapterListCategory(List<Category> categoryList, OnClickItem onClickItem, int[] imgs, Context context) {
        this.categoryList = categoryList;
        this.onClickItem = onClickItem;
        this.imgs = imgs;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_category, parent, false);
        return new AdapterListCategory.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category category = categoryList.get(position);

        if (imgs!=null)holder.ic_image.setImageResource(imgs[category.getIDCategory()]);
        holder.nameCategory.setText(category.getNameCategory());
        String totalLayerCat = category.getTotalLayerCategory() + " camadas" + context.getResources().getString(R.string.title_activity_store_layers);
        holder.totalLayers.setText(totalLayerCat);
        holder.card.setOnClickListener(v -> onClickItem.onClickItem(category));
    }

    @Override
    public int getItemCount() {
        return this.categoryList != null ? this.categoryList.size():0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView card;
        private ImageView ic_image;
        private TextView nameCategory;
        private TextView totalLayers;
        private View view;

        ViewHolder(View view) {
            super(view);
            this.view = itemView;

            card = view.findViewById(R.id.card);
            ic_image = view.findViewById(R.id.ic_image);
            nameCategory = view.findViewById(R.id.nameCategory);
            totalLayers = view.findViewById(R.id.totalLayers);
        }
    }
}
