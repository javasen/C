package cn.itcast.web.controller;

import cn.itcast.utils.UploadUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

//文件上传控制器
@Controller
public class FileUploadController {
    @RequestMapping("/upload")
    public  String upload(HttpServletRequest request, MultipartFile uploadFile, String desc) throws IOException {

        //文件名
        String originalFilename = uploadFile.getOriginalFilename();
        System.out.println(originalFilename);
        // 生成唯一文件名
        String uniqueName = UploadUtils.getUUIDName(originalFilename);
        // 获取保存文件的路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        // /upload/2/A
        // 产生2层目录结构
        String dir = UploadUtils.getDir();
        // 创建保存的目录
        File saveDir = new File(realPath, dir);//项目虚拟路径+2级目录
        if (!saveDir.exists()){//判断该目录是否存在
            saveDir.mkdirs();// 创建多层结构
        }
        //保存文件
        uploadFile.transferTo(new File(saveDir, uniqueName));
        System.out.println("desc=" + desc);
        return "success";
    }


    @RequestMapping("/upload2")
    public  String upload2(HttpServletRequest request, MultipartFile uploadFile, String desc) throws IOException {
        //文件名
        String originalFilename = uploadFile.getOriginalFilename();
        System.out.println(originalFilename);
        // 生成唯一文件名
        String uniqueName = UploadUtils.getUUIDName(originalFilename);

        //保存到文件服务器上

        String url="http://localhost:9090/upload/";
        //使用jersey客户端
        Client client = Client.create();
        //定位存储的目录,存储的地址
        WebResource resource = client.resource(url + uniqueName);//路径+文件名
        //开始上传,文件存在于uploadFile中,是流
        resource.put(uploadFile.getBytes());//获取文件的字节数组流
        return "success";
    }
}
