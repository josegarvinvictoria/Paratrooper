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

        // Helicopter helicopter = crearHelicopterAleatori();
        //
        //
        //
        // if (helicopter.estaDinsFinestra(this, helicopter.getImatge())) {
        // System.out.println("Esta dins");
        //
        // //Creem un array en el que el length correpont al numero de soldats
        // int[] posicionsSortida = helicopter.calculOperturaPortes(this);
        //
        // int numSoldats = posicionsSortida.length;
        //
        // //Carreguem el soldats a l'helicopter.
        // helicopter.carregaSoldats(numSoldats);
        //
        //
        // while (helicopter.estaDinsFinestra(this, helicopter.getImatge())) {
        //
        // helicopter.moureHelicopter(this);
        //
        // helicopter.obrirPortes(posicionsSortida, this);

        // }
        // }

        /**
         *
         * ESTO ES NUEVO!
         *
         */
        boolean haSortit = false;

        this.crearHelicopterAleatori();
        this.generarSoldats(7);
        ObjecteEnMoviment helicopterPare = new Helicopter();


        helicopterPare = objectesEnMoviment.get(0);
        while (!haSortit) {
            for (int i = 0; i < objectesEnMoviment.size(); i++) {

                // Desem els helicopters en una variable per poder pasarlos al
                // mètode startPlay dels soldats.

                if (objectesEnMoviment.get(i) instanceof Helicopter) {
                    helicopterPare = objectesEnMoviment.get(i);

                    if (!helicopterPare.estaDinsFinestra(this,
                            helicopterPare.getImatge())) {
                        haSortit = true;
                    } else {
                        objectesEnMoviment.get(i).startPlay(this);
                    }

                }
                if (objectesEnMoviment.get(i) instanceof Soldat) {
                    Soldat soldat = ((Soldat) objectesEnMoviment.get(i));
                    soldat.startPlay(this, helicopterPare);



                }
            }
            this.pause(25);
        }
    }

    public void crearHelicopterAleatori() {
        /**
         * Creem un helicopter
         */
        Helicopter helicopter = new Helicopter(assignarImatgeHeli());
        if (helicopter.ubicacio == 0) {
            helicopter.getImatge().setLocation(
                    0 - helicopter.getImatge().getWidth(), 0);
            helicopter.flipHorizontal(helicopter.getImatges().get(0));
            helicopter.flipHorizontal(helicopter.getImatges().get(1));
            this.add(helicopter.getImatge());

        } else {
            helicopter.getImatge().setLocation(
                    this.getWidth() - helicopter.getImatge().getWidth(), 0);
            this.add(helicopter.getImatge());
        }

        objectesEnMoviment.add(helicopter);
    }

    /**
     * Generar Soldats Aleatoris.
     */
    public void generarSoldats(int numSoldats) {
        System.out.println("Nº de soldats a bord: " + numSoldats);
        for (int i = 0; i < numSoldats; i++) {

            Soldat soldat = new Soldat();
            objectesEnMoviment.add(soldat);
        }
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
            cano.rotarTubCano(-7);
            System.out.println("Tecla polsada.");

        }
        if (e.VK_RIGHT == e.getKeyCode()) {
            cano.rotarTubCano(7);
            System.out.println("Tecla polsada.");
        }
        if (e.VK_SPACE == e.getKeyCode()) {

            cano.dispara(this);

            System.out.println("Tecla polsada.");
        }
    }

    public static int getTamanyY() {
        return TAMANY_Y;
    }

    public static int getTamanyX() {
        return TAMANY_X;
    }

}