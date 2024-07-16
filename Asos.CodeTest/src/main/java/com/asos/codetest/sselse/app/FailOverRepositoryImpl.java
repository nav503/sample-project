package com.asos.codetest.sselse.app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FailOverRepositoryImpl implements FailOverRepository{

    private String connectionString;

    public FailOverRepositoryImpl() {
        AppSettings appSettings = new ApplicationSettings();
        connectionString = appSettings.getFailOverDatabaseConnectionString();
    }

    @Override
    public List<FailOverEntry> getFailOverEntries() throws SQLException {
        List<FailOverEntry> failOverEntries = new ArrayList<FailOverEntry>();

        try(Connection connection = DriverManager.getConnection(connectionString)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("{call GetFailoverEntries}");
            while (resultSet.next()) {
                FailOverEntry failOverEntry = new FailOverEntry();
                failOverEntry.setDate( resultSet.getDate(0) );
                failOverEntries.add(failOverEntry);
            }

        } catch (SQLException e) {
            throw e;
        }

        return failOverEntries;
    }
}
