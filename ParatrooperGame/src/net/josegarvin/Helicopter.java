package net.josegarvin;

import java.util.ArrayList;
import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class Helicopter extends ObjecteEnMoviment {

    ArrayList<GImage> imatges;
    int indexImatge;
    int velocitatD;
    int velocitatE;
    int ubicacio;


    Helicopter(){

    }
    /**
     * Constructor
     *
     * @param imatgesHeli
     */


    Helicopter(ArrayList<GImage> imatgesHeli) {
        imatge = imatgesHeli.get(0);
        indexImatge = 0;
        imatges = imatgesHeli;
        velocitatD = 5;
        velocitatE = -5;
        ubicacio = generarRandom();
        this.setHaSortit(false);

    }

    /**
     * Mètode per moure els helicopters
     *
     * @param main
     */

    public void startPlay(Main finestra) {

        if(this.estaDinsFinestra(finestra, getImatge())){
            this.moureHelicopter(finestra);
        }else{
            this.haSortit = true;
        }


    }

    public void moureHelicopter(Main finestra) {
        if (ubicacio == 0) {
            this.getImatge().move(2, 0);

        } else {
            this.getImatge().move(-2, 0);

        }

        canviImatge(finestra);

    }

    /**
     * Mètode per donar l'efecte de moviment a l'helicopter.
     *
     * @param main
     */
    public void canviImatge(Main main) {
        if (indexImatge == 0) {

            double x = imatge.getX();
            double y = imatge.getY();
            main.remove(imatge);
            setImatge(imatges.get(1));

            imatge.setLocation(x, y);

            indexImatge = 1;

        } else {
            double x = imatge.getX();
            double y = imatge.getY();
            main.remove(imatge);
            setImatge(imatges.get(0));
            imatge.setLocation(x, y);
            indexImatge = 0;
        }
        main.add(imatge);
    }



    public ArrayList<GImage> getImatges() {
        return imatges;
    }

    public void setImatges(ArrayList<GImage> imatges) {
        this.imatges = imatges;
    }

    /**
     * Generar valor aleatori per determinar des de on ha de sortir
     * l'helicopter.
     *
     * @return --> 0 o 1.
     */
    public int generarRandom() {
        Random rnd = new Random();
        return (int) (rnd.nextDouble() * 2);

    }





    @Override
    public void startPlay(Main finestra, ObjecteEnMoviment helicopter) {
        // TODO Auto-generated method stub

    }

}
