package org.gaurav.utils;

import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.stream.Collectors;

public class DataTableConverters {

    public static List<String> asListOfStrings(DataTable dataTable){

        return dataTable.asLists().stream().flatMap(x->x.stream()).collect(Collectors.toList());

    }
}
