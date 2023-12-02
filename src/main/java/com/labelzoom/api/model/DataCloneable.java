package com.labelzoom.api.model;

public interface DataCloneable extends Cloneable
{
    Object clone(boolean cloneData);
}
