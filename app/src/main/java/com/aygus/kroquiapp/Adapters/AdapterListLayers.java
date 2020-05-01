package com.aygus.kroquiapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aygus.kroquiapp.Modelos.ADrawable;
import com.aygus.kroquiapp.Modelos.AStyle;
import com.aygus.kroquiapp.Modelos.Layer;
import com.aygus.kroquiapp.R;
import com.aygus.kroquiapp.Style.StyleMarker;
import com.aygus.kroquiapp.Utils.GenerateId;

import java.util.List;

/**
 * Created by SANTIAGO from AIGUS on 30/11/2018.
 */
public class AdapterListLayers extends RecyclerView.Adapter<AdapterListLayers.ViewHolder>{
    private List<Layer> layerList;
    private OnClickItemLayer onClickItemLayer;
    private Context context;
    private int superPosition = -1;
    private ViewHolder holderPosistion = null;

    public AdapterListLayers(List<Layer> layerList, OnClickItemLayer onClickItemLayer, Context context) {
        this.layerList = layerList;
        this.onClickItemLayer = onClickItemLayer;
        this.context = context;
    }

    public interface OnClickItemLayer{
        void onClickItem(View view, Layer layer, int position);
        void onClickStyleDraw(View view, Layer layer);
        void onClickMoreOptions(View view, Layer layer);
        void onClickCheck(View view, Layer layer, int position);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_layer, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Layer layer = layerList.get(holder.getAdapterPosition());

        holder.titleLayer.setText(layer.getNameLayer());

        holder.itemLayer.setOnClickListener(v -> {
            onClickItemLayer.onClickItem(v, layer, holder.getAdapterPosition());

            if (holderPosistion!=null) {
                returnViewStyle(holderPosistion);
            }

            lightViewStyle(holder);

            holderPosistion = holder;

        });

        holder.itemLayer.setOnLongClickListener(v -> true);

        holder.styleDrawLayer.setOnClickListener(v -> onClickItemLayer.onClickStyleDraw(v, layer));
        holder.styleDrawLayer.setImageDrawable(new ADrawable(layer.getaStyle()));

        holder.checkLayer.setOnClickListener(v -> onClickItemLayer.onClickCheck(v, layer, holder.getAdapterPosition()));

        holder.moreOptions.setOnClickListener(v -> onClickItemLayer.onClickMoreOptions(v, layer));

    }

    @Override
    public int getItemCount() {
        return this.layerList != null ? this.layerList.size():0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout itemLayer;
        private ImageButton styleDrawLayer, moreOptions;
        private TextView titleLayer;
        private CheckBox checkLayer;
        private View view;

        ViewHolder(View view) {
            super(view);
            this.view = itemView;

            itemLayer = view.findViewById(R.id.itemLayer);
            styleDrawLayer = view.findViewById(R.id.styleDrawLayer);
            moreOptions = view.findViewById(R.id.moreOptions);
            titleLayer = view.findViewById(R.id.titleLayer);
            checkLayer = view.findViewById(R.id.checkLayer);

        }
    }

    private void lightViewStyle(ViewHolder holderPosistion){
        holderPosistion.itemLayer.setBackgroundColor(Color.parseColor("#6666FF"));
        holderPosistion.titleLayer.setTextColor(Color.parseColor("#ffffff"));
        holderPosistion.moreOptions.setVisibility(View.VISIBLE);

    }

    private void returnViewStyle(ViewHolder holderPosistion){
        holderPosistion.itemLayer.setBackgroundResource(R.drawable.riple_rect_default);
        holderPosistion.titleLayer.setTextColor(Color.parseColor("#333333"));
        holderPosistion.moreOptions.setVisibility(View.INVISIBLE);

    }
}
