package net.josegarvin;

import java.util.Random;

import acm.graphics.GImage;

public class Soldat extends ObjecteEnMoviment {

    boolean haSaltat;
    double xSalt = 0;
    boolean tocaTerra;
    Paracaigudes paraca;

    public Soldat() {
        // TODO Auto-generated constructor stub
        this.setImatge(new GImage("resources/soldat/soldierMini.png"));
        haSaltat = false;
        xSalt = calculXSalt();
    }

    public void saltarHelicopter(Main finestra, ObjecteEnMoviment helicopter) {
        this.getImatge().setLocation(helicopter.getImatge().getX() + 55,
                helicopter.getImatge().getY() + 55);
        finestra.add(this.getImatge());

        this.imatge.move(0, 1);

    }

    public int calculXSalt() {
        Random rnd = new Random();
        return (int) (rnd.nextDouble() * 900);
    }

    @Override
    public void startPlay(Main finestra) {
        // TODO Auto-generated method stub

    }

    @Override
    public void startPlay(Main finestra, ObjecteEnMoviment helicopter) {
        // TODO Auto-generated method stub

        // Si el soldat no ha saltat.
        if (!this.isHaSaltat()) {

            // Quan s'arriba a la pos de salt el fem saltar.
            if (helicopter.getImatge().getX() == this.getxSalt()) {
                System.out.println("Salta!");
                this.saltarHelicopter(finestra, helicopter);
                this.setHaSaltat(true);
            }

        }// Si el soldat ha saltat i no ha tocat terra
        if (this.haSaltat && !this.tocaTerra) {
            this.caure();

            if(this.getImatge().getY() == finestra.getHeight()/2){
                System.out.println("Obrir paracaigudes!");
                obrirParaca(finestra);
            }

            if (this.getImatge().getY() == (finestra.getHeight() - this
                    .getImatge().getHeight())) {
                this.setTocaTerra(true);
                finestra.remove(paraca.getImatge());
                this.paraca = null;

            }

        }



    }

    public void obrirParaca(Main finestra){
        Paracaigudes paracaigudes = new Paracaigudes();
        this.paraca = paracaigudes;
        paraca.getImatge().setLocation(this.getImatge().getX() +5, this.getImatge().getY() - 30);
        finestra.add(paraca.getImatge());
    }

    public void caure() {

        if(this.paraca == null){
            this.imatge.move(0, 2);
        }
        if(this.paraca != null){
            System.out.println("Baixa amb paraca!");
            this.imatge.move(0, 0.5);
            this.paraca.getImatge().move(0, 0.5);
        }

    }

    public boolean isTocaTerra() {
        return tocaTerra;
    }

    public void setTocaTerra(boolean tocaTerra) {
        this.tocaTerra = tocaTerra;
    }

    public boolean isHaSaltat() {
        return haSaltat;
    }

    public void setHaSaltat(boolean haSaltat) {
        this.haSaltat = haSaltat;
    }

    public double getxSalt() {
        return xSalt;
    }

    public void setxSalt(double xSalt) {
        this.xSalt = xSalt;
    }

}