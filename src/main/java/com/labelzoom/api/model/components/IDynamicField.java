package com.labelzoom.api.model.components;

import java.util.regex.Pattern;

public interface IDynamicField extends Cloneable, IDataField
{
    Pattern SIMPLE_EXPRESSION_PATTERN = Pattern.compile("\"\\$\\{(\\w+)\\}\"");
    Pattern VARIABLE_EXPRESSION_PATTERN = Pattern.compile("\\$\\{(\\w+)\\}");
    Pattern VARIABLE_NAME_PATTERN = Pattern.compile("^(\\w+)$");

    void setExpression(String expression);
    String getExpression();
}
