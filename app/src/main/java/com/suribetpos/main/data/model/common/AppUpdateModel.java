package com.suribetpos.main.data.model.common;

public class AppUpdateModel {
    String VersionName,ApkUrl;
    int VersionCode;
    boolean IsActive;

    public String getVersionName() {
        return VersionName;
    }

    public void setVersionName(String versionName) {
        VersionName = versionName;
    }

    public String getApkUrl() {
        return ApkUrl;
    }

    public void setApkUrl(String apkUrl) {
        ApkUrl = apkUrl;
    }

    public int getVersionCode() {
        return VersionCode;
    }

    public void setVersionCode(int versionCode) {
        VersionCode = versionCode;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }
}
