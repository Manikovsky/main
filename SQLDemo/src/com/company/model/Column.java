package com.company.model;

import java.util.Objects;

/**
 * Класс - поле (столбец) содержащий информацию о конкретном поле:
 * номер, имя, тип данных и сами данныме
 */
public class Column {

    /** Номер  поля*/
    private long columnId;

    /** Имя  поля*/
    private final String columnName;

    /** Тип данных в  поле*/
    private final String columnType;

    /** Данные*/
    private final Object columnData;

    /** Конструктор поля
     * @param columnId - номер поля
     * @param columnName - имя поля
     * @param columnType - тип данных в поле
     * @param columnData - данные
     * */
    public Column(long columnId, String columnName, String columnType, Object columnData) {
        this.columnId = columnId;
        this.columnName = columnName;
        this.columnType = columnType;
        this.columnData = columnData;
    }

    /**
     * Функция получения номера поля
     * @return long
     */
    public long getColumnId() {
        return columnId;
    }

    /**
     * Функция получения наименования поля
     * @return String
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Функция получения типа данных поля
     * @return String
     */
    public String getColumnType() {
        return columnType;
    }

    /**
     * Функция получения данных поля
     * @return String
     */
    public Object getColumnData() {
        return columnData;
    }

    /**
     * Функция установки номера поля
     * @param columnId - номер поля
     */
    public void setColumnId(long columnId) {
        this.columnId = columnId;
    }

    /**
     * Функция определения эквивалентности двух объектов
     * @param o - сравниваемый объект
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Column)) return false;
        Column column = (Column) o;
        return getColumnId() == column.getColumnId() &&
                Objects.equals(getColumnName(), column.getColumnName()) &&
                Objects.equals(getColumnType(), column.getColumnType()) &&
                Objects.equals(getColumnData(), column.getColumnData());
    }

    /**
     * Функция определения хэш кода объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(getColumnId(), getColumnName(), getColumnType(), getColumnData());
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnId=" + columnId +
                ", columnName='" + columnName + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnData='" + columnData + '\'' +
                '}';
    }
}
