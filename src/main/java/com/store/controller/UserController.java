package com.store.controller;

import com.store.controller.ex.*;
import com.store.entity.User;
import com.store.service.IUserService;
import com.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.store.util.Const.*;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    @RequestMapping("register")
    public JsonResult<Void> register(User user){
        JsonResult<Void> res = new JsonResult<>();
        userService.register(user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User user = userService.login(username, password);
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", user.getUsername());
        return new JsonResult<>(OK, user);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session){
        userService.changePassword(getUidFromSession(session), getUsernameFromSession(session), oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid_in_session")
    public JsonResult<User> getBySession(HttpSession session){
        return new JsonResult<>(OK, userService.findByUid(getUidFromSession(session)));
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session){
        userService.changeInfo(getUidFromSession(session), getUsernameFromSession(session),user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file){
        if (file.isEmpty()) throw new FileEmptyException("文件为空");
        if (file.getSize() > AVATAR_MAXSIZE) throw new FileSizeException("文件大小超出限制");
        if (!AVATAR_TYPE.contains(file.getContentType())) throw new FileTypeException("文件类型不支持");
        String parent = session.getServletContext().getRealPath("upload");
        File dir = new File(parent);
        if (!dir.exists()) dir.mkdirs();
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString().toUpperCase() +suffix;
        try {
            file.transferTo(new File(dir, filename));
        } catch (IOException e) {
            throw new FileUploadIoException("文件读写异常");
        }
        String avatar = "/upload/" + filename;
        userService.changeAvatar(getUidFromSession(session), getUsernameFromSession(session), avatar);
        return new JsonResult<>(OK, avatar);
    }
 }
