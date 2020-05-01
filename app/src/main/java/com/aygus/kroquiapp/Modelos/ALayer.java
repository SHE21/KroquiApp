package com.aygus.kroquiapp.Modelos;

import java.io.Serializable;

/**
 * Created by SANTIAGO from AIGUS on 12/12/2018.
 */
public class ALayer implements Serializable{
    private String nameAlayer;
    private String idMaskAlayer;
    private AStyle AStyle;

    public ALayer(String nameAlayer, String idMaskAlayer, com.aygus.kroquiapp.Modelos.AStyle AStyle) {
        this.nameAlayer = nameAlayer;
        this.idMaskAlayer = idMaskAlayer;
        this.AStyle = AStyle;
    }

    public String getNameAlayer() {
        return nameAlayer;
    }

    public void setNameAlayer(String nameAlayer) {
        this.nameAlayer = nameAlayer;
    }

    public String getIdMaskAlayer() {
        return idMaskAlayer;
    }

    public void setIdMaskAlayer(String idMaskAlayer) {
        this.idMaskAlayer = idMaskAlayer;
    }

    public com.aygus.kroquiapp.Modelos.AStyle getAStyle() {
        return AStyle;
    }

    public void setAStyle(com.aygus.kroquiapp.Modelos.AStyle AStyle) {
        this.AStyle = AStyle;
    }
}
