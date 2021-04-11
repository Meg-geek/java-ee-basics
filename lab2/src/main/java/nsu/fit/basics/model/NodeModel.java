package nsu.fit.basics.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@Builder
public class NodeModel {
    private BigInteger id;

    private double latitude;

    private double longitude;

    private String user;

    private List<TagModel> tags;
}
