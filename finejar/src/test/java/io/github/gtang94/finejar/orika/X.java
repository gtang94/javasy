package io.github.gtang94.finejar.orika;

import lombok.Data;

import java.util.List;

@Data
public class X {
    private int id;
    private String type = "X";
    private List<Y> yList;
}
