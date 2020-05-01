package com.aygus.kroquiapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.aygus.kroquiapp.R;

/**
 * Created by SANTIAGO from AIGUS on 13/10/2018.
 */
public class PreferenceAplication {
    private Context context;
    private final String LOGING = "log";
    private final String ACCOUNT = "account";
    private final String NOTIFY_DARWIN = "notify_darwin";
    private final String LICENCA_STATUS = "license_status";
    private final String LICENSE_TYPE = "license_type";
    private final String ID_LICENSE_PLAY = "id_license_play";
    private final String SAVE_IN_CLOUD = "save_in_cloud";
    private final String CONTROL_LICENSE = "control_license";

    private SharedPreferences getPreferences() {
        final String PREFER_DARWIN = "prefer_darwin";
        return context.getSharedPreferences(PREFER_DARWIN, Context.MODE_PRIVATE);
    }

    public PreferenceAplication(Context context) {
        this.context = context;
    }
    //RETORNA STATUS DO LOG
    public boolean getStatusLogUser(){
        return getUserLog();
    }

    //GERENCIADOR DE LOG// retorna TRUE se as modificações foram  feitas
    public boolean managerUserLog(){
        if (getUserLog()){
            return setUserLogFalse();
        }else{
            return setUserLogTrue();
        }
    }

    private boolean setUserLogTrue(){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(LOGING, true);
        return editor.commit();
    }

    public boolean setUserLogFalse(){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(LOGING, false);
        return editor.commit();
    }
    //seta o status da licensa
    public boolean setLicenseStatus(boolean status){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(LICENCA_STATUS, status);
        return editor.commit();
    }
    //verifica o status da licensa
    public boolean getLicenseStatus(){
        return getPreferences().getBoolean(LICENCA_STATUS, false);
    }

    public boolean setLicenseType(int type){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(LICENSE_TYPE, type);
        return editor.commit();
    }

    public int getLicenseType(){
        return getPreferences().getInt(LICENSE_TYPE, 0);
    }

    private boolean getUserLog(){
        return getPreferences().getBoolean(LOGING, false);
    }

    public boolean getStatusNotify(){
        return getUserNotify();
    }
    //gerencia as notificações
    public boolean menagerUserNotify(){
        if(getUserNotify()){
            return setUserNotifyFalse();
        }else{
            return setUserNotifyTrue();
        }
    }
    //ativa notificações
    private boolean setUserNotifyTrue(){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(NOTIFY_DARWIN, true);
        return editor.commit();
    }
    //desativa notificações
    private boolean setUserNotifyFalse(){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(NOTIFY_DARWIN, false);
        return editor.commit();
    }
    //pega o status da notificação
    private boolean getUserNotify(){
        return getPreferences().getBoolean(NOTIFY_DARWIN, false);
    }

    public boolean setUserAccount(String idUserMask){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(ACCOUNT, idUserMask);
        return editor.commit();
    }

    public boolean setIdLicense(String productId){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(ID_LICENSE_PLAY, productId);
        return editor.commit();
    }

    public String getIdLicense() {
        return getPreferences().getString(ID_LICENSE_PLAY, "null");
    }

    public String getUserAccount(){
        return getPreferences().getString(ACCOUNT, null);
    }

    public boolean setSaveInCloudTrue(boolean bool){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(SAVE_IN_CLOUD, bool);
        return editor.commit();
    }

    public boolean setControlLicense(int control){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(CONTROL_LICENSE, control);
        return editor.commit();
    }

    public int getControlLicense(){
        return getPreferences().getInt(CONTROL_LICENSE, 0);
    }

    public boolean getSaveInCloud(){
        return getPreferences().getBoolean(SAVE_IN_CLOUD, false);
    }
}
