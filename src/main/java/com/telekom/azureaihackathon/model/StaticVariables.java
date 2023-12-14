package com.telekom.azureaihackathon.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class StaticVariables {
    public static Query context = new Query(new ArrayList<>());
}
