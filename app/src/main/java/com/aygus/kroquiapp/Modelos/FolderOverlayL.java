package com.aygus.kroquiapp.Modelos;

import org.osmdroid.views.overlay.FolderOverlay;

import java.io.Serializable;

/**
 * Created by SANTIAGO from AIGUS on 12/12/2018.
 */
public class FolderOverlayL implements Serializable {
    public FolderOverlay folderOverlay;

    public FolderOverlayL(FolderOverlay folderOverlay) {
        this.folderOverlay = folderOverlay;
    }
}
