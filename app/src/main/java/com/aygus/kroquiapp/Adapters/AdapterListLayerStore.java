package com.aygus.kroquiapp.Adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aygus.kroquiapp.Modelos.LayerStore;
import com.aygus.kroquiapp.R;

import java.util.List;

/**
 * Created by SANTIAGO from AIGUS on 02/01/2019.
 */
public class AdapterListLayerStore extends RecyclerView.Adapter<AdapterListLayerStore.ViewHolder>{
    private List<LayerStore> storeList;
    private OnClickItemStore onClickItemStore;
    private Context context;
    private int adapter;

    public interface OnClickItemStore{
        void onClickItem(LayerStore layerStore);
    }

    public AdapterListLayerStore(List<LayerStore> storeList, OnClickItemStore onClickItemStore, Context context, int adapter) {
        this.storeList = storeList;
        this.onClickItemStore = onClickItemStore;
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(adapter, parent, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LayerStore layerStore = storeList.get(position);

        if (adapter==R.layout.adapter_item_product_layer2){
            settMargin(holder.cardViewLayer, position);
        }

        holder.cardViewLayer.setOnClickListener(v -> { onClickItemStore.onClickItem(layerStore); });
        holder.title.setText(layerStore.getTitle());
        holder.typeGeom.setText(layerStore.getTypeGeom());
        holder.category.setText(layerStore.getCategory());
        String  prize = "R$ " + String.valueOf(layerStore.getPrize());
        holder.prize.setText(prize);
        holder.ic_card_point.setBackground(LayerStore.setIconGeometryType(layerStore.getTypeGeom(), context));
    }

    @Override
    public int getItemCount() {
        return  this.storeList != null ? this.storeList.size():0;
    }

    private void settMargin(CardView cardView, int position){
        if (position==0) {
            CardView.LayoutParams params = new CardView.LayoutParams(CardView.LayoutParams.WRAP_CONTENT,
                    CardView.LayoutParams.WRAP_CONTENT);
            params.setMargins(17, 4, 5, 4);
            cardView.setLayoutParams(params);
            cardView.requestLayout();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardViewLayer;
        private ImageView ic_card_point;
        private TextView title, category, typeGeom, prize;
        private View view;

        ViewHolder(View view) {
            super(view);
            this.view = itemView;

            cardViewLayer = view.findViewById(R.id.cardViewLayer);
            ic_card_point = view.findViewById(R.id.ic_card_point);
            title = view.findViewById(R.id.title);
            category = view.findViewById(R.id.category);
            typeGeom = view.findViewById(R.id.typeGeom);
            prize = view.findViewById(R.id.prize);
        }
    }

}
