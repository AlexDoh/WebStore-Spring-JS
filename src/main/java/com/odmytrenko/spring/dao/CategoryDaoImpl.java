package com.odmytrenko.spring.dao;

import com.odmytrenko.spring.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CategoryDaoImpl(JdbcTemplate jt) {
        jdbcTemplate = jt;
        String createCategoriesTable = "CREATE TABLE IF NOT EXISTS CATEGORIES (" +
                " ID INT PRIMARY KEY AUTO_INCREMENT," +
                " NAME VARCHAR(30)" +
                ");";
        jdbcTemplate.execute(createCategoriesTable);
        String category1 = "MERGE INTO CATEGORIES (NAME, ID) VALUES('Shoes', 1);";
        String category2 = "MERGE INTO CATEGORIES (NAME, ID) VALUES('Dresses', 2);";
        String category3 = "MERGE INTO CATEGORIES (NAME, ID) VALUES('Pants', 3);";
        jdbcTemplate.execute(category1);
        jdbcTemplate.execute(category2);
        jdbcTemplate.execute(category3);
    }

    @Override
    public Set<Category> getAll() {
        String getAll = "SELECT ID, NAME FROM CATEGORIES;";
        List<Category> results = jdbcTemplate.query(getAll, (rs, rowNum) -> {
            Category category = new Category();
            category.setId(rs.getLong("ID"));
            category.setName(rs.getString("NAME"));
            return category;
        });
        return new HashSet<>(results);
    }

    @Override
    public Category create(Category category) {
        return null;
    }

    @Override
    public Category delete(Category category) {
        return null;
    }

    @Override
    public Category update(Category category) {
        return null;
    }

    @Override
    public Category findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT NAME FROM CATEGORIES WHERE ID = ?;",
                    new Object[]{id},
                    (rs, rowNum) -> {
                        Category result = new Category();
                        result.setId(id);
                        result.setName(rs.getString("NAME"));
                        return result;
                    }
            );
        } catch (Exception e) {
            throw new RuntimeException("Category doesn't exist!");
        }
    }
}
