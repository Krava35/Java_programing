package com.kravchenko.Classes;

import com.kravchenko.Interfaces.GetOut;
import com.kravchenko.Interfaces.Run;
import com.kravchenko.Interfaces.Stop;

public class Rat extends Animal implements Run, Stop, GetOut {
    final private Muzzle muzzle = new Muzzle();
    private BodyCondition bodyCondition;

    {
        this.setName("крыса");
        this.bodyCondition = BodyCondition.FAT;
        setColor(Color.GREY);
    }

    @Override
    public void run(){
        System.out.println(this.getName() + " отбежала");
    }

    @Override
    public  void stop(){
        System.out.println(this.getName() + " остановилась");
    }

    @Override
    void eat() {
        System.out.println(this.getName() + " ест");
    }

    public  void getOut(){
        System.out.println(this.getName() + " вылезла");
    }

    public String getBodyCondition() {
        return bodyCondition.getCondition();
    }

    public void setBodyCondition(BodyCondition bodyCondition) {
        this.bodyCondition = bodyCondition;
    }

    public Muzzle getMuzzle() {
        return muzzle;
    }

    protected class Muzzle{

        public void BreathNose() {

            class Nose {

                public void breath(){
                    System.out.println(getName() + " обнюхивает ботинок");
                }

            }

            Nose nose = new Nose();

            nose.breath();
        }

        public void MoveNose() {

            class Nose {

                public void move(){
                    System.out.println(getName() + " шевелит носом");
                }

            }

            Nose nose = new Nose();

            nose.move();
        }


        public void getEyes(String status) {

            class Eyes{
                private String status;

                Eyes (String status){
                    this.status = status;
                }

                public String getStatus() {
                    return status;
                }


                public void watch(){
                    System.out.print(getName() + " смотрит " + getStatus() + " глазами");
                }
            }

            Eyes eyes = new Eyes(status);
            eyes.watch();
        }

        public void wipedOut(){
            System.out.println(getName() + " вытинула мордочку");
        }



        }
}
