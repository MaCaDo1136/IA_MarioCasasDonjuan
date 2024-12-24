/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan.Utils;

import ia_mariocasasdonjuan.databaseLib.DatabaseManager;

public class Constants {
    public static class DatabaseConstants {
        public static final String url = "jdbc:mysql://172.168.100.195:3306/medWarehouse";
        public static final String user = "root";
        public static final String password = "CDMayito1136**";
    }

    public static class DbConnection {
        public static final DatabaseManager db = new DatabaseManager(
            DatabaseConstants.url,
            DatabaseConstants.user,
            DatabaseConstants.password
        );
    }
}
