package com.g2g.helloworld;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button button;
	private Button button1;
	private Button button2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// btn.setOnClickListener(new View.OnClickListener() {

		button = (Button) findViewById(R.id.button01);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, SmsActivity.class);
				startActivity(intent);

			}
		});
		
		/*
		 * 토스트 메세지 예제
		 */		
		button1 = (Button) findViewById(R.id.button02);

		button1.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View arg0) {
				Toast toast = Toast.makeText(MainActivity.this, "토스트 메세지",
						Toast.LENGTH_SHORT);
				toast.show();
			}
		});
/*		
		
		button1 = (Button) findViewById(R.id.button02);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, SmsJusoActivity.class);
				startActivity(intent);

			}
		});
*/		
		
		button2 = (Button) findViewById(R.id.button03);

		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, MailActivity.class);
				startActivity(intent);

			}
		});		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
