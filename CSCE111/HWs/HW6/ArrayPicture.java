import java.io.*;

/*
  Manas Paramathmuni
  CSCE 111
  Writing file example, in a method.
  Creating ppm File output.ppm

  install Name: PBM/PPM/PGM Viewer for Visual Studio Code. Search in VS code extensions.
  */

public class ArrayPicture {

    public static void main(String[] args) {
        //set the size of your square 600.
        int mySize = 1000;
        //build 3 parallel arrays. You can rename them if you want.
        int[][] red = new int[mySize][mySize];
        int[][] green = new int[mySize][mySize];
        int[][] blue = new int[mySize][mySize];
        //and idea for createing colors  - Maroon
        int maroonR = 88;
        int maroonG = 0;
        int maroonB = 0;
        // and here is White.
        int whiteR = 255;
        int whiteG = 255;
        int whiteB = 255;

        // TODO(Student)
        // Fill the arrays using loops, equations,
        // user input for color choices (validate data if you do this)
        // or use methods to do this.
        // Hint, fill with one color, then overwrite certian areas with
        // another color.

        // we need to make 4 colors: we'll just do 4 squares
        for (int i = 0; i < mySize / 2; i++) {
            for (int j = 0; j < mySize / 2; j++) {
                red[i][j] = 88;
            }
        }
        for (int i = mySize / 2; i < mySize; i++) {
            for (int j = 0; j < mySize / 2; j++) {
                green[i][j] = 88;
            }
        }
        for (int i = 0; i < mySize / 2; i++) {
            for (int j = mySize / 2; j < mySize; j++) {
                blue[i][j] = 88;
            }
        }
        for (int i = mySize / 2; i < mySize; i++) {
            for (int j = mySize / 2; j < mySize; j++) {
                red[i][j] = 255;
                green[i][j] = 255;
                blue[i][j] = 255;
            }
        }


        // when arrays are filled, send them to writeFile
        writeFile(red, green, blue, "C");

        // TODO (Student)
        // convert your image to gray scale (Black and white)

        // when arrays are filled, send them to writeFile
        writeFile(red, green, blue, "G");
    } //end main

    // do not change anything below this line, but feel free to read it and learn.

    /**
     * Given 3 parallel arrays, Writefile will write an output
     * file in PPM format that can be viewed with a ppm viewer.
     * All 3 arrays must be the same size.
     * @param r 2 dimentional array of integers from 0 - 255
     * @param g 2 dimentional array of integers from 0 - 255
     * @param b 2 dimentional array of integers from 0 - 255
     * you do not need to edit this method
     */
    public static void writeFile(
        int[][] r,
        int[][] g,
        int[][] b,
        String outputType
    ) {
        //the output will be based on the array size sent.
        int size = r.length;
        //pixel will store the color to be output to the file.
        String pixel;
        try {
            //open a file in the current directory. will overwrite
            //previous versions.
            String fileName = "output" + outputType + ".ppm";
            File output = new File(fileName);
            FileWriter fw = new FileWriter(output);
            //create the header of PPM files.
            fw.write("P3\n" + size + " " + size + "\n255\n");
            //write out all pixels from the RGB arrays.
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    pixel = String.format(
                        "%d %d %d \n",
                        r[j][i],
                        g[j][i],
                        b[j][i]
                    );
                    fw.write(pixel);
                } //end for j
            } //end for i
            fw.close();
        } catch (Exception e) {
            System.out.println("Error writing file.");
        } //end try catch
    } // end writeFile2
} // end class
