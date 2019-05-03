package shop.infrastructure.persistence.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MySQL {
    private Connection connection;

    public MySQL(String host, String username, String password, String database) {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void execute(String sql) {
        Statement statement;

        try {
            statement = this.connection.createStatement();

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, String>> query(String sql) {
        Statement statement;
        List<Map<String, String>> result = new ArrayList<>();

        try {
            statement = this.connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Map<String, String> row = new HashMap<>();

                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.put(resultSet.getMetaData().getColumnName(i), resultSet.getString(i));
                }

                result.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
