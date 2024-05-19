package mft.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)

public class Account {
    private int accountNumber;
    private int balance;
    private Transaction transaction;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
