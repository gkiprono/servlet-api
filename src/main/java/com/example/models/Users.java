/*
* Users class demonstrating builder design pattern
*
*
* */

package com.example.models;


public class Users extends Person{

    public Users() {
    }

    private Users(PersonBuilder builder){
        this.setUserId(builder.userId);
        this.setEmail(builder.email);
        this.setSalt(builder.salt);
        this.setPassword(builder.password);
        this.setFirstName(builder.firstName);
        this.setLastName(builder.lastName);
        this.setAccountId(builder.accountId);
        this.setUserName(builder.userName);
    }

    @Override
    public int getUserId() {
        return super.getUserId();
    }

    @Override
    public void setUserId(int userId) {
        super.setUserId(userId);
    }

    @Override
    public int getAccountId() {
        return super.getAccountId();
    }

    @Override
    public void setAccountId(int accountId) {
        super.setAccountId(accountId);
    }

    @Override
    public String getSalt() {
        return super.getSalt();
    }

    @Override
    public void setSalt(String salt) {
        super.setSalt(salt);
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getUserName() {
        return super.getUserName();
    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static class PersonBuilder{
        private final String firstName;
        private final String lastName;
        private String email;
        private String userName;
        private String password;
        private int accountId;
        private int userId;
        private String salt;

        public PersonBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public PersonBuilder email(String email){
            this.email = email;
            return this;
        }
        public PersonBuilder userName(String userName){
            this.userName = userName;
            return this;
        }

        public PersonBuilder password(String ts){
            this.password = ts;
            return this;
        }

        public PersonBuilder accountId(int id){
            this.accountId = id;
            return this;
        }

        public PersonBuilder userId(int id){
            this.userId = id;
            return this;
        }

        public PersonBuilder salt(String salt){
            this.salt = salt;
            return this;
        }

        public Users build(){
            Users user = new Users(this);
            return user;
        }
    }
}
