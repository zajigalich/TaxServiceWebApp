package com.my.web.dto;

import java.sql.Date;

public class UserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String tin;

    private String type;

    private String address;

    private Date dateOfRegistration;

    private Long userId;

    private String userRole;

    public static Builder builder() {
        return new UserDTO.Builder();
    }


    public static class Builder {

        private final UserDTO userDTO;

        public Builder() {
            userDTO = new UserDTO();
        }

        public Builder firstName(String firstName) {
            this.userDTO.setFirstName(firstName);
            return this;
        }

        public Builder lastName(String lastName) {
            this.userDTO.setLastName(lastName);
            return this;
        }

        public Builder email(String email) {
            this.userDTO.setEmail(email);
            return this;
        }

        public Builder password(String password) {
            this.userDTO.setPassword(password);
            return this;
        }

        public Builder tin(String tin) {
            this.userDTO.setTin(tin);
            return this;
        }

        public Builder dateOfRegistration(Date dateOfRegistration) {
            this.userDTO.setDateOfRegistration(dateOfRegistration);
            return this;
        }

        public Builder entrepreneurType(String type) {
            this.userDTO.setType(type);
            return this;
        }

        public Builder address(String address) {
            this.userDTO.setAddress(address);
            return this;
        }

        public Builder userId(Long userId) {
            this.userDTO.setUserId(userId);
            return this;
        }

        public Builder userRole(String userRole) {
            this.userDTO.setUserRole(userRole);
            return this;
        }
        public UserDTO build() {
            return this.userDTO;
        }
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", tin='" + tin + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", dateOfRegistration=" + dateOfRegistration +
                ", userId=" + userId +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
