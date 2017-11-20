package com.larinego.web.controllers;

import com.larinego.dao.entities.Product;
import com.larinego.services.ProductService;
import com.larinego.web.exceptions.ResourceNotFoundException;
import com.larinego.services.utils.BasketVO;
import com.larinego.web.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(path = "/{lang}/products")
public class ProductController {

    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    private ProductService productService;

    @Autowired
    BasketVO basketVO;

    private static final String MAIN = "locale/products";


    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String productsPage(ModelMap model, @PageableDefault(size = 3, sort = {"supplier"}) Pageable pageable, @ModelAttribute("flashPageNumber") String flashPageNumber) {

        if (!flashPageNumber.equals("")) {
            fillModel(model, PageRequest.of(Integer.valueOf(flashPageNumber), 3, new Sort("supplier")));
        } else {
            fillModel(model, pageable);
        }

        return MAIN;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProduct(ModelMap model, RedirectAttributes redirectAttributes, Product product, Integer pageNumber) {

        if (product != null) {
            if (product.getId() == null) {
                productService.save(product);
            }
        }
        if (pageNumber != null) {
            redirectAttributes.addFlashAttribute("flashPageNumber", pageNumber.toString());
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProduct(ModelMap model, RedirectAttributes redirectAttributes, Product product, Integer pageNumber) {
        if (product != null) {
            if (product.getId() != null) {
                productService.save(product);
            }
        }
        if (pageNumber != null) {
            redirectAttributes.addFlashAttribute("flashPageNumber", pageNumber.toString());
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteProduct(ModelMap model, RedirectAttributes redirectAttributes, Product product, Integer pageNumber) {
        if (product != null) {
            if (product.getId() != null) {
                productService.deleteById(product.getId());
            }
        }
        if (pageNumber != null) {
            redirectAttributes.addFlashAttribute("flashPageNumber", pageNumber.toString());
        }

        return "redirect:/";

    }

    @RequestMapping(value = "/incrementProductInBasket")
    public Integer incrementProductInBasket(@RequestBody Long productJsonId) {

        Integer currentQuantiy = 0;
        Product product = productService.getProductById(productJsonId);


        if(product != null) {
            currentQuantiy = basketVO.incrementProductInBasket(product);
        }

        return currentQuantiy;
    }

    @RequestMapping(value = "/decrementProductInBasket")
    public @ResponseBody Integer decrementProductInBasket(@RequestBody Long productJsonId) {

        Integer currentQuantiy = 0;
        Product product = productService.getProductById(productJsonId);
        if(product != null) {
            currentQuantiy = basketVO.decrementProductInBasket(product);
        }

        return currentQuantiy;
    }

    private void fillModel(ModelMap model, Pageable pageable) {

        Page<Product> productsPage = productService.getProductsByPage(pageable);
        List<Product> products = productsPage.getContent();
        model.addAttribute("basket", basketVO);
        model.addAttribute("totalPages", productsPage.getTotalPages());
        model.addAttribute("products", products);
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

