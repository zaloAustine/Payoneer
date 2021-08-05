
package com.zalocoders.payoneer.data.models.payment_options;

import com.google.gson.annotations.SerializedName;

public class Style {

    @SerializedName("language")
    private String mLanguage;

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

}
