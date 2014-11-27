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
    ArrayList<Soldat> soldatsAbord;

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
        soldatsAbord = new ArrayList<>();
    }

    /**
     * Mètode per moure els helicopters
     *
     * @param main
     */

    public void startPlay(Main finestra) {


            this.moureHelicopter(finestra);

    }

    public void moureHelicopter(Main finestra) {
        if (ubicacio == 0) {
            this.getImatge().move(1, 0);

        } else {
            this.getImatge().move(-1, 0);

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

    public int[] calculOperturaPortes(Main finestra) {
        Random rnd = new Random();

        // Calculem un numero a l'atzar de soldats.
        int[] xAleatoris = new int[(int) (rnd.nextDouble() * 7 + 1)];

        // Generem les coordenades X que correspondran al moment de l'expulsio
        // dels soldats de l'helicopter....

        for (int i = 0; i < xAleatoris.length; i++) {
            xAleatoris[i] = (int) (rnd.nextDouble() * finestra.getWidth());
        }

        return xAleatoris;

    }

    public void obrirPortes(int[] posicionsX, Main finestra) {
        for (int i = 0; i < posicionsX.length; i++) {
            if (this.getImatge().getX() == posicionsX[i]) {
                soldatsAbord.get(soldatsAbord.size() - 1).saltarHelicopter(
                        finestra, this);
                soldatsAbord.remove(soldatsAbord.size() - 1);
            }
        }

        // if (this.getImatge().getX() == posicionsX[0]) {
        // System.out.println("Surt Soldat!");
        // for (int i = 0; i < soldatsAbord.size(); i++) {
        // soldatsAbord.get(i).saltarHelicopter(finestra, this);
        // }
        // }
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

    public void carregaSoldats(int numSoldats) {
        System.out.println("Nº de soldats a bord: " + numSoldats);
        for (int i = 0; i < numSoldats; i++) {

            Soldat soldat = new Soldat();
            soldatsAbord.add(soldat);
        }
    }



    @Override
    public void startPlay(Main finestra, ObjecteEnMoviment helicopter) {
        // TODO Auto-generated method stub

    }

}