package com.thoughtworks.pos.dao.impl;

import com.thoughtworks.pos.dao.ItemDao;
import com.thoughtworks.pos.model.Category;
import com.thoughtworks.pos.model.Item;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemDaoImpl implements ItemDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Item> getItems() {
        String sql = "select * from items";

        return namedParameterJdbcTemplate.query(sql, new ItemMapper());
    }

    @Override
    public Item getItemByBarcode(String barcode) {
        String sql = "select * from items where barcode = :barcode";

        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("barcode", barcode);

        return namedParameterJdbcTemplate.query(sql, namedParameters, new ItemMapper()).get(0);
    }

    private static final class ItemMapper implements RowMapper<Item> {
        @Override
        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setBarcode(rs.getString("barcode"));
            item.setName(rs.getString("name"));
            item.setUnit(rs.getString("unit"));
            item.setPrice(rs.getDouble("price"));
            Category category = new Category();
            category.setId(rs.getInt("category"));
            item.setCategory(category);
            return item;
        }
    }
}
