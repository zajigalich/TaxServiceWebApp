package com.my.persistence.entity;

import java.sql.Date;
import java.util.Objects;

public class User extends AbstractBaseEntity {

    private String email;

    private String password;

    private UserRole userRole;

    private EntrepreneurType type;

    private String name;

    private String lastName;

    private Date dateOfRegistration;

    private String tin;

    private String address;

    public static Builder builder(){
        return new User.Builder();
    }

    public static class Builder extends AbstractEntityBuilder<User> {

        public Builder() {
            this.entity = new User();
        }

        @Override
        public Builder id(Long userId) {
            //this.user.setId(userId);
            this.entity.id = userId;
            return this;
        }

        public Builder name(String firstName) {
            this.entity.name = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.entity.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.entity.email = email;
            return this;
        }

        public Builder password(String password) {
            this.entity.password = password;
            return this;
        }

        public Builder tin(String tin) {
            this.entity.tin = tin;
            return this;
        }

        public Builder entrepreneurType(EntrepreneurType type) {
            this.entity.type = type;
            return this;
        }

        public Builder address(String address) {
            this.entity.address = address;
            return this;
        }

        public Builder dateOfRegistration(Date dateOfRegistration) {
            this.entity.dateOfRegistration = dateOfRegistration;
            return this;
        }

        public Builder userRole(UserRole userRole) {
            this.entity.userRole = userRole;
            return this;
        }
        public User build() {
            return this.entity;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public EntrepreneurType getEntrepreneurType() {
        return type;
    }

    public void setEntrepreneurType(EntrepreneurType entrepreneurType) {
        this.type = entrepreneurType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        return Objects.equals(id, report.id);
    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + userRole.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (dateOfRegistration != null ? dateOfRegistration.hashCode() : 0);
        result = 31 * result + (tin != null ? tin.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfRegistration=" + dateOfRegistration +
                ", tin='" + tin + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
