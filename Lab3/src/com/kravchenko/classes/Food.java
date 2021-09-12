package com.kravchenko.classes;

public class Food {
    int quantity;
    String name;
    boolean exist;

    {
        name = "неизвестно";
        quantity = 0;
        exist = false;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        if (this.quantity != 0){
            this.exist = true;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public boolean getExist(){
        return exist;
    }


    public boolean equals(Food food){
        return (quantity == food.quantity) && (name == food.name);
    }

    @Override
    public String toString(){
        return getClass().getName()+"@"+hashCode();
    }

    @Override
    public int hashCode(){
        return quantity * 10;
    }
}
