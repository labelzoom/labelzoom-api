package com.labelzoom.api.model.components;

import java.util.regex.Pattern;

public interface IDynamicField extends Cloneable, IDataField
{
    Pattern SIMPLE_EXPRESSION_PATTERN = Pattern.compile("\"\\$\\{([A-Za-z0-9_]+)\\}\"");
    Pattern VARIABLE_EXPRESSION_PATTERN = Pattern.compile("\\$\\{([A-Za-z0-9_]+)\\}");
    Pattern VARIABLE_NAME_PATTERN = Pattern.compile("^([A-Za-z0-9_]+)$");

    void setExpression(String expression);
    String getExpression();
}
