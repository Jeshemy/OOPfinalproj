package oop.tanregister.register;

import java.io.Serializable;

public class Users implements Serializable {
    private String firstname;
    private String middlename;
    private String lastname;
    private String email;
    private String phonenumber;
    private String address;
    private String password;

    public Users(String firstname, String middlename, String lastname,
                 String email, int age, String birthdate,
                 String phonenumber, String address, String password) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.address = address;
        this.password = password;
    }

    public Users(String firstName, String middlename, String lastName, String email, String phone, String address, String password) {
    }

    // === Getters and Setters ===
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getMiddlename() { return middlename; }
    public void setMiddlename(String middlename) { this.middlename = middlename; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhonenumber() { return phonenumber; }
    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
