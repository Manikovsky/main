package com.company.repository;

import com.company.model.Catalog;
import com.company.service.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Базовый (абстрактный) класс репозитория
 */

public abstract class BaseRepository {

    /** Поле - соединение с базой двнных */
    protected Connection connection = ConnectionFactory.getConnection();

    /** Функция инициальзации таблицы
     * соответствующего репозитория
     */
    public abstract void initializationTable();

    /**
     * Функция добавления записи
     * @param catalog - модель справочника
     */
    public abstract void addRecord(Catalog catalog);

    /**
     * Функция удаления записи
     * @param catalog - модель справочника
     */
    public abstract void deleteRecord(Catalog catalog);

    /**
     * Функция изменения записи
     * @param catalog - модель справочника
     */
    public abstract void changeRecord(Catalog catalog);

    /**
     * Функция поиска записи
     * @param catalog - модель справочника
     * @return Catalog - искомая модель справочника
     */
    public abstract Catalog findRecord(Catalog catalog);

    /**
     * Функция выполнения SQL запроса
     * @param sql - строковое представление запроса
     */
    protected void executeSqlStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
