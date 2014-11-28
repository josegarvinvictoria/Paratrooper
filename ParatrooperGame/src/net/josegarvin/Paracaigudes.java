package net.josegarvin;

import acm.graphics.GImage;

public class Paracaigudes {
    GImage imatge;

    public Paracaigudes() {
        // TODO Auto-generated constructor stub
        this.setImatge(new GImage("resources/soldat/paracaigudesR.png"));
    }

    public GImage getImatge() {
        return imatge;
    }

    public void setImatge(GImage imatge) {
        this.imatge = imatge;
    }



}
