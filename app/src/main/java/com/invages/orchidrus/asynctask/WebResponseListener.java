package com.invages.orchidrus.asynctask;

import org.json.JSONObject;

public interface WebResponseListener {

    void onResponse(JSONObject str);
    void onError(String error);

}
