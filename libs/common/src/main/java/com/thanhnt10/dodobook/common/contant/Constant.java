package com.thanhnt10.dodobook.common.contant;

import java.time.format.DateTimeFormatter;

public class Constant {
    public static class KEY {
        private KEY() {
        }

        public static final String TRACE_ID = "TRACE_ID";
        public static final String TRACE_USER = "TRACE_USER";
        public static final String TRACE_ID_HEADER = "X-REQUEST-ID";
        public static final String TENANT_ID_HEADER = "X-TENANT-ID";
        public static final String ORIGIN_HEADER = "Origin";
        public static final String REFERER_HEADER = "Referer";
    }

    public static class CHARACTER {
        private CHARACTER() {
        }

        public static final String SLASH = "/";
        public static final String UNDERSCORE = "_";
    }

    public static class DATE_TIME_FORMATTER {
        private DATE_TIME_FORMATTER() {}

        public static final String DEFAULT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        public static final String TIMESTAMP = "yyyyMMddHHmmss";
        public static final String DATE_TIME_EXCEL = "dd/MM/yyyy HH:mm:ss";
        public static final String DATE_TIME_EXCEL_2 = "yyyy-MM-dd HH:mm:ss.S";

        public static final String ONLY_DATE = "dd-MM-yyyy";

        public static final String ONLY_DATE_2 = "dd/MM/yyyy";
        public static final String ONLY_DATE_3 = "ddMMyyyy";

        public static final String START_OF_DAY = "T00:00:00Z";
        public static final String END_OF_DAY = "T23:59:59Z";
        public static final String YYYY_MM_DD = "yyyy-MM-dd";
        public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
        public static final String DDMMYYYY_HHMM = "ddMMyyyy_HHmm";

        public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT);
        public static final DateTimeFormatter ONLY_DATE_2_FORMATTER = DateTimeFormatter.ofPattern(ONLY_DATE_2);
        public static final DateTimeFormatter EXCEL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_EXCEL);
    }


    public static class CLIENT_TOKEN_CLAIMS {
        private CLIENT_TOKEN_CLAIMS() {}

        public static final String CHANNEL = "channel";
        public static final String EMAIL = "email";
        public static final String USER_NAME = "userName";
        public static final String FULL_NAME = "fullName";
        public static final String ORG_CHART_CODE = "orgChartCode";
        public static final String IP_ADDRESS = "ipAddress";
        public static final String USER_AGENT = "userAgent";
        public static final String BROWSER_ID = "browserId";
        public static final String ROLES = "roles";
    }

    public static class SERVER_TOKEN_CLAIMS {
        private SERVER_TOKEN_CLAIMS() {}

        public static final String CHANNEL = "channel";
        public static final String IP_ADDRESS = "ipAddress";
    }
}