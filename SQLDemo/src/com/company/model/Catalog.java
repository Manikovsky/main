package com.company.model;

import java.util.List;
import java.util.Objects;

/**
 * Класс - справочник содержащий информацию о конкретном справочнике:
 * имя, номер, перечень строк с данными
 */
public class Catalog {

    /** Наименование  справочника*/
    private final String tableName;

    /** Номер  справочника*/
    private long tableId;

    /** Перечень строк с данными*/
    private List<Row> rows;

    /** Конструктор справочника
     * @param tableName - имя справочника
     * @param tableId - номер справочника
     * @param rows - перечень строк справочника с данными
     * */
    public Catalog(String tableName, long tableId, List<Row> rows) {
        this.tableName = tableName;
        this.tableId = tableId;
        this.rows = rows;
    }

    /**
     * Функция получения наименования справочника
     * @return String
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Функция получения номера справочника
     * @return long
     */
    public long getTableId() {
        return tableId;
    }

    /**
     * Функция получения переченя строк справочника с данными
     * @return List<Row>
     */
    public List<Row> getRows() {
        return rows;
    }

    /**
     * Функция установки номера справочника
     * @param tableId - номер справочника
     */
    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    /**
     * Функция установки перечня строк справочника с данными
     * @param rows - перечень строк с данными
     */
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    /**
     * Функция определения эквивалентности двух объектов
     * @param o - сравниваемый объект
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Catalog)) return false;
        Catalog catalog = (Catalog) o;
        return getTableId() == catalog.getTableId() &&
                Objects.equals(getTableName(), catalog.getTableName()) &&
                Objects.equals(getRows(), catalog.getRows());
    }

    /**
     * Функция определения хэш кода объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(getTableName(), getTableId(), getRows());
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "tableName='" + tableName + '\'' +
                ", tableId=" + tableId +
                ", rows=" + rows +
                '}';
    }
}
