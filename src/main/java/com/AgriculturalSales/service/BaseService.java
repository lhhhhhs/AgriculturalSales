package com.AgriculturalSales.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class BaseService {


    /**
     * 保存文件并返回存储路径
     * @param file
     * @param res
     * @return
     */
    public String saveFile(MultipartFile file, HttpServletRequest res){
        long currentTimeMillis = System.currentTimeMillis();
        String contextPath = res.getSession().getServletContext().getRealPath("/static/productImg/");
        InputStream is = null;
        FileOutputStream fos = null;
        String imgpath=contextPath+currentTimeMillis+".jpg";
        if(!file.isEmpty()) {
            try {
                is = file.getInputStream();
                fos = new FileOutputStream(contextPath+currentTimeMillis+".jpg");
                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                System.out.println("写入成功");
            }  catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if(fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if(is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        return "/static/productImg/"+currentTimeMillis+".jpg";
    }
}
