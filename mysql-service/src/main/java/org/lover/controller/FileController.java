package org.lover.controller;

import cn.hutool.core.util.IdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.lover.component.FileManager;
import org.lover.exception.BizException;
import org.lover.exception.CommonEnum;
import org.lover.exception.ResultBody;
import org.lover.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementServerProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "FileController")
@RestController
@RequestMapping("file")
@Slf4j
public class FileController {
    @Autowired
    private FileManager fileManager;


    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public ResultBody upload(MultipartFile file)
    {
        try {
            fileManager.saveImage(file);
        } catch (IOException e) {
            log.error("[upload]:文件上传失败");
            throw  new BizException(CommonEnum.FILE_UPLOAD_FAILED);
        }
        return ResultBody.success("文件上传成功");
    }

    @ApiOperation("下载文件")
    @GetMapping("/download")
    public  ResultBody download(@RequestParam("fileName") String fileName, HttpServletResponse response)
    {
        try {
            fileManager.downloadImage(fileName, response);
        } catch (IOException e) {
            log.error("[download]:文件下载失败");
            throw  new BizException(CommonEnum.DOWNLOAD_FIALED);
        }
        return ResultBody.success(fileName+"下载成功");
    }

    @GetMapping("/test")
    public  ResultBody test() throws  InterruptedException
    {

        return ResultBody.success();
    }
}
