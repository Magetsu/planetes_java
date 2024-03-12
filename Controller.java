import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class Controller implements ActionListener, ChangeListener, DocumentListener {

    private final Sql sql;
    private final Ui ui;
    private final Parser dataParser;

    private JFrame mainFrame;
    private JTable cometTable, asteroidTable;
    private JScrollPane cometTableScrollPane, asteroidTableScrollPane;
    private JPanel mainPanel;

    private final Comet comet = new Comet();
    private final Asteroid asteroid = new Asteroid();

    private int paneIndex, oldPaneIndex;
    private int cometPaneIndex = 0;
    private int asteroidPaneIndex = 1;

    private int cometFieldTotalLength, asteroidFieldTotalLength = 0;

    /**
     *
     */
    public Controller() {

        System.out.println("Controller.Controller()");

        sql = new Sql();
        ui = new Ui();
        dataParser = new Parser();

        for (int j = 0; j < Text.cometTableFieldNames.length; j++) {

            cometFieldTotalLength += Text.cometTableFieldLengths[j];
        }

        for (int j = 0; j < Text.asteroidTableFieldNames.length; j++) {

            asteroidFieldTotalLength += Text.asteroidTableFieldLengths[j];
        }
    }

    public void InitializeDatabase() {

        System.out.println("Controller.InitializeDatabase()");

        // Otetaan yhteys tietokantaan
        sql.GetConnection(Text.url, Text.username, Text.password);

        // Luodaan tietokanta jos ei ole luotu
        sql.CreateDatabase(Text.DATABASE);

        // Luodaan taulut jos ei ole jo luotu
        sql.CreateTable(Text.DATABASE, Text.COMET_TABLE, Text.COMET_ROWS);
        sql.CreateTable(Text.DATABASE, Text.ASTEROID_TABLE, Text.ASTEROID_ROWS);
    }

    /**
     *
     */
    public void CreateModel() {

        System.out.println("Controller.CreateModel()");

        CreateCometDataPanel();
        CreateAsteroidDataPanel();
    }

    /**
     *
     */
    private void CreateCometDataPanel() {

        System.out.println("Controller.CreateCometModel()");

        // Haetaan komeetat tietokannasta
        Vector<Comet> newCometData;

        String modelQuery = Text.GET_DATA+Text.DATABASE+"."+Text.COMET_TABLE;
        newCometData = sql.GetCometData(modelQuery);

        // Tehdään komeetoista ja asteroideista mallit
        comet.SetCometModel(newCometData);

        System.out.println("CreateModel: Returned comet count is : " + comet.GetCometCount());

        CreateCometScrollPane();
    }

    /**
     *
     */
    public void CreateCometScrollPane() {

        System.out.println("    Ui.CreateCometScrollPane()");

        cometTable = new JTable();
        cometTable.setPreferredScrollableViewportSize(new Dimension(cometFieldTotalLength,Text.tableHeight));
        cometTable.setFillsViewportHeight(true);
        cometTable.setAutoCreateRowSorter(true);
        cometTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        cometTable.setAutoCreateColumnsFromModel(false);
        cometTable.setModel(comet.GetCometModel());
        for (int k = 0; k < CometModel.columns.length; k++) {

            TableRenderer renderer = new TableRenderer();
            renderer.setHorizontalAlignment(CometModel.columns[k].alignment);
            TableColumn column = new TableColumn(k, CometModel.columns[k].width, renderer, null);
            column.setHeaderValue(CometModel.columns[k].title);
            cometTable.addColumn(column);
        }
        cometTable.setDefaultRenderer(Comet.class, new TableRenderer());

        cometTableScrollPane = new JScrollPane(cometTable);

        ui.SetCometTableScrollPane(cometTableScrollPane);
    }

    /**
     *
     */
    private void CreateAsteroidDataPanel() {

        System.out.println("Controller.CreateAsteroidModel()");

        // Haetaan asteroidit tietokannasta
        Vector<Asteroid> newAsteroidData;

        String modelQuery = Text.GET_DATA+Text.DATABASE+"."+Text.ASTEROID_TABLE;
        newAsteroidData = sql.GetAsteroidData(modelQuery);

        // Tehdään komeetoista ja asteroideista mallit
        asteroid.SetAsteroidModel(newAsteroidData);

        System.out.println("CreateModel: Returned asteroid count is : " + asteroid.GetAsteroidCount());

        CreateAsteroidScrollPane();
    }

    /**
     *
     */
    public void CreateAsteroidScrollPane() {

        System.out.println("    Ui.CreateAsteroidScrollPane()");

        asteroidTable = new JTable();
        asteroidTable.setPreferredScrollableViewportSize(new Dimension(asteroidFieldTotalLength,Text.tableHeight));
        asteroidTable.setFillsViewportHeight(true);
        asteroidTable.setAutoCreateRowSorter(true);
        asteroidTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        asteroidTable.setAutoCreateColumnsFromModel(false);
        asteroidTable.setModel(asteroid.GetAsteroidModel());
        for (int k = 0; k < AsteroidModel.columns.length; k++) {

            TableRenderer renderer = new TableRenderer();
            renderer.setHorizontalAlignment(AsteroidModel.columns[k].alignment);
            TableColumn column = new TableColumn(k, AsteroidModel.columns[k].width, renderer, null);
            column.setHeaderValue(AsteroidModel.columns[k].title);
            asteroidTable.addColumn(column);
        }
        asteroidTable.setDefaultRenderer(Asteroid.class, new TableRenderer());

        asteroidTableScrollPane = new JScrollPane(asteroidTable);

        ui.SetAsteroidTableScrollPane(asteroidTableScrollPane);
    }

    /**
     *
     */
    public void CreateUI() {

        System.out.println("Controller.CreateUI()");

        // Otetaan talteen komeettojen määrä talteen UI:lle
        ui.SetCometCount(comet.GetCometCount());

        // Otetaan talteen asteroidien määrä talteen UI:lle
        ui.SetAsteroidCount(asteroid.GetAsteroidCount());

        // Luodaan paneelit
        ui.CreateHeaderPanel(this);
        ui.CreateSelectionPanel(this);
        ui.CreateCountPanel();
        ui.CreateCometConstraintsChoicePanel(this);
        ui.CreateAsteroidConstraintsChoicePanel(this);
        ui.CreateDataPanel();

        mainFrame = new JFrame(Text.mainCaptionText);

        // Ikkunan kokoa ei voi muuttaa
        mainFrame.setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Lisätään paneelit ikkunaan
        mainPanel.add(ui.GetHeaderPanel());
        mainPanel.add(ui.GetCountPanel());
        mainPanel.add(ui.GetSelectionPanel());
        mainPanel.add(ui.GetCometConstraintsChoicePanel());
        mainPanel.add(ui.GetDataPanel());

        mainFrame.add(mainPanel);

        // Ikkunan sijainti ja muut perusjutut
        mainFrame.setLocation(100, 200);
        mainFrame.setSize(1700, 800);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);

        // Lähetetään pääikkunan luokka Ui:lle käytettäväksi
        ui.SetFrame(mainFrame);
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Controller.actionPerformed()");

        String command = e.getActionCommand();
        System.out.println("Command is : "+command);

        if (Objects.equals(command, Text.importDataCommand)) {

            ui.CreateUploadDialog(this);

        } else if (Objects.equals(command, Text.queryCommand)) {

            CreateQuery();

        } else if (Objects.equals(command, Text.cometUploadText)) {

            File cometFile;

            cometFile = ui.CometUpload();

            if(cometFile != null) {

                ParseCometData(cometFile);
            }

        } else if (Objects.equals(command, Text.numberAsteroidUploadText)) {

            File numberAsteroidFile;

            numberAsteroidFile = ui.NumberAsteroidUpload();

            if(numberAsteroidFile != null) {

                ParseNumberAsteroidData(numberAsteroidFile);
            }

        } else if (Objects.equals(command, Text.unnumberAsteroidUploadText)) {

            ui.UnnumberAsteroidUpload();

        } else if (Objects.equals(command, Text.exitUploadText)) {

        }
    }

    /**
     *
     */
    private void CreateQuery() {

        System.out.println("Controller.CreateQuery()");

        ArrayList<String> limitArray;

        if(!ui.CheckConstraintsFieldValues()) {

            System.out.println("Virhe tietojen syötössä");
            ui.CreateMessageDialog(mainFrame, Text.parseError, Text.parseErrorTitle,JOptionPane.INFORMATION_MESSAGE);

        } else {

            if (paneIndex == cometPaneIndex) {

                // Ensin valitaan kaikki komeetat
                String query = Text.GET_DATA+Text.DATABASE+"."+Text.COMET_TABLE;

                // Tarkistetaan onko olemassa hakurajoituksia
                if(ui.CheckQueryLimitsEnabled()) {

                    int constraintsCount = 0;

                    query += " WHERE";

                    // Tehdään hakulauseke perihelietäisyydelle
                    limitArray = ui.GetPerihelionLimits();

                    if (limitArray != null) {

                        query +=  QueryType(limitArray, Text.cometDataFieldNames[2]);

                        constraintsCount += 1;
                    }

                    // Tehdään hakulauseke eksentrisyydelle
                    limitArray = ui.GetEccentricityLimits();

                    if (limitArray != null) {

                        // Tällä tarkistetaan että kahden tai enemmän haun välille tulee AND
                        if (constraintsCount > 0) {

                            query += " AND";

                        }
                        query +=  QueryType(limitArray, Text.cometDataFieldNames[3]);

                        constraintsCount += 1;
                    }

                    // Tehdään hakulauseke inklinaatiolle
                    limitArray = ui.GetInclinationLimits();

                    if (limitArray != null) {

                        // Tällä tarkistetaan että kahden tai enemmän haun välille tulee AND
                        if (constraintsCount > 0) {

                            query += " AND";

                        }
                        query +=  QueryType(limitArray, Text.cometDataFieldNames[4]);

                        constraintsCount += 1;
                    }

                    // Tehdään hakulauseke perihelin argumentille
                    limitArray = ui.GetPerArgLimits();

                    if (limitArray != null) {

                        // Tällä tarkistetaan että kahden tai enemmän haun välille tulee AND
                        if (constraintsCount > 0) {

                            query += " AND";

                        }
                        query +=  QueryType(limitArray, Text.cometDataFieldNames[5]);

                        constraintsCount += 1;
                    }

                    // Tehdään hakulauseke nousevan solmun pituudelle
                    limitArray = ui.GetNodeLimits();
                    if (limitArray != null) {

                        // Tällä tarkistetaan että kahden tai enemmän haun välille tulee AND
                        if (constraintsCount > 0) {

                            query += " AND";

                        }
                        query +=  QueryType(limitArray, Text.cometDataFieldNames[6]);
                    }
                }

                System.out.println("Query is : "+query);

                // Haetaan rajatut komeetat tietokannasta
                Vector<Comet> newQueryData;
                newQueryData = sql.GetCometData(query);

                // Tehdään rajatuista komeetoista mallit
                comet.SetCometModel(newQueryData);

                System.out.println("CreateQuery: Returned comet count is : " + comet.GetCometCount());

                CreateCometScrollPane();

            } else if (paneIndex == asteroidPaneIndex) {

                // Ensin valitaan kaikki komeetat
                String query = Text.GET_DATA+Text.DATABASE+"."+Text.ASTEROID_TABLE;

                // Tarkistetaan onko olemassa hakurajoituksia
                if(ui.CheckQueryLimitsEnabled()) {

                    int constraintsCount = 0;

                    query += " WHERE";

                    // Tehdään hakulauseke keskitäisyydelle
                    limitArray = ui.GetSemiAxisLimits();

                    if (limitArray != null) {

                        query += QueryType(limitArray, Text.asteroidDataFieldNames[2]);

                        constraintsCount += 1;
                    }

                    // Tehdään hakulauseke eksentrisyydelle
                    limitArray = ui.GetEccentricityLimits();

                    if (limitArray != null) {

                        // Tällä tarkistetaan että kahden tai enemmän haun välille tulee AND
                        if (constraintsCount > 0) {

                            query += " AND";

                        }
                        query += QueryType(limitArray, Text.asteroidDataFieldNames[3]);

                        constraintsCount += 1;
                    }

                    // Tehdään hakulauseke inklinaatiolle
                    limitArray = ui.GetInclinationLimits();

                    if (limitArray != null) {

                        // Tällä tarkistetaan että kahden tai enemmän haun välille tulee AND
                        if (constraintsCount > 0) {

                            query += " AND";

                        }
                        query += QueryType(limitArray, Text.asteroidDataFieldNames[4]);

                        constraintsCount += 1;
                    }

                    // Tehdään hakulauseke perihelin argumentille
                    limitArray = ui.GetPerArgLimits();

                    if (limitArray != null) {

                        // Tällä tarkistetaan että kahden tai enemmän haun välille tulee AND
                        if (constraintsCount > 0) {

                            query += " AND";

                        }
                        query += QueryType(limitArray, Text.asteroidDataFieldNames[5]);

                        constraintsCount += 1;
                    }

                    // Tehdään hakulauseke nousevan solmun pituudelle
                    limitArray = ui.GetNodeLimits();
                    if (limitArray != null) {

                        // Tällä tarkistetaan että kahden tai enemmän haun välille tulee AND
                        if (constraintsCount > 0) {

                            query += " AND";

                        }
                        query += QueryType(limitArray, Text.asteroidDataFieldNames[6]);

                        constraintsCount += 1;
                    }

                    // Tehdään hakulauseke nousevan solmun pituudelle
                    limitArray = ui.GetMeanAnomalyLimits();
                    if (limitArray != null) {

                        // Tällä tarkistetaan että kahden tai enemmän haun välille tulee AND
                        if (constraintsCount > 0) {

                            query += " AND";

                        }
                        query += QueryType(limitArray, Text.asteroidDataFieldNames[7]);

                    }
                }

                System.out.println("Query is : " + query);

                // Haetaan rajatut komeetat tietokannasta
                Vector<Asteroid> newQueryData;
                newQueryData = sql.GetAsteroidData(query);

                // Tehdään rajatuista komeetoista mallit
                asteroid.SetAsteroidModel(newQueryData);

                System.out.println("CreateQuery: Returned asteroid count is : " + asteroid.GetAsteroidCount());

                CreateAsteroidScrollPane();
            }

            UpdateDataPanel();
        }

    }

    /**
     *
     * @param limitArray
     * @param constraintType
     * @return
     */
    private String QueryType(ArrayList<String> limitArray, String constraintType) {

        String lowerLimit = limitArray.get(0);
        String upperLimit = limitArray.get(1);

        String query = null;

        if (!lowerLimit.isEmpty() && !upperLimit.isEmpty()) {

            query = " "+constraintType+" BETWEEN "+lowerLimit+" AND "+upperLimit;

        } else if (lowerLimit.isEmpty() && !upperLimit.isEmpty()) {

            query = " "+constraintType+" < "+upperLimit;

        } else if (!lowerLimit.isEmpty() && upperLimit.isEmpty()) {

            query = " "+constraintType+" > "+lowerLimit;

        }

        return query;
    }

    /**
     *
     * @param cometFile
     */
    private void ParseCometData(File cometFile) {

        System.out.println("Controller.ParseCometData()");

        // Luodaan komeetta-malli saadusta tiedosta
        CometModel cometModel = new CometModel(dataParser.ParseCometVector(cometFile));

        // Otetaan talteen komeettojen määrä talteen UI:lle
        ui.SetCometCount(comet.GetCometCount());

        // Luodaan taulukkoikkuna komeetoille
        CreateCometScrollPane();

        sql.TruncateTable(Text.DATABASE, Text.COMET_TABLE);

        // Lisätään komeetat tietokantaan
        for (int i=0; i < cometModel.getRowCount(); i++) {

            sql.AddCometToDatabase(Text.DATABASE,Text.COMET_TABLE,cometModel.getRowAt(i));
        }

        UpdateDataPanel();
    }

    private void ParseNumberAsteroidData(File asteroidFile) {

        System.out.println("Controller.ParseNumberAsteroidData()");

        // Luodaan komeetta-malli saadusta tiedosta
        AsteroidModel asteroidModel = new AsteroidModel(dataParser.ParseAsteroidVector(asteroidFile));

        // Otetaan talteen komeettojen määrä talteen UI:lle
        ui.SetAsteroidCount(asteroid.GetAsteroidCount());

        // Luodaan taulukkoikkuna komeetoille
        CreateAsteroidScrollPane();

        sql.TruncateTable(Text.DATABASE, Text.ASTEROID_TABLE);

        // Lisätään komeetat tietokantaan
        for (int i=0; i < asteroidModel.getRowCount(); i++) {

            System.out.println("Adding asteroid : "+i);
            sql.AddAsteroidToDatabase(Text.DATABASE,Text.ASTEROID_TABLE,asteroidModel.getRowAt(i));
        }
    }
    /**
     *
     */
    public void UpdateDataPanel() {

        System.out.println("	Ui.UpdateDataPanel()");

        mainPanel.remove(ui.GetDataPanel());
        ui.CreateDataPanel();
        mainPanel.add(ui.GetDataPanel());
        mainPanel.validate();
        mainPanel.repaint();
    }


    private void UpdateConstraintsChoicePanel() {

        System.out.println("	Ui.UpdateConstraintsPanel()");

        if (oldPaneIndex == cometPaneIndex) {

            mainPanel.remove(ui.GetCometConstraintsChoicePanel());
            ui.ClearConstraints();

        } else if (oldPaneIndex == asteroidPaneIndex) {

            mainPanel.remove(ui.GetAsteroidConstraintsChoicePanel());
            ui.ClearConstraints();
        }

        if (paneIndex == cometPaneIndex) {

            mainPanel.add(ui.GetCometConstraintsChoicePanel());

        } else if (paneIndex == asteroidPaneIndex) {

            mainPanel.add(ui.GetAsteroidConstraintsChoicePanel());
        }

        mainPanel.validate();
        mainPanel.repaint();
    }

    /**
     *
     * @param e  a ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {

        System.out.println("Controller.stateChanged()");

        JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();

        // Otetaan talteen vanha paneIndex
        oldPaneIndex = paneIndex;

        paneIndex = sourceTabbedPane.getSelectedIndex();
        ui.SetPaneIndex(paneIndex);
        UpdateConstraintsChoicePanel();
        UpdateDataPanel();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}

final class TableRenderer extends DefaultTableCellRenderer {
    private static final long serialVersionUID = 1L;

    /*
     * setValue
     */
    public void setValue(Object aValue) {

        if ( aValue != null ) {

            setToolTipText( aValue.toString() );
        }

        super.setValue(aValue);
    }
}