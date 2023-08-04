package io.github.gtang94.finejar.orika;

import lombok.Data;

import java.util.List;

@Data
public class B {
    private int id;
    private String type = "B";
    private List<C> cList;
}
