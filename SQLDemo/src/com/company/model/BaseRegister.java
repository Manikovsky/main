package com.company.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;
@JsonAutoDetect
public class BaseRegister {

    private long id;

    public BaseRegister() {  }

    public BaseRegister(long id) {
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
        BaseRegister baseModel = (BaseRegister) o;
        return id == baseModel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
