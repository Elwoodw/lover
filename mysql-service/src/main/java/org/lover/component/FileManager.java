package org.lover.component;

import cn.hutool.crypto.SecureUtil;
import org.lover.config.ImageConfig;
import org.lover.exception.BizException;
import org.lover.exception.CommonEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Component
public class FileManager {
    @Autowired
    private ImageConfig imageConfig;

    public  void saveImage(MultipartFile uploadFile) throws IOException
    {
        String contentType=uploadFile.getContentType();
        String type=contentType.substring(contentType.indexOf('/')+1);
        if(!imageConfig.getAllowType().contains(type))
        {
            throw new BizException("500","上传文件类型不正确");
        }

        File file=new File(imageConfig.getSavePath());
        if(!file.exists())
        {
            file.mkdirs();
        }
        byte[]bytes=uploadFile.getBytes();
        String md5= SecureUtil.md5(uploadFile.getInputStream());
        String path=imageConfig.getSavePath()+md5+"."+type;
        BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(path));
        outputStream.write(bytes);
    }

    public void downloadImage(String fileName,HttpServletResponse response) throws IOException
    {
        File file=new File(imageConfig.getSavePath()+File.separator+fileName);
        if(!file.exists())
        {
            throw new BizException(CommonEnum.FILE_NOT_FOUND);
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName );

        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
        byte[]buff=new byte[1024];
        OutputStream os=response.getOutputStream();
        int i=0;
        while ((i=bis.read(buff))!=1)
        {
            os.write(buff,0,i);
            os.flush();
        }

    }
}
