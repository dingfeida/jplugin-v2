package net.luis.main.webreq;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.jplugin.ext.webasic.api.IController;

/**
 *
 * @author: LiuHang
 * @version ����ʱ�䣺2015-3-14 ����03:56:28
 **/

public class UserRequest{
	public void showAddPage(HttpServletRequest req,HttpServletResponse res) throws IOException{
		StringBuffer sb = new StringBuffer();
		sb.append("<table>");
		sb.append("<tr><td>aaaa</td><td>bbb</td></tr>");
		sb.append("</table>");
		
		res.getWriter().println(sb.toString());
	}

}
