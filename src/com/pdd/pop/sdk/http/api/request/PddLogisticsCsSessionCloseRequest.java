package com.pdd.pop.sdk.http.api.request;

import com.pdd.pop.ext.fasterxml.jackson.annotation.JsonProperty;
import com.pdd.pop.sdk.http.api.response.PddLogisticsCsSessionCloseResponse;
import com.pdd.pop.sdk.http.HttpMethod;
import com.pdd.pop.sdk.http.PopBaseHttpRequest;
import com.pdd.pop.sdk.common.util.JsonUtil;


import java.util.Map;
import java.util.TreeMap;

public class PddLogisticsCsSessionCloseRequest extends PopBaseHttpRequest<PddLogisticsCsSessionCloseResponse>{

    /**
     * pdd会话id
     */
    @JsonProperty("session_id")
    private String sessionId;

    /**
     * 物流公司会话id
     */
    @JsonProperty("wp_session_id")
    private String wpSessionId;

    /**
     * 样式YYYY-MM-DD HH:MM:SS
     */
    @JsonProperty("action_time")
    private String actionTime;

    
    @Override
    public String getVersion() {
        return "V1";
    }

    @Override
    public String getDataType() {
        return "JSON";
    }

    @Override
    public String getType() {
        return "pdd.logistics.cs.session.close";
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public Class<PddLogisticsCsSessionCloseResponse> getResponseClass() {
        return PddLogisticsCsSessionCloseResponse.class;
    }

    @Override
    public Map<String, String> getParamsMap() {
        Map<String, String> paramsMap = new TreeMap<String, String>();

        paramsMap.put("version", getVersion());
        paramsMap.put("data_type", getDataType());
        paramsMap.put("type", getType());
        paramsMap.put("timestamp", getTimestamp().toString());

        if(sessionId != null) {
            paramsMap.put("session_id", sessionId.toString());
            
        }
        if(wpSessionId != null) {
            paramsMap.put("wp_session_id", wpSessionId.toString());
            
        }
        if(actionTime != null) {
            paramsMap.put("action_time", actionTime.toString());
            
        }
        
        return paramsMap;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setWpSessionId(String wpSessionId) {
        this.wpSessionId = wpSessionId;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }

    
    
}