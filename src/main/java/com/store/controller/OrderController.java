package com.store.controller;

import com.store.entity.Order;
import com.store.service.IOrderService;
import com.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.store.util.Const.OK;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        return new JsonResult<>(OK, orderService.create(aid, cids, getUidFromSession(session), getUsernameFromSession(session)));
    }
}
