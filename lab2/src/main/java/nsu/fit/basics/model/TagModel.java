package nsu.fit.basics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
public class TagModel {
    private String key;

    private String value;

    private BigInteger nodeId;
}
