package model;

abstract public class User{
    protected String username;
    protected String password;

    public User(String username,String password){
        this.username=username;
        this.password=password;
    }

    abstract public String getRole();

    public String getUsername(){
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
}