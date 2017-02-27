package ru.stqa.training.selenium.model;

/**
 * Created by irinagavrilova on 2/27/17.
 */
public class RegisterData {
  private final String firstName;
  private final String lastName;
  private final String address1;
  private final String zipCode;
  private final String city;
  private final String email;
  private final String phone;
 // private final String country;
 // private final String state;
  private final String password;
  private final String confirmedPassword;

  public RegisterData(String firstName, String lastName, String address1,
                      String zipCode, String city, String email, String phone,
                      String password, String confirmedPassword) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address1 = address1;
    this.zipCode = zipCode;
    this.city = city;
    this.email = email;
    this.phone = phone;
  //  this.country = country;
  //  this.state = state;
    this.password = password;
    this.confirmedPassword = confirmedPassword;
  }

 // public String getState() {return state;}

 // public String getCountry() {
 //   return country;
 // }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getCity() {
    return city;
  }

  public String getZipCode() {
    return zipCode;
  }

  public String getAddress1() {
    return address1;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getPassword() {
    return password;
  }

  public String getConfirmedPassword() {
    return confirmedPassword;
  }
}
