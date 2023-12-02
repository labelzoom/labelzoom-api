package com.labelzoom.api.model.components;

public interface IContainer extends IComponent
{
    Iterable<? extends IComponent> getChildren();
}
