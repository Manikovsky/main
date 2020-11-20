package com.company.model;

import java.util.List;
import java.util.Objects;

/**
 * Класс - строка содержащий информацию о конкретной строке:
 * номер, перечень полей (столбцов) с данными
 *
 */
public class Row {

    /** Номер  строки*/
    private long id;

    /** Перечень полей (столбцов) с данными*/
    private List<Column> columns;

    /** Конструктор строки
     * @param id - номер строки
     * @param columns - перечень полей
     * */
    public Row(long id, List<Column> columns) {
        this.id = id;
        this.columns = columns;
    }

    /**
     * Функция получения номера строки
     * @return long
     */
    public long getId() {
        return id;
    }

    /**
     * Функция получения переченя полей строки
     * @return List<Column>
     */
    public List<Column> getColumns() {
        return columns;
    }

    /**
     * Функция установки номера строки
     * @param id - номер справочника
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Функция установки перечня полей (столбцов) строки
     * @param columns - номер справочника
     */
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    /**
     * Функция определения эквивалентности двух объектов
     * @param o - сравниваемый объект
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Row)) return false;
        Row row = (Row) o;
        return getId() == row.getId() &&
                Objects.equals(getColumns(), row.getColumns());
    }


    /**
     * Функция определения хэш кода объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getColumns());
    }

    @Override
    public String toString() {
        return "Row{" +
                "id=" + id +
                ", columns=" + columns +
                '}';
    }
}
