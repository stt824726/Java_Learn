package com.stt.ownSpring.myioc.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PropertyValues {

    private final List<PropertyValue> propertyValues = new CopyOnWriteArrayList<>();

    public PropertyValues(){

    }

    public void addPropertyValue(PropertyValue propertyValue){
        this.propertyValues.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValues(){
        return this.propertyValues;
    }
}
