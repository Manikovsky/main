package com.company.service;

import com.company.model.Catalog;
import com.company.model.Column;
import com.company.model.Row;
import com.company.repository.CatalogRepository;
import com.company.repository.ColumnRepository;


import java.util.List;

/**
 * Класс - сервис, необходимый для осуществления
 * редактирования структуры справочника
 */
public class CatalogService {

    /** Репозиторий  справочника*/
    private final CatalogRepository catalogRepository = new CatalogRepository();

    /** Репозиторий  поля (столбца)*/
    private final ColumnRepository columnRepository = new ColumnRepository();

    /** Создание таблиц для справочников и полей справочников*/
    public void initialize() {
        catalogRepository.initializationTable();
        columnRepository.initializationTable();
    }

    /**
     * Функция добавления справочника
     * @param  catalog - структура справочника
     */
    public void addCatalog(Catalog catalog) {
        catalogRepository.addRecord(catalog);
        getCatalogId(catalog);
        List<Row> rowList = catalog.getRows();
        if(rowList != null && !rowList.isEmpty()) {
            List<Column> columnList = catalog.getRows().get(0).getColumns();
            if(columnList != null) addColumns(catalog);
        }
    }

    /**
     * Функция добавления полей (столбцов) в указанный справочник
     * @param  catalog - структура справочника
     */
    public void addColumns(Catalog catalog) {
        getCatalogId(catalog);
        columnRepository.addRecord(catalog);
    }

    /**
     * Функция изменения наименования указанного справочника
     * @param  catalog - структура справочника
     */
    public void changeCatalogName(Catalog catalog) {
        catalogRepository.changeRecord(catalog);
    }

    /**
     * Функция удаления указанного справочника
     * @param  catalog - структура справочника
     */
    public void deleteCatalog(Catalog catalog) {
        catalogRepository.deleteRecord(catalog);
    }

    /**
     * Функция установки номера указанного справочника
     * @param  catalog - структура справочника
     */
    public void getCatalogId(Catalog catalog) {
        if(catalog.getTableId() <= 0) {
            long id = catalogRepository.findRecord(catalog).getTableId();
            catalog.setTableId(id);
        }
    }
    /**
     * Функция удаления указанного поля (столбца)
     * @param  catalog - структура справочника
     */
    public void deleteColumn(Catalog catalog) {
        getCatalogId(catalog);
        columnRepository.deleteRecord(catalog);
    }

    /**
     * Функция замены указанного поля
     * @param  catalog - структура справочника
     */
    public void changeColumn(Catalog catalog) {
        getCatalogId(catalog);
        columnRepository.changeRecord(catalog);
    }

    /**
     * Функция получения полной информации о указанных полях
     * @param  catalog - структура справочника
     * @return Catalog - искомая структура справочника
     */
    public Catalog getColumns(Catalog catalog) {
        getCatalogId(catalog);
        return columnRepository.findRecord(catalog);
    }

}
