package com.example.hamlet.hw3;

public class Vote {
    private String firstName;
    private String lastName;
    private boolean willCome;
    private int drink;
    private int food;

    public Vote(String firstName, String lastName, boolean willCome, int drink, int food) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.willCome = willCome;
        this.drink = drink;
        this.food = food;
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

    public boolean willCome() {
        return willCome;
    }

    public void setWillCome(boolean willCome) {
        this.willCome = willCome;
    }

    public int getDrink() {
        return drink;
    }

    public void setDrink(int drink) {
        this.drink = drink;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }
}
