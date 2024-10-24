/*
 * Written by Mario Casas
 */

package ia_mariocasasdonjuan.databaseLib;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import ia_mariocasasdonjuan.Utils.Variables;

public class DatabaseManager {

    private Connection connection;

    public DatabaseManager(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {  
            System.err.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //Database Manager

    public void insertData(String tableName, String data, String value) {
        try {
            String sql = "INSERT INTO " + tableName + "(" + data + ")" + " VALUES (" + value + ")";
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateData(String tableName, String newData, String newValue, String refData, String refValue) {
        try {
            String sql = "UPDATE " + tableName + " SET " + newData + " = " + newValue + " WHERE " + refData + " = " + refValue;
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void dropTable(String tableName) {
        try {
            String sql = "DROP TABLE " + tableName;
            connection.createStatement().executeUpdate(sql);
            // System.out.println("Tabla eliminada con exito");
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void selectTable(String tableName) { //specific for an agenda (modificable)
        try {
            String sql = "SELECT * FROM " + tableName;
            ResultSet rs = connection.createStatement().executeQuery(sql);

            System.out.printf("%-10s %-20s %-20s %-20s%n", "ID", "Nombre", "Primer Apellido", "Segundo Apellido");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String primerApellido = rs.getString("primerApellido");
                String segundoApellido = rs.getString("segundoApellido");

                System.out.printf("%-10d %-20s %-20s %-20s%n", id, nombre, primerApellido, segundoApellido);
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteData(String tableName, String idNum) {
        try {
            String sql = "DELETE FROM " + tableName + " WHERE id = " + idNum;
            connection.createStatement().executeUpdate(sql); 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Advanced Database Manager

    public void updateLine(String tableName, String[] newData, String[] newValue, String refData, String refValue) {
        for (int i = 0; i < newData.length; i++) {
            if (newValue[i] == null) {
                continue;
            } else {
                updateData(tableName, newData[i], newValue[i], refData, refValue);
            }
        }
    }

    public void insertLine(DatabaseManager db, String tableName, String refData, String refValue, String newData[], String newValue[]) {
        db.insertData(tableName, refData, refValue);

        updateLine(tableName, newData, newValue, refData, refValue);
    }

    public String returnId(String tableName, String data, String value) {
        try {
            String sql = "SELECT id FROM " + tableName + " WHERE " + data + " = " + value;
            ResultSet rs = connection.createStatement().executeQuery(sql);
            if (rs.next()) {
                int idNum = rs.getInt("id");
                return String.valueOf(idNum);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return null;
    }

    public void selectLine(String tableName, String data, String value) {
        try {
            String sql = "SELECT * FROM " + tableName + " WHERE " + data + " = " + value;
            
            ResultSet rs = connection.createStatement().executeQuery(sql);
    
            System.out.printf("%-10s %-6s %-20s %-20s %-20s %-20s %-16s %-56s%n", "id", "codeId", "name", "midname", "lastName1", "lastName2", "telephoneNum", "mail");
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String codeId = rs.getString("codeId");
                String name = rs.getString("name");
                String midname = rs.getString("midname");
                String lastName1 = rs.getString("lastName1");
                String lastName2 = rs.getString("lastName2");
                String telephoneNum = rs.getString("telephoneNum");
                String mail = rs.getString("mail");
    
                System.out.printf("%-10d %-6s %-20s %-20s %-20s %-20s %-16s %-56s%n", id, codeId, name, midname, lastName1, lastName2, telephoneNum, mail);
            }
    
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getPreviousValue(String tableName, String data) {
        String lastValue = null;
        String sql = "SELECT " + data + " FROM " + tableName + " ORDER BY id DESC LIMIT 1";
        try {
                ResultSet rs = connection.createStatement().executeQuery(sql);

                if (rs.next()) {
                    lastValue = rs.getString(data);
                }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return lastValue;
    }

    public String getColumnWithValue(String tableName, String value) {
        String columnNameResult = null;
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String sql = "SELECT 1 FROM " + tableName + " WHERE " + columnName + " = '" + value + "' LIMIT 1";
                try (Statement st = connection.createStatement();
                     ResultSet resultSet = st.executeQuery(sql)) {

                    if (resultSet.next()) {
                        columnNameResult = columnName;
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return columnNameResult;
    }
    

    //Advanced Dates Manager

    public void addDate(String titulo, String descripcion, LocalDate fecha, LocalTime hora) { //specific for an agenda (modificable)
        String sql = "INSERT INTO citas (titulo, descripcion, fecha, hora) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, descripcion);
            pstmt.setDate(3, Date.valueOf(fecha));
            pstmt.setTime(4, Time.valueOf(hora));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllDates(String tableName) { //specific for an agenda (modificable)
        try {
            String sql = "SELECT * FROM " + tableName;
            ResultSet rs = connection.createStatement().executeQuery(sql);
    
            System.out.printf("%-10s %-20s %-20s %-20s %-20s%n", "ID", "titulo", "descripcion", "fecha", "hora");
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                LocalTime hora = rs.getTime("hora").toLocalTime();
    
                System.out.printf("%-10d %-20s %-20s %-20s %-20s%n", id, titulo, descripcion, fecha, hora);
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateDate(int id, String titulo, String descripcion, LocalDate fecha, LocalTime hora) { //specific for an agenda (modificable)
        String sql = "UPDATE citas SET titulo = ?, descripcion = ?, fecha = ?, hora = ? WHERE id = ?";
    
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, descripcion);
            pstmt.setDate(3, Date.valueOf(fecha));
            pstmt.setTime(4, Time.valueOf(hora));
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertDateTime(String tableName, String data, LocalDateTime dateTime) {
        try {
            String sql = "INSERT INTO " + tableName + "(" + data + ")" + " VALUES (" + dateTime + ")";
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //medWarehouse Advanced Manager

    /*
     * RegisterFrame Main Method
     */
    public void registerOrUpdateMedicine(String barcode, String name, String lote, String expDate, String quantity, String location, String description) throws SQLException {
        String sql = "SELECT id FROM Medicines WHERE barcode = " + barcode;
        ResultSet rs = connection.createStatement().executeQuery(sql); 

        if (rs.next()) {
            updateInventory(getMedIdWithBarcode(barcode), lote, expDate, quantity);
            updateLocation(getInventoryIdWithMedId(getMedIdWithBarcode(barcode), lote, expDate, quantity), location, quantity);
        } else {
            registerMedicine(barcode, name, description);
            updateInventory(getMedIdWithBarcode(barcode), lote, expDate, quantity);
            updateLocation(getInventoryIdWithMedId(getMedIdWithBarcode(barcode), lote, expDate, quantity), location, quantity);
        }
    }

    //dependencies
    private void registerMedicine(String barcode, String name, String description) throws SQLException {
        String sql = "INSERT INTO Medicines (barcode, name, description) VALUES (?, ?, ?)";
        PreparedStatement insertStmt = getConnection().prepareStatement(sql);
        insertStmt.setString(1, barcode);
        insertStmt.setString(2, name);
        insertStmt.setString(3, description);
        insertStmt.executeUpdate();

        int medicamentId = getMedIdWithBarcode(barcode);
        registerMovement(medicamentId, "Registro", 0);
    }

    private void registerMovement(int inventoryId, String movementType, int movementQuantity) throws SQLException {
        String sql = "INSERT INTO Movements (inventoryId, movementType, movementQuantity, movementDate) VALUES (?, ?, ?, ?)";
        PreparedStatement insertMovementStmt = getConnection().prepareStatement(sql);
        insertMovementStmt.setInt(1, inventoryId);
        insertMovementStmt.setString(2, movementType);
        insertMovementStmt.setInt(3, movementQuantity);
        insertMovementStmt.setString(4, Variables.TimeVariables.sDateTime);
        insertMovementStmt.executeUpdate();
    }

    private void updateInventory(int medicamentId, String lote, String expDate, String actualQuantity) throws SQLException { //NECESITO CORREGIR ESTO
            String sql = "INSERT INTO Inventory (medicamentId, lote, expDate, quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement updateInventoryStmt = connection.prepareStatement(sql);
            int quantity = Integer.parseInt(actualQuantity);
            int loteI = Integer.parseInt(lote);
            updateInventoryStmt.setInt(1, medicamentId);
            updateInventoryStmt.setInt(2, loteI);
            updateInventoryStmt.setString(3, expDate);
            updateInventoryStmt.setInt(4, quantity);
            updateInventoryStmt.executeUpdate();

            registerMovement(medicamentId, "Ingreso", quantity);
    }

    private void updateLocation(int inventoryId, String actualLocation, String quantity) throws SQLException {
        String sql = "INSERT INTO Locations (inventoryId, actualLocation) VALUES (?, ?)";
        PreparedStatement insertLocationStmt = connection.prepareStatement(sql);
        insertLocationStmt.setInt(1, inventoryId);
        insertLocationStmt.setString(2, actualLocation);
        insertLocationStmt.executeUpdate();

        int movementQuantity = Integer.parseInt(quantity);

        registerMovement(inventoryId, "Cambio de Ubicación", movementQuantity);
    }

    private int getMedIdWithBarcode(String barcode) throws SQLException {
        barcode = DataValidator.formatValue(barcode);
        String sql = "SELECT id FROM Medicines WHERE barcode = " + barcode;
        ResultSet rs = connection.createStatement().executeQuery(sql);
        if (rs.next()) {
            return rs.getInt("id");
        }
        return -1;
    }

    private int getInventoryIdWithMedId(int medicamentId, String lote, String expDate, String quantity) throws SQLException {
        lote = DataValidator.formatValue(lote);
        expDate = DataValidator.formatValue(expDate);
        String sql = "SELECT id FROM Inventory WHERE medicamentId = " + medicamentId + " AND lote = " + lote + " AND expDate = " + expDate + " AND quantity = " + quantity;
        ResultSet rs = connection.createStatement().executeQuery(sql);
        if (rs.next()) {
            return rs.getInt("id");
        }
        return -1;
    }

    public boolean checkIfMedicineExists_Medicines(String barcode) throws SQLException {
        String sql = "SELECT id FROM Medicines WHERE barcode = " + barcode;
        ResultSet rs = connection.createStatement().executeQuery(sql);
        return rs.next();
    }

    public boolean checkIfMedicineExists_Inventory(int medicamentId) throws SQLException {
        String sql = "SELECT id FROM Inventory WHERE medicamentId = " + medicamentId;
        ResultSet rs = connection.createStatement().executeQuery(sql);
        return rs.next();
    }

    /*
     * MedInventory_InFrame Methods
     */

     public List<String> getLotesByBarcode(String barcode) throws SQLException {
        List<String> lotes = new ArrayList<>();
        String sql = "SELECT lote FROM Inventory WHERE medicamentId = " + getMedIdWithBarcode(barcode) + " AND quantity > 0";

        ResultSet rs = connection.createStatement().executeQuery(sql);
        while (rs.next()) {
            lotes.add(rs.getString("lote"));
        }
        return lotes;
    }

    public String getQuantityByBarcodeAndLote(String barcode, String lote) throws SQLException {
        String sql = "SELECT quantity FROM Inventory WHERE medicamentId = " + getMedIdWithBarcode(barcode) + " AND lote = " + lote;
        ResultSet rs = connection.createStatement().executeQuery(sql);
        if (rs.next()) {
            return rs.getString("quantity");
        }
        return null;
    }

    public void updateQuantity(String barcode, String lote, String newQuantity) throws SQLException {
        String sql = "UPDATE Inventory SET quantity = " + newQuantity + " WHERE medicamentId = " + getMedIdWithBarcode(barcode) + " AND lote = " + lote;
        connection.createStatement().executeUpdate(sql);
    }
}
