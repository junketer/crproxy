package com.djt.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.mail.iap.Response;

public class ChangeRequest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4493901285093271837L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String author = req.getParameter("author");
		String title = req.getParameter("title");
		String tester = req.getParameter("tester");
		
		try {
			sendMail(author, title, tester);
		} catch (Throwable t) {
			resp.setContentType("text/plain");
			t.printStackTrace();
			t.printStackTrace(resp.getWriter());
		}
	}

	private void sendMail(String author, String title, String tester) {
		// Recipient's email ID needs to be mentioned.
	      String to = "dtillin@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = "dtillin@gmail.com";

	      // Assuming you are sending email from localhost
	      String host = "smtp.gmail.com";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);
	      properties.put("mail.smtp.user", "dtillin");
	      properties.put("mail.smtp.password", "luton48");
	      properties.put("mail.smtp.port", "587");
	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);
	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("CR: " + title +" raised");

	         // Now set the actual message
	         message.setText("Author: " + author +"\n\r Tester: "+tester);

	         // Send message
	         Transport transport = session.getTransport("smtps");
	         transport.connect("smtp.gmail.com", "dtillin", "luton48");
	         transport.sendMessage(message, message.getAllRecipients());
	         
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
}
