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

    void moureBala(Main finestra) {
        imatge.setLocation(x, finestra.getHeight()/2);
        finestra.add(imatge);

//		while (this.estaDinsFinestra(finestra, this.getImatge())) {
//			System.out.println("Esta dins");
//			imatge.move(0, 2);
//			finestra.pause(100);
//
//		}

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

    }

    @Override
    public void startPlay(Main finestra, ObjecteEnMoviment helicopter) {
        // TODO Auto-generated method stub

    }



}
