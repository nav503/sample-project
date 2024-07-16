package com.asos.codetest.sselse.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationProperties(prefix = "config")
@ConfigurationPropertiesScan
public class ApplicationSettings implements AppSettings{

    private boolean isFailoverModeEnabled;

    private String customerServiceThirdPartyHttpConnection;

    private String archiveDatabaseConnection;

    private String failoverDatabaseConnection;

    @Override
    public boolean getIsFailoverModeEnabled() {
        return this.isFailoverModeEnabled;
    }

    @Override
    public String getThirdPartyCustomerServiceBaseUrl() {
        return this.customerServiceThirdPartyHttpConnection;
    }

    @Override
    public String getFailOverDatabaseConnectionString() {
        return this.failoverDatabaseConnection;
    }

    @Override
    public String getArchiveDatabaseConnectionString() {
        return this.archiveDatabaseConnection;
    }

    public void setFailoverDatabaseConnection(String failoverDatabaseConnection) {
        this.failoverDatabaseConnection = failoverDatabaseConnection;
    }

    public void setArchiveDatabaseConnection(String archiveDatabaseConnection) {
        this.archiveDatabaseConnection = archiveDatabaseConnection;
    }

    public void setCustomerServiceThirdPartyHttpConnection(String customerServiceThirdPartyHttpConnection) {
        customerServiceThirdPartyHttpConnection = customerServiceThirdPartyHttpConnection;
    }

    public void setFailoverModeEnabled(boolean failoverModeEnabled) {
        isFailoverModeEnabled = failoverModeEnabled;
    }
}
