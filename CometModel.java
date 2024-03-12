import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class CometModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private final Vector<Comet> cometContainer;

    static final public ColumnData columns[] = {
            new ColumnData( Text.cometTableFieldNames[0], Text.cometTableFieldLengths[0], JLabel.LEFT ),
            new ColumnData( Text.cometTableFieldNames[1], Text.cometTableFieldLengths[1], JLabel.LEFT ),
            new ColumnData( Text.cometTableFieldNames[2], Text.cometTableFieldLengths[2], JLabel.LEFT ),
            new ColumnData( Text.cometTableFieldNames[3], Text.cometTableFieldLengths[3], JLabel.LEFT ),
            new ColumnData( Text.cometTableFieldNames[4], Text.cometTableFieldLengths[4], JLabel.LEFT ),
            new ColumnData( Text.cometTableFieldNames[5], Text.cometTableFieldLengths[5], JLabel.LEFT ),
            new ColumnData( Text.cometTableFieldNames[6], Text.cometTableFieldLengths[6], JLabel.LEFT ),
            new ColumnData( Text.cometTableFieldNames[7], Text.cometTableFieldLengths[7], JLabel.LEFT )
    };

    public CometModel(Vector<Comet> container) {

        System.out.println("CometModel.CometModel()");

        cometContainer = container;
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

        return cometContainer==null ? 0 : cometContainer.size();
    }

    /**
     *
     */
    public Object getValueAt(int nRow, int nCol) {

        if (nRow < 0 || nRow>=getRowCount())
            return "";
        Comet row = cometContainer.elementAt(nRow);
        switch (nCol) {
            case 0: return row.GetName();
            case 1: return row.GetEpoch();
            case 2: return row.GetQ();
            case 3: return row.GetE();
            case 4: return row.GetI();
            case 5: return row.GetW();
            case 6: return row.GetNode();
            case 7: return row.GetTp();
        }
        return "";
    }

    /**
     *
     */
    public Comet getRowAt(int nRow) {

        return cometContainer.elementAt(nRow);
    }

    /**
     *
     */
    public boolean isCellEditable(int nRow, int nCol) {

        return false;
    }
}

/**
 * @author fiinmani
 *
 */
class ColumnData
{
    public String  title;
    public int     width;
    public int     alignment;

    public ColumnData(String aTitle, int aWidth, int aAlignment) {

        title = aTitle;
        width = aWidth;
        alignment = aAlignment;
    }
}
