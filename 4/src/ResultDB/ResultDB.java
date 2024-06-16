package ResultDB;

public class ResultDB {
    private Connection connection;

    public ResultDB(String connectionString) {        try {
        this.connection = DriverManager.getConnection(connectionString);
    } catch (SQLException e) {
        throw new RuntimeException("Failed to connect to the database", e);
    }
    }

    public void storeResult(Result result) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO results (id, data) VALUES (?, ?)")) {
            statement.setString(1, result.getId());
            statement.setString(2, result.getData());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to store result in the database", e);
        }
    }

    public Result loadResult(String id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT data FROM results WHERE id = ?")) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String data = resultSet.getString("data");
                    return new Result(id, data);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load result from the database", e);
        }
        return null;
    }

    public void exportResult(Result result, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(result.getData());
        } catch (IOException e) {
            throw new RuntimeException("Failed to export result to file", e);
        }
    }
}
