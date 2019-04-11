package util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

public class ImageConvertor {
    public static byte[] getByte(BufferedImage image){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            ImageIO.write(image, "png", baos);
        }catch(Exception e){
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
    public static BufferedImage getImage(byte[] bytes){
        InputStream in = new ByteArrayInputStream(bytes);
        BufferedImage image = null;
        try{
            image = ImageIO.read(in);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(image);
        return image;
    }

    public static BufferedImage getBuffered(Image image){
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image,null);
        return bufferedImage;
    }

    public static Image getFXImage(BufferedImage bufferedImage){
        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        System.out.println(image);
        return image;
    }

}
