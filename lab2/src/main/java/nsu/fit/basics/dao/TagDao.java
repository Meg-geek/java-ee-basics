package nsu.fit.basics.dao;

import nsu.fit.basics.database.DatabaseUtils;
import nsu.fit.basics.model.TagModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TagDao {
    private static final String INSERT_TEMPLATE = "INSERT INTO tag (key, value, node_id) " +
        "values (%s, %s, %d);";

    private static final String INSERT_FOR_PREPARED_STATEMENT = "INSERT INTO tag (key, value, node_id) " +
        "values (?, ?, ?);";

    public void addTagsWithSimpleInsert(List<TagModel> tags) throws SQLException {
        Connection connection = DatabaseUtils.getConnection();

        for (TagModel tag : tags) {
            String insertStatement = String.format(INSERT_TEMPLATE,
                tag.getKey(), tag.getValue(), tag.getNodeId());
            Statement statement = connection.createStatement();
            statement.execute(insertStatement);
        }
    }

    public void addTagsWithPreparedStatement(List<TagModel> tags) throws SQLException {
        Connection connection = DatabaseUtils.getConnection();

        for (TagModel tag : tags) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FOR_PREPARED_STATEMENT);
            setTagDataForPreparedStatement(preparedStatement, tag);
            preparedStatement.execute();
        }
    }

    public void addTagsUsingBatch(List<TagModel> tags) throws SQLException {
        Connection connection = DatabaseUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FOR_PREPARED_STATEMENT);

        for (TagModel tag : tags) {
            setTagDataForPreparedStatement(preparedStatement, tag);
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
    }

    private void setTagDataForPreparedStatement(PreparedStatement preparedStatement, TagModel tag)
        throws SQLException {
        preparedStatement.setString(1, tag.getKey());
        preparedStatement.setString(2, tag.getValue());
        preparedStatement.setLong(3, tag.getNodeId().longValue());
    }
}
