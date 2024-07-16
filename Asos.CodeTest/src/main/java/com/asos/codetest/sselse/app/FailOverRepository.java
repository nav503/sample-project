package com.asos.codetest.sselse.app;

import java.sql.SQLException;
import java.util.List;

public interface FailOverRepository {
    List<FailOverEntry> getFailOverEntries() throws SQLException;
}
