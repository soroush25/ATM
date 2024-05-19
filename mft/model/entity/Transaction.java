package mft.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mft.model.entity.enums.TransactionTypes;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)

public class Transaction {
    private LocalDateTime date;
    private Double amount;
    private String deposit;
    private LocalDateTime transactionDateAndTime;
    private TransactionTypes transactionType;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
