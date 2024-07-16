package com.asos.codetest.sselse.app;

public interface AppSettings {
    boolean getIsFailoverModeEnabled();
    String getThirdPartyCustomerServiceBaseUrl();

    String getFailOverDatabaseConnectionString();
    String getArchiveDatabaseConnectionString();
}
