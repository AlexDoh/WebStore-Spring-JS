package com.odmytrenko.spring.dao;

import com.odmytrenko.spring.model.Category;
import com.odmytrenko.spring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CategoryDao categoryDao;

    public ProductDaoImpl(JdbcTemplate jt) {
        jdbcTemplate = jt;
        String createProductsTable = "CREATE TABLE IF NOT EXISTS PRODUCTS (" +
                " ID INT PRIMARY KEY AUTO_INCREMENT," +
                " NAME VARCHAR(30)," +
                " DESCRIPTION VARCHAR(255)," +
                " CATEGORYID INT," +
                " FOREIGN KEY (CATEGORYID) REFERENCES CATEGORIES(ID)" +
                ");";
        jdbcTemplate.execute(createProductsTable);
        String product1 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Low shoe'," +
                " 'HighQualifiedProduct', 1, 1);";
        String product2 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Sandals'," +
                " 'MiddleQualifiedProduct', 1, 2);";
        String product3 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Boots'," +
                " 'LowQualifiedProduct', 1, 3);";
        String product4 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Shift'," +
                " 'HighQualifiedProduct', 2, 4);";
        String product5 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Doll'," +
                " 'MiddleQualifiedProduct', 2, 5);";
        String product6 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Kimono'," +
                " 'LowQualifiedProduct', 2, 6);";
        String product7 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Breeches'," +
                " 'HighQualifiedProduct', 3, 7);";
        String product8 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Trousers'," +
                " 'MiddleQualifiedProduct', 3, 8);";
        String product9 = "MERGE INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID, ID) VALUES('Drawers'," +
                " 'LowQualifiedProduct', 3, 9);";
        jdbcTemplate.execute(product1);
        jdbcTemplate.execute(product2);
        jdbcTemplate.execute(product3);
        jdbcTemplate.execute(product4);
        jdbcTemplate.execute(product5);
        jdbcTemplate.execute(product6);
        jdbcTemplate.execute(product7);
        jdbcTemplate.execute(product8);
        jdbcTemplate.execute(product9);
    }

    @Override
    public Set<Product> getAllByCategoryId(Long categoryId) {
        String getAll = "SELECT ID, NAME, DESCRIPTION FROM PRODUCTS WHERE CATEGORYID = ?;";
        List<Product> results = jdbcTemplate.query(getAll, new Object[]{categoryId}, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getLong("ID"));
            product.setName(rs.getString("NAME"));
            product.setDescription(rs.getString("DESCRIPTION"));
            Category category = new Category();
            category.setId(categoryId);
            product.setCategory(category);
            return product;
        });
        return new HashSet<>(results);
    }

    @Override
    public Product create(Product product) {
        String createQuery = "INSERT INTO PRODUCTS (NAME, DESCRIPTION, CATEGORYID) VALUES(?, ?, ?);";
        jdbcTemplate.update(createQuery,
                product.getName(),
                product.getDescription(),
                product.getCategory().getId()
        );
        return product;
    }

    @Override
    public Product delete(Product product) {
        String deleteQuery = "DELETE FROM PRODUCTS WHERE NAME = ? AND CATEGORYID = ?";
        jdbcTemplate.update(deleteQuery,
                product.getName(),
                product.getCategory().getId()
        );
        return product;
    }

    @Override
    public Product update(Product product) {
        String updateQuery = "UPDATE PRODUCTS SET DESCRIPTION = ?, CATEGORYID = ? WHERE NAME = ?";
        jdbcTemplate.update(updateQuery,
                product.getDescription(),
                product.getCategory().getId(),
                product.getName()
        );
        return product;
    }

    @Override
    public Product findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT CATEGORYID, NAME, DESCRIPTION FROM PRODUCTS WHERE ID = ?;",
                    new Object[]{id},
                    (rs, rowNum) -> {
                        Product result = new Product();
                        result.setId(id);
                        result.setName(rs.getString("NAME"));
                        result.setDescription(rs.getString("DESCRIPTION"));
                        Category category = new Category();
                        category.setId(rs.getLong("CATEGORYID"));
                        result.setCategory(category);
                        return result;
                    }
            );
        } catch (Exception e) {
            throw new RuntimeException("Category doesn't exist!");
        }
    }

    @Override
    public Set<Product> getAll() {
        String getAll = "SELECT ID, CATEGORYID, NAME, DESCRIPTION FROM PRODUCTS;";
        List<Product> results = jdbcTemplate.query(getAll, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getLong("ID"));
            product.setName(rs.getString("NAME"));
            product.setDescription(rs.getString("DESCRIPTION"));
            product.setCategory(categoryDao.findById(rs.getLong("CATEGORYID")));
            return product;
        });
        return new HashSet<>(results);
    }
}
