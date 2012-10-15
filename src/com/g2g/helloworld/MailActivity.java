package com.g2g.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//import com.success.R;
 
public class MailActivity extends Activity { 
     
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
}