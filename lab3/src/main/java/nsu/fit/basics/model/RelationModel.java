package nsu.fit.basics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "relation")
public class RelationModel {
    @Id
    private BigInteger id;

    @Column(name = "user_name")
    private String user;

    private boolean isVisible;

    @Column
    @Convert(converter = TagConverter.class)
    private TagsModel tags;
}
