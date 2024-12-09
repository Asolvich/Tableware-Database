package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Класс для взаимодействия с базой данных, выполняет CRUD операции с таблицей посуды.
 */
@Repository
public class TablewareDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Получение всех записей из таблицы посуды.
     *
     * @return Список всех объектов Tableware из базы данных.
     */
    public List<Tableware> getAll() {
        String sql = "SELECT * FROM tableware";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tableware.class));
    }

    /**
     * Добавление новой посуды в таблицу.
     *
     * @param tableware Объект посуды, который будет добавлен в таблицу.
     */
    public void add(Tableware tableware) {
        String sql = "INSERT INTO tableware (name, material, type, volume_ml, price_rub) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, tableware.getName(), tableware.getMaterial(), tableware.getType(), tableware.getVolumeMl(), tableware.getPriceRub());
    }

    /**
     * Обновление записи посуды по ID.
     *
     * @param id        Идентификатор посуды, которую нужно обновить.
     * @param tableware Обновленный объект посуды.
     */
    public void update(int id, Tableware tableware) {
        String sql = "UPDATE tableware SET name = ?, material = ?, type = ?, volume_ml = ?, price_rub = ? WHERE id = ?";
        jdbcTemplate.update(sql, tableware.getName(), tableware.getMaterial(), tableware.getType(), tableware.getVolumeMl(), tableware.getPriceRub(), id);
    }

    /**
     * Удаление записи посуды по ID.
     *
     * @param id Идентификатор посуды, которую нужно удалить.
     */
    public void delete(int id) {
        String sql = "DELETE FROM tableware WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Поиск посуды по материалу.
     *
     * @param type Тип, по которому будет выполнен поиск.
     * @return Список объектов Tableware, которые соответствуют заданному типу.
     */
    public List<Tableware> searchByType(String type) {
        String sql = "SELECT * FROM tableware WHERE type = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Tableware.class), type);
    }
}
