public class Text {

    static final String unSupportedFeature = "Ominaisuutta ei ole vielä lisätty";
    static final String mainCaptionText = "Planetes";
    static final String mainTitleText1 = "Tervetuloa Komeetta- ja pikkuplaneettatietokantaan.";
    static final String mainTitleText2 = "Täällä voi katsella eri otannoilla nykyisiä komeettoja ja pikkuplaneettoja. Laatikoista voi valita millä arvoilla haluaa otantoja tehdä.";
    static final String mainTitleText3 = "Tietokannassa on tällä hetkellä ";
    static final String mainTitleText4 = " komeettaa ja ";
    static final String mainTitleText5 = " pikkuplaneettaa";
    static final String importDataCommand = "Tuo tiedot";
    static final String queryCommand = "Hae";
    static final String cometTabPaneText  = "Komeetat";
    static final String asteroidTabPaneText  = "Pikkuplaneetat";
    static final String lowerLimitText = "Alaraja : ";
    static final String upperLimitText = "Yläraja : ";
    static final String perihelionCheckBoxText = "Perihelietäisyys";
    static final String eccentricityCheckBoxText  = "Eksentrisyys";
    static final String inclinationCheckBoxText  = "Inklinaatio";
    static final String perArgCheckBoxText  = "Perihelin argumentti";
    static final String nodeCheckBoxText  = "Nousevan solmun pituus";
    static final String semiAxisCheckBoxText  = "Keskietäisyys auringosta";
    static final String meanAnomalyCheckBoxText  = "Keskianomalia";
    static final String uploadDialogText = "Tietojen lisäys";
    static final String cometUploadText = "Lataa komeetat";
    static final String numberAsteroidUploadText = "Lataa numeroidut pikkuplaneetat";
    static final String unnumberAsteroidUploadText = "Lataa numeroimattomat pikkuplaneetat";
    static final String exitUploadText = "Poistu";
    static final String addDataText = "Tietojen lisäys";
    static final String parseError = "Rajoitusten syötössä on virhe";
    static final String parseErrorTitle = "Virhe tiedoissa";

    static final int tableHeight = 600;

    static final String importCometText = "Tuo komeetat";
    static final String importCometCancel = "Komeettojen tuonti keskeytettiin.";
    static final String importNumberAsteroidText = "Tuo numeroidut pikkuplaneetat";
    static final String importAsteroidCancel = "Pikkuplaneettojen tuonti keskeytettiin.";
    static final String importUnnumberAsteroidText = "Tuo numeroimattomat pikkuplaneetat";


    /**
     * Tekstit jotka liittyvät SQL-tietokantaan
     */
    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/KIRJASTO";
    static final String username = "root";
    static final String password = "root";
    static final String[] cometDataFieldNames = { "name", "epoch", "q", "e", "i", "w", "node", "tp" };
    static final String[] cometTableFieldNames = { "Komeetan nimi :", "Komeetan epookki :", "Perihelietäisyys :", "Eksentrisyys :", "Inklinaatio :", "Perihelin argumentti :", "Nousevan solmun pituus :", "Periheliaika :" };
    static final int[] cometTableFieldLengths = { 260, 150, 150, 150, 150, 200, 200, 150 };
    static final String[] asteroidDataFieldNames = { "name", "epoch", "a", "e", "i", "w", "node", "m", "h", "g" };
    static final String[] asteroidTableFieldNames = { "Asteroidin nimi :", "Asteroidin epookki :", "Keskietäisyys auringosta :", "Eksentrisyys :", "Inklinaatio :", "Perihelin pituus :", "Nousevan solmun pituus :", "Keskianomalia :", "Absoluuttinen magnitudi :", "Kirkkauden perihelijyrkkyys :" };
    static final int[] asteroidTableFieldLengths = { 200, 150, 150, 100, 100, 100, 150, 100, 150, 200 };
    static final String DATABASE = "planetes";
    static final String COMET_TABLE = "comet";
    static final String ASTEROID_TABLE = "asteroid";
    static final String COMET_ROWS = " ( " +
            cometDataFieldNames[0] + " VARCHAR(43) NOT NULL, " +
            cometDataFieldNames[1] + " INT(7) NOT NULL, " +
            cometDataFieldNames[2] + " DECIMAL (11,8) NOT NULL, " +
            cometDataFieldNames[3] + " DECIMAL (10,9) NOT NULL, " +
            cometDataFieldNames[4] + " DECIMAL (9,5) NOT NULL, " +
            cometDataFieldNames[5] + " DECIMAL (9,5) NOT NULL, " +
            cometDataFieldNames[6] + " DECIMAL (9,5) NOT NULL, " +
            cometDataFieldNames[7] + " DECIMAL (14,5) NOT NULL )";
    static final String ADD_COMET = " ( " +
            cometDataFieldNames[0] + ", " +
            cometDataFieldNames[1] + ", " +
            cometDataFieldNames[2] + ", " +
            cometDataFieldNames[3] + ", " +
            cometDataFieldNames[4] + ", " +
            cometDataFieldNames[5] + ", " +
            cometDataFieldNames[6] + ", " +
            cometDataFieldNames[7] + " ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
    static final String ASTEROID_ROWS = " ( " +
            asteroidDataFieldNames[0] + " VARCHAR(24) NOT NULL, " +
            asteroidDataFieldNames[1] + " INT(7) NOT NULL, " +
            asteroidDataFieldNames[2] + " DECIMAL(10,7) NOT NULL, " +
            asteroidDataFieldNames[3] + " DECIMAL(10,8) NOT NULL, " +
            asteroidDataFieldNames[4] + " DECIMAL(9,5) NOT NULL, " +
            asteroidDataFieldNames[5] + " DECIMAL(9,5) NOT NULL, " +
            asteroidDataFieldNames[6] + " DECIMAL(9,5) NOT NULL, " +
            asteroidDataFieldNames[7] + " DECIMAL(11,7) NOT NULL, " +
            asteroidDataFieldNames[8] + " DECIMAL(5,2) NOT NULL, " +
            asteroidDataFieldNames[9] + " DECIMAL(4,2) NOT NULL)";
    static final String ADD_ASTEROID = " ( " +
            asteroidDataFieldNames[0] + ", " +
            asteroidDataFieldNames[1] + ", " +
            asteroidDataFieldNames[2] + ", " +
            asteroidDataFieldNames[3] + ", " +
            asteroidDataFieldNames[4] + ", " +
            asteroidDataFieldNames[5] + ", " +
            asteroidDataFieldNames[6] + ", " +
            asteroidDataFieldNames[7] + ", " +
            asteroidDataFieldNames[8] + ", " +
            asteroidDataFieldNames[9] + " ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    static final String GET_DATA = "SELECT * FROM ";
    static final String INSERT_DATA = "INSERT INTO ";
    static final String DELETE = "TRUNCATE TABLE ";
}



