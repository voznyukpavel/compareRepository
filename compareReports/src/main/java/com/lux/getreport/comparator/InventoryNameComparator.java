package com.lux.getreport.comparator;

import java.util.Comparator;

import com.lux.getreport.model.InventoryReport;

public class InventoryNameComparator implements Comparator<InventoryReport> {

    @Override
    public int compare(InventoryReport o1, InventoryReport o2) {
        return o1.getComponentName().compareTo(o2.getComponentName());
    }
}