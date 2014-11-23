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
        //this.pause(1000);
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

        Helicopter helicopter = crearHelicopterAleatori();



        if (helicopter.estaDinsFinestra(this)) {
            System.out.println("Esta dins");
            while (helicopter.estaDinsFinestra(this)) {
                helicopter.moureHelicopter(this);
                helicopter.getImatgeActual().pause(50);
            }
        } else {
            System.out.println("Ha sortit");
            this.remove(helicopter.getImatgeActual());
        }

    }

public Helicopter crearHelicopterAleatori(){
    /**
     * Creem un helicopter
     */
    Helicopter helicopter = new Helicopter(assignarImatgeHeli());
    if(helicopter.ubicacio == 0){
        helicopter.getImatgeActual().setLocation(0 - helicopter.getImatgeActual().getWidth(), 0);
        this.add(helicopter.getImatgeActual());
    }else{
        helicopter.getImatgeActual().setLocation(this.getWidth() - helicopter.getImatgeActual().getWidth(),0);
        this.add(helicopter.getImatgeActual());
    }

    return helicopter;
}

    final void controlInici() {
        GLabel glabel = new GLabel("");
        glabel.setLocation(TAMANY_X / 2 - (glabel.getWidth() / 2), TAMANY_Y / 2);

        add(glabel);
        waitForClick();
        remove(glabel);

    }

    public final boolean estaDinsFinestra(final Helicopter helicopter) {
        GRectangle finestra = new GRectangle(0, 0, this.getWidth(),
                this.getHeight());
        boolean estaDins = finestra.getBounds().intersects(
                helicopter.getImatges().get(0).getBounds());
        return estaDins;
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
            // helicopter.moureHelicopter(this);
            cano.getTubCano().move(0, 50);
            // this.remove(cano.getTubCano());
            cano.getTubCano().move(0, -50);
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
