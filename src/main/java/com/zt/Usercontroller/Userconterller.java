package com.zt.Usercontroller;

import com.zt.entity.User;
import com.zt.server.UserServer;
import com.zt.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("user")
public class Userconterller {
    @Autowired
    private UserServer userServer;

    //登录
    @RequestMapping("login")
    public String login(User user, HttpServletRequest request,String message,String enCode){
        //调用事务
        message= userServer.qureyOne(user,request,enCode);
        if (message.equals("success")){
            //登录成功
            return "main/main";
        }else{
            request.setAttribute("message",message);
            return "login/login";
        }
        //失败

    }



    @RequestMapping("getImageCode")
    //获取去验证码
    public void getImageCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        //随机获取验证码
        String code = ImageCodeUtil.getSecurityCode();
        System.out.println("获取验证码======="+code);
        //获取验证图片
        BufferedImage image = ImageCodeUtil.createImage(code);
        //
        response.setContentType("image/png");
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image,"gif",outputStream);
        //将验证码放入session
        HttpSession session = request.getSession();
        session.setAttribute("code",code);
    }

    //安全退出
    @RequestMapping("exit")
    public String exit(HttpServletRequest request){
        request.getAttribute("user");
        //删除request
        request.removeAttribute("user");
        return "redirect:/login/login.jsp";
    }
}
