package net.josegarvin;

import java.util.ArrayList;
import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class Helicopter {

    GImage imatgeActual;
    ArrayList<GImage> imatges;
    int indexImatge;
    int velocitatD;
    int velocitatE;
    int ubicacio;


    Helicopter(ArrayList<GImage> imatgesHeli){
        imatgeActual = imatgesHeli.get(0);
        indexImatge = 0;
        imatges = imatgesHeli;
        velocitatD = 5;
        velocitatE = -5;
        ubicacio = generarRandom();
    }


   /**
    * Mètode per moure els helicopters
    * @param main
    */
    public void moureHelicopter(Main main){
            if(ubicacio == 0){
                this.getImatgeActual().move(20, 0.7);

            }else{
                this.getImatgeActual().move(-20, 0.7);
            }

           canviImatge(main);

    }

    /**
     * Mètode per comprovar si els helicopters estan dins de la finestra
     * @param finestra
     * @return
     */
    public final boolean estaDinsFinestra(Main finestra) {
        GRectangle GRfinestra  = new GRectangle(0, 0,
                finestra.getWidth(), finestra.getHeight());
        boolean estaDins = GRfinestra.getBounds().intersects(this.getImatgeActual().getBounds());
        return estaDins;
    }

    /**
     * Mètode per donar l'efecte de moviment a l'helicopter.
     * @param main
     */
    public void canviImatge(Main main){
        if(indexImatge == 0){
            double x = imatgeActual.getX();
            double y = imatgeActual.getY();
            main.remove(imatgeActual);
            setImatgeActual(imatges.get(1));
            imatgeActual.setLocation(x, y);

            indexImatge = 1;

        }else{
            double x = imatgeActual.getX();
            double y = imatgeActual.getY();
            main.remove(imatgeActual);
            setImatgeActual(imatges.get(0));
            imatgeActual.setLocation(x, y);
            indexImatge = 0;
        }
        main.add(imatgeActual);
     }




    public GImage getImatgeActual() {
        return imatgeActual;
    }


    public void setImatgeActual(GImage imatgeActual) {
        this.imatgeActual = imatgeActual;
    }


    public ArrayList<GImage> getImatges() {
        return imatges;
    }


    public void setImatges(ArrayList<GImage> imatges) {
        this.imatges = imatges;
    }


    /**
     * Generar valor aleatori per determinar des de on ha de sortir l'helicopter.
     * @return --> 0 o 1.
     */
    public int generarRandom(){
        Random rnd = new Random();
        return (int)(rnd.nextDouble() * 2);

    }

    final void flipHorizontal(GImage imatge) {
        int[][] array = imatge.getPixelArray();
        int height = array.length;
        int width = array[0].length;

        for (int y = 0; y < height; y++) {
            for (int x1 = 0; x1 < width / 2; x1++) {
                int x2 = width - x1 - 1;
                int temp = array[y][x1];
                array[y][x1] = array[y][x2];
                array[y][x2] = temp;
            }
        }
        imatge.setImage(new GImage(array).getImage());
    }




}
