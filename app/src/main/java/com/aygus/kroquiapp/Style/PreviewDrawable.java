package com.aygus.kroquiapp.Style;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.aygus.kroquiapp.Modelos.AStyle;

/**
 * Created by SANTIAGO from AIGUS on 13/12/2018.
 */
public class PreviewDrawable extends View {
    private AStyle aStyle;
    private Paint mPaintExtern = new Paint();
    private Paint mPaintIntern = new Paint();
    private Paint mLabel = new Paint();
    private ColorFilter colorFilter = new ColorFilter();

    public AStyle getaStyle() {
        return aStyle;
    }

    public void setaStyle(AStyle aStyle) {
        this.aStyle = aStyle;
    }

    public PreviewDrawable(Context context) {
        super(context, null);
        setBackgroundColor(Color.WHITE);

    }

    public PreviewDrawable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public void onDraw(Canvas canvas){

        mPaintExtern.setStyle(Paint.Style.FILL);
        mPaintExtern.setColor(aStyle.colorRadExtern);
        mPaintExtern.setAntiAlias(true);
        mPaintExtern.setStrokeWidth(0);
        mPaintExtern.setAlpha(aStyle.alpha);

        mPaintIntern.setStyle(Paint.Style.FILL);
        mPaintIntern.setColor(aStyle.colorRadIntern);
        mPaintIntern.setAntiAlias(true);
        mPaintIntern.setStrokeWidth(0);
        mPaintIntern.setAlpha(aStyle.alphaIntern);

        mLabel.setColor(Color.BLACK);
        mLabel.setTextSize(toPixel(13));
        mLabel.setTextAlign(Paint.Align.CENTER);
        mLabel.setAntiAlias(true);

        float dpi50 = toPixel(50);
        float xLavel = toPixel(10);
        float yLabel = toPixel(10);

        canvas.drawCircle(dpi50, dpi50, toPixel(aStyle.radExtern), mPaintExtern);
        canvas.drawCircle(dpi50, dpi50, toPixel(aStyle.radInter), mPaintIntern);
        canvas.drawText("Label de Marker", toPixel(dpi50+5), toPixel(aStyle.radExtern+1f), mLabel);
        //canvas.drawText("Label de Marker", dpi50/2, yLabel, mLabel);
    }

    public float toPixel(float dpi){
        Resources rs = getContext().getResources();
        float densidade = rs.getDisplayMetrics().density;
        return  (int) (dpi * densidade + 0.5f);
    }
}
