package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    Connection connect;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection connect) {
        this.connect = connect;
    }

    private void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("db/liquibase.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver"));
            connect = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("login"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Item add(Item item) {
        Timestamp timestamp = Timestamp.valueOf(item.getCreated());
        try (PreparedStatement statement = connect.prepareStatement("insert into items"
                + " (name, created) values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, timestamp);
            statement.execute();
            try (ResultSet key = statement.getGeneratedKeys()) {
                if (key.next()) {
                    item.setId(key.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean validate = false;
        try (PreparedStatement statement = connect.prepareStatement("update items set name = ? where id = ?")) {
            statement.setString(1, item.getName());
            statement.setInt(2, id);
            validate = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return validate;
    }

    @Override
    public boolean delete(int id) {
        boolean validate = false;
        try (PreparedStatement statement = connect.prepareStatement("delete from items where id = ?")) {
            statement.setInt(1, id);
            validate = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return validate;
    }

    @Override
    public List<Item> findAll() {
        List<Item> resultList = new ArrayList<>();
        try (PreparedStatement statement = connect.prepareStatement("select * from items")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultList.add(createItem(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private Item createItem(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getInt(1));
        item.setName(resultSet.getString(2));
        item.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
        return item;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> resultList = new ArrayList<>();
        try (PreparedStatement statement = connect.prepareStatement("select * from items where name = ?")) {
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultList.add(createItem(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public Item findById(int id) {
        Item item = new Item();
        try (PreparedStatement statement = connect.prepareStatement("select * from items where id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    item = createItem(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}