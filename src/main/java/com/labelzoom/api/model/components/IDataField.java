package com.labelzoom.api.model.components;

public interface IDataField
{
    void setFieldValue(String value);
    String getFieldValue();

    AComponent clone(boolean cloneData);
}
