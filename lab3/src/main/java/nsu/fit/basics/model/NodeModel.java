package nsu.fit.basics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "node")
public class NodeModel {
    @Id
    @GeneratedValue(generator = "node_id_seq")
    private BigInteger id;

    private double latitude;

    private double longitude;

    @Column(name = "user_name")
    private String user;

    @Column
    @Convert(converter = TagConverter.class)
    private TagsModel tags;
}

