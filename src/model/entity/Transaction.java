package src.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.model.entity.enums.TransactionTypes;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Transaction {
    private int id;
    private int amount;
    private int deposit;
    private Account sourceAccount;
    private Account destinationAccount;
    private LocalDate transactionDate;
    private TransactionTypes transactionType;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
