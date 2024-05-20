package src.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.model.entity.enums.BankAccountTypes;
import src.model.entity.enums.Banks;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)

public class Account {
    private int accountNumber;
    private int balance;
    private Customer customer;
    private Transaction transaction;
    private Banks bank;
    private BankAccountTypes accountTypes;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
