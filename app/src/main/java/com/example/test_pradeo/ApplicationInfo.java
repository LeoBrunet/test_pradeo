package com.example.test_pradeo;

import android.graphics.drawable.Drawable;

class ApplicationInfo {

    private String name;
    private Drawable icon;
    private String hash;

    public ApplicationInfo(String name, Drawable icon, String hash) {
        this.name = name;
        this.icon = icon;
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
