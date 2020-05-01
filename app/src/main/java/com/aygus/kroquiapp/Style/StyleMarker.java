package com.aygus.kroquiapp.Style;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by SANTIAGO from AIGUS on 27/04/2018.
 */
public class StyleMarker extends Drawable{
    private int colorIntern;
    private int colorExtern;
    private int sizeIntern;
    private int sizeExtern;
    private Canvas mCanvas;
    private Context context;

    public int getColorIntern() {
        return colorIntern;
    }

    public void setColorIntern(int colorIntern) {
        this.colorIntern = colorIntern;
    }

    public int getColorExtern() {
        return colorExtern;
    }

    public void setColorExtern(int colorExtern) {
        this.colorExtern = colorExtern;
    }

    public Canvas getCanvas() {
        return mCanvas;
    }

    public void setCanvas(Canvas mCanvas) {
        this.mCanvas = mCanvas;
    }

    public int getSizeIntern() {
        return sizeIntern;
    }

    public void setSizeIntern(int sizeIntern) {
        this.sizeIntern = sizeIntern;
    }

    public int getSizeExtern() {
        return sizeExtern;
    }

    public void setSizeExtern(int sizeExtern) {
        this.sizeExtern = sizeExtern;
    }

    public StyleMarker(){}

    public StyleMarker(int colorIntern, int colorExtern, int sizeIntern, int sizeExtern, Context context) {
        this.colorIntern = colorIntern;
        this.colorExtern = colorExtern;
        this.sizeIntern = sizeIntern;
        this.sizeExtern = sizeExtern;
        this.context = context;
    }

    public StyleMarker(String colorIntern, String colorExtern, int sizeIntern, int sizeExtern, Context context) {
        this.colorIntern = Color.parseColor(colorIntern);
        this.colorExtern = Color.parseColor(colorExtern);
        this.sizeIntern = sizeIntern;
        this.sizeExtern = sizeExtern;
        this.context = context;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();

        float x = ((bounds.right - bounds.left) / 2f) + bounds.left;
        float y = ((bounds.bottom - bounds.top) / 2f) + bounds.top;

        Paint mPaintExtern = new Paint();
        Paint mPaintIntern = new Paint();

        mPaintExtern.setStyle(Paint.Style.FILL);
        mPaintExtern.setColor(colorExtern);
        mPaintExtern.setAntiAlias(true);
        mPaintExtern.setStrokeWidth(0);

        mPaintIntern.setStyle(Paint.Style.FILL);
        mPaintIntern.setColor(colorIntern);
        mPaintIntern.setAntiAlias(true);
        mPaintIntern.setStrokeWidth(0);

        canvas.drawCircle(x, y, sizeExtern, mPaintExtern);
        canvas.drawCircle(x, y, sizeIntern, mPaintIntern);

        mCanvas = canvas;

    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

}
