import java.io.*;
import java.net.URL;
import java.util.Scanner;

/**
 * Grabs and saves the banner image associated with a given beatmap ID
 * Make sure to use the beatmap set ID, not the difficulty ID.
 * @author - Hunter Leibly
 */
public class BeatmapBannerGrabber {
    public static void main(String[] args) {
        try {
            final int byteSize = 2048;
            File directory = new File(System.getProperty("user.dir") + "\\backgrounds\\");
            if(!directory.exists()){
                directory.mkdir();
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter an osu! beatmap ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            URL url = new URL("https://assets.ppy.sh/beatmaps/" + id + "/covers/cover.jpg");
            System.out.println(url.toString());

            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(directory.toString() + "\\" + id + ".jpg");

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
            System.out.println("An unknown error has occurred.");
        }
    }
}
