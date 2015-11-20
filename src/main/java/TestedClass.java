import org.dbunit.database.IDatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by sztosz on 11/18/12.
 */
public class TestedClass {
    IDatabaseConnection connection;
    public TestedClass(IDatabaseConnection connection) {
        this.connection = connection;
    }

    public void add() throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(
                "INSERT INTO TEST_TABLE VALUES ('row 2 col 0', 'row 2 col 1', 'row 2 col 2')");
        preparedStatement.execute();
    }

    public void remove() throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(
                "REMOVE FROM TEST_TABLE WHERE COL0='row 0 col 0'");
        preparedStatement.executeUpdate();
    }

    public void edit() throws SQLException {
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(
                "UPDATE TEST_TABLE SET COL0='row 11 col 23', WHERE COL0='row 0 col 0'");
        preparedStatement.executeUpdate();
    }

}
