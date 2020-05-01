package com.aygus.kroquiapp.Modelos;

import java.io.Serializable;

/**
 * Created by SANTIAGO from AIGUS on 14/10/2018.
 */
public class License implements Serializable{
    private int idLicense;
    private String idMaskLicense;
    private String idKeyLicense;
    private String idStoreLicense;
    private int idControllerLicense;
    private String idUserLicense;
    private boolean statusLicense;
    private String dominusLicense;
    private int nDivices;
    private int subLicense;
    private String dateLicense;

    public License(){}

    public License(int idLicense, String idMaskLicense, String idKeyLicense, String idStoreLicense, int idControllerLicense, String idUserLicense, boolean statusLicense, String dominusLicense, int nDivices, int subLicense, String dateLicense) {
        this.idLicense = idLicense;
        this.idMaskLicense = idMaskLicense;
        this.idKeyLicense = idKeyLicense;
        this.idStoreLicense = idStoreLicense;
        this.idControllerLicense = idControllerLicense;
        this.idUserLicense = idUserLicense;
        this.statusLicense = statusLicense;
        this.dominusLicense = dominusLicense;
        this.nDivices = nDivices;
        this.subLicense = subLicense;
        this.dateLicense = dateLicense;
    }

    public int getIdLicense() {
        return idLicense;
    }

    public void setIdLicense(int idLicense) {
        this.idLicense = idLicense;
    }

    public String getIdMaskLicense() {
        return idMaskLicense;
    }

    public void setIdMaskLicense(String idMaskLicense) {
        this.idMaskLicense = idMaskLicense;
    }

    public String getIdKeyLicense() {
        return idKeyLicense;
    }

    public void setIdKeyLicense(String idKeyLicense) {
        this.idKeyLicense = idKeyLicense;
    }

    public String getIdStoreLicense() {
        return idStoreLicense;
    }

    public void setIdStoreLicense(String idStoreLicense) {
        this.idStoreLicense = idStoreLicense;
    }

    public int getIdControllerLicense() {
        return idControllerLicense;
    }

    public void setIdControllerLicense(int idControllerLicense) {
        this.idControllerLicense = idControllerLicense;
    }

    public String getIdUserLicense() {
        return idUserLicense;
    }

    public void setIdUserLicense(String idUserLicense) {
        this.idUserLicense = idUserLicense;
    }

    public boolean isStatusLicense() {
        return statusLicense;
    }

    public void setStatusLicense(boolean statusLicense) {
        this.statusLicense = statusLicense;
    }

    public String getDominusLicense() {
        return dominusLicense;
    }

    public void setDominusLicense(String dominusLicense) {
        this.dominusLicense = dominusLicense;
    }

    public int getnDivices() {
        return nDivices;
    }

    public void setnDivices(int nDivices) {
        this.nDivices = nDivices;
    }

    public int getSubLicense() {
        return subLicense;
    }

    public void setSubLicense(int subLicense) {
        this.subLicense = subLicense;
    }

    public String getDateLicense() {
        return dateLicense;
    }

    public void setDateLicense(String dateLicense) {
        this.dateLicense = dateLicense;
    }
}
