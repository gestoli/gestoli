
package es.caib.gestoli.logic.util;

import javax.activation.DataHandler;
import javax.ejb.EJBException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;




public class ServeiCorreu {
	private static Logger logger = Logger.getLogger(ServeiCorreu.class);
	public boolean TEST_MODE = false;
	public final String emailTest = "jcgarcia@at4.net";
	public final String SERVIDOR_DESARROLLO_AT4 = "dgtic";


	public ServeiCorreu() throws Exception{

	}


	/**
	 * @ejb.transaction type="Required"
	 * @ejb.interface-method view-type="remote"
	 * 
	 * @param from String
	 * @param to String
	 * @param subject String
	 * @param body String
	 * @param isHTML boolean
	 * @throws EJBException
	 */
	public void enviaCorreu(String from, String to, String subject, String body,  byte[] archivo, String filename , String emailFrom) throws Exception{

		if (logger.isInfoEnabled()) logger.info(">>> sendMail(FROM:" + from + ", TO:" + to + ", SUBJECT:" + subject + ", BODY:" + body + ")");

		// Enviamos email con el archivo adjunto
		Session session = null;

		// Recuperamos session del JNDI
		try {
			session = (Session)PortableRemoteObject.narrow(new InitialContext().lookup("java:/es.caib.gestoli.Mail"), Session.class);
		} catch (NamingException e) {
			logger.error(e);
			throw new MessagingException("No s'ha trobat servei de mail : " + "java:comp/env/mail/Mail" );
		}
		if (session == null) {
			throw new MessagingException("No s'ha trobat servei de mail : " + "java:comp/env/mail/Mail" );
		}

		try {
			MimeMultipart multipart = new MimeMultipart();
			MimeMessage msg = new MimeMessage(session);
			if (from != null) {
				if (session != null && session.getProperty("mail.from") != null && !session.getProperty("mail.from").equals("")) {
					msg.setFrom(new InternetAddress(session.getProperty("mail.from")));		// Del archivo "mail-service.xml" del JBoss
				} else {
					msg.setFrom(new InternetAddress(emailFrom));							// Del archivo "app.properties" de la aplicacion
				}
				InternetAddress [] iAddress = new InternetAddress[1];
				iAddress[0]=new InternetAddress(from);
				msg.setReplyTo(iAddress);
			}
			msg.setRecipients(Message.RecipientType.TO, to);
			msg.setSubject(subject);
			msg.setSentDate(new java.util.Date());

			// BODY
			MimeBodyPart mbp = new MimeBodyPart();
			if ((body != null) && !body.equals("")) {
				StringBuffer sb = new StringBuffer (body);
				mbp.setText(sb.toString());
				multipart.addBodyPart(mbp);
			}

			//Adjuntamos pdf
			if (filename != null) {
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setFileName(filename);
				messageBodyPart.setHeader("Content-Type", "application/pdf");
				messageBodyPart.setDataHandler( new DataHandler(new BufferedDataSource(archivo, filename, "application/pdf")) );
				messageBodyPart.setHeader("Content-ID","<"+ filename+">");
				multipart.addBodyPart(messageBodyPart);

			}
			msg.setContent(multipart);
			Transport.send(msg);

		} catch (MessagingException e){
			throw new Exception(e);
		}	   

	}




}