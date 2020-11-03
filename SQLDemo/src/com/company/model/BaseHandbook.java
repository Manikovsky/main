package com.company.model;

import java.util.Objects;

public class BaseHandbook {

    private long id;

    public BaseHandbook() {  }

    public BaseHandbook(long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseHandbook baseModel = (BaseHandbook) o;
        return id == baseModel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
