package com.aygus.kroquiapp.Modelos;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import com.aygus.kroquiapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SANTIAGO from AIGUS on 27/12/2018.
 */
public class BannerStore {
    private int id;
    private Drawable background;
    private int imgBanner;
    private String banTitle;
    private String banMenssage;
    private String date;

    public BannerStore(int id, Drawable background, int imgBanner, String banTitle, String banMenssage, String date) {
        this.id = id;
        this.background = background;
        this.imgBanner = imgBanner;
        this.banTitle = banTitle;
        this.banMenssage = banMenssage;
        this.date = date;
    }

    public static List<BannerStore> getStyle(Resources resources){
        List<BannerStore> list = new ArrayList<>();
        list.add(new BannerStore(0, ResourcesCompat.getDrawable(resources, R.drawable.shape_banner_store, null), R.drawable.ic_back_banner, "Centenas de dados geográficos", "Faça download de centenas de dedos geográficos para seus projetos", ""));
        list.add(new BannerStore(1, ResourcesCompat.getDrawable(resources, R.drawable.shape_banner_store_001, null), R.drawable.ic_back_banner_001, "Arquivos interoperáveis", "Faça download de arquivos em formato KML  e geoJSON. Formatos altamente interoperáveis com softwares SIG", ""));

        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drawable getBackground() {
        return background;
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }

    public int getImgBanner() {
        return imgBanner;
    }

    public void setImgBanner(int imgBanner) {
        this.imgBanner = imgBanner;
    }

    public String getBanTitle() {
        return banTitle;
    }

    public void setBanTitle(String banTitle) {
        this.banTitle = banTitle;
    }

    public String getBanMenssage() {
        return banMenssage;
    }

    public void setBanMenssage(String banMenssage) {
        this.banMenssage = banMenssage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
