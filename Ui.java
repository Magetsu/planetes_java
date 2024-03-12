import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Ui {

    private JFrame uiMainFrame;
    private JScrollPane uiCometTableScrollPane, uiAsteroidTableScrollPane;
    private JPanel headerPanel, cometConstraintsChoicePanel, asteroidConstraintsChoicePanel, dataPanel, countPanel;
    private JTabbedPane selectionPanel;
    private JPanel perihelionCometConstraintsPanel;
    private JPanel eccentricityCometConstraintsPanel, eccentricityAsteroidConstraintsPanel;
    private JPanel inclinationCometConstraintsPanel, inclinationAsteroidConstraintsPanel;
    private JPanel perArgCometConstraintsPanel, perArgAsteroidConstraintsPanel;
    private JPanel nodeCometConstraintsPanel, nodeAsteroidConstraintsPanel;
    private JPanel semiAxisAsteroidConstraintsPanel,meanAnomalyAsteroidConstraintsPanel;

    private final Font font;

    private int uiCometCount = 0;
    private int uiAsteroidCount = 0;
    private int uiPaneIndex = 0;

    private ArrayList<String> perihelionLimits, eccentricityLimits, inclinationLimits, perArgLimits, nodeLimits;
    private ArrayList<String> semiAxisLimits,meanAnomalyLimits;
    public Ui() {

        System.out.println("	Ui.Ui()");

        font = new Font("SansSerif", Font.PLAIN, 50);
    }

    /**
     * @param cometCount
     */
    public void SetCometCount(int cometCount) {

        System.out.println("	Ui.SetCometCount()");

        uiCometCount = cometCount;
    }

    /**
     * @param asteroidCount
     */
    public void SetAsteroidCount(int asteroidCount) {

        System.out.println("	Ui.SetAsteroidCount()");

        uiAsteroidCount = asteroidCount;
    }

    /**
     * @param mainFrame
     */
    public void SetFrame(JFrame mainFrame) {

        System.out.println("	Ui.SetFrame()");

        uiMainFrame = mainFrame;
    }

    /**
     *
     * @param paneIndex
     */
    public void SetPaneIndex(int paneIndex) {

        System.out.println("	Ui.SetPaneIndex()");
        System.out.println("    Pane index is : "+paneIndex);

        uiPaneIndex = paneIndex;
    }

    /**
     *
     * @param cometTableScrollPane
     */
    public void SetCometTableScrollPane(JScrollPane cometTableScrollPane) {

        uiCometTableScrollPane = cometTableScrollPane;
    }

    /**
     *
     * @param asteroidTableScrollPane
     */
    public void SetAsteroidTableScrollPane(JScrollPane asteroidTableScrollPane) {

        uiAsteroidTableScrollPane = asteroidTableScrollPane;
    }

    /**
     *
     * @return
     */
    public JFrame GetFrame() {

        System.out.println("	Ui.GetFrame()");

        return uiMainFrame;
    }

    /**
     *
     * @return
     */
    public JPanel GetCometConstraintsChoicePanel() {

            return cometConstraintsChoicePanel;
    }

    /**
     *
     * @return
     */
    public JPanel GetAsteroidConstraintsChoicePanel() {

        return asteroidConstraintsChoicePanel;
    }

    /**
     *
     * @return
     */
    public JPanel GetHeaderPanel() {

        return headerPanel;
    }

    /**
     *
     * @return
     */
    public JPanel GetCountPanel() {

        return countPanel;

    }
    /**
     *
     * @return
     */
    public JTabbedPane GetSelectionPanel() {

        return selectionPanel;
    }

    /**
     *
      * @return
     */
    public JScrollPane GetCometTableScrollPane() {

        return uiCometTableScrollPane;
    }

    /**
    *
    * @return
    */
    public JScrollPane GetAsteroidTableScrollPane() {

        return uiAsteroidTableScrollPane;
    }
    /**
     *
     * Luodaan ikkunan yläosan poneeli jossa on alkutekstit ja komeettojen sekä asteroidien lukumäärä
     * Lisäksi paneelissa on datan hakeminen tiedostosta
     *
     * @param controller
     */

    public void CreateHeaderPanel(Controller controller) {

        System.out.println("	Ui.GetHeaderPanel()");

        headerPanel = new JPanel();

        headerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Luodaan otsikkopaneeli
        setMyConstraints(c,0,0,GridBagConstraints.WEST);
        headerPanel.add(CreateHeadingPanel(),c);

        // Luodaan datan syöttö paneeli
        setMyConstraints(c,1,0,GridBagConstraints.EAST);
        headerPanel.add(CreateButtonPanel(controller),c);
    }

    public void CreateCountPanel() {

        System.out.println("	Ui.GetCountPanel()");

        String countlabel = Text.mainTitleText3+uiCometCount+Text.mainTitleText4+uiAsteroidCount+Text.mainTitleText5;
        countPanel = new JPanel();
        JLabel countLabel = new JLabel(countlabel, SwingConstants.CENTER);
        countPanel.add(countLabel);
    }

    /**
     * Luodaan keskipaneeli jossa voidaan valita halutaanko katsoa komeetta- vai pikkuplaneetta-tietoja
     *
     * @param controller
     *
     */
    public void CreateSelectionPanel(Controller controller) {

        System.out.println("    Ui.CreateSelectionPanel()");

        selectionPanel = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

        selectionPanel.addTab(Text.cometTabPaneText, null, null, null);
        selectionPanel.addTab(Text.asteroidTabPaneText, null, null, null);

        selectionPanel.addChangeListener(controller);
    }

    /**
     *  Luodaan paneelit jossa voidaan rajata hakutietoja ja näytetään saatava komeetta- tai pikkuplaneetta-tieto
     * @param controller
     *
     */
    public void CreateCometConstraintsChoicePanel(Controller controller) {

        System.out.println("    Ui.CreateCometConstraintsChoicePanel()");

        cometConstraintsChoicePanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Luodaan perihelin rajauspaneelit
        setMyConstraints(c,0,0,GridBagConstraints.WEST);

        // Otetaan talteen paneeli jotta saadaan tiedonsyötöt haettua talteen
        perihelionCometConstraintsPanel = CreateConstraintsPanel(controller,Text.perihelionCheckBoxText);
        cometConstraintsChoicePanel.add(perihelionCometConstraintsPanel,c);

        // Luodaan eksentrisyyden rajauspaneelit
        setMyConstraints(c,1,0,GridBagConstraints.WEST);

        // Otetaan talteen paneeli jotta saadaan tiedonsyötöt haettua talteen
        eccentricityCometConstraintsPanel = CreateConstraintsPanel(controller,Text.eccentricityCheckBoxText);
        cometConstraintsChoicePanel.add(eccentricityCometConstraintsPanel,c);

        // Luodaan inklinaation rajauspaneelit
        setMyConstraints(c,2,0,GridBagConstraints.WEST);

        // Otetaan talteen paneeli jotta saadaan tiedonsyötöt haettua talteen
        inclinationCometConstraintsPanel = CreateConstraintsPanel(controller,Text.inclinationCheckBoxText);
        cometConstraintsChoicePanel.add(inclinationCometConstraintsPanel,c);

        // Luodaan perihelin argumentin rajauspaneelit
        setMyConstraints(c,3,0,GridBagConstraints.WEST);

        // Otetaan talteen paneeli jotta saadaan tiedonsyötöt haettua talteen
        perArgCometConstraintsPanel = CreateConstraintsPanel(controller,Text.perArgCheckBoxText);
        cometConstraintsChoicePanel.add(perArgCometConstraintsPanel,c);

        // Luodaan nousevan solmun pituuden rajauspaneelit
        setMyConstraints(c,4,0,GridBagConstraints.WEST);

        // Otetaan talteen paneeli jotta saadaan tiedonsyötöt haettua talteen
        nodeCometConstraintsPanel = CreateConstraintsPanel(controller,Text.nodeCheckBoxText);
        cometConstraintsChoicePanel.add(nodeCometConstraintsPanel,c);

        // Luodaan painike jolla otetaan käyttöön rajaukset
        setMyConstraints(c,5,0,GridBagConstraints.CENTER);
        cometConstraintsChoicePanel.add(CreateButton(controller),c);

        // Luodaan tietopaneeli
        setMyConstraints(c,1,1,GridBagConstraints.EAST);

        // Tehdään tästä paneelista 2x6 paneeli
        c.gridwidth = 6;
        c.gridheight = 2;
    }

    /**
     *  Luodaan paneelit jossa voidaan rajata hakutietoja ja näytetään saatava tai pikkuplaneetta-tieto
     * @param controller
     *
     */
    public void CreateAsteroidConstraintsChoicePanel(Controller controller) {


        System.out.println("    Ui.CreateAsteroidConstraintsChoicePanel()");

        asteroidConstraintsChoicePanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Luodaan perihelin rajauspaneelit
        setMyConstraints(c,0,0,GridBagConstraints.WEST);

        // Otetaan talteen paneeli jotta saadaan tiedonsyötöt haettua talteen
        semiAxisAsteroidConstraintsPanel = CreateConstraintsPanel(controller,Text.semiAxisCheckBoxText);
        asteroidConstraintsChoicePanel.add(semiAxisAsteroidConstraintsPanel,c);

        // Luodaan eksentrisyyden rajauspaneelit
        setMyConstraints(c,1,0,GridBagConstraints.WEST);

        // Otetaan talteen paneeli jotta saadaan tiedonsyötöt haettua talteen
        eccentricityAsteroidConstraintsPanel = CreateConstraintsPanel(controller,Text.eccentricityCheckBoxText);
        asteroidConstraintsChoicePanel.add(eccentricityAsteroidConstraintsPanel,c);

        // Luodaan inklinaation rajauspaneelit
        setMyConstraints(c,2,0,GridBagConstraints.WEST);

        // Otetaan talteen paneeli jotta saadaan tiedonsyötöt haettua talteen
        inclinationAsteroidConstraintsPanel = CreateConstraintsPanel(controller,Text.inclinationCheckBoxText);
        asteroidConstraintsChoicePanel.add(inclinationAsteroidConstraintsPanel,c);

        // Luodaan perihelin argumentin rajauspaneelit
        setMyConstraints(c,3,0,GridBagConstraints.WEST);

        // Otetaan talteen paneeli jotta saadaan tiedonsyötöt haettua talteen
        perArgAsteroidConstraintsPanel = CreateConstraintsPanel(controller,Text.perArgCheckBoxText);
        asteroidConstraintsChoicePanel.add(perArgAsteroidConstraintsPanel,c);

        // Luodaan nousevan solmun pituuden rajauspaneelit
        setMyConstraints(c,4,0,GridBagConstraints.WEST);

        // Otetaan talteen paneeli jotta saadaan tiedonsyötöt haettua talteen
        nodeAsteroidConstraintsPanel = CreateConstraintsPanel(controller,Text.nodeCheckBoxText);
        asteroidConstraintsChoicePanel.add(nodeAsteroidConstraintsPanel,c);

        // Luodaan nousevan solmun pituuden rajauspaneelit
        setMyConstraints(c,5,0,GridBagConstraints.WEST);

        // Otetaan talteen paneeli jotta saadaan tiedonsyötöt haettua talteen
        meanAnomalyAsteroidConstraintsPanel = CreateConstraintsPanel(controller,Text.meanAnomalyCheckBoxText);
        asteroidConstraintsChoicePanel.add(meanAnomalyAsteroidConstraintsPanel,c);

        // Luodaan painike jolla otetaan käyttöön rajaukset
        setMyConstraints(c,6,0,GridBagConstraints.CENTER);
        asteroidConstraintsChoicePanel.add(CreateButton(controller),c);

        // Luodaan tietopaneeli
        setMyConstraints(c,1,1,GridBagConstraints.EAST);

        // Tehdään tästä paneelista 2x6 paneeli
        c.gridwidth = 7;
        c.gridheight = 2;

    }

    /**
     *
     * @param controller
     * @return
     */
    public JButton CreateButton(Controller controller) {

        System.out.println("    Ui.CreateButton()");

        // Luodaan painonappi jolla käytetään rajat
        JButton queryButton = new JButton(Text.queryCommand);
        queryButton.addActionListener(controller);

        return queryButton;
    }

    /**
     *
     * @param controller
     * @param constraintsText
     * @return
     */
    public JPanel CreateConstraintsPanel(Controller controller, String constraintsText) {

        System.out.println("    Ui.CreateConstraintsPanel()");
        System.out.println("    Created panel : "+constraintsText);

        JPanel constraintsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        constraintsPanel.setBorder(BorderFactory.createEtchedBorder());

        // Luodaan rajoitustyyppitekstipaneeli
        JLabel constraintsLabel = new JLabel(constraintsText+" :");
        setMyConstraints(c,0,0,GridBagConstraints.WEST);
        constraintsPanel.add(constraintsLabel);

        // Luodaan alarajan syöttökohta
        setMyConstraints(c,0,1,GridBagConstraints.WEST);
        constraintsPanel.add(new JLabel(Text.lowerLimitText),c);
        JTextField lowerLimitField = new JTextField(10);
        setMyConstraints(c,1,1,GridBagConstraints.EAST);
        constraintsPanel.add(lowerLimitField,c);

        // Luodaan ylärajan syöttökohta
        setMyConstraints(c,0,2,GridBagConstraints.WEST);
        constraintsPanel.add(new JLabel(Text.upperLimitText),c);
        JTextField upperLimitField = new JTextField(10);
        setMyConstraints(c,1,2,GridBagConstraints.EAST);
        constraintsPanel.add(upperLimitField,c);

        c.gridwidth = 2;
        c.gridheight = 3;

        return constraintsPanel;
    }

    /**
     *
     * @return
     */
    public boolean CheckConstraintsFieldValues() {

        System.out.println("    Ui.CheckConstraintsFieldValues()");

        boolean isPerihelionLimitsValid, isEccentricityLimitsValid, isInclinationLimitsValid, isPerArgLimitsValid = true;
        boolean isNodeLimitsValid, isSemiAxisLimitsValid, isMeanAnomalyLimitsValid = true;
        boolean isValid = false;

        if (uiPaneIndex == 0) {

            // Tarkastetaan komeettojen raja-arvot
            perihelionLimits = iterateOverJTextFields(perihelionCometConstraintsPanel);
            eccentricityLimits = iterateOverJTextFields(eccentricityCometConstraintsPanel);
            inclinationLimits = iterateOverJTextFields(inclinationCometConstraintsPanel);
            perArgLimits = iterateOverJTextFields(perArgCometConstraintsPanel);
            nodeLimits = iterateOverJTextFields(nodeCometConstraintsPanel);

        } else if (uiPaneIndex == 1) {

            // Tarkastetaan pikkuplaneettojen raja-arvot
            semiAxisLimits = iterateOverJTextFields(semiAxisAsteroidConstraintsPanel);
            eccentricityLimits = iterateOverJTextFields(eccentricityAsteroidConstraintsPanel);
            inclinationLimits = iterateOverJTextFields(inclinationAsteroidConstraintsPanel);
            perArgLimits = iterateOverJTextFields(perArgAsteroidConstraintsPanel);
            nodeLimits = iterateOverJTextFields(nodeAsteroidConstraintsPanel);
            meanAnomalyLimits = iterateOverJTextFields(meanAnomalyAsteroidConstraintsPanel);
        }

        isPerihelionLimitsValid = CheckBoundary(perihelionLimits, 0, 1000);
        isEccentricityLimitsValid = CheckBoundary(eccentricityLimits, 0, 2);
        isInclinationLimitsValid = CheckBoundary(inclinationLimits, 0, 180);
        isPerArgLimitsValid = CheckBoundary(perArgLimits, 0, 360);
        isNodeLimitsValid = CheckBoundary(nodeLimits, 0, 360);
        isSemiAxisLimitsValid = CheckBoundary(semiAxisLimits, 0, 10000);
        isMeanAnomalyLimitsValid = CheckBoundary(meanAnomalyLimits, 0, 360);

        System.out.println("    Raja-arvot ovat  : "+Text.perihelionCheckBoxText+" - "+ perihelionLimits+" - "+isPerihelionLimitsValid);
        System.out.println("                     : "+Text.eccentricityCheckBoxText+" - "+ eccentricityLimits+" - "+isEccentricityLimitsValid);
        System.out.println("                     : "+Text.inclinationCheckBoxText+" - "+ inclinationLimits+" - "+isInclinationLimitsValid);
        System.out.println("                     : "+Text.perArgCheckBoxText+" - "+ perArgLimits+" - "+isPerArgLimitsValid);
        System.out.println("                     : "+Text.nodeCheckBoxText+" - "+ nodeLimits+" - "+isNodeLimitsValid);
        System.out.println("                     : "+Text.semiAxisCheckBoxText+" - "+ semiAxisLimits+" - "+isSemiAxisLimitsValid);
        System.out.println("                     : "+Text.meanAnomalyCheckBoxText+" - "+ meanAnomalyLimits+" - "+isMeanAnomalyLimitsValid);

        if ((isPerihelionLimitsValid) &&
                (isEccentricityLimitsValid) &&
                (isInclinationLimitsValid) &&
                (isPerArgLimitsValid) &&
                (isNodeLimitsValid) &&
                (isSemiAxisLimitsValid) &&
                (isMeanAnomalyLimitsValid)) {

            isValid = true;
        }
        return isValid;
    }

    public void ClearConstraints() {

        ClearJTextFields(perihelionCometConstraintsPanel);
        ClearJTextFields(eccentricityCometConstraintsPanel);
        ClearJTextFields(inclinationCometConstraintsPanel);
        ClearJTextFields(perArgCometConstraintsPanel);
        ClearJTextFields(nodeCometConstraintsPanel);
        ClearJTextFields(eccentricityAsteroidConstraintsPanel);
        ClearJTextFields(inclinationAsteroidConstraintsPanel);
        ClearJTextFields(perArgAsteroidConstraintsPanel);
        ClearJTextFields(nodeAsteroidConstraintsPanel);
        ClearJTextFields(semiAxisAsteroidConstraintsPanel);
        ClearJTextFields(meanAnomalyAsteroidConstraintsPanel);

        perihelionLimits = null;
        eccentricityLimits = null;
        inclinationLimits = null;
        perArgLimits = null;
        nodeLimits = null;
        semiAxisLimits = null;
        meanAnomalyLimits = null;
    }

    /**
     *
     * @return
     */
    public boolean CheckQueryLimitsEnabled() {

        System.out.println("    Ui.CheckQueryLimitsEnabled()");

        boolean isLimitCheck = false;

        if (perihelionLimits != null ||
                eccentricityLimits != null ||
                inclinationLimits != null ||
                perArgLimits != null ||
                nodeLimits != null ||
                semiAxisLimits != null ||
                meanAnomalyLimits != null) {

            isLimitCheck = true;
        }

        return isLimitCheck;
    }

    /**
     * Luodaan tietopaneeli missä näytetään datatulos rajauksen mukaan
     *
     * @return
     */
    public JPanel CreateDataPanel() {

        System.out.println("    Ui.CreateDataPanel()");

        dataPanel = new JPanel();

        if (uiPaneIndex == 0) {

            dataPanel.add(GetCometTableScrollPane());

        } else if (uiPaneIndex == 1) {

            dataPanel.add(GetAsteroidTableScrollPane());
        }
        return dataPanel;
    }

    public void CreateUploadDialog(Controller controller) {

        System.out.println("    Ui.CreateUploadDialog()");

        JDialog uploadDialog = new JDialog(GetFrame(), Text.uploadDialogText);

        JPanel inputPanel = new JPanel();

        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder(Text.addDataText));
        GridBagConstraints c = new GridBagConstraints();


        JButton cometLoadButton = new JButton(Text.cometUploadText);
        cometLoadButton.addActionListener(controller);
        setMyConstraints(c,0,0,GridBagConstraints.WEST);
        inputPanel.add(cometLoadButton, c);

        JButton numberAsteroidLoadButton = new JButton(Text.numberAsteroidUploadText);
        numberAsteroidLoadButton.addActionListener(controller);

        setMyConstraints(c,0,1,GridBagConstraints.WEST);
        inputPanel.add(numberAsteroidLoadButton, c);

        JButton unnumberAsteroidLoadButton = new JButton(Text.unnumberAsteroidUploadText);
        unnumberAsteroidLoadButton.addActionListener(controller);

        setMyConstraints(c,0,2,GridBagConstraints.WEST);
        inputPanel.add(unnumberAsteroidLoadButton, c);

        JButton exitUploadButton = new JButton(Text.exitUploadText);
        setMyConstraints(c,0,3,GridBagConstraints.WEST);

        inputPanel.add(exitUploadButton, c);

        uploadDialog.add(inputPanel);
        uploadDialog.setLocation(800, 400);
        uploadDialog.setSize(300, 200);
        uploadDialog.setVisible(true);

        exitUploadButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                uploadDialog.setVisible(false);
                uploadDialog.dispose();
            }
        });
    }

    /**
     *
     * @return
     */
    public File CometUpload() {

        System.out.println("    Ui.CometUpload()");

        JFileChooser importFile = new JFileChooser();
        importFile.setCurrentDirectory(new File("."));
        importFile.setDialogTitle(Text.importCometText);
        importFile.setAcceptAllFileFilterUsed(false);
        int valinta = importFile.showOpenDialog(GetFrame());

        if (valinta == JFileChooser.CANCEL_OPTION) {

            //CreateMessageDialog(GetFrame(),Text.importCometCancel,Text.importCometText,JOptionPane.INFORMATION_MESSAGE);

            return null;

        } else if (valinta == JFileChooser.APPROVE_OPTION) {

            return importFile.getSelectedFile();
        }

        return null;
    }

    /**
     *
     * @return
     */
    public File NumberAsteroidUpload() {

        System.out.println("    Ui.NumberAsteroidUpload()");

        JFileChooser importFile = new JFileChooser();
        importFile.setCurrentDirectory(new File("."));
        importFile.setDialogTitle(Text.importNumberAsteroidText);
        importFile.setAcceptAllFileFilterUsed(false);
        int tulos = importFile.showOpenDialog(GetFrame());

        if (tulos == JFileChooser.CANCEL_OPTION) {

            //CreateMessageDialog(GetFrame(),Text.importAsteroidCancel,Text.importNumberAsteroidText,JOptionPane.INFORMATION_MESSAGE);

        } else if (tulos == JFileChooser.APPROVE_OPTION) {

            return importFile.getSelectedFile();
        }

        return null;
    }

    public void UnnumberAsteroidUpload() {

        System.out.println("    Ui.UnnumberAsteroidUpload()");

        JFileChooser importFile = new JFileChooser();
        importFile.setCurrentDirectory(new File("."));
        importFile.setDialogTitle(Text.importUnnumberAsteroidText);
        importFile.setAcceptAllFileFilterUsed(false);
        int tulos = importFile.showOpenDialog(GetFrame());

        if (tulos == JFileChooser.CANCEL_OPTION) {

            //CreateMessageDialog(GetFrame(),Text.importAsteroidCancel,Text.importUnnumberAsteroidText,JOptionPane.INFORMATION_MESSAGE);

        } else if (tulos == JFileChooser.APPROVE_OPTION) {

            CreateMessageDialog(GetFrame(),Text.unSupportedFeature,Text.importNumberAsteroidText,JOptionPane.INFORMATION_MESSAGE);

        }
    }

    /**
     *
     * @param aFrame
     * @param aMessage
     * @param aTitle
     * @param aType
     */

    public void CreateMessageDialog(JFrame aFrame, String aMessage, String aTitle, int aType) {

        JOptionPane.showMessageDialog(aFrame, aMessage, aTitle, aType);
    }

    public ArrayList<String> GetPerihelionLimits() {

        return perihelionLimits;
    }

    public ArrayList<String> GetEccentricityLimits() {

        return eccentricityLimits;
    }

    public ArrayList<String> GetInclinationLimits() {

        return inclinationLimits;
    }

    public ArrayList<String> GetPerArgLimits() {

        return perArgLimits;
    }

    public ArrayList<String> GetNodeLimits() {

        return nodeLimits;
    }


    public ArrayList<String> GetSemiAxisLimits() {

        return semiAxisLimits;
    }

    public ArrayList<String> GetMeanAnomalyLimits() {

        return meanAnomalyLimits;
    }

    public JPanel GetDataPanel() {

        return dataPanel;

    }

    /**
     *
     * @param c
     * @param gridx
     * @param gridy
     * @param anchor
     */
    private static void setMyConstraints(GridBagConstraints c, int gridx, int gridy, int anchor) {

        c.gridx = gridx;
        c.gridy = gridy;
        c.anchor = anchor;
    }

    /**
     *
     * @param container
     * @return
     */
    private ArrayList<String> iterateOverJTextFields(Container container) {

        ArrayList<String> fieldValues = new ArrayList<>();

        for (Component component : container.getComponents()) {

            if (component instanceof JTextField) {

                String value = ((JTextField) component).getText();
                fieldValues.add(value);
            }
        }

        if (fieldValues.get(0).isEmpty() && fieldValues.get(1).isEmpty())
            return null;

        return fieldValues;
    }


    private void ClearJTextFields(Container container) {

        ArrayList<String> fieldValues = new ArrayList<>();

        for (Component component : container.getComponents()) {

            if (component instanceof JTextField) {

                ((JTextField) component).setText("");
            }
        }
    }

    /**
     *
     * @param limitArray
     * @param minLimit
     * @param maxLimit
     * @return
     */
    private boolean CheckBoundary(ArrayList<String> limitArray, int minLimit, int maxLimit) {

        boolean isValid = false;

        if (limitArray == null) {

            isValid = true;
            return isValid;
        }

        return CheckNumber(limitArray, minLimit, maxLimit);
    }

    /**
     *
     * @param limitArray
     * @param minLimit
     * @param maxLimit
     * @return
     */
    private boolean CheckNumber(ArrayList<String> limitArray, int minLimit, int maxLimit) {

        boolean isValid1 = true;
        boolean isValid2 = true;
        BigDecimal.valueOf(minLimit);
        BigDecimal.valueOf(maxLimit);

        if(!limitArray.get(0).isEmpty()) {

            try {

                BigDecimal d = new BigDecimal(limitArray.get(0));
                if (d.compareTo(BigDecimal.valueOf(minLimit)) == 1 || d.compareTo(BigDecimal.valueOf(minLimit)) == 0) {

                    isValid1 = true;

                } else {

                    isValid1 = false;
                }

            } catch (NumberFormatException nfe) {

                isValid1 = false;
            }
        }

        System.out.println("    limitArray[0] : "+isValid1);

        if(!limitArray.get(1).isEmpty()) {

            try {

                BigDecimal d = new BigDecimal(limitArray.get(1));
                if (d.compareTo(BigDecimal.valueOf(maxLimit)) == -1 || d.compareTo(BigDecimal.valueOf(maxLimit)) == 0) {

                    isValid2 = true;

                } else {

                    isValid2 = false;
                }

            } catch (NumberFormatException nfe) {

                isValid2 = false;
            }
        }

        System.out.println("    limitArray[1] : "+isValid2);

        if ( !isValid1 || !isValid2)
            return false;
        else
            return true;
    }

    /**
     *  Luodaan otsikkopaneeli
     *
     * @return headingPanel Palautetaan otsikko
     */
    private JPanel CreateHeadingPanel() {

        System.out.println("	Ui.CreateHeadingPanel()");

        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new GridLayout(0, 1));

        JLabel headingLabel1 = new JLabel(Text.mainTitleText1, SwingConstants.CENTER);
        JLabel headingLabel2 = new JLabel(Text.mainTitleText2, SwingConstants.CENTER);

        headingPanel.add(headingLabel1);
        headingPanel.add(Box.createGlue());
        headingPanel.add(headingLabel2);

        return headingPanel;
    }

    /**
     *  Luodaan datansyöttöpainike
     *
     * @param controller
     * @return
     */
    private JPanel CreateButtonPanel(Controller controller) {

        System.out.println("    Ui.CreateButtonPanel()");

        JPanel dataImportPanel = new JPanel(new GridBagLayout());

        JButton dataImportButton = new JButton(Text.importDataCommand);
        dataImportButton.addActionListener(controller);

        dataImportPanel.add(dataImportButton);

        return dataImportPanel;
    }
}