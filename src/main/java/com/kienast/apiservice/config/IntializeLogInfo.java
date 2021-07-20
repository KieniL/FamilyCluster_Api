package com.kienast.apiservice.config;

import org.slf4j.MDC;

public class IntializeLogInfo {

  public static void initializeLogInfo(String requestId, String sourceIP, String userId, String loglevel) {
    MDC.put("SYSTEM_LOG_LEVEL", loglevel);
    MDC.put("REQUEST_ID", requestId);
    MDC.put("SOURCE_IP", sourceIP);
    MDC.put("USER_ID", userId);
  }
}
