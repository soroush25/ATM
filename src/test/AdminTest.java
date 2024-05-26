package src.test;

import src.model.bl.AdminBl;
import src.model.entity.Admin;
import src.model.entity.enums.Gender;

import java.time.LocalDate;

public class AdminTest {
    public static void main(String[] args) throws Exception {
        Admin admin = Admin
                .builder()
                .username("ali")
                .password("alipour")
                .gender(Gender.Male)
                .birthDate(LocalDate.now())
                .build();
        System.out.println(AdminBl.getAdminBl().save(admin));
    }
}
