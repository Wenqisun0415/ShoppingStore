package store.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		Properties properties = new Properties();
		  properties.put("mail.transport.protocol", "smtp");     
		  properties.put("mail.smtp.host", "smtp.163.com");       
		  properties.put("mail.smtp.port", 465);       
		  properties.put("mail.smtp.auth", "true");        
		  properties.put("mail.smtp.ssl.enable", "true");//设置是否使用ssl安全连接  ---一般都使用        
		  //properties.put("mail.debug", "true");//设置是否显示debug信息  true 会在控制台显示相关信息        
  
		Session session = Session.getInstance(properties);        
    
		Message message = new MimeMessage(session);        
		message.setFrom(new InternetAddress("wenqisun0415@163.com"));       
		message.setRecipients(RecipientType.TO, new InternetAddress[] { new InternetAddress(email) });       
		message.setSubject("用户激活");
		String url="http://localhost:8080/BookStore/UserServlet?method=activate&code="+emailMsg;
		String content="<h1>来自购物天堂的激活邮件!激活请点击以下链接!</h1><h3><a href='"+url+"'>"+url+"</a></h3>";
		
		message.setContent(content, "text/html;charset=utf-8");     
		Transport transport = session.getTransport();        
		transport.connect("wenqisun0415@163.com", "Ws851853");      
		transport.sendMessage(message, message.getAllRecipients());
	}
	public static void main(String[] args) throws AddressException, MessagingException {
		MailUtils.sendMail("821168643@qq.com", "");
	}
}
