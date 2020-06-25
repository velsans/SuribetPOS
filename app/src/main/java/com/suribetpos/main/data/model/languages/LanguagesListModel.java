package com.suribetpos.main.data.model.languages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class LanguagesListModel {
    @SerializedName("LangID")
    @Expose
    private Integer langID;
    @SerializedName("Language")
    @Expose
    private String language;
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("IsActiveWeb")
    @Expose
    private Boolean isActiveWeb;

    public Integer getLangID() {
        return langID;
    }

    public void setLangID(Integer langID) {
        this.langID = langID;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsActiveWeb() {
        return isActiveWeb;
    }

    public void setIsActiveWeb(Boolean isActiveWeb) {
        this.isActiveWeb = isActiveWeb;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getActiveWeb() {
        return isActiveWeb;
    }

    public void setActiveWeb(Boolean activeWeb) {
        isActiveWeb = activeWeb;
    }
}