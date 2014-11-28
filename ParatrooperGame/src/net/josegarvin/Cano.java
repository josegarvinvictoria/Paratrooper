package net.josegarvin;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import acm.graphics.GImage;

public class Cano {

    GImage baseCano;
    GImage tubCano;
    double posXBase;
    double posYBase;
    double posXTub;
    double posYTub;


    Cano(GImage base, GImage tub, Main finestra) {
        this.baseCano = base;
        this.tubCano = tub;
        this.posXBase = finestra.getTamanyX();
        this.posYBase = finestra.getTamanyY() - base.getHeight();

    }

    void rotarTubCano(int graus) {

        this.tubCano.setImage((rotateMyImage( toBufferedImage(this.getTubCano()
                .getImage()), graus)));
    }


    Bala dispara(){

        Bala bala = new Bala(tubCano.getY(), tubCano.getWidth()/2);
        System.out.println("Bala Creada!");
        return bala;
    }


    /**
     * Mètode per rotar una imatge.
     * @param img
     * @param angle
     * @return
     */
    public Image rotateMyImage(BufferedImage img, double angle) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(w, h, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.rotate(Math.toRadians(angle), w / 2, h / 2);

        g.drawImage(img, null, 0, 0);

        return (Image) dimg;
    }

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public GImage getTubCano() {
        return tubCano;
    }

    public void setTubCano(GImage tubCano) {
        this.tubCano = tubCano;
    }

    public GImage getBaseCano() {
        return baseCano;
    }

    public void setBaseCano(GImage baseCano) {
        this.baseCano = baseCano;
    }

}
