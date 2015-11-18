import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;

/**
 * Created by sztosz on 11/15/15.
 */
public class DbTest extends DBTestCase {
    public DbTest(String name) {
        super(name);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.hsqldb.jdbcDriver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:hsqldb:sample");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
        // System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "" );
    }

    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("dataset.xml"));
    }

//    protected DatabaseOperation getSetUpOperation() throws Exception {
//        return DatabaseOperation.REFRESH;
//    }
//
//    protected DatabaseOperation getTearDownOperation() throws Exception {
//        return DatabaseOperation.NONE;
//    }

//    protected void setUpDatabaseConfig(DatabaseConfig config) {
//        config.setProperty(DatabaseConfig.PROPERTY_BATCH_SIZE, 97);
//        config.setFeature(DatabaseConfig.FEATURE_BATCHED_STATEMENTS, true);
//    }

    public void testOne() throws Exception {
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("TEST_TABLE");

        // operacje na bazie danych

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("expected_dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable("TEST_TABLE");
        // sprawdzenie
        Assertion.assertEquals(expectedTable, actualTable);
    }
}