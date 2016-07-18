package com.dev.sys.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.base.controller.BaseController;
import com.dev.base.util.WebUtil;

/**
 * 
		* <p>Title: 验证码</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月25日上午10:13:06</p>
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController{
	/**
	 * 
	 		*@name 生成验证码
			*@Description  
			*@CreateDate 2015年8月25日上午10:13:56
	 */
	@RequestMapping("/build.htm")
	public void build(HttpServletRequest request,HttpServletResponse response,String oper) throws Exception{
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);

		int width=65, height=28;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman",Font.PLAIN,24));
		g.setColor(getRandColor(160,200));
		
		Random random = new Random();
		for (int i=0;i<155;i++) {
			int x = random.nextInt(width);
		    int y = random.nextInt(height);
		    int xl = random.nextInt(12);
		    int yl = random.nextInt(12);
		    
		    g.drawLine(x,y,x + xl,y + yl);
		}

		String sRand="";
		for (int i = 0;i < 4;i ++){
		    String rand=String.valueOf(random.nextInt(10));
		    sRand += rand;

		    g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
		    g.drawString(rand,13*i+5,22);
		}
		g.dispose();
		
		WebUtil.setSessionAttr(request, oper, sRand);

		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
	
	//随机生成颜色
	private Color getRandColor(int fc,int bc){
        Random random = new Random();
        if(fc > 255){
        	fc = 255;
        }
        
        if(bc > 255){
        	bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        
        return new Color(r,g,b);
	}
}
