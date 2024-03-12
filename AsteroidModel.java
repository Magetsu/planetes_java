import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class AsteroidModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private final Vector<Asteroid> asteroidContainer;

    static final public ColumnData columns[] = {
            new ColumnData( Text.asteroidTableFieldNames[0], Text.asteroidTableFieldLengths[0], JLabel.LEFT ),
            new ColumnData( Text.asteroidTableFieldNames[1], Text.asteroidTableFieldLengths[1], JLabel.LEFT ),
            new ColumnData( Text.asteroidTableFieldNames[2], Text.asteroidTableFieldLengths[2], JLabel.LEFT ),
            new ColumnData( Text.asteroidTableFieldNames[3], Text.asteroidTableFieldLengths[3], JLabel.LEFT ),
            new ColumnData( Text.asteroidTableFieldNames[4], Text.asteroidTableFieldLengths[4], JLabel.LEFT ),
            new ColumnData( Text.asteroidTableFieldNames[5], Text.asteroidTableFieldLengths[5], JLabel.LEFT ),
            new ColumnData( Text.asteroidTableFieldNames[6], Text.asteroidTableFieldLengths[6], JLabel.LEFT ),
            new ColumnData( Text.asteroidTableFieldNames[7], Text.asteroidTableFieldLengths[7], JLabel.LEFT ),
            new ColumnData( Text.asteroidTableFieldNames[8], Text.asteroidTableFieldLengths[8], JLabel.LEFT ),
            new ColumnData( Text.asteroidTableFieldNames[9], Text.asteroidTableFieldLengths[9], JLabel.LEFT )
    };

    public AsteroidModel(Vector<Asteroid> container) {

        System.out.println("AsteroidModel.AsteroidModel()");

        asteroidContainer = container;
    }

    /**
     *
     */
    public int getColumnCount() {

        return columns.length;
    }

    /**
     *
     */
    public int getRowCount() {

        return asteroidContainer==null ? 0 : asteroidContainer.size();
    }

    /**
     *
     */
    public Object getValueAt(int nRow, int nCol) {

        if (nRow < 0 || nRow>=getRowCount())
            return "";
        Asteroid row = asteroidContainer.elementAt(nRow);
        switch (nCol) {
            case 0: return row.GetName();
            case 1: return row.GetEpoch();
            case 2: return row.GetA();
            case 3: return row.GetE();
            case 4: return row.GetI();
            case 5: return row.GetW();
            case 6: return row.GetNode();
            case 7: return row.GetM();
            case 8: return row.GetH();
            case 9: return row.GetG();
        }
        return "";
    }

    /**
     *
     */
    public Asteroid getRowAt(int nRow) {

        return asteroidContainer.elementAt(nRow);
    }

    /**
     *
     */
    public boolean isCellEditable(int nRow, int nCol) {

        return false;
    }
}