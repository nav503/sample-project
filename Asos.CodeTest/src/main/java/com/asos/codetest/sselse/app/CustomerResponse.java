package com.asos.codetest.sselse.app;

public class CustomerResponse {

    private boolean isArchived;
    private Customer customer;

    public CustomerResponse() {
        this.customer = new Customer();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean getIsArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }
}
