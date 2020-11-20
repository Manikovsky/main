package com.company.service;

import com.company.model.Catalog;
import com.company.model.Column;
import com.company.model.Row;
import com.company.repository.RowRepository;
import com.company.repository.ValueRepository;

import java.util.List;

/**
 * Класс - сервис, осуществляющий операции
 * над данными в справочнике
 */
public class DataService {

    /** Поле - сервис, необходимый для получения информации
     * о структуре справочника*/
    private final CatalogService catalogService = new CatalogService();

    /** Репозиторий  строк*/
    private final RowRepository rowRepository = new RowRepository();

    /** Репозиторий  данных*/
    private final ValueRepository valueRepository = new ValueRepository();

    /** Создание таблиц для строк и данных*/
    public void initialize(){
        rowRepository.initializationTable();
        valueRepository.initializationTable();
    }

    /**
     * Функция добавления данных
     * @param  catalog - структура справочника
     */
    public void addRegister(Catalog catalog) {
        catalogService.getCatalogId(catalog);
        for(Row row : catalog.getRows()) {
            if(row.getId() <= 0) {
                rowRepository.addRecord(catalog);
                long rowId = rowRepository.findRecord
                        (new Catalog(null,0, null)).getRows().get(0).getId();
                row.setId(rowId);
            }
        }
        setColumnsIndex(catalog);
        valueRepository.addRecord(catalog);
    }

    /**
     * Функция удаления записи из справочника
     * @param  catalog - структура справочника
     */
    public void deleteRegister(Catalog catalog) {
        rowRepository.deleteRecord(catalog);
    }

    /**
     * Функция замены записи из справочника
     * @param  catalog - структура справочника
     */
    public void changeRegister(Catalog catalog) {
        valueRepository.deleteRecord(catalog);
        addRegister(catalog);
    }

    /**
     * Функция поиска необходимых строк
     * @param  catalog - структура справочника
     * @return Catalog - искомая структура справочника
     */
    public Catalog findRow(Catalog catalog) {
        catalogService.getCatalogId(catalog);
        setColumnsIndex(catalog);
        return rowRepository.findRecord(catalog);
    }

    /**
     * Функция поиска необходимых занчений
     * @param  catalog - структура справочника
     * @return Catalog - искомая структура справочника
     */
    public Catalog findRegister(Catalog catalog) {
        catalogService.getCatalogId(catalog);
        Catalog catalogWithRow = findRow(catalog);
        return valueRepository.findRecord(catalogWithRow);
    }

    /**
     * Функция получения сервиса для работы с настройками таблицы
     * @return CatalogService - сервис настройки таблицы
     */
    public CatalogService getCatalogService() {
        return catalogService;
    }

    /**
     * Функция установки номеров полей (столбцов)
     * @param  catalog - структура справочника
     */
    private void setColumnsIndex(Catalog catalog) {
        long columnIndex = catalog.getRows().get(0).getColumns().get(0).getColumnId();
        if(columnIndex <= 0) {
            List<Column> columnsWithId = catalogService.getColumns(catalog).getRows().get(0).getColumns();
            for(Row row : catalog.getRows()) {
                for(Column column : row.getColumns()) {
                    for(Column id : columnsWithId) {
                        if(column.getColumnName().equals(id.getColumnName())) {
                            column.setColumnId(id.getColumnId());
                        }
                    }
                }
            }
        }
    }

}
