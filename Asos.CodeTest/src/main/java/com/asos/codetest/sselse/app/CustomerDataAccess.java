package com.asos.codetest.sselse.app;

import java.io.IOException;

public interface CustomerDataAccess {
    CustomerResponse loadCustomer(int customerId) throws IOException;
}
