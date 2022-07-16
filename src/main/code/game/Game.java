package main.code.game;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private ArrayList<String> playerList = new ArrayList<String>();
   // private int currentPlayerTurnIndex = 0;
    private ArrayList<Image> imageList = new ArrayList<Image>();
    private ArrayList<String> cardDirectories = new ArrayList<String>();

    public void setPlayerList(ArrayList<String> playerList) {
        this.playerList = playerList;
    }

    public ArrayList<String> getPlayerList() {
        return playerList;
    }

    public void setCardDirectories(ArrayList<String> cardDirectories) {
        this.cardDirectories = cardDirectories;
        setImageList();
    }

    public void clearGame(){
        playerList.clear();
        imageList.clear();
        cardDirectories.clear();;
    }

    // array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]{
            "gif", "png", "bmp", "jpg" // and other formats you need
    };
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };

    private void setImageList() {
        for (int i = 0; i < cardDirectories.size(); i++) {
            final File dir = new File("src/main/resources/cards/" + cardDirectories.get(i));

            if (dir.isDirectory()) {
                for (final File f : dir.listFiles(IMAGE_FILTER)) {
                    BufferedImage img = null;

                    try {
                        img = ImageIO.read(f);
                        Image image = SwingFXUtils.toFXImage(img, null);

                        imageList.add(image);

                    } catch (final IOException e) {
                        // handle errors here
                    }
                }
            }
        }

        Collections.shuffle(imageList);
    }

   public Image getTopOfCardDeck() throws IOException {
        if(imageList.size() > 0) {
            Image img = imageList.get(0);
            imageList.remove(0);
            imageList.add(img);

            return img;
        } else {
            File dir = new File("src/main/resources/imgs/logo.png");
            BufferedImage img = ImageIO.read(dir);
            Image image = SwingFXUtils.toFXImage(img, null);

            return image;
        }
   }

}
