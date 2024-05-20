package src.model.tools;

import java.util.regex.Pattern;

public class Validator {
    public static String nameValidator(String name, String message) throws Exception {
        if(Pattern.matches("[\\s*\\w*\\s*]{3,30}", name)){
            return name;
        }else{
            throw new Exception(message);
        }
    }
    public static String nationalIDValidator(String nationalID, String message) throws Exception {
        if(Pattern.matches("\\d{3}\\-{0,1}\\d{6}\\-{0,1}\\d{1}", nationalID)){
            return nationalID;
        }else{
            throw new Exception(message);
        }
    }
    public static String addressValidator(String address, String message) throws Exception {
        if(Pattern.matches("[\\s*\\w\\s*\\-*\\s*\\w*\\,*]{0,200}", address)){
            return address;
        }else{
            throw new Exception(message);
        }
    }
    public static String emailValidator(String email, String message) throws Exception {
        if(Pattern.matches("\\w*\\.*\\w*\\@(gmail|yahoo).com", email)){
            return email;
        }else{
            throw new Exception(message);
        }
    }
    public static String phoneValidator(String phone, String message) throws Exception {
        if(Pattern.matches("09\\d{9}|\\+989\\d{9}", phone)){
            return phone;
        }else{
            throw new Exception(message);
        }
    }
}
