package com.g2g.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//import com.success.R;
import java.io.ByteArrayInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.security.Security;  
import java.util.Properties;  
  
import javax.activation.DataHandler;  
import javax.activation.DataSource;  
import javax.mail.Message;  
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  

import com.g2g.helloworld.GMailSender.ByteArrayDataSource;

 
public class MailActivity extends Activity { 
	
	private Button button;
	
    private String mailhost = "smtp.gmail.com"; 
    private String user; 
    private String password; 
    private Session session; 	
	
    @Override 
    public void onCreate(Bundle icicle) { 
        super.onCreate(icicle); 
        setContentView(R.layout.activity_send_mail); 
        
        Button send = (Button) this.findViewById(R.id.btnsendmail); 
        send.setOnClickListener(new View.OnClickListener() { 
        	
            public void onClick(View view) { 
            GMailSender sender = new GMailSender("icaruswings77@gmail.com","snowball77"); // SUBSTITUTE HERE                   
                try { 
                    sender.sendMail( 
                            "메일제목 !!",   					//subject.getText().toString(),  
                            "메일 본문입니다..~~ ",       	//body.getText().toString(),  
                            "icaruswings77@gmail.com",  	//from.getText().toString(), 
                            "snowthing@naver.com"       	//to.getText().toString() 
                            ); 
                } catch (Exception e) { 
                    Log.e("SendMail", e.getMessage(), e); 
                } 
            } 
        }); 

    }
    
    public void GMailSender(String user, String password) { 
        this.user = user; 
        this.password = password; 
 
        Properties props = new Properties();  
        props.setProperty("mail.transport.protocol", "smtp");  
        props.setProperty("mail.host", mailhost);  
        props.put("mail.smtp.auth", "true");  
        props.put("mail.smtp.port", "465");  
        props.put("mail.smtp.socketFactory.port", "465");  
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
        props.put("mail.smtp.socketFactory.fallback", "false");  
        props.setProperty("mail.smtp.quitwait", "false");  
 
        //session = Session.getDefaultInstance(props, this); 
        
    } 

    protected PasswordAuthentication getPasswordAuthentication() { 
        return new PasswordAuthentication(user, password); 
    } 
 
    public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception { 
        
    	MimeMessage message = new MimeMessage(session); 
        DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain")); 
        message.setSender(new InternetAddress(sender)); 
        message.setSubject(subject); 
        message.setDataHandler(handler); 
        
        if (recipients.indexOf(',') > 0) 
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients)); 
        else 
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients)); 
        Transport.send(message); 
    } 
 
    public class ByteArrayDataSource implements DataSource { 
        private byte[] data; 
        private String type; 
 
        public ByteArrayDataSource(byte[] data, String type) { 
            super(); 
            this.data = data; 
            this.type = type; 
        } 
 
        public ByteArrayDataSource(byte[] data) { 
            super(); 
            this.data = data; 
        } 
 
        public void setType(String type) { 
            this.type = type; 
        } 
 
        public String getContentType() { 
            if (type == null) 
                return "application/octet-stream"; 
            else 
                return type; 
        } 
 
        public InputStream getInputStream() throws IOException { 
            return new ByteArrayInputStream(data); 
        } 
 
        public String getName() { 
            return "ByteArrayDataSource"; 
        } 
 
        public OutputStream getOutputStream() throws IOException { 
            throw new IOException("Not Supported"); 
        } 
    }     
}    
