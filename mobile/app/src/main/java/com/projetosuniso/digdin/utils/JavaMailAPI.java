package com.projetosuniso.digdin.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;

import com.projetosuniso.digdin.BuildConfig;
import com.projetosuniso.digdin.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class JavaMailAPI extends AsyncTask<Void, Void, Void> {

    private Context context;
    private Session session;
    private String email, subject, message;

    public JavaMailAPI(Context context, String email, String subject, String message) {
        this.context = context;
        this.session = session;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.debug", "true");


        session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(JavaEmailUtil.EMAIL, JavaEmailUtil.PASSWORD);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(JavaEmailUtil.EMAIL));
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));
            mimeMessage.setSubject(subject);

            //MimeMultipart multipart = new MimeMultipart("related");

            String path ="android.resource://com.example.project/drawable/email";
            String fileName = path.substring(path.lastIndexOf('/') + 1);
            String htmlText = "<html><body>TEST:<img src=\"cid:img1\"></body></html>";
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(path);
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<img1>");
            messageBodyPart.setDisposition(MimeBodyPart.INLINE);
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            mimeMessage.setContent(multipart);
            //mimeMessage.setText(message);
            Transport.send(mimeMessage);
        } catch (MessageRemovedException | AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getURLForResource (int resourceId) {
        //use BuildConfig.APPLICATION_ID instead of R.class.getPackage().getName() if both are not same
        return Uri.parse("android.resource://" + R.class.getPackage().getName()+"/" +resourceId).toString();
    }
}
