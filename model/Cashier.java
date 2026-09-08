package model;

public class Cashier extends User{
    public Cashier(String username,String password){

        super(username,password);
    }

    @Override
    public String getRole() {
        return "Cashier";
    }
}
