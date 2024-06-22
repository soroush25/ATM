package src.model.bl;

import lombok.Getter;
import src.controller.exceptions.NotFoundException;
import src.model.da.AccountDa;
import src.model.entity.Account;
import src.model.tools.CRUD;

import java.util.List;

public class AccountBl implements CRUD<Account> {
    @Getter
    private static AccountBl accountBl = new AccountBl();

    private AccountBl() {
    }

    @Override
    public Account save(Account account) throws Exception {
        try (AccountDa accountDa = new AccountDa()) {
            accountDa.save(account);
            return account;
        }
    }

    @Override
    public Account edit(Account account) throws Exception {
        try (AccountDa accountDa = new AccountDa()) {
            if (accountDa.findById(account.getCustomer().getId()) != null) {
                accountDa.edit(account);
                return account;
            } else {
                throw new NotFoundException();
            }
        }
    }

    @Override
    public Account remove(int id) throws Exception {
        try (AccountDa accountDa = new AccountDa()) {
            Account account = accountDa.findById(id);
            if (account != null) {
                accountDa.remove(id);
                return account;
            } else {
                throw new NotFoundException();
            }
        }
    }


    @Override
    public List<Account> findAll() throws Exception {
        try (AccountDa accountDa = new AccountDa()) {
            List<Account> accountList = accountDa.findAll();
            if (!accountList.isEmpty()) {
                for (Account account : accountList) {
                    account.setCustomer(CustomerBl.getCustomerBl().findById(account.getCustomer().getId()));
                }
                return accountList;
            } else {
                throw new NotFoundException();
            }
        }
    }

    @Override
    public Account findById(int id) throws Exception {
        try (AccountDa accountDa = new AccountDa()) {
            Account account = accountDa.findById(id);
            if (account != null) {
                account.setCustomer(CustomerBl.getCustomerBl().findById(account.getCustomer().getId()));
                return account;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public Account findByAccountNumber(int accountNumber) throws Exception {
        try (AccountDa accountDa = new AccountDa()) {
            Account account = accountDa.findByAccountNumber(accountNumber);
            if (account != null) {
                account.setCustomer(CustomerBl.getCustomerBl().findById(account.getCustomer().getId()));
                return account;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public String balanceSum () throws Exception {
        try (AccountDa accountDa = new AccountDa()) {
            return accountDa.balanceSum();
        }
    }


    public Account findByCustomerId(int customer) throws Exception {
        try (AccountDa accountDa = new AccountDa()) {
            Account account = accountDa.findByCustomerId(customer);
            if (account != null) {
                    account.setCustomer(CustomerBl.getCustomerBl().findById(account.getCustomer().getId()));
                    return account;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public List<Account> findByAccountType(String customer) throws Exception {
        try (AccountDa accountDa = new AccountDa()) {
            List<Account> accountList = accountDa.findByAccountType(customer);
            if (!accountList.isEmpty()) {
                for (Account account : accountList) {
                    account.setCustomer(CustomerBl.getCustomerBl().findById(account.getCustomer().getId()));
                }
                return accountList;
            } else {
                throw new NotFoundException();
            }
        }
    }
}
