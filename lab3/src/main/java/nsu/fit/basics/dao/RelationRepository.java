package nsu.fit.basics.dao;

import nsu.fit.basics.model.RelationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface RelationRepository extends JpaRepository<RelationModel, BigInteger> {
}
