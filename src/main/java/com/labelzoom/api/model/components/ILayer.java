package com.labelzoom.api.model.components;

import java.util.List;

public interface ILayer
{
    String getName();
    void setName(String name);

    List<IComponent> getElements();
    void setElements(List<IComponent> elements);
}
