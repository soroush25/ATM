package mft.model.bl;

import lombok.Getter;
import mft.controller.exceptions.NoPersonFoundException;
import mft.model.da.PersonDa;
import mft.model.entity.Customer;
import mft.model.tools.CRUD;

import java.util.List;

public class PersonBl implements CRUD<Customer> {
    @Getter
    private static PersonBl personBl = new PersonBl();

    private PersonBl() {
    }

    @Override
    public Customer save(Customer customer) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            personDa.save(customer);
            return customer;
        }
    }

    @Override
    public Customer edit(Customer customer) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            if (personDa.findById(customer.getId()) != null) {
                personDa.edit(customer);
                return customer;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    @Override
    public Customer remove(int id) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            Customer customer = personDa.findById(id);
            if (customer != null) {
                personDa.remove(id);
                return customer;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    @Override
    public List<Customer> findAll() throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            List<Customer> perosnList = personDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    @Override
    public Customer findById(int id) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            Customer customer = personDa.findById(id);
            if (customer != null) {
                return customer;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    public List<Customer> findByFamily(String family) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            List<Customer> perosnList = personDa.findByFamily(family);
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }
}
