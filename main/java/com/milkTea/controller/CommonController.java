package com.milkTea.controller;



import com.milkTea.util.AjaxResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/api")
public class CommonController {

    private static final String FILEPATH = "/profile";

    @Value("${file.path}")
    private String filePath;

    /**
     * 通用上传
     * @param file 文件
     * @return 文件路径
     */
    @PostMapping("/upload")
    public AjaxResult upload(@RequestBody MultipartFile file){
        if(file.isEmpty()){
            return AjaxResult.error("文件不能为空!");
        }
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String fileName = new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/" +UUID.randomUUID() + "." + suffix;
        File f = new File(filePath + "/" + fileName);
        try{
            if(!f.getParentFile().exists()){
                f.getParentFile().mkdirs();
            }
            file.transferTo(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件夹创建失败！");
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败！");
        }
        return AjaxResult.success("上传成功",FILEPATH + "/" + fileName);
    }
}
