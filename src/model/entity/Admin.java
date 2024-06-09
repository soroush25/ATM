package src.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.model.entity.enums.City;
import src.model.entity.enums.Gender;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Admin {
    private int id;
    private String firstName;
    private String lastName;
    private String nationalId;
    private Gender gender;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private String address;
    private City city;
    private String username;
    private String password;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}