package io.github.gtang94.finejar.orika;

import lombok.Data;

import java.util.List;

@Data
public class Y {
    private int id;
    private String type = "Y";
    private List<Z> zList;
}
