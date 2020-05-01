package com.aygus.kroquiapp.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.aygus.kroquiapp.R;

/**
 * Created by SANTIAGO from AIGUS on 11/01/2019.
 */
public class AdapterListTags extends RecyclerView.Adapter<AdapterListTags.ViewHolder>{
    private String[] listTags;
    private OnClickItemTag onClickItemTag;
    private ViewHolder holderPosistion = null;

    public interface OnClickItemTag{
        void onClickItem(String strings);
    }

    public AdapterListTags(String[] listTags, OnClickItemTag onClickItemTag) {
        this.listTags = listTags;
        this.onClickItemTag = onClickItemTag;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_tag, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tags.setText(listTags[position]);
        setMargin(holder.tags, position);
        holder.tags.setOnClickListener(v -> {
            onClickItemTag.onClickItem(listTags[position]);

            if (holderPosistion!=null) {
                returnViewStyle(holderPosistion);
            }

            lightViewStyle(holder);
            holderPosistion = holder;

        });
    }

    @Override
    public int getItemCount() {
        return this.listTags != null ? this.listTags.length:0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tags;
        private View view;

        ViewHolder(View view) {
            super(view);
            this.view = itemView;
            tags = view.findViewById(R.id.itemTag);
        }
    }

    private void setMargin(TextView textView, int position){
        if (position==0) {
            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(11, 3, 3, 3);
            textView.setLayoutParams(params);
            textView.requestLayout();
        }
    }

    private void lightViewStyle(ViewHolder holderPosistion){
        holderPosistion.tags.setBackgroundResource(R.drawable.shape_list_tags_actived);
        holderPosistion.tags.setTextColor(Color.WHITE);
    }

    private void returnViewStyle(ViewHolder holderPosistion){
        holderPosistion.tags.setBackgroundResource(R.drawable.shape_list_tags);
        holderPosistion.tags.setTextColor(Color.parseColor("#818181"));
    }
}
