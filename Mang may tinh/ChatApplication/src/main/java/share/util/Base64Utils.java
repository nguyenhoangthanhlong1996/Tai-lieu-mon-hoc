package share.util;

import javafx.scene.image.Image;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class Base64Utils {
    public static String encoder(String path) {
        String base64Image = "";
        File file = new File(path);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Exception while reading the Image " + ioe);
        }
        return base64Image;
    }

    public static Image getImageFromBase64String(String base64String) throws IOException {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(base64Decoder.decodeBuffer(base64String));
        Image img = new Image(inputStream);
        return img;
    }

    public static void decoder(String base64Image, String pathFile) {
        try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }
}
