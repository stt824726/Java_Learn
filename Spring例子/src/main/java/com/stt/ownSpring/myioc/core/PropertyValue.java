package com.stt.ownSpring.myioc.core;


import lombok.Data;
import lombok.Getter;

@Data
public class PropertyValue {

    private final String key;

    private final Object value;

    public PropertyValue(String key,String value){
        this.key = key;
        this.value = value;
    }
}
