package com.zhuoxun.controller.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.zhuoxun.common.Constants;

/**
 * 响应图片验证码的Servlet
 */
@Controller
public class VerifyCodeContoller {

	@GetMapping("/vc.jpg")
	protected void doGet(HttpSession session, HttpServletResponse response) throws Exception {

		String txt = randTxt(4);

		session.setAttribute(Constants.KEY_SESSION_VC_CODE, txt);

		response.setContentType("image/jpeg");
		// 设置响应头控制浏览器不要缓存响应内容
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		generate(txt, response.getOutputStream());
	}

	private void generate(String txt, OutputStream os) throws IOException {

		BufferedImage img = new BufferedImage(100, 40, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 100, 40);

		for (int i = 0; i < 20; i++) {
			g.setColor(randColor(150, 250));
			g.drawOval(random.nextInt(110), random.nextInt(24), 5 + random.nextInt(10), 5 + random.nextInt(10));
		}

		g.setColor(randColor(10, 240));
		Font mFont = new Font("Arial", Font.ITALIC, 28);
		g.setFont(mFont);
		g.drawString(txt, 8, 30);

		ImageIO.write(img, "jpg", os);
	}

	private Random random = new Random();
	private char[] c = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private String randTxt(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			char ch = c[random.nextInt(c.length)];
			sb.append(ch);
		}

		return sb.toString();
	}

	private Color randColor(int fc, int bc) {// 给定范围获得随机颜色
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
