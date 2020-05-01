package com.aygus.kroquiapp.Modelos;

/**
 * Created by SANTIAGO from AIGUS on 15/10/2018.
 */
public class MensageDb {

    public String mensage;
    public String sResult;
    public long iResult;
    public boolean status;

    public MensageDb(String mensage, String sResult, long iResult, boolean status) {
        this.mensage = mensage;
        this.sResult = sResult;
        this.iResult = iResult;
        this.status = status;
    }
}
