import java.math.BigDecimal;
import java.util.Vector;

public class Asteroid {

    /**
     * @param name Asteroidin nimi
     * @param epoch Asteroidin epookki
     * @param a Asteroidin keskietäisyys auringosta
     * @param e Asteroidin eksentrisyys
     * @param i Asteroidin inklinaatio
     * @param w Asteroidin perihelin pituus
     * @param node Asteroidin nouseva solmu
     * @param m Asteroidin keskianomalia
     * @param h Asteroidin absoluuttinen magnitudi
     * @param g Asteroidin kirkkauden perihelijyrkkyys
     */

    private String asteroid_name;
    private int asteroid_epoch;
    private BigDecimal asteroid_a;
    private BigDecimal asteroid_e;
    private BigDecimal asteroid_i;
    private BigDecimal asteroid_w;
    private BigDecimal asteroid_node;
    private BigDecimal asteroid_m;
    private BigDecimal asteroid_h;
    private BigDecimal asteroid_g;

    private AsteroidModel asteroidModel;


    public void SetAsteroid(String name, int epoch, BigDecimal a, BigDecimal e, BigDecimal i, BigDecimal w, BigDecimal node, BigDecimal m, BigDecimal h, BigDecimal g) {

        asteroid_name = name;
        asteroid_epoch = epoch;
        asteroid_a = a;
        asteroid_e = e;
        asteroid_i = i;
        asteroid_w = w;
        asteroid_node = node;
        asteroid_m = m;
        asteroid_h = h;
        asteroid_g = g;
    }

    /**
     * SetAsteroidModel
     *
     * Asetetaan pikkuplaneettamalli
     *
     */
    public void SetAsteroidModel(Vector<Asteroid> asteroids) {

        System.out.println("Asteroid.SetAsteroidModel()");

        asteroidModel = new AsteroidModel(asteroids);
    }

    public String GetName() {

        return asteroid_name;
    }

    public int GetEpoch() {

        return asteroid_epoch;
    }

    public BigDecimal GetA() {

        return asteroid_a;
    }

    public BigDecimal GetE() {

        return asteroid_e;
    }

    public BigDecimal GetI() {

        return asteroid_i;
    }

    public BigDecimal GetW() {

        return asteroid_w;
    }

    public BigDecimal GetNode() {

        return asteroid_node;
    }

    public BigDecimal GetM() {

        return asteroid_m;
    }

    public BigDecimal GetH() {

        return asteroid_h;
    }

    public BigDecimal GetG() {

        return asteroid_g;
    }

    /**
     * Palautetaan asteroidimalli
     *
     * @return asteroidModel Asteroidimalli
     */
    public AsteroidModel GetAsteroidModel() {

        return asteroidModel;
    }

    /**
     * Palautetaan asteroidien määrä tietokannassa
     *
     * @return int Asteroidien määrä tietokannassa
     */
    public int GetAsteroidCount() {

        System.out.println("Asteroid.GetAsteroidCount()");

        return asteroidModel.getRowCount();
    }
}
