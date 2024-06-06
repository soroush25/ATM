package src.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.model.entity.enums.AccountType;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Account {
    private int accountNumber;
    private int balance;
    private Customer customer;
    private AccountType accountTypes;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
