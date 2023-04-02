package com.store.controller;

import com.store.controller.ex.FileEmptyException;
import com.store.controller.ex.FileSizeException;
import com.store.controller.ex.FileTypeException;
import com.store.controller.ex.FileUploadIoException;
import com.store.entity.District;
import com.store.entity.User;
import com.store.service.IDistrictService;
import com.store.service.IUserService;
import com.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.store.util.Const.*;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{
    @Autowired
    private IDistrictService districtService;

    @RequestMapping({"/", ""})
    public JsonResult<List<District>> getByParent(String parent){
        return new JsonResult<>(OK, districtService.getByParent(parent));
    }
 }
