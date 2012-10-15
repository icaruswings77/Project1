package com.g2g.helloworld;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsActivity extends Activity {

    EditText receiver;
    EditText message;
	
    private Button button;
    private Button button1;
    private Button button2;

    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
         
        receiver = (EditText) findViewById(R.id.receiver);
        message = (EditText) findViewById(R.id.message);
        
        button = (Button) findViewById(R.id.btnSmsSend);
        
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// 1.SmsManager 객체를 얻는다
				SmsManager sms = SmsManager.getDefault();
				// 2.sendTextMessage 메소드로 SMS 전송
				sms.sendTextMessage(receiver.getText().toString(), null, message.getText().toString(), null, null);
				
				// 토스트로 문자보내기 완료 메세지 출력
				Toast toast = Toast.makeText(SmsActivity.this, "문자보내기 완료", Toast.LENGTH_SHORT);
				toast.show();
			}
		});
        
		button1 = (Button) findViewById(R.id.btnSmsJuso);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(SmsActivity.this, SmsJusoActivity.class);
				startActivity(intent);

			}
		});     
		
		button2 = (Button) findViewById(R.id.btnMailSend);

		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(SmsActivity.this, MailActivity.class);
				startActivity(intent);

			}
		});		
				
        		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.a, menu);
        return true;
    }
}
