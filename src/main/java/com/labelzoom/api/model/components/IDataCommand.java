package com.labelzoom.api.model.components;

public interface IDataCommand extends Cloneable
{
    String getLanguage();
    void setLanguage(String language);

    String getDataCommand();
    void setDataCommand(String dataCommand);
}
