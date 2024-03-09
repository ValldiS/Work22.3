
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;



public class Main {

    public static void main(String[] args) {
        openZip("C://Games/saveGames/zip.zip", "C://Games/saveGames");

        openProgress("C://Games/saveGames/save1.dat");
        openProgress("C://Games/saveGames/save2.dat");
        openProgress("C://Games/saveGames/save3.dat");

    }

    public static void openZip(String wayZip, String wayEnd) {
        try (ZipInputStream zin = new ZipInputStream(new
                FileInputStream(wayZip))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream gameDat = new FileOutputStream(name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    gameDat.write(c);
                }
                gameDat.flush();
                zin.closeEntry();
                gameDat.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void openProgress(String way) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(way);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress);
    }
}
