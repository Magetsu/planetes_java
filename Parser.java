import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

public class Parser {

    /**
     *
     * @param cometFile
     * @return
     */
    public Vector<Comet> ParseCometVector(File cometFile) {

        System.out.println("    Parser.ParseCometVector()");

        FileReader fileReader = null;

        try {

            fileReader = new FileReader(cometFile);

        } catch (FileNotFoundException ex) {

            throw new RuntimeException(ex);
        }

        ArrayList<String[]> cometArray = new ArrayList<>();

        int cometDataFieldLength = Text.cometDataFieldNames.length;

        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;

            while ((line = bufferedReader.readLine()) != null) {

                String[] newComet = new String[cometDataFieldLength];

                newComet[0] = line.substring(0, 42);
                newComet[1] = line.substring(44, 52);
                newComet[2] = line.substring(52, 63);
                newComet[3] = line.substring(64, 75);
                newComet[4] = line.substring(75, 85);
                newComet[5] = line.substring(85, 95);
                newComet[6] = line.substring(95, 105);
                newComet[7] = line.substring(105, 120);

                cometArray.add(newComet);
            }
        } catch (IOException ex) {

            throw new RuntimeException(ex);
        }

        // Poistetaan kaksi ensimmäistä tulosta koska ne ovat muuta tekstiä tiedostossa
        cometArray.remove(1);
        cometArray.remove(0);

        int count = 0;

        Vector<Comet> cometVector = new Vector<>();

        while (cometArray.size() > count) {

            Comet newComet = new Comet();

            newComet.SetComet(
                    cometArray.get(count)[0],
                    Integer.parseInt(cometArray.get(count)[1].trim()),
                    new BigDecimal(cometArray.get(count)[2].trim()),
                    new BigDecimal(cometArray.get(count)[3].trim()),
                    new BigDecimal(cometArray.get(count)[4].trim()),
                    new BigDecimal(cometArray.get(count)[5].trim()),
                    new BigDecimal(cometArray.get(count)[6].trim()),
                    new BigDecimal(cometArray.get(count)[7].trim())
            );

            cometVector.addElement(newComet);
            count++;
        }

        return cometVector;
    }

    /**
     *
     * @param asteroidFile
     * @return
     */
    public Vector<Asteroid> ParseAsteroidVector(File asteroidFile) {

        System.out.println("    Parser.ParseAsteroidVector()");

        FileReader fileReader = null;

        try {

            fileReader = new FileReader(asteroidFile);

        } catch (FileNotFoundException ex) {

            throw new RuntimeException(ex);
        }

        ArrayList<String[]> asteroidArray = new ArrayList<>();

        int asteroidDataFieldLength = Text.asteroidDataFieldNames.length;

        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;

            while ((line = bufferedReader.readLine()) != null) {

                String[] newAsteroid = new String[asteroidDataFieldLength];

                newAsteroid[0] = line.substring(0, 23);
                newAsteroid[1] = line.substring(24, 30);
                newAsteroid[2] = line.substring(31, 41);
                newAsteroid[3] = line.substring(42, 52);
                newAsteroid[4] = line.substring(53, 62);
                newAsteroid[5] = line.substring(63, 72);
                newAsteroid[6] = line.substring(73, 82);
                newAsteroid[7] = line.substring(83, 94);
                newAsteroid[8] = line.substring(95, 100);
                newAsteroid[9] = line.substring(101, 106);

                asteroidArray.add(newAsteroid);
            }
        } catch (IOException ex) {

            throw new RuntimeException(ex);
        }
        // Poistetaan kaksi ensimmäistä tulosta koska ne ovat muuta tekstiä tiedostossa
        asteroidArray.remove(1);
        asteroidArray.remove(0);

        int count = 0;

        Vector<Asteroid> asteroidVector = new Vector<>();

        while (asteroidArray.size() > count) {

            Asteroid newAsteroid = new Asteroid();

            newAsteroid.SetAsteroid(
                    asteroidArray.get(count)[0],
                    Integer.parseInt(asteroidArray.get(count)[1].trim()),
                    new BigDecimal(asteroidArray.get(count)[2].trim()),
                    new BigDecimal(asteroidArray.get(count)[3].trim()),
                    new BigDecimal(asteroidArray.get(count)[4].trim()),
                    new BigDecimal(asteroidArray.get(count)[5].trim()),
                    new BigDecimal(asteroidArray.get(count)[6].trim()),
                    new BigDecimal(asteroidArray.get(count)[7].trim()),
                    new BigDecimal(asteroidArray.get(count)[8].trim()),
                    new BigDecimal(asteroidArray.get(count)[9].trim())
            );

            asteroidVector.addElement(newAsteroid);
            count++;
        }

        return asteroidVector;
    }
}
