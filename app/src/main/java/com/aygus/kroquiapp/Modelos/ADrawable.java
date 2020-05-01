package com.aygus.kroquiapp.Modelos;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by SANTIAGO from AIGUS on 12/12/2018.
 */
public class ADrawable extends Drawable {
    private AStyle aStyle;

    public ADrawable(AStyle aStyle) {
        this.aStyle = aStyle;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        switch (aStyle.typeIcon){
            case AStyle.MARKER:
                aStyle.mCanvas = getMarker(canvas);
                break;

            case AStyle.POLYGON:
                aStyle.mCanvas = getRect(canvas);
                break;
        }

    }

    private Canvas getMarker(Canvas canvas){
        Rect bounds = getBounds();

        float x = ((bounds.right - bounds.left) / 2f) + bounds.left;
        float y = ((bounds.bottom - bounds.top) / 2f) + bounds.top;

        Paint mPaintExtern = new Paint();
        Paint mPaintIntern = new Paint();

        mPaintExtern.setStyle(Paint.Style.FILL);
        mPaintExtern.setColor(aStyle.colorRadExtern);
        mPaintExtern.setAntiAlias(true);
        mPaintExtern.setStrokeWidth(0);

        mPaintIntern.setStyle(Paint.Style.FILL);
        mPaintIntern.setColor(aStyle.colorRadIntern);
        mPaintIntern.setAntiAlias(true);
        mPaintIntern.setStrokeWidth(0);

        canvas.drawCircle(x, y, aStyle.radExtern, mPaintExtern);
        canvas.drawCircle(x, y, aStyle.radInter, mPaintIntern);

        return canvas;
    }

    private Canvas getRect(Canvas canvas){
        Rect bounds = getBounds();
        Paint mPaintIntern = new Paint();

        mPaintIntern.setStyle(Paint.Style.FILL);
        mPaintIntern.setColor(aStyle.colorFill);
        mPaintIntern.setAntiAlias(true);
        mPaintIntern.setStrokeWidth(1);

        canvas.drawRect(bounds.left, bounds.top, bounds.right, bounds.bottom, mPaintIntern);

        return canvas;
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
