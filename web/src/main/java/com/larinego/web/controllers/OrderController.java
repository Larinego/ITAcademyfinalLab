package com.larinego.web.controllers;

import com.larinego.dao.entities.Item;
import com.larinego.dao.entities.Product;
import com.larinego.dao.entities.User;
import com.larinego.dao.entities.UserOrder;
import com.larinego.services.OrderService;
import com.larinego.services.ProductService;
import com.larinego.services.UserService;
import com.larinego.services.utils.BasketVO;
import com.larinego.web.exceptions.ResourceNotFoundException;
import com.larinego.web.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping(path = "/{lang}/orders")
public class OrderController {

    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    BasketVO basketVO;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getOrders(Model model){
        return "locale/loginsasa";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addOrder(Model model){

        UserOrder userOrder = new UserOrder();
        List<Item> items = userOrder.getItems();
        Double total = 0.0;
        for (Map.Entry<Product, AtomicInteger> entry : basketVO.getBasket().entrySet()) {
            Item item = new Item();
            item.setProduct(entry.getKey());
            item.setQuantity(entry.getValue().intValue());
            item.setSelledPrice(entry.getKey().getPrice());
            items.add(item);
            total += entry.getValue().intValue()*entry.getKey().getPrice();
        }
        userOrder.setTotal(total);
        orderService.save(userOrder);

        return "redirect:/";
    }

    @RequestMapping(value = {"/",""}, method = RequestMethod.GET)
    public String getUserOrders(ModelMap model, @PageableDefault(size = 3, sort = {"date"}) Pageable pageable){

        fillModel(model, pageable);

        return "locale/orders";
    }

    private void fillModel(ModelMap model, Pageable pageable) {

        User user = userService.getCurrentUser();

        if (user != null){
            Page<UserOrder> pageUserOrders = orderService.findByUser(user, pageable);
            List<UserOrder> userOrders = pageUserOrders.getContent();
            model.addAttribute("totalPages", pageUserOrders.getTotalPages());
            model.addAttribute("userOrders", userOrders);
        }
    }

    @ModelAttribute(name = "locale")
    void setLocale(@PathVariable(name = "lang") String lang, HttpServletRequest request,
                   HttpServletResponse response) {
        Locale locale;
        if (WebUtils.isLocaleValid(lang)) {
            locale = StringUtils.parseLocaleString(lang);
        } else {
            throw new ResourceNotFoundException("locale url " + lang + " is not supported");
        }
        localeResolver.setLocale(request, response, locale);
    }

}
