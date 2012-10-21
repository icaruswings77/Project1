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
            GMailSender sender = new GMailSender("icaruswings77@gmail.com","pass"); // SUBSTITUTE HERE                   
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
    
}    
