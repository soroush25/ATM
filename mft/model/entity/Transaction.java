package mft.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)

public class Transaction {
    private Date date;
    private Double amount;
    private String deposit;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}