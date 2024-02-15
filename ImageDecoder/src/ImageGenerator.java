import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

/**
 * Generates an image based on pixel data from a text file.
 */
public class ImageGenerator {

  private static final String PNG_FORMAT = "png";

  public static void main(String[] args) throws Exception {
    
    File file = new File("picture.txt");
    Scanner scanner = new Scanner(file);

    int imageWidth = scanner.nextInt();
    int imageHeight = scanner.nextInt();

    // Validate input
    if(imageWidth <= 0 || imageHeight <= 0) {
      throw new IllegalArgumentException("Width and height must be positive"); 
    }

    ImagePlotter plotter = new ImagePlotter(imageWidth, imageHeight);

    int x = 0;
    int y = 0; 

    while(scanner.hasNextInt()) {
      
      int pixelCount = scanner.nextInt();
      
if(pixelCount <= 0) {
  System.err.println("Invalid pixel count " + pixelCount + ", skipping");
  continue;
}
      
      int pixelColor = scanner.nextInt();

      // Validate input  
      if(pixelColor < 0 || pixelColor > 255) {
        throw new IllegalArgumentException("Invalid color value");  
      }
      
      plotter.plot(x, y, pixelCount, pixelColor);

      x += pixelCount;
      if(x >= imageWidth) {
        x = 0;
        y++;
      }
    }

    BufferedImage image = plotter.getImage();
    ImageIO.write(image, PNG_FORMAT, new File("output.png"));

  }

}

/**
 * Plots pixels into a BufferedImage.
 */
class ImagePlotter {

  private BufferedImage image;
  
  public ImagePlotter(int width, int height) {
    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  }

  public void plot(int x, int y, int pixelCount, int rgbColor) {
    // Plot pixelCount pixels at x, y with rgbColor
  }

  public BufferedImage getImage() {
    return image;
  }

}