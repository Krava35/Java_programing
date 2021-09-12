package com.kravchenko.classes;

public class Act {

    private Times InTravel;
    private Times DaysInMonth;
    private Times MonthsInYear;
    private Korotishki traveler1;
    private Korotishki traveler2;
    private Korotishki Pokladistyi;
    private Korotishki[] Tramps;
    private Korotishki[] PlanedTravelers;
    private Root[] roots;
    private Bedbug bedbug;
    private Advice advice;
    private Food FoodSupplies;
    private Times MonthWithFood;
    private Times YearsWithFood;
    Operation mult = Operation.MULTIPLY;
    Operation div = Operation.DIV;
    Operation mod = Operation.MOD;

    {
        InTravel = Times.TRAVEL_DAYS;
        DaysInMonth = Times.DAYS_IN_MONTH;
        MonthsInYear = Times.MONTH_IN_YEARS;
        FoodSupplies = new Food();
        InTravel.setTravelDays(10);
        MonthWithFood = Times.MONTHS_WITH_FOOD;
        YearsWithFood = Times.YEARS_WITH_FOOD;
    }


    public Act(){};

    public void addTravelers(Korotishki traveler1, Korotishki traveler2){
        this.traveler1 = traveler1;
        this.traveler2 = traveler2;
    };
    public void setPlanedTravelers(Korotishki[] planedTravelers){
        this.PlanedTravelers = planedTravelers;
    }
    public String getPlannedTravelers(){
        return(PlanedTravelers.length + " путешествеников");
    }

    public void setDaysInMonth(int days){
        this.DaysInMonth.setDays(days);
    }
    public void setMonthsInYear(int months){
        this.MonthsInYear.setMonths(months);
    }
    public void setFoodSupplies(){
        this.FoodSupplies.setQuantity(traveler1.multy(mult, InTravel.getNumber(), PlanedTravelers.length));
    }

    public void MonthAndYearsWithFood() {
        if (FoodSupplies.getExist() == true) {
            this.YearsWithFood.setYearsWithFood(traveler1.div(div, FoodSupplies.getQuantity(),
                    MonthsInYear.getNumber() * DaysInMonth.getNumber()));
            this.MonthWithFood.setMonthsWithFood(traveler1.div(div,
                    traveler1.mod(mod, FoodSupplies.getQuantity(), MonthsInYear.getNumber() * DaysInMonth.getNumber()),
                    DaysInMonth.getNumber()));
        } else {
            System.out.println("Еды нет");
        }
    }


    public void Start(){
        traveler1.setEyesMove(Moves.OPENS);
        System.out.println(traveler1.getEyesMove());
        traveler1.setMove(Moves.SEE);
        roots[0].setBody_volume(Volume.FAT);
        System.out.println(traveler1.getMove() + " " + roots[0].getBody_volume() + " "
                + roots[0].getColor() + roots[0].getName() );
        System.out.println(roots[0].getName() + " " + roots[0].getMuzzle());
        System.out.println(roots[0].getName() + " " + roots[0].getNose() + " ботинок");
        traveler1.setStatus(StatusHeroes.SCARED, 0);
        System.out.println(traveler1.getStatus(0));
        traveler1.setFootMove(Moves.PULL_BACK);
        System.out.println(traveler1.getFootMove());
        roots[0].setMove(Moves.RUN_AWAY);
        System.out.println(roots[0].getMove());
        roots[0].setMove(Moves.STOP);
        System.out.println(roots[0].getMove() + "не подалёку");
        roots[0].setMove(Moves.PEEPS);
        System.out.println(roots[0].getMove() + "на Незнайку блястяшими глазами");
        traveler1.setStatus(StatusHeroes.ANXIETY, 0);
        System.out.println(traveler1.getStatus(0));
        traveler1.setMove(Moves.LOOK_AROUND);
        System.out.println(traveler1.getMove() + "по сторонам");
        traveler1.setMove(Moves.NOTICE);
        roots[1] = roots[0].clone();
        System.out.println(traveler1.getMove() + " ещё одну крысу");
        roots[1].setMove(Moves.GET_OUT);
        System.out.println(roots[1].getMove() + " из под лавки");
        System.out.println(roots[1].getNose());
        traveler1.setMove(Moves.UNDERSTAND);
        Place.TUPICHOK.setStatusPlace(StatusPlace.NOT_SAFE, 0);
        System.out.println(traveler1.getMove() + " " + Place.TUPICHOK.getStatusPlace(0));
        traveler1.setMove(Moves.CLIMB);
        System.out.println(traveler1.getMove() + " на полку");
        if ((advice.getFrom() == Pokladistyi) && (advice.getTo() == traveler1)){
            traveler1.setMove(Moves.FOLLOW_ADVICE);
            System.out.println(traveler1.getMove() + " " + Pokladistyi.getName());
        }
        traveler1.setMove(Moves.LIE_ON);
        System.out.println(traveler1.getMove());
        traveler1.setStatus(StatusHeroes.QUITE, 0);
        System.out.println(traveler1.getStatus(0));
        bedbug.setEda(traveler1);
        bedbug.setEatingBlood();
        if ((bedbug.getEda() == traveler1) && (bedbug.isEatingBlood() == true)){
            bedbug.setMove(Moves.EATING);
            System.out.println(bedbug.getMove() + "" + bedbug.getEda().getName());
        }
        traveler1.setStatus(StatusHeroes.ANGRY, 1);
        traveler1.setMove(Moves.MUTTER);
        System.out.println(traveler1.getStatus(1));
        System.out.println(traveler1.getMove());





    }

    public boolean equals(Heroes hero1, Heroes hero2 ){
        return (this.traveler1.getName() == hero1.getName()) && (this.traveler2.getName() == hero2.getName());
    }

    public String toString(){
        return getClass().getName()+"@"+hashCode();
    }

    public int hashCode(){
        return traveler1.getName().length() + traveler2.getName().length();
    }
}
