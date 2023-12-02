package com.labelzoom.api.model.components;

import com.labelzoom.api.model.PageOrientation;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public interface ILabel
{
    String DEFAULT_LAYER_NAME = "background";

    int getWidth();
    void setWidth(int width);

    int getHeight();
    void setHeight(int height);

    boolean isHighRes();
    void setHighRes(boolean highRes);

    PageOrientation getOrientation();
    void setOrientation(PageOrientation orientation);

    List<ILayer> getLayers();
    void setLayers(List<ILayer> layers);

    IDataCommand getDataCommand();
    void setDataCommand(IDataCommand dataCommand);

    String getSchemaLocation();
    void setSchemaLocation(String schemaLocation);

    String getSchemaVersion();
    void setSchemaVersion(String schemaVersion);

    UUID getId();
    void setId(UUID id);

    List<IComponent> getElements();
    List<IComponent> getSortedElements();
    void setElements(List<IComponent> elements);

    void addElement(IComponent element);
}
