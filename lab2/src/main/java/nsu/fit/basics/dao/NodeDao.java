package nsu.fit.basics.dao;

import nsu.fit.basics.database.DatabaseUtils;
import nsu.fit.basics.model.NodeModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class NodeDao {
    private static final String INSERT_TEMPLATE = "INSERT INTO node (id, latitude, longitude, user_name) " +
        "values (%d, %f, %f, %s);";

    private static final String INSERT_FOR_PREPARED_STATEMENT = "INSERT INTO node (id, latitude, longitude, user_name) " +
        "values (?, ?, ?, ?);";

    public void addNodeWithSimpleInsert(NodeModel node) throws SQLException {
        String insertStatement = String.format(INSERT_TEMPLATE,
            node.getId(), node.getLatitude(), node.getLongitude(), node.getUser());

        Connection connection = DatabaseUtils.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(insertStatement);
    }

    public void addNodeWithPreparedStatement(NodeModel node) throws SQLException {
        Connection connection = DatabaseUtils.getConnection();

        PreparedStatement statement = getPreparedStatementForNode(connection, node);

        statement.executeUpdate();
    }

    public void addNodeUsingBatch(NodeModel node) throws SQLException {
        Connection connection = DatabaseUtils.getConnection();

        PreparedStatement statement = getPreparedStatementForNode(connection, node);
        statement.executeBatch();
    }

    public void addNodesUsingBatch(List<NodeModel> nodes) throws SQLException {
        Connection connection = DatabaseUtils.getConnection();

        PreparedStatement statement = connection.prepareStatement(INSERT_FOR_PREPARED_STATEMENT);

        for (NodeModel node : nodes) {
            setNodeDataToPreparedStatement(statement, node);
            statement.addBatch();
        }

        statement.executeBatch();
    }

    private PreparedStatement getPreparedStatementForNode(Connection connection, NodeModel node) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_FOR_PREPARED_STATEMENT);
        setNodeDataToPreparedStatement(statement, node);
        return statement;
    }

    private void setNodeDataToPreparedStatement(PreparedStatement statement, NodeModel node) throws SQLException {
        statement.setLong(1, node.getId().longValue());
        statement.setDouble(2, node.getLatitude());
        statement.setDouble(3, node.getLongitude());
        statement.setString(4, node.getUser());
    }
}
