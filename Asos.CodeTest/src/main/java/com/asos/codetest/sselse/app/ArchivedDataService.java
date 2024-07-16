package com.asos.codetest.sselse.app;

import org.apache.commons.lang3.NotImplementedException;

import java.sql.*;

public class ArchivedDataService {
    private String connectionString;
    AppSettings appSettings;
    public ArchivedDataService(AppSettings appSettings){
        this.appSettings = appSettings;
        connectionString = appSettings.getFailOverDatabaseConnectionString();
    }

    public Customer getArchivedCustomer(int customerId) throws SQLException {

        try(Connection connection = DriverManager.getConnection(connectionString)) {
            PreparedStatement statement = connection.prepareStatement("SELECT Id, Name FROM Customer WHERE CustomerId = ?");
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.first()) {
                Customer customer = new Customer();
                customer.setId( resultSet.getInt(0) );
                customer.setName(resultSet.getString(1));
                return customer;
            }

            return null;

        } catch (SQLException e) {
            throw e;
        }
    }
}
