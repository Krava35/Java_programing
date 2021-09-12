package com.kravchenko.classes;

public class Root extends Heroes implements Cloneable {
    private Color color;
    private Muzzle muzzle;

    {
        setName("крыса");
        color = Color.GREY;
    }

    public Root(){};

    public Root(Color c){
        this.color = c;
    }

    public Root(Color c, Volume v){
        this(c);
        setBody_volume(v);
    }

    public void setColor(Color c){
        this.color = c;
    }

    public Color getColor(){
        return color;
    }

    public void setMuzzleMove(Moves move){
        muzzle.setMove(move);
    }

    public String getMuzzleMove(){
        return muzzle.getMove();
    }

    public String getMuzzle() {
        return muzzle.StuckOut();
    }

    public String getNose(){
        return muzzle.nose.Sniff();
    }

    public class Muzzle{
        private boolean mustache;
        private Moves move;
        private Nose nose;

        {
            mustache = true;
        }

        public void setMustache(boolean m) {
            this.mustache = m;
        }

        public boolean getMustache(){
            return mustache;
        }

        public void setMove(Moves move) {
            this.move = move;
        }

        public String getMove() {
            return this.move.getMove();
        }

        public String StuckOut(){
            if (getMustache() == true){
                return (this.getMove() + " усатую мордочку");
            }
            else{
                return (this.getMove() + " не усатую мордочку");
            }
        }

        public class Nose{
            private Moves move;
            {
                move = Moves.SNIFF;
            }

            public String Sniff(){
                return (move.getMove() + "носом");
            }
        }

    }

    @Override
    public Root clone() {
        try {
            return (Root)super.clone();
        }
        catch( CloneNotSupportedException ex ) {
            throw new InternalError();
        }
    }

}
