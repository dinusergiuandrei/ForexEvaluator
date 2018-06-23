package Database;

import Forex.CurrencyPair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteDatabase implements AutoCloseable {
    private String databasePath;

    private String databaseUrl;

    private Connection connection = null;

    public SqliteDatabase() {
        this.databasePath = "database/forex_archive.db";
        this.databaseUrl = "jdbc:sqlite:" + this.databasePath;
    }

    public SqliteDatabase(String databasePath) {
        this.databasePath = databasePath;
        this.databaseUrl = "jdbc:sqlite:" + this.databasePath;
    }

    public Connection getConnection() {
        if (this.connection != null) {
            return this.connection;
        } else {
            this.connection = this.createConnection();
            return this.connection;
        }
    }

    private Connection createConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.databaseUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public List<CurrencyPair> getAll() {
        String sql = "SELECT * FROM currency order by timestamp";
        List<CurrencyPair> currencyPairs = new ArrayList<>();
        try {
            Connection conn = this.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            List<CurrencyPair> currencyPairsTmp = getCurrencyPairsFromResultSet(resultSet);
            currencyPairs.addAll(currencyPairsTmp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return currencyPairs;
    }

    public List<CurrencyPair> getBySymbol(String symbol) {
        List<CurrencyPair> currencyPairs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM currency where symbol==? order by timestamp";
            Connection connection = this.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, symbol);
            ResultSet resultSet = stmt.executeQuery();
            List<CurrencyPair> currencyPairsTmp = getCurrencyPairsFromResultSet(resultSet);
            currencyPairs.addAll(currencyPairsTmp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return currencyPairs;
    }

    private List<CurrencyPair> getCurrencyPairsFromResultSet(ResultSet rs) throws SQLException {
        List<CurrencyPair> currencyPairs = new ArrayList<>();
        while (rs.next()) {
            String symbol = rs.getString("symbol");
            Double price = rs.getDouble("price");
            Double ask = rs.getDouble("ask");
            Double bid = rs.getDouble("bid");
            Long timeStamp = rs.getLong("timestamp");

            currencyPairs.add(new CurrencyPair(symbol, price, ask, bid, timeStamp));
        }
        return currencyPairs;
    }

    public void insert(CurrencyPair currencyPair) {
        String sql = "INSERT INTO currency VALUES(?,?,?,?,?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, currencyPair.getSymbol());
            statement.setDouble(2, currencyPair.getPrice());
            statement.setDouble(3, currencyPair.getAsk());
            statement.setDouble(4, currencyPair.getBid());
            statement.setLong(5, currencyPair.getTimeStamp());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}