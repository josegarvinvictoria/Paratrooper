package net.josegarvin;
import acm.graphics.GImage;
import acm.graphics.GRectangle;




public abstract class ObjecteEnMoviment {

    GImage imatge;



    public abstract void startPlay(Main finestra);

    public abstract void startPlay(Main finestra, ObjecteEnMoviment helicopter);


    /**
     * Mètode per comprovar si els helicopters estan dins de la finestra
     * @param finestra
     * @return
     */

    public final boolean estaDinsFinestra(Main finestra, GImage imatgeOM) {
        GRectangle GRfinestra  = new GRectangle(0, 0,
                finestra.getWidth(), finestra.getHeight());
        boolean estaDins = GRfinestra.getBounds().intersects(imatgeOM.getBounds());
        if(!estaDins){
            System.out.println("Objecte fora de la finestra! ELIMINAT!");
            imatgeOM.getParent().remove(imatgeOM);
        }
        return estaDins;
    }

    /**
     * Mètode per invertir l'imatge d'un soldat.
     */
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

    public GImage getImatge() {
        return imatge;
    }

    public void setImatge(GImage imatge) {
        this.imatge = imatge;
    }







}