package com.larinego;

import com.larinego.dao.entities.Product;
import com.larinego.dao.entities.User;
import com.larinego.services.OrderService;
import com.larinego.services.ProductService;
import com.larinego.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration("/beans-services.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Test
    public void TestProductgetById(){
        Product product1 = new Product();
        product1.setSupplier("Bosch");
        product1.setModel("Z50");
        product1.setPrice(250.20);
        productService.save(product1);

        Assert.assertEquals(product1, productService.getProductById(product1.getId()));
        Assert.assertEquals(product1, productService.findProductById(product1.getId()));

    }

    @Test
    public void TestProductgetDeleteById(){
        Product product1 = new Product();
        product1.setSupplier("Bosch");
        product1.setModel("Z50");
        product1.setPrice(250.20);
        productService.save(product1);
        productService.deleteById(product1.getId());

        Assert.assertEquals(null, productService.getProductById(product1.getId()));
        Assert.assertEquals(null, productService.findProductById(product1.getId()));

    }

    @Test
    public void TestProductgetPage(){
        Product product1 = new Product();
        product1.setSupplier("Bosch");
        product1.setModel("Z50");
        product1.setPrice(250.20);
        productService.save(product1);

        Product product2 = new Product();
        product2.setSupplier("Bosch");
        product2.setModel("Z100");
        product2.setPrice(270.40);
        productService.save(product2);

        List<Product> list = new ArrayList<>();

        Page<Product> productsByPage = productService.getProductsByPage(PageRequest.of(0, 2));
        List<Product> content = productsByPage.getContent();

        Assert.assertEquals(list, content);

    }


    @Test
    public void TestUserById(){
        User user1 = new User();
        user1.setRole("ADMIN");
        user1.setEmail("as@mail.ru");
        user1.setLogin("admin");
        user1.setPassword("passs");
        user1.setName("Alex");
        user1.setSurname("Larin");
        user1.setPhoneNumberFirst("375297047031");
        user1.setPhoneNumberSecond("375297047031");
        userService.save(user1);

        Assert.assertEquals(user1, userService.getUserById(user1.getUserId()));
        Assert.assertEquals(user1, userService.getUserByLogin(user1.getLogin()));


    }

    @Test
    public void TestUserDeleteById(){
        User user1 = new User();
        user1.setRole("ADMIN");
        user1.setEmail("as@mail.ru");
        user1.setLogin("admin");
        user1.setPassword("passs");
        user1.setName("Alex");
        user1.setSurname("Larin");
        user1.setPhoneNumberFirst("375297047031");
        user1.setPhoneNumberSecond("375297047031");
        userService.save(user1);

        Assert.assertEquals(user1, userService.getUserById(user1.getUserId()));
        Assert.assertEquals(user1, userService.getUserByLogin(user1.getLogin()));


    }







}
