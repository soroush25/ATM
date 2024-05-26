package src.model.bl;

import lombok.Getter;
import src.controller.exceptions.NotFoundException;
import src.model.da.CustomerDa;
import src.model.entity.Customer;
import src.model.tools.CRUD;

import java.util.List;

public class CustomerBl implements CRUD<Customer> {
    @Getter
    private static CustomerBl customerBl = new CustomerBl();

    private CustomerBl() {
    }

    @Override
    public Customer save(Customer customer) throws Exception {
        try (CustomerDa customerDa = new CustomerDa()) {
            customerDa.save(customer);
            return customer;
        }
    }

    @Override
    public Customer edit(Customer customer) throws Exception {
        try (CustomerDa customerDa = new CustomerDa()) {
            if (customerDa.findById(customer.getId()) != null) {
                customerDa.edit(customer);
                return customer;
            } else {
                throw new NotFoundException();
            }
        }
    }

    @Override
    public Customer remove(int id) throws Exception {
        try (CustomerDa customerDa = new CustomerDa()) {
            Customer customer = customerDa.findById(id);
            if (customer != null) {
                customerDa.remove(id);
                return customer;
            } else {
                throw new NotFoundException();
            }
        }
    }

    @Override
    public List<Customer> findAll() throws Exception {
        try (CustomerDa customerDa = new CustomerDa()) {
            List<Customer> perosnList = customerDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NotFoundException();
            }
        }
    }

    @Override
    public Customer findById(int id) throws Exception {
        try (CustomerDa customerDa = new CustomerDa()) {
            Customer customer = customerDa.findById(id);
            if (customer != null) {
                return customer;
            } else {
                throw new NotFoundException();
            }
        }
    }

    @Override
    public List<Customer> findByFamily(String family) throws Exception {
        try (CustomerDa customerDa = new CustomerDa()) {
            List<Customer> customerList = customerDa.findByFamily(family);
            if (!customerList.isEmpty()) {
                return customerList;
            } else {
                throw new NotFoundException();
            }
        }
    }
}
