/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.Utils;

import ia_mariocasasdonjuan.databaseLib.DatabaseManager;

public class Constants {
    public static class DatabaseConstants {
        public static final String url = "jdbc:mysql://localhost:3306/medWarehouse";
        public static final String user = "root";
        public static final String password = "Marioc";
    }

    public static class DbConnection {
        public static final DatabaseManager db = new DatabaseManager(
            DatabaseConstants.url,
            DatabaseConstants.user,
            DatabaseConstants.password
        );
    }

    public static class MedicinesTABLE {
        public static final String tableName = "Medicines";
        public static final String[] allColumns = {
            "barcode",
            "name",
            "lote",
            "expDate",
            "quantity",
            "description",
            "registerDate"
        };
    }
}
