package com.taxi.domain;

import com.taxi.service.PasswordEncryptor;

import java.sql.Date;
import java.util.Objects;

public class User {
    private final String id;
    private final String email;
    private final String password;
    private final String name;
    private final String surname;
    private final String telephoneNumber;
    private final Date birthday;
    private final String gender;
    private final Role role;

    private User(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.password = builder.password;
        this.name = builder.name;
        this.surname = builder.surname;
        this.telephoneNumber = builder.telephoneNumber;
        this.birthday = builder.birthday;
        this.gender = builder.gender;
        this.role = builder.role;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static User copyUser(User user){
        PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
        return new User.Builder()
                .withId(user.getId())
                .withEmail(user.getEmail())
                .withPassword(passwordEncryptor.encrypt(user.getPassword()))
                .withName(user.getName())
                .withSurname(user.getSurname())
                .withPhone(user.getTelephoneNumber())
                .withRole(user.getRole())
                .withBirthday(user.getBirthday())
                .withGender(user.getGender())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(telephoneNumber, user.telephoneNumber) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, surname, telephoneNumber, birthday, gender, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", role=" + role +
                '}';
    }

    public static class Builder {
        private String id;
        private String email;
        private String password;
        private String name;
        private String surname;
        private String telephoneNumber;
        private Date birthday;
        private String gender;
        private Role role;

        private Builder() {
        }

        public User build() {
            return new User(this);
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withPhone(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
            return this;
        }

        public Builder withBirthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }
    }
}
