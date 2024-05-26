package src.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.model.entity.enums.TransactionTypes;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Transaction {
    private int id;
    private Double amount;
    private String deposit;
    private Account sourceAccount;
    private Account destinationAccount;
    private LocalDateTime transactionDateTime;
    private TransactionTypes transactionType;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
