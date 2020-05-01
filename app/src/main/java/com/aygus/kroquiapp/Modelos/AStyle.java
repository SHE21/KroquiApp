package com.aygus.kroquiapp.Modelos;

import android.graphics.Canvas;

import java.io.Serializable;

/**
 * Created by SANTIAGO from AIGUS on 12/12/2018.
 */
public class AStyle implements Serializable{
    public int radInter;
    public int radExtern;
    public int colorRadExtern;
    public int colorRadIntern;

    public int colorFill;
    public int colorStroke;
    public int strokeWidth;

    public int alpha;
    public int alphaIntern;

    public transient Canvas mCanvas;
    public int typeIcon = -1;

    public static final int MARKER = 0;
    public static final int POLYGON = 1;

    public AStyle(int radInter, int radExtern, int colorRadExtern, int colorRadIntern, int typeIcon) {
        this.radInter = radInter;
        this.radExtern = radExtern;
        this.colorRadExtern = colorRadExtern;
        this.colorRadIntern = colorRadIntern;
        this.typeIcon = typeIcon;
    }

    public AStyle(int colorFill, int colorStroke, int strokeWidth, int typeIcon) {
        this.colorFill = colorFill;
        this.colorStroke = colorStroke;
        this.strokeWidth = strokeWidth;
        this.typeIcon = typeIcon;
    }

    public AStyle(){}

    public int getRadInter() {
        return radInter;
    }

    public void setRadInter(int radInter) {
        this.radInter = radInter;
    }

    public int getRadExtern() {
        return radExtern;
    }

    public void setRadExtern(int radExtern) {
        this.radExtern = radExtern;
    }

    public int getColorRadExtern() {
        return colorRadExtern;
    }

    public void setColorRadExtern(int colorRadExtern) {
        this.colorRadExtern = colorRadExtern;
    }

    public int getColorRadIntern() {
        return colorRadIntern;
    }

    public void setColorRadIntern(int colorRadIntern) {
        this.colorRadIntern = colorRadIntern;
    }

    public int getColorFill() {
        return colorFill;
    }

    public void setColorFill(int colorFill) {
        this.colorFill = colorFill;
    }

    public int getColorStroke() {
        return colorStroke;
    }

    public void setColorStroke(int colorStroke) {
        this.colorStroke = colorStroke;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public Canvas getCanvas() {
        return mCanvas;
    }

    public void setCanvas(Canvas mCanvas) {
        this.mCanvas = mCanvas;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getAlphaIntern() {
        return alphaIntern;
    }

    public void setAlphaIntern(int alphaIntern) {
        this.alphaIntern = alphaIntern;
    }
}
