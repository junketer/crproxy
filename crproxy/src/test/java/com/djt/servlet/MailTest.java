package com.djt.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.TestCase;

import com.djt.servlet.ChangeRequest;

public class MailTest extends TestCase {

	public void testServlet() {
		HttpServletRequest req =mock(HttpServletRequest.class);
		when(req.getParameter("tester")).thenReturn("Jo");
		when(req.getParameter("author")).thenReturn("Dan");
		when(req.getParameter("title")).thenReturn("CRTest");
		when(req.getMethod()).thenReturn("POST");
		HttpServletResponse resp = mock(HttpServletResponse.class);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			when(resp.getWriter()).thenReturn(pw);
			
			new ChangeRequest().doPost(req, resp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
	}
}
