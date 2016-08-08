package bmf.services.webServices;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mailAPI {
   public static void sendRegisterMail(String to, int activation_number) {
      // Recipient's email ID needs to be mentioned in 'to'.

      // Sender's email ID needs to be mentioned
      String from = "BMF";
      final String username = "arscse06@gmail.com";
      final String password = "012345abcde";

      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }
      });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
         InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("BMF - Activate Your Account");

         // Now set the actual message
         message.setText("Congratulations! Your account has been registered.\n"
            +"To activate your account click this link : http://localhost:8090/bmf.services/rest/Activation/ActivationService/Activate/" + activation_number);

         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
    	  System.out.println(e);
      }
   }
   
   //Forgot Password Mail
   public static void forgotPasswordMail(String to, String user_password) {
	      // Recipient's email ID needs to be mentioned in 'to'.

	      String from = "BMF";
	      final String username = "arscse06@gmail.com";
	      final String password = "012345abcde";

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject("BMF - Password Recovery");

	         // Now set the actual message
	         message.setText("Hello, your password is "
	            + user_password);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	    	  System.out.println(e);
	      }
	   }
   
}
