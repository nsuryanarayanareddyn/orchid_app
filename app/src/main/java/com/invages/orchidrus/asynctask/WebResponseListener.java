package com.invages.orchidrus.asynctask;

public interface WebResponseListener {

    void onResponse(String str);
    void onError(String error);

}
