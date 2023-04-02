package com.store.controller;

import com.store.service.ICartService;
import com.store.util.JsonResult;
import com.store.vo.CartVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.store.util.Const.OK;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        cartService.addToCart(getUidFromSession(session), pid, amount, getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session) {
        return new JsonResult<>(OK, cartService.getVOByUid(getUidFromSession(session)));
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        return new JsonResult<Integer>(OK, cartService.addNum(cid, getUidFromSession(session), getUsernameFromSession(session)));
    }

    @RequestMapping("list")
    public JsonResult<List<CartVO>> getVOByCids(Integer[] cids, HttpSession session) {
        return new JsonResult<>(OK, cartService.getVOByCids(getUidFromSession(session), cids));
    }

}
