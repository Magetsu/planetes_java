import java.math.BigDecimal;
import java.util.Vector;

public class Comet {

    /**
     * Luo Comet olion yhdestä komeetasta
     *
     * @param name Komeetan nimi
     * @param epoch epookki
     * @param q perihelietäisyys
     * @param e eksentrisyys
     * @param i inklinaatio
     * @param w perihelin argumentti
     * @param node nousevan solmun pituus
     * @param tp periheliaika
     */

    private String comet_name;
    private int comet_epoch;
    private BigDecimal comet_q;
    private BigDecimal comet_e;
    private BigDecimal comet_i;
    private BigDecimal comet_w;
    private BigDecimal comet_node;
    private BigDecimal comet_tp;

    private CometModel cometModel;

    public void SetComet(String name, int epoch, BigDecimal q, BigDecimal e, BigDecimal i, BigDecimal w, BigDecimal node, BigDecimal tp) {

        comet_name = name;
        comet_epoch = epoch;
        comet_q = q;
        comet_e = e;
        comet_i = i;
        comet_w = w;
        comet_node = node;
        comet_tp = tp;
    }

    /**
     * SetCometModel
     *
     * Asetetaan komeettamalli
     *
     */
    public void SetCometModel(Vector<Comet> comets) {

        System.out.println("Comet.SetCometModel()");

        cometModel = new CometModel(comets);
    }

    public String GetName() {

        return comet_name;
    }

    public int GetEpoch() {

        return comet_epoch;
    }

    public BigDecimal GetQ() {

        return comet_q;
    }

    public BigDecimal GetE() {

        return comet_e;
    }

    public BigDecimal GetI() {

        return comet_i;
    }

    public BigDecimal GetW() {

        return comet_w;
    }

    public BigDecimal GetNode() {

        return comet_node;
    }

    public BigDecimal GetTp() {

        return comet_tp;
    }

    /**
     * Palautetaan komeettamalli
     *
     * @return cometModel Komeettamalli
     */
    public CometModel GetCometModel() {

        System.out.println("Comet.GetCometModel()");

        return cometModel;
    }
    /**
     * Palautetaan komeettojen määrä tietokannassa
     *
     * @return int Komeettojen määrä tietokannassa
     */
    public int GetCometCount() {

        System.out.println("Comet.GetCometCount()");

        return cometModel.getRowCount();
    }
}
