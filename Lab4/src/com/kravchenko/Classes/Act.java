package com.kravchenko.Classes;

import com.sun.source.tree.NewArrayTree;

public class Act {
    private Traveler traveler;
    private Traveler traveler2;
    private Traveler[] PlanedTravelers;
    private Rat rat1 = new Rat();
    private Rat rat2 = new Rat();
    private Bedbugs bedbugs = new Bedbugs();
    private Korotishki korotishka;
    private Homeless homeless = new Homeless();
    private Tupichok tupichok = new Tupichok();
    private Food FoodSupplies;
    private Times MonthWithFood;
    private Times YearsWithFood;
    private Times InTravel;
    private Times DaysInMonth;
    private Times MonthsInYear;

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

    private void NeznaiksEyes(){
        Heroes.Eyes eyes = traveler.new Eyes();
        eyes.setOpen(true);
        System.out.println(this.traveler.getName() + " открыл глаза");
        System.out.println(this.traveler.getName() + " увидел " + rat1.getColor() + rat1.getBodyCondition() + rat1.getName());
    }

    private void Status(String status){
        traveler.setStatus(status);
        System.out.println( traveler.getName() + " " + traveler.getStatus() );
    }

    private void ratLookOn(Korotishki person, String eyesStatus){
        rat1.getMuzzle().getEyes(eyesStatus);
        System.out.println( " на " + (person.getName()));
    }

    private void WhereAreSleep(){
        tupichok.mixed();
        if (tupichok.findFreeShelf() == true){
            traveler.climb();
        }
        else{
            System.out.println("Нет мест для сна");
        }
    }

    private void FollowAdvice(){
        korotishka.giveAdvice("Лежать тихо", traveler);
        if (traveler.getAdvice() == true){
            System.out.println(traveler.getName() + " следует совету от " + korotishka.getName());
        }
        else {
            System.out.println(traveler.getName() + " не получас советов");
        }
    }

    private void BedbugsEat(){
        bedbugs.eat();
        System.out.println(" " + traveler.getName());
    }

    private void Illnesses(){
        homeless.addIllnesses(Illness.COLD);
        homeless.addIllnesses(Illness.COUGHT);
        System.out.print("У бездомных коротышек: ");
        homeless.getIllnesses();
        System.out.println();
    }

    private void StatusPlace(){
        tupichok.setStatus("шумно");
        System.out.println("В тупичке " + tupichok.getStatus());
        tupichok.setStatus("вонь");
        System.out.println("В тупичке " + tupichok.getStatus());
        tupichok.setStatus("душно");
        System.out.println("В тупичке " + tupichok.getStatus());
    }

    public void addTravelers(Traveler traveler1, Traveler traveler2){
        this.traveler = traveler1;
        this.traveler2 = traveler2;
    };

    public void addKorotishka(Korotishki korotishka){
        this.korotishka = korotishka;
    }
    public void setPlanedTravelers(Traveler[] planedTravelers){
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
        this.FoodSupplies.setQuantity(traveler.multy(InTravel.getNumber(), PlanedTravelers.length));
    }

    private void MonthAndYearsWithFood() {
        if (FoodSupplies.getExist() == true) {
            this.YearsWithFood.setYearsWithFood(traveler.div(FoodSupplies.getQuantity(),
                    MonthsInYear.getNumber() * DaysInMonth.getNumber()));
            this.MonthWithFood.setMonthsWithFood(traveler.div(
                    traveler.mod(FoodSupplies.getQuantity(), MonthsInYear.getNumber() * DaysInMonth.getNumber()),
                    DaysInMonth.getNumber()));
        } else {
            System.out.println("Еды нет");
        }
    }

    private boolean isTravelerCorrect() throws NullPointerException{
        if ((traveler != null) || (traveler2 != null)){
            return true;
        }
        else{
            throw new TravelerException("Exception: Объекты путешествиников - null");
        }
    }

    private void  timeException() throws TimeException{
        if ((DaysInMonth.getNumber() > 0) || (MonthsInYear.getNumber() > 0)){
        }
        else{
            throw new TimeException("Exception: Так c временем не бывает, давай устоновим стандартные значения");
        }
    }






    public void start(){
        try {
            isTravelerCorrect();
        }
        catch (TravelerException exception){
            System.out.println(exception.getMessage());
            System.exit(0);
        }

        try {
            timeException();
        }
        catch (TimeException exception){
            System.out.println(exception.getMessage());
            this.setDaysInMonth(30);
            this.setMonthsInYear(12);
        }



        NeznaiksEyes();
        rat1.getMuzzle().wipedOut();
        rat1.getMuzzle().MoveNose();
        rat1.getMuzzle().BreathNose();
        traveler.phrase("-Чу! Чтоб ты пропал! ");
        Status("испугался");
        rat1.run();
        rat1.stop();
        ratLookOn(traveler, "блястящими");
        Status("тревожен");
        rat2.getOut();
        Status("заметил такую же крысу");
        rat2.getMuzzle().MoveNose();
        Status("не в безопасности");
        WhereAreSleep();
        korotishka.giveAdvice("Лежать надо тихо", traveler);
        FollowAdvice();
        BedbugsEat();
        traveler.phrase("–Ешьте, черти! Хоть всего съешьте! Всё равно жизнь такая, что её и не жалко вовсе! ");
        Illnesses();
        homeless.sleep();
        homeless.moan();
        homeless.moo();
        homeless.scream();
        Status("вздрагивет");
        StatusPlace();
        Status("вспонил о " + traveler2.getName() + "e");
        Status("рассчитывает на сколько дней хватит " + traveler2.getName() + "у еды");
        Status("вспомнил, что запас еды был приготовлен " + InTravel.getNumber() +
                " дней для " + getPlannedTravelers());
        setFoodSupplies();
        System.out.println("Получилось " + FoodSupplies.getQuantity() + " порций");
        System.out.println("В году круглым счётом " + DaysInMonth.getNumber() * MonthsInYear.getNumber() + " дней");
        System.out.println("А в месяце " + DaysInMonth.getNumber() + " дней");
        MonthAndYearsWithFood();
        System.out.println(traveler2.getName() + "у должно хватить еды на " + YearsWithFood.getNumber() +
                " год и " + MonthWithFood.getNumber() + " месеца");
        traveler2.setStatus("в безопасности");
        System.out.println(traveler2.getName() + " " + traveler2.getStatus());
        Status("в спокойствие");
        traveler.sleep();
    }

}
