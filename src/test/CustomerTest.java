package src.test;

import src.model.bl.CustomerBl;

import src.model.entity.Customer;
import src.model.entity.enums.City;
import src.model.entity.enums.Gender;

import java.time.LocalDate;

public class CustomerTest {
    public static void main(String[] args) throws Exception {
        Customer customer = Customer
                .builder()
                .firstName("ali")
                .lastName("reza")
                .nationalId("0123456789")
                .gender(Gender.Male)
                .birthDate(LocalDate.now())
                .city(City.Tabriz)
                .build();
        System.out.println(CustomerBl.getCustomerBl().save(customer));
    }
}
