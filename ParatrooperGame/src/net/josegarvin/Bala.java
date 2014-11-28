package net.josegarvin;

import acm.graphics.GImage;

public class Bala extends ObjecteEnMoviment {

    double x;
    double y;

    Bala(double inicix, double iniciy) {
        this.setImatge(new GImage("resources/cano/bala.png"));
        x = inicix;
        y = iniciy;
    }

    public void moureBala(){
        this.getImatge().move(0, 2);
    }


    public GImage getImatge() {
        return imatge;
    }

    public void setImatge(GImage imatge) {
        this.imatge = imatge;
    }

    @Override
    public void startPlay(Main finestra) {
        // TODO Auto-generated method stub
        this.moureBala();
    }

    @Override
    public void startPlay(Main finestra, ObjecteEnMoviment helicopter) {
        // TODO Auto-generated method stub

    }



}
