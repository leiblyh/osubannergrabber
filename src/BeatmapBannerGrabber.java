import java.io.*;
import java.net.URL;
import java.util.Scanner;

/**
 * Grabs and saves the banner image associated with a given beatmap ID.
 * @author - Hunter Leibly
 */
public class BeatmapBannerGrabber {
    public static void main(String[] args) {
        try {
            final int byteSize = 2048;
            String directory = System.getProperty("user.dir") + "\\backgrounds\\";
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter an osu! beatmap ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            URL url = new URL("https://assets.ppy.sh/beatmaps/" + id + "/covers/cover.jpg");

            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(directory + id + ".jpg");

            byte[] b = new byte[byteSize];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
            System.out.println("Banner successfully saved!");
        } catch (FileNotFoundException e) {
            System.out.println("There is no banner associated with this ID.");
        } catch (NumberFormatException e) {
            System.out.println("The ID you entered was invalid.");
        } catch (IOException e) {
            System.out.println("An unknown error has occured.");
        }
    }
}
