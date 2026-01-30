package com.GymManagement.GymManagement.repository;

import com.GymManagement.GymManagement.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepository {

    public static final String INSERT_ITEM_QUERY = "INSERT INTO ITEM(id, name, quantity) VALUES(?, ?, ?)";
    public static final String GET_ITEM_QUERY = "SELECT * FROM ITEM";
    public static final String DELETE_ITEM_QUERY = "DELETE FROM ITEM WHERE ID=?";
    public static final String UPDATE_ITEM_QUERY = "UPDATE ITEM SET quantity=? WHERE id=?";
    public static final String GET_ITEM_BY_ID_QUERY = "SELECT * FROM ITEM WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveItem(Item item){
        jdbcTemplate.update(INSERT_ITEM_QUERY,
                item.getId(),
                item.getName(),
                item.getQuantity());
    }

    public List<Item> showItems(){
        return jdbcTemplate.query(GET_ITEM_QUERY,((rs, rowNum) -> {
            return new Item(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("quantity"));
        }));
    }

    public Item getItemById(int id){
        return jdbcTemplate.queryForObject(GET_ITEM_BY_ID_QUERY,
                new BeanPropertyRowMapper<>(Item.class), id);
    }

    public String deleteById(int id){
        jdbcTemplate.update(DELETE_ITEM_QUERY, id);
        return "Item deleted with id"+id;
    }

    public Item updateById(Item item){
        jdbcTemplate.update(UPDATE_ITEM_QUERY,
                item.getQuantity(),
                item.getId());

        return item;
    }
}
