package nsu.fit.basics.dao;

import nsu.fit.basics.model.NodeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface NodeRepository extends JpaRepository<NodeModel, BigInteger> {
}
