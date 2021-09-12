package com.kravchenko.classes;

public class Bedbug extends Heroes {
    private Korotishki eda;
    private boolean EatingBlood;

    {
        setName("клоппы");
    }


    public void setEatingBlood() {
        if (this.eda != null) {
            EatingBlood = true;
        } else {
            EatingBlood = false;
        }
    }

    public boolean isEatingBlood() {
        return EatingBlood;
    }

    public void setEda(Korotishki eda){
            this.eda = eda;
    }

    public Korotishki getEda(){
        return eda;
    }
}

