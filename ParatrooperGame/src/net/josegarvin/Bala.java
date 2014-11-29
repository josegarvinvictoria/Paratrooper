package net.josegarvin;

import java.util.ArrayList;

import acm.graphics.GImage;

public class Bala extends ObjecteEnMoviment {

    double x;
    double y;

    Bala(double inicix, double iniciy) {
        this.setImatge(new GImage("resources/cano/bala.png"));
        x = inicix;
        y = iniciy;
        setHaSortit(false);
    }

    public void moureBala() {
        // this.getImatge().move(0, -5);
        this.getImatge().move(this.x * -7, this.y * -7);
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

        if (this.estaDinsFinestra(finestra, getImatge())) {
            if (!balaMata(finestra)) {
                this.moureBala();

            }

        } else {
            finestra.objectesEnMoviment.remove(this);
            this.haSortit = true;
        }

    }

    public boolean balaMata(Main finestra) {

        for (int i = finestra.objectesEnMoviment.size() -1; i >0 ; i--) {

            boolean toca = this
                    .getImatge()
                    .getBounds()
                    .intersects(
                            finestra.objectesEnMoviment.get(i).getImatge()
                                    .getBounds()) && !(finestra.objectesEnMoviment.get(i) instanceof Bala);
            if (toca) {
                System.out.println("La bala toca alguna cosa!");
                finestra.objectesEnMoviment.get(i).getImatge().getParent().remove(finestra.objectesEnMoviment.get(i).getImatge());
                this.getImatge().getParent().remove(this.getImatge());
                return true;

            }
            break;

        }
        return false;
    }

    @Override
    public void startPlay(Main finestra, ObjecteEnMoviment helicopter) {
        // TODO Auto-generated method stub

    }

}
