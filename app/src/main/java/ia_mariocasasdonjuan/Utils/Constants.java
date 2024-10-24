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
            "name",
            "lote",
            "expDate",
            "quantity",
            "location",
            "description",
            "registerDate"  
        };
    }

    public static class MovementsTABLE {
        public static final String tableName = "Movements";
        public static final String[] allColumns = {
            "medicamentId",
            "movementType",
            "movementQuantity",
            "movementDate",
        };
    }

    public static class LocationTABLE {
        public static final String tableName = "Location";
        public static final String[] allColumns = {
            "medicamentId",
            "actualLocation",
            "movementDate"
        };
    }

    public static class InventoryTABLE {
        public static final String tableName = "Inventory";
        public static final String[] allColumns = {
            "medicamentId",
            "actualQuantity",
            "modificationDate"
        };
    }
}
