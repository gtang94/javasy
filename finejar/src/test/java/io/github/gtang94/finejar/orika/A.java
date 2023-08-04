package io.github.gtang94.finejar.orika;

import lombok.Data;

import java.util.List;

@Data
public class A {
    private int id;
    private String type = "A";
    private List<B> bList;
}
