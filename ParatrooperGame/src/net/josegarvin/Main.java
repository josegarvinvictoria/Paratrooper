package net.josegarvin;

import java.awt.Font;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRectangle;
import acm.program.GraphicsProgram;

public class Main extends GraphicsProgram {

    ArrayList<ObjecteEnMoviment> objectesEnMoviment = new ArrayList<ObjecteEnMoviment>();

    private static final int TAMANY_Y = 540;

    private static final int TAMANY_X = 900;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creem l'objecte "Cano".
     */
    Cano cano = new Cano(new GImage("resources/cano/canonbase.png"),
            new GImage("resources/cano/canontub.png"), this);

    public final void run() {

        /**
         * Assignem un tamany a la finestra.
         */
        this.setSize(TAMANY_X, TAMANY_Y);

        mostrarBenvinguda();

        controlInici();
        // this.pause(1000);
        addKeyListeners(this);
        iniciarJoc();

    }

    void mostrarBenvinguda() {
        add(new GImage("resources/startGame.png"));
    }

    void iniciarJoc() {
        /**
         * Assignem la imatge de fons.
         */
        add(new GImage("resources/gamebackground.jpg"));

        /**
         * Afegim la base del cano i el col.loquem.
         */
        add(cano.getBaseCano());
        cano.getBaseCano().setLocation(
                Main.getTamanyX() / 2 - ((cano.getBaseCano().getWidth()) / 2),
                Main.getTamanyY() - cano.getBaseCano().getHeight());

        /**
         * Afegim el tub del cano i el col.loquem.
         */
        add(cano.getTubCano());
        cano.getTubCano().setLocation(
                Main.getTamanyX() / 2 - ((cano.getBaseCano().getWidth()) / 2),
                Main.getTamanyY() - cano.getBaseCano().getHeight());

        /**
         *
         * ESTO ES NUEVO!
         *
         */
        boolean haSortit = false;
        boolean jocFinalitzat = false;
        // Generar helicopters aletoris.
        // int numHelicopters = generarRandom(4);
        // System.out.println(numHelicopters);
        // for (int i = 0; i < numHelicopters; i++) {
        // this.crearHelicopterAleatori();
        // }

        Helicopter helicopterPare = new Helicopter();

        while (!jocFinalitzat) {
            generarHelicopters();

            for (int i = 0; i < objectesEnMoviment.size(); i++) {

                // Desem els helicopters en una variable per poder pasarlos al
                // mètode startPlay dels soldats.

                if (!objectesEnMoviment.get(i).isHaSortit()) {

                    // Si trobem un soldat:
                    if (objectesEnMoviment.get(i) instanceof Soldat) {

                        objectesEnMoviment.get(i).startPlay(this,
                                helicopterPare);

                        // Si trobem un helicopter:
                    }
                    if (objectesEnMoviment.get(i) instanceof Helicopter) {
                        helicopterPare = (Helicopter) objectesEnMoviment.get(i);
                        objectesEnMoviment.get(i).startPlay(this);

                        // Si trobem una bala:
                    } else {
                        objectesEnMoviment.get(i).startPlay(this);
                    }

                }

            }
            this.pause(25);

        }
    }

    public void generarHelicopters() {
        int x = generarRandom(500);
        int numHelicopters = generarRandom(4);
        if (x == 1) {
            for (int i = 0; i < numHelicopters; i++) {
                crearHelicopterAleatori();
            }
        }
    }

    public void crearHelicopterAleatori() {
        /**
         * Creem un helicopter
         */

        Helicopter helicopter = new Helicopter(assignarImatgeHeli());
        if (helicopter.ubicacio == 0) {
            helicopter.getImatge().setLocation(
                    0 - helicopter.getImatge().getWidth(),
                    generarFilaAleatoria());
            helicopter.flipHorizontal(helicopter.getImatges().get(0));
            helicopter.flipHorizontal(helicopter.getImatges().get(1));
            this.add(helicopter.getImatge());

        } else {
            helicopter.getImatge().setLocation(this.getWidth(),
                    generarFilaAleatoria());
            this.add(helicopter.getImatge());
        }

        objectesEnMoviment.add(helicopter);
        generarSoldats(generarRandom(2));

    }

    /**
     * Generar Soldats Aleatoris.
     */
    public void generarSoldats(int numSoldats) {
        //System.out.println("Nº de soldats a bord: " + numSoldats);

        for (int i = 0; i < numSoldats; i++) {
            int xSalt = generarRandom(800);
            Soldat soldat = new Soldat(xSalt);
            objectesEnMoviment.add(soldat);
        }
    }

    public int generarFilaAleatoria() {
        int ran = generarRandom01();
        if (ran == 0) {
            return 0;
        }
        return 95;

    }

    public int generarRandom(int valor) {
        Random rnd = new Random();
        return (int) (rnd.nextDouble() * valor) + 1;
    }

    public int generarRandom01() {
        Random rnd = new Random();
        return (int) (rnd.nextDouble() * 2);

    }

    final void controlInici() {
        GLabel glabel = new GLabel("");
        glabel.setLocation(TAMANY_X / 2 - (glabel.getWidth() / 2), TAMANY_Y / 2);

        add(glabel);
        waitForClick();
        remove(glabel);

    }

    public ArrayList<GImage> assignarImatgeHeli() {
        ArrayList<GImage> imatgesHeli = new ArrayList<GImage>();
        imatgesHeli.add(new GImage("resources/minihelicopter1.png"));
        imatgesHeli.add(new GImage("resources/minihelicopter1-1.png"));
        return imatgesHeli;

    }

    public void keyPressed(KeyEvent e) {

        if (e.VK_LEFT == e.getKeyCode()) {
             cano.rotarTubCano(-5);

             if (cano.getOrientacio() < 1.75) {
                 double orientacioActual = cano.getOrientacio();
                 cano.setOrientacio(orientacioActual += 0.25);
             }

             System.out.println(cano.getOrientacio());

        }
        if (e.VK_RIGHT == e.getKeyCode()) {
             cano.rotarTubCano(5);

             if (cano.getOrientacio() > -1.75) {
                 double orientacioActual = cano.getOrientacio();
                 cano.setOrientacio(orientacioActual -= 0.25);
             }

             System.out.println(cano.getOrientacio());


        }
        if (e.VK_SPACE == e.getKeyCode()) {

            // Calcul angles bala
            double balaX = Math.sin(cano.getOrientacio());
            double balaY = Math.cos(cano.getOrientacio());

            Bala novaBala = cano.dispara(balaX, balaY);
            objectesEnMoviment.add(novaBala);
            novaBala.getImatge().setLocation(cano.getTubCano().getX() +23  , cano.getTubCano().getY() - 8);
            this.add(novaBala.getImatge());
            //System.out.println("Bala afegida!");
            System.out.println(objectesEnMoviment.toString());
        }
    }

    public static int getTamanyY() {
        return TAMANY_Y;
    }

    public static int getTamanyX() {
        return TAMANY_X;
    }

}
