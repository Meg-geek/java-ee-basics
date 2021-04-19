package nsu.fit.basics.dao;

import nsu.fit.basics.model.NodeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface NodeRepository extends JpaRepository<NodeModel, BigInteger> {
    @Query(value = "select * from " +
        "( select (point(longitude, latitude)<@>point(:lon, :lat))*1609 as dist,  * from node) node_dist " +
        "where dist <= :r order by dist;", nativeQuery = true)
    List<NodeModel> getNearestNodesInRadius(@Param("lat") double latitude,
                                            @Param("lon") double longitude,
                                            @Param("r") double radius);
}
