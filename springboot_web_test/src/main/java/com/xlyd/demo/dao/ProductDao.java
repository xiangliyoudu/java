package com.xlyd.demo.dao;

import com.xlyd.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ProductDao {

    @Autowired
    JdbcTemplate template;

    public List<Product> findAll() {
        List<Product> prods = new ArrayList<>();
        String sql = "select * from product";
        RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
        prods = template.query(sql, rowMapper);
        return prods;
    }
}






