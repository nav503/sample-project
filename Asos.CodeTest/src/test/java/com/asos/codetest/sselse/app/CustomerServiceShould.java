package com.asos.codetest.sselse.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class CustomerServiceShould {

    private CustomerService customerService;
    private FailOverRepository failOverRepositoryMock;
    private AppSettings appSettingsMock;
    private CustomerDataAccess customerDataAccessMock;

    final String stubConnectionString = "jdbc:sqlserver://localhost:1433;databaseName=CustomerServiceArchive;user=csArchiveUser;password=$ecret";
    @BeforeEach
    public void setUp() {
        failOverRepositoryMock = mock(FailOverRepository.class);
        customerDataAccessMock = mock(CustomerDataAccess.class);
        appSettingsMock = mock(AppSettings.class);
    }

    @Test
    public void ReturnsCustomerFromMainCustomerDataStore_WHEN_NotInFailOver_AND_NotArchived() throws IOException, SQLException {
        // Arrange
        final int customerId = 12345;
        List<FailOverEntry> emptyFailoverEntries = new ArrayList<FailOverEntry>();
        Customer expectedCustomer = new Customer();
        CustomerResponse stubCustomerResponse = mock(CustomerResponse.class);

        expectedCustomer.setId(customerId);
        expectedCustomer.setName("Test Customer");
        when(appSettingsMock.getIsFailoverModeEnabled()).thenReturn(true);
        when(appSettingsMock.getFailOverDatabaseConnectionString()).thenReturn(stubConnectionString);
        when(failOverRepositoryMock.getFailOverEntries()).thenReturn(emptyFailoverEntries);
        when(stubCustomerResponse.getCustomer()).thenReturn(expectedCustomer);
        when(customerDataAccessMock.loadCustomer(customerId)).thenReturn(stubCustomerResponse);
        customerService = new CustomerService(appSettingsMock, failOverRepositoryMock, customerDataAccessMock);

        // Act
        Customer actualCustomer = customerService.getCustomer(customerId, false);

        // Assert
        assertThat(expectedCustomer, is(equalTo(actualCustomer)));
    }
}
