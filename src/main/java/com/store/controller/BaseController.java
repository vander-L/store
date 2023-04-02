package com.store.controller;

import com.store.controller.ex.*;
import com.store.service.ex.*;
import com.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    //异常拦截器
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> res = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException)
            res.setState(4000);
        else if (e instanceof UsernameNotFoundException)
            res.setState(4001);
        else if (e instanceof PasswordNotMatchException)
            res.setState(4002);
        else if (e instanceof AddressCountLimitException)
            res.setState(4003);
        else if (e instanceof AddressNotFoundException)
            res.setState(4004);
        else if (e instanceof AccessDeniedException)
            res.setState(4005);
        else if (e instanceof ProductNotFoundException)
            res.setState(4006);
        else if (e instanceof CartNotFoundException)
            res.setState(4007);
        else if (e instanceof InsertException)
            res.setState(5000);
        else if (e instanceof UpdateException)
            res.setState(5001);
        else if (e instanceof DeleteException)
            res.setState(5002);
        else if (e instanceof FileEmptyException)
            res.setState(6000);
        else if (e instanceof FileSizeException)
            res.setState(6001);
        else if (e instanceof FileTypeException)
            res.setState(6002);
        else if (e instanceof FileStateException)
            res.setState(6003);
        else if (e instanceof FileUploadIoException)
            res.setState(6004);
        res.setMessage(e.getMessage());
        return res;
    }

    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录的用户的uid
     */
    protected final Integer getUidFromSession(HttpSession session){
        return (Integer) session.getAttribute("uid");
    }

    /**
     * 获取session对象中的username
     * @param session session对象
     * @return 当前登录用户的username
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

}
