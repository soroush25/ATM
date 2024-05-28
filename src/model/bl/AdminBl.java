package src.model.bl;

import lombok.Getter;
import src.controller.exceptions.NotFoundException;
import src.model.da.AdminDa;
import src.model.entity.Admin;
import src.model.tools.CRUD;

import java.util.List;

public class AdminBl implements CRUD<Admin> {
    @Getter
    private static AdminBl adminBl = new AdminBl();

    private AdminBl() {
    }

    @Override
    public Admin save(Admin admin) throws Exception {
        try (AdminDa adminDa = new AdminDa()) {
            adminDa.save(admin);
            return admin;
        }
    }

    @Override
    public Admin edit(Admin admin) throws Exception {
        try (AdminDa adminDa = new AdminDa()) {
            if (adminDa.findById(admin.getId()) != null) {
                adminDa.edit(admin);
                return admin;
            } else {
                throw new NotFoundException();
            }
        }
    }

    @Override
    public Admin remove(int id) throws Exception {
        try (AdminDa adminDa = new AdminDa()) {
            Admin admin = adminDa.findById(id);
            if (admin != null) {
                adminDa.remove(id);
                return admin;
            } else {
                throw new NotFoundException();
            }
        }
    }

    @Override
    public List<Admin> findAll() throws Exception {
        try (AdminDa adminDa = new AdminDa()) {
            List<Admin> perosnList = adminDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NotFoundException();
            }
        }
    }

    @Override
    public Admin findById(int id) throws Exception {
        try (AdminDa adminDa = new AdminDa()) {
            Admin admin = adminDa.findById(id);
            if (admin != null) {
                return admin;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public List<Admin> findByFamily(String family) throws Exception {
        try (AdminDa adminDa = new AdminDa()) {
            List<Admin> adminList = adminDa.findByFamily(family);
            if (!adminList.isEmpty()) {
                return adminList;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public Admin findByNationalId(String nationalId) throws Exception {
        try (AdminDa adminDa = new AdminDa()) {
            Admin admin = adminDa.findByNationalId(nationalId);
            if (admin != null) {
                return admin;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public Admin findByUsername(String username) throws Exception {
        try (AdminDa adminDa = new AdminDa()) {
            Admin admin = adminDa.findByUsername(username);
            if (admin != null) {
                return admin;
            } else {
                throw new NotFoundException();
            }
        }
    }

    public Admin findByUsernameAndPassword(String username,String password) throws Exception {
        try (AdminDa adminDa = new AdminDa()) {
            Admin admin = adminDa.findByUsernameAndPassword(username, password);
            if (admin != null) {
                return admin;
            } else {
                throw new NotFoundException();
            }
        }
    }
}
