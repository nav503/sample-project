package com.asos.codetest.sselse.app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CustomerService {

    private AppSettings appSettings;
    private FailOverRepository failoverRepository;
    private CustomerDataAccess customerDataAccess;

    public CustomerService(AppSettings appSettings, FailOverRepository failoverRepository, CustomerDataAccess customerDataAccess) {

        this.appSettings = appSettings;
        this.failoverRepository = failoverRepository;
        this.customerDataAccess = customerDataAccess;
    }

    public Customer getCustomer(int customerId, boolean isCustomerArchived) throws IOException, SQLException {
        Customer archivedCustomer = null;
        if(!isCustomerArchived) {
            ArchivedDataService archivedDataService = new ArchivedDataService(appSettings);
            archivedCustomer = archivedDataService.getArchivedCustomer(customerId);

            return archivedCustomer;
        }
        else {
            List<FailOverEntry> failoverEntries = failoverRepository.getFailOverEntries();
            int failedRequests = 0;

            for (FailOverEntry failoverEntry : failoverEntries) {
                Date now = new Date();
                if (failoverEntry.getDate().toInstant().isAfter( now.toInstant().minusMillis(600000)))
                {
                    failedRequests++;
                }
            }

            CustomerResponse customerResponse = null;
            Customer customer = null;

            if(failedRequests > 100 && appSettings.getIsFailoverModeEnabled()) {
                customerResponse = FailoverCustomerDataAccess.getCustomerById(customerId);
            } else {
                customerResponse = customerDataAccess.loadCustomer(customerId);
            }

            if(customerResponse.getIsArchived()) {
                ArchivedDataService archivedDataService = new ArchivedDataService(appSettings);
                customer = archivedDataService.getArchivedCustomer(customerId);
            } else {
                customer = customerResponse.getCustomer();
            }

            return customer;
        }
    }
}
