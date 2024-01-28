package com.demotechreport.demotechreportform.enums;

public enum Vehicle {
    SPRINTER_01("SPRINTER 01"), SPRINTER_02("SPRINTER 02"), SPRINTER_04("SPRINTER 04"), SPRINTER_05("SPRINTER 05"), ASTRO("ASTRO"), HINO_BOX_20_FT("HINO BOX 20 FT"), CONCRETE_TRUCK_16FT("CONCRETE TRUCK 16FT"), PERSONAL("PERSONAL");

    private String value;

    Vehicle(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
