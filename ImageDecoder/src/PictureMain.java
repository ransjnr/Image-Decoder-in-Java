/*
 * Created on Thu Feb 15 2024
 * Hosted privately on Github (https://ransjnr/image-decoder-in-java)
 * Index Number - 7084021
 * 
 * Copyright (c) 2024 
 * 
 */


//importing packages
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.File;
//main class declaration
public class PictureMain {

  public static void main(String[] args) throws Exception {
    //read image file into project
    File txtFile = new File("picture.txt");
    Scanner scanner = new Scanner(txtFile);
    //handle error with try and catch method
 try {  
    //reading the width from the file
    int width = scanner.nextInt();
    //reading the height from the file
    int height = scanner.nextInt();
    //calling the validateDimensions error handling function from the ErrorHandle class
    ErrorHandler.validateDimensions(width, height);
    //creating an object of the class PicturePlotter
    PicturePlotter plotter = new PicturePlotter(width, height);

    //starting position of plot counts
    int x = 0, y = 0;
    //conditions to check the normalization of the change in count and color with the loop
    while(true) {
      //reading the count values
      int count = scanner.nextInt();
      if(count < 0) {
        break;
      }
      //reading the color values
      int color = scanner.nextInt();
      plotter.plot(x, y, count, color);

      x += count;
      if(x >= width) {
        x = 0; 
        y++;
      }
    }
    //calling the method to display the custom GUI
    plotter.showImage();
      } catch (Exception e) {
      ErrorHandler.handleUnexpectedError(e); //checking unexpected errors
    }
  }


}

//class to plot the image
class PicturePlotter {

  private BufferedImage image;
  
  public PicturePlotter(int width, int height) {
    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  }

  public void plot(int x, int y, int count, int color) {
    for(int i = 0; i < count; i++) {
      image.setRGB(x + i, y, colorToRGB(color)); 
    }
  }

  public void showImage() {
    //calling the custom gui class 
    CustomGUI gui = new CustomGUI("Ransford's Java Image Decoder", image);
  }

private int colorToRGB(int color) {

  //calling error handler to check valid color values
  int validColor = ErrorHandler.getValidColor(color);

  switch(color) {
    //color display cases from 0-3
    case 0: return 0xA52A2A; //brown
    case 1: return 0x808080; //gray
    case 2: return 0x00FF00; //green
    case 3: return 0xA0522D; //sienna
  }
    return 0; 
  }

}