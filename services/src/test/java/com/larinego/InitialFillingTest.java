package com.larinego;

import com.larinego.dao.entities.Item;
import com.larinego.dao.entities.Product;
import com.larinego.dao.entities.User;
import com.larinego.dao.entities.UserOrder;
import com.larinego.dao.repositories.ProductRepository;
import com.larinego.dao.repositories.UserRepository;
import com.larinego.services.OrderService;
import com.larinego.services.ProductService;
import com.larinego.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration("/beans-services.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class InitialFillingTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Test
    public void initialFilling(){
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
        Product product3 = new Product();
        product3.setSupplier("Bosch");
        product3.setModel("Z150");
        product3.setPrice(320.00);
        productService.save(product3);
        Product product4 = new Product();
        product4.setSupplier("Makita");
        product4.setModel("N12");
        product4.setPrice(140.20);
        productService.save(product4);
        Product product5 = new Product();
        product5.setSupplier("Makita");
        product5.setModel("N15");
        product5.setPrice(250.20);
        productService.save(product5);
        Product product6 = new Product();
        product6.setSupplier("Makita");
        product6.setModel("N20");
        product6.setPrice(300.00);
        productService.save(product6);
        Product product7 = new Product();
        product7.setSupplier("Scarlett");
        product7.setModel("125-12-80");
        product7.setPrice(101.21);
        productService.save(product7);
        System.out.println(productService.getProductsByPage(PageRequest.of(0,100)).getContent());
        //System.out.println(productService.getProductById(1L));

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

        User user2 = new User();
        user2.setRole("USER");
        user2.setEmail("asd@mail.ru");
        user2.setLogin("userr");
        user2.setPassword("passs");
        user2.setName("Alex");
        user2.setSurname("Larin");
        user2.setPhoneNumberFirst("375297047031");
        user2.setPhoneNumberSecond("375297047031");
        userService.save(user2);

        User user3 = new User();
        user3.setRole("USER");
        user3.setEmail("asd@mail.ru");
        user3.setLogin("alexx");
        user3.setPassword("passs");
        user3.setName("Alex");
        user3.setSurname("Larin");
        user3.setPhoneNumberFirst("375297047031");
        user3.setPhoneNumberSecond("375297047031");
        userService.save(user3);

        UserOrder order1 = new UserOrder();
        List<Item> items1 = order1.getItems();
        Double total1 = 0.0;

        Item item1 = new Item();
        item1.setProduct(product1);
        item1.setQuantity(4);
        item1.setSelledPrice(88.0);
        total1 += item1.getQuantity() * item1.getSelledPrice();
        item1.setUserOrder(order1);
        items1.add(item1);


        Item item2 = new Item();
        item2.setProduct(product3);
        item2.setQuantity(3);
        item2.setSelledPrice(206.7);
        total1 += item2.getQuantity() * item2.getSelledPrice();
        items1.add(item2);
        item2.setUserOrder(order1);

        order1.setTotal(total1);
        order1.setUser(user2);

        orderService.save(order1);

        UserOrder order2 = new UserOrder();
        List<Item> items2 = order2.getItems();
        Double total2 = 0.0;

        Item item3 = new Item();
        item3.setProduct(product5);
        item3.setQuantity(12);
        item3.setSelledPrice(55.13);
        total2 += item3.getQuantity() * item3.getSelledPrice();
        item3.setUserOrder(order2);
        items2.add(item3);

        order2.setTotal(total2);
        order2.setUser(user2);

        orderService.save(order2);

        UserOrder order3 = new UserOrder();
        List<Item> items3 = order3.getItems();
        Double total3 = 0.0;

        Item item5 = new Item();
        item5.setProduct(product7);
        item5.setQuantity(12);
        item5.setSelledPrice(55.13);
        total3 += item5.getQuantity() * item5.getSelledPrice();
        item5.setUserOrder(order3);
        items3.add(item5);

        order3.setTotal(total3);
        order3.setUser(user3);

        orderService.save(order3);







    }
}
