package com.store.controller;

import com.store.entity.Address;
import com.store.entity.Product;
import com.store.service.IAddressService;
import com.store.service.IProductService;
import com.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.store.util.Const.OK;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList(){
        return new JsonResult<>(OK, productService.findHotList());
    }

    @RequestMapping("details/{id}")
    public JsonResult<Product> getById(@PathVariable("id") Integer id) {
        Product data = productService.findById(id);
        return new JsonResult<>(OK, data);
    }
 }
