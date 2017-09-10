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

    @Autowired
    private ProductDao productDao;

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
            category.setProductList(productDao.getAllByCategoryId(category.getId()));
            return category;
        });
        return new HashSet<>(results);
    }

    @Override
    public Category create(Category category) {
        String categoryName = category.getName();
        if (doesCategoryExist(categoryName)) {
            throw new RuntimeException("Such category is already exist");
        }
        String createCategoryQuery = "INSERT INTO CATEGORIES (NAME) VALUES(?);";
        jdbcTemplate.update(createCategoryQuery, categoryName);
        return category;
    }

    @Override
    public Category delete(Category category) {
        String categoryName = category.getName();
        if (!doesCategoryExist(categoryName)) {
            throw new RuntimeException("There is no such category");
        }
        String deleteProductsInCategoryQuery = "DELETE FROM PRODUCTS WHERE CATEGORYID = (SELECT ID FROM CATEGORIES" +
                " WHERE NAME = ?);";
        String deleteCategoryQuery = "DELETE FROM CATEGORIES WHERE NAME = ?;";
        jdbcTemplate.update(deleteProductsInCategoryQuery, categoryName);
        jdbcTemplate.update(deleteCategoryQuery, categoryName);
        return category;
    }

    @Override
    public Category update(Category category) {
        throw new RuntimeException("Update category through product update procedure");
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

    private boolean doesCategoryExist(String categoryName) {
        return jdbcTemplate.queryForRowSet("SELECT * FROM CATEGORIES WHERE NAME = ?;", categoryName).next();
    }
}
