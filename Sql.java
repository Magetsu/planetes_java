import java.sql.*;
import java.util.Vector;

/**
 * Tämä luokka hoitaa kommunikoinnin MySQL-tietokannan kanssa
 *
 */
public class Sql {

    Connection connection;

    /*
     *
     */
    public Sql() {

        System.out.println("	Sql.Sql()");
    }

    /**
     * Tämä funktio luo yhteyden MySQL-kantaan
     *
     */
    public void GetConnection(String url, String username, String password) {

        System.out.println("	Sql.GetConnection("+url+","+username+","+password+")");

        try {

            Class.forName(Text.driver);
            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {

            System.out.println("SQL Driver was not found. SQL is not installed?");

        } catch (SQLException e) {

            System.out.println("Could not open the connection. Database not found.");
        }

        System.out.println("	Connection created");
    }

    /**
     * Tämä funktio sulkee yhteyden MySQL-kantaan
     */
    public void CloseConnection() {

        System.out.println("	Sql.CloseConnection()");

        if (connection != null) {
            try {

                connection.close();

            } catch (SQLException e) {

                System.out.println("Could not close the connection.");
            }
        }
        System.out.println("	Connection closed");
    }

    /**
     * Tämä funktio luo tietokannan MySQL-kantaan
     *
     */
    public void CreateDatabase(String database) {

        System.out.println("	Sql.CreateDatabase("+database+")");
        System.out.println("    Statement: CREATE DATABASE IF NOT EXISTS "+database);

        Statement statement = null;

        try {

            statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS "+database);
            statement.close();

        } catch (SQLException e) {

            System.out.println("Could not create the database.");
        }
    }

    /**
     * Tämä funktio luo taulukon tietokantaan
     *
     */
    public void CreateTable(String database, String table, String rows) {

        System.out.println("	Sql.CreateTable()");
        System.out.println("    Statement: CREATE TABLE IF NOT EXISTS "+database+"."+table+" "+rows);

        Statement statement = null;

        try {

            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+database+"."+table+" "+rows);
            statement.close();

        } catch (SQLException e) {

            System.out.println("Could not create the table or close the statement.");
        }
    }

    /**
     * Tämä funktio hakee komeetta-datan määrätystä paikasta
     *
     */
    public Vector<Comet> GetCometData(String query) {

        System.out.println("	Sql.GetDatabaseData()");
        System.out.println("    Statement: "+query);

        Statement statement = null;
        ResultSet res;
        Vector<Comet> dataVector = new Vector<>();

        try {

            statement = connection.createStatement();

            res = statement.executeQuery(query);

            while (res.next()) {

                Comet newComet = new Comet();

                newComet.SetComet(res.getString(1),
                        res.getInt(2),
                        res.getBigDecimal(3),
                        res.getBigDecimal(4),
                        res.getBigDecimal(5),
                        res.getBigDecimal(6),
                        res.getBigDecimal(7),
                        res.getBigDecimal(8));

                dataVector.addElement(newComet);

            }
            res.close();
        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (statement != null) {

                try {

                    statement.close();

                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }
        }
        return dataVector;
    }

    /**
     * Tämä funktio hakee asteroidi-datan määrätystä paikasta
     *
     */
    public Vector<Asteroid> GetAsteroidData(String query) {

        System.out.println("	Sql.GetAsteroidData()");
        System.out.println("    Statement: "+query);

        Statement statement = null;
        ResultSet res;
        Vector<Asteroid> dataVector = new Vector<Asteroid>();

        try {

            statement = connection.createStatement();

            res = statement.executeQuery(query);

            while (res.next()) {

                Asteroid newAsteroid = new Asteroid();

                newAsteroid.SetAsteroid(res.getString(1),
                        res.getInt(2),
                        res.getBigDecimal(3),
                        res.getBigDecimal(4),
                        res.getBigDecimal(5),
                        res.getBigDecimal(6),
                        res.getBigDecimal(7),
                        res.getBigDecimal(8),
                        res.getBigDecimal(9),
                        res.getBigDecimal(10));

                dataVector.addElement(newAsteroid);

            }
            res.close();
        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (statement != null) {

                try {

                    statement.close();

                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }
        }
        return dataVector;
    }

    /**
     *
     * @param database
     * @param table
     * @param comet
     * @return
     */
    public boolean AddCometToDatabase(String database, String table, Comet comet) {

        PreparedStatement statement = null;
        boolean returnValue = false;
        boolean addfail = false;

        try {

            statement  = connection.prepareStatement(Text.INSERT_DATA+database+"."+table+Text.ADD_COMET);
            statement.setString(1,comet.GetName());
            statement.setInt(2,comet.GetEpoch());
            statement.setBigDecimal(3,comet.GetQ());
            statement.setBigDecimal(4,comet.GetE());
            statement.setBigDecimal(5,comet.GetI());
            statement.setBigDecimal(6,comet.GetW());
            statement.setBigDecimal(7,comet.GetNode());
            statement.setBigDecimal(8,comet.GetTp());
            statement.executeUpdate();

        } catch (Exception e) {

            System.out.println("Could not add comet to the database.");
            addfail = true;

        } finally {

            if (statement != null) {

                try {

                    statement.close();

                    if (!addfail) {

                        returnValue = true;

                    }
                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }
        }
        return returnValue;
    }


    public boolean AddAsteroidToDatabase(String database, String table, Asteroid asteroid) {

        PreparedStatement statement = null;
        boolean returnValue = false;
        boolean addfail = false;

        try {

            statement  = connection.prepareStatement(Text.INSERT_DATA+database+"."+table+Text.ADD_ASTEROID);
            statement.setString(1,asteroid.GetName());
            statement.setInt(2,asteroid.GetEpoch());
            statement.setBigDecimal(3,asteroid.GetA());
            statement.setBigDecimal(4,asteroid.GetE());
            statement.setBigDecimal(5,asteroid.GetI());
            statement.setBigDecimal(6,asteroid.GetW());
            statement.setBigDecimal(7,asteroid.GetNode());
            statement.setBigDecimal(8,asteroid.GetM());
            statement.setBigDecimal(9,asteroid.GetH());
            statement.setBigDecimal(10,asteroid.GetG());
            statement.executeUpdate();

        } catch (Exception e) {

            System.out.println("Could not add asteroid to the database.");
            addfail = true;

        } finally {

            if (statement != null) {

                try {

                    statement.close();

                    if (!addfail) {

                        returnValue = true;

                    }
                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }
        }
        return returnValue;
    }
    /**
     *
     * @param database
     * @param table
     */
    public void TruncateTable(String database, String table) {

        System.out.println("	Sql.TruncateTable()");
        System.out.println("    Statement: "+Text.DELETE+database+"."+table);
        Statement statement = null;

        try {

            statement = connection.createStatement();
            statement.executeUpdate(Text.DELETE+database+"."+table);

        } catch (Exception e) {

            System.out.println("Could not delete the database.");
            e.printStackTrace();

        } finally {
            if (statement != null) {

                try {

                    statement.close();

                } catch (SQLException e) {

                    System.out.println("Could not close the statement.");
                }
            }
        }

    }
}

