package com.aygus.kroquiapp.Utils;

import android.content.Context;

import com.aygus.kroquiapp.Modelos.BannerStore;
import com.aygus.kroquiapp.R;

import java.util.List;
import java.util.Random;

public class GenerateId {
    public static String generateId(){
        String letras = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvYyWwXxZz";

        Random random = new Random();

        StringBuilder armazenaChaves = new StringBuilder();
        int index;
        for( int i = 0; i < 9; i++ ) {
            index = random.nextInt( letras.length() );
            armazenaChaves.append(letras.substring(index, index + 1));
        }
        return armazenaChaves.toString();
    }

    public static String generateKey(){
        String form = "ABCDEFGHIJKLMNOPQRSTUYWZ";
        Random random = new Random();

        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        StringBuilder c = new StringBuilder();

        int indexA;
        for( int i = 0; i < 4; i++ ) {
            indexA = random.nextInt(form.length() );
            a.append(form.substring(indexA, indexA + 1));
        }

        int indexB;
        for( int i = 0; i < 4; i++ ) {
            indexB = random.nextInt(form.length() );
            b.append(form.substring(indexB, indexB + 1));
        }

        int indexC;
        for( int i = 0; i < 4; i++ ) {
            indexC = random.nextInt(form.length() );
            c.append(form.substring(indexC, indexC + 1));
        }

        return a + "-" + b + "-" + c;
    }

    public static int randIdJob(){
        String num = "0123456789";
        Random random = new Random();

        StringBuilder armazenaChaves = new StringBuilder();
        int index;
        for( int i = 0; i < 9; i++ ) {
            index = random.nextInt( num.length() );
            armazenaChaves.append(num.substring(index, index + 1));
        }
        return Integer.parseInt(armazenaChaves.toString());
    }

    public static int generateColor(Context context, int obj){
        int[] colors;
        Random random = new Random();

        switch (obj){
            case 0:
                colors = context.getResources().getIntArray(R.array.colorFill);
                break;

            case 1:
                colors = context.getResources().getIntArray(R.array.colorStroke);
                break;

                default:
                    colors = context.getResources().getIntArray(R.array.colorFill);
                    break;
        }

        return colors[random.nextInt(colors.length)];
    }

    public static BannerStore gBanner(List<BannerStore> list){
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
