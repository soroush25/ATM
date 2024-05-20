package src.model.bl;

import lombok.Getter;
import src.controller.exceptions.NoPersonFoundException;
import src.model.da.UserDa;
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
        try (UserDa customerDa = new UserDa()) {
            customerDa.save(customer);
            return customer;
        }
    }

    @Override
    public Customer edit(Customer customer) throws Exception {
        try (UserDa customerDa = new UserDa()) {
            if (customerDa.findById(customer.getId()) != null) {
                customerDa.edit(customer);
                return customer;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    @Override
    public Customer remove(int id) throws Exception {
        try (UserDa customerDa = new UserDa()) {
            Customer customer = customerDa.findById(id);
            if (customer != null) {
                customerDa.remove(id);
                return customer;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    @Override
    public List<Customer> findAll() throws Exception {
        try (UserDa customerDa = new UserDa()) {
            List<Customer> perosnList = customerDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    @Override
    public Customer findById(int id) throws Exception {
        try (UserDa customerDa = new UserDa()) {
            Customer customer = customerDa.findById(id);
            if (customer != null) {
                return customer;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

//    public List<Customer> findByFamily(String family) throws Exception {
//        try (UserDa customerDa = new UserDa()) {
//            List<Customer> perosnList = customerDa.findByFamily(family);
//            if (!perosnList.isEmpty()) {
//                return perosnList;
//            } else {
//                throw new NoPersonFoundException();
//            }
//        }
//    }
}
