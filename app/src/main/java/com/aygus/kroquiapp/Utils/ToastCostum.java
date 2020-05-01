package com.aygus.kroquiapp.Utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aygus.kroquiapp.R;

/**
 * Created by SANTIAGO from AIGUS on 20/10/2018.
 */
public class ToastCostum {
    private String msn;
    private Activity activity;
    private int duration;
    private int view;
    private int typeToast;

    public static final int ERROR = 1;
    public static final int SUCESSE = 2;
    public static final int ALERT = 3;
    public static final int DICA = 4;


    public ToastCostum(String msn, Activity activity, int duration, int typeToast) {
        this.msn = msn;
        this.activity = activity;
        this.duration = duration;
        this.typeToast = typeToast;
    }

    public static ToastCostum toast(String msn, Activity activity, int duration, int typeToast){
        return new ToastCostum(msn, activity, duration, typeToast);

    }


    public void show(){
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, activity.findViewById(R.id.custom_toast_container));

        ImageView imageView = layout.findViewById(R.id.iconImg);

        int color = activity.getResources().getColor(R.color.blackDark);

        if (typeToast==1){
            color = activity.getResources().getColor(R.color.colorError);
            imageView.setImageResource(R.drawable.ic_error_white_24dp);

        }else if(typeToast==2){
            color = activity.getResources().getColor(R.color.colorSucesse);
            imageView.setImageResource(R.drawable.ic_check_circle_white_24dp);

        }else if (typeToast==3){
            color = activity.getResources().getColor(R.color.colorAlert);
            imageView.setImageResource(R.drawable.ic_warning_black_24dp);
        }

        CardView cardView = layout.findViewById(R.id.custom_toast_container);
        cardView.setCardBackgroundColor(color);

        TextView text = layout.findViewById(R.id.text);
        text.setText(msn);

        Toast toast = new Toast(activity.getApplicationContext());
        //toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
    }
}
