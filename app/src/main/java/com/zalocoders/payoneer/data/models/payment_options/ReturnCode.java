
package com.zalocoders.payoneer.data.models.payment_options;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ReturnCode {

    @SerializedName("name")
    private String mName;
    @SerializedName("source")
    private String mSource;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String source) {
        mSource = source;
    }

}
