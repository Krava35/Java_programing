package com.kravchenko.classes;

public enum Times {
    TRAVEL_DAYS(0),
    DAYS_IN_MONTH(0),
    MONTH_IN_YEARS(0),
    MONTHS_WITH_FOOD(0),
    YEARS_WITH_FOOD(0);

    private int number;

    Times(int number){
        this.number = number;
    }

    public void setTravelDays(int n){
        TRAVEL_DAYS.number = n;
    }

    public void setDays(int n){
        DAYS_IN_MONTH.number = n;
    }

    public void setMonths(int n){
        MONTH_IN_YEARS.number = n;
    }

    public void setMonthsWithFood(int n){
        MONTHS_WITH_FOOD.number = n;
    }

    public void setYearsWithFood(int n){
        this.YEARS_WITH_FOOD.number = n;
    }

    public int getNumber(){
        return number;
    }
}
