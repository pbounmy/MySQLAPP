package appone.bmp.com.mysqlapp;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ActionDB {
    public ResultSet SelectData();
    public int InsertData() throws SQLException;
    public int UpdateData();
    public int DeleteData();

}
