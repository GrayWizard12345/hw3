package com.example.hamlet.hw3;

import java.io.Serializable;

public class Vote implements Serializable {
    private String firstName;
    private String lastName;
    private boolean willCome;
    private String drink;
    private String food;

    public Vote(String firstName, String lastName, boolean willCome, String drink, String food) {
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

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    @Override
    public String toString() {
        if (willCome) {
            return String.format("-> %s %s will go to the party, and will drink %s and eat %s\n\n",
                    firstName, lastName, drink, food);
        }

        return String.format("-> %s %s will not come\n\n", firstName, lastName);
    }
}
