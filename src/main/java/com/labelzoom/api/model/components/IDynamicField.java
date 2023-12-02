package com.labelzoom.api.model.components;

import com.labelzoom.api.model.DataCloneable;

import java.util.regex.Pattern;

public interface IDynamicField extends DataCloneable
{
    Pattern SIMPLE_EXPRESSION_PATTERN = Pattern.compile("\"\\$\\{([A-Za-z0-9_]+)\\}\"");
    Pattern VARIABLE_EXPRESSION_PATTERN = Pattern.compile("\\$\\{([A-Za-z0-9_]+)\\}");
    Pattern VARIABLE_NAME_PATTERN = Pattern.compile("^([A-Za-z0-9_]+)$");

    void setExpression(String expression);
    String getExpression();

    void setFieldValue(String value);
    String getFieldValue();
}
