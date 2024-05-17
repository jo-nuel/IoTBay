package uts.isd.controller;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator implements Serializable {

    private String emailPattern = ("^(.+)@(.+)$");
    private String namePattern = "[a-zA-Z\\s]*";
    private String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{3,20}$";
    private String phonePattern = "[0-9]{8,10}";

    public Validator() {
    }

    // email validation
    public String validateEmail(String input) {
        Pattern regEx = Pattern.compile(emailPattern);
        Matcher match = regEx.matcher(input);
        boolean check = match.matches();
        if (check) {
            return null;
        } else {
            return "Email: Ensure it has a similar layout to the provided example e.g. johnsmith@email.com";
        }
    }

    // name validation
    public String validateName(String input) {
        Pattern regEx = Pattern.compile(namePattern);
        Matcher match = regEx.matcher(input);
        boolean check = match.matches();
        if (check) {
            return null;
        } else {
            return "Name: Please enter a valid name";
        }
    }

    // password validation
    public String validatePassword(String input) {
        Pattern regEx = Pattern.compile(passwordPattern);
        Matcher match = regEx.matcher(input);
        boolean check = match.matches();
        if (check) {
            return null;
        } else {
            return "Password: Miniumn one digit, one uppercase, one lowercase and is 3-10 characters long.";
        }
    }

    // phone validation
    public String validatePhone(String input) {
        Pattern regEx = Pattern.compile(phonePattern);
        Matcher match = regEx.matcher(input);
        boolean check = match.matches();
        if (check) {
            return null;
        } else {
            return "Phone: Please ensure contact number is 8-10 digits long.";
        }
    }
}