package com.suribetpos.main.data.model.common;

public class LanguageModel {
    String Language,LanguageCode;

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public LanguageModel(String language, String languageCode) {
        Language = language;
        LanguageCode = languageCode;
    }

}
