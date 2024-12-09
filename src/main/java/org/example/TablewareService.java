package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для работы с сущностью Tableware.
 * Служит промежуточным слоем между контроллером и DAO.
 */
@Service
public class TablewareService {
    @Autowired
    private TablewareDAO dao;

    /**
     * Получение всех записей из таблицы посуды.
     *
     * @return Список всех объектов Tableware.
     */
    public List<Tableware> getAll() {
        return dao.getAll();
    }

    /**
     * Добавление новой записи о посуде.
     *
     * @param tableware Объект посуды, который будет добавлен в базу.
     */
    @Transactional
    public void add(Tableware tableware) {
        dao.add(tableware);
        if (tableware.getPriceRub() < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        if (tableware.getVolumeMl() < 0){
            throw new IllegalArgumentException("Объем не может быть отрицательный");
        }
    }

    /**
     * Обновление записи посуды по ID.
     *
     * @param id        Идентификатор посуды, которую нужно обновить.
     * @param tableware Обновленный объект посуды.
     */
    @Transactional
    public void update(int id, Tableware tableware) {
        dao.update(id, tableware);
        if (tableware.getPriceRub() < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        if (tableware.getVolumeMl() < 0){
            throw new IllegalArgumentException("Объем не может быть отрицательный");
        }
    }

    /**
     * Удаление записи посуды по ID.
     *
     * @param id Идентификатор посуды, которую нужно удалить.
     */
    public void delete(int id) {
        dao.delete(id);
    }

    /**
     * Поиск посуды по материалу.
     *
     * @param type тип для поиска.
     * @return Список посуды с указанным типом.
     */
    public List<Tableware> searchByType(String type) {
        return dao.searchByType(type);
    }
}
