package com.g2g.helloworld;

	import android.app.*;
	import android.content.*;
	import android.database.*;
	import android.database.sqlite.*;
	import android.os.*;
	import android.view.*;
	import android.widget.*;

	public class SmsJusoActivity extends Activity {
		JusoDBHelper mHelper;
		EditText mText;
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_sms_juso);
			//setContentView(R.layout.activity_sms);

			mHelper = new JusoDBHelper(this);
			mText = (EditText)findViewById(R.id.edittext);
		}
		
		public void mOnClick(View v) {
			SQLiteDatabase db;
			ContentValues row;
			switch (v.getId()) {
			case R.id.insert:
				db = mHelper.getWritableDatabase();
				// insert 메서드로 삽입
				row = new ContentValues();
				row.put("iname", "소태균");
				row.put("telno", "01012345678");
				db.insert("smsjuso", null, row);
				// SQL 명령으로 삽입
				db.execSQL("INSERT INTO smsjuso VALUES (null, '함용기', '01088887777');");
				mHelper.close();
				mText.setText("Insert Success");
				break;
			case R.id.delete:
				db = mHelper.getWritableDatabase();
				// delete 메서드로 삭제
				db.delete("smsjuso", null, null);
				// SQL 명령으로 삭제
				//db.execSQL("DELETE FROM smsjuso;");
				mHelper.close();
				mText.setText("Delete Success");
				break;
			case R.id.update:
				db = mHelper.getWritableDatabase();
				// update 메서드로 갱신
				row = new ContentValues();
				row.put("소태균", "01012345678");
				//db.update("smsjuso", row, "iname = '박주희'", null);
				// SQL 명령으로 갱신
				db.execSQL("UPDATE smsjuso SET iname = '이영기' WHERE telno = '01088887777';");
				mHelper.close();
				mText.setText("Update Success");
				break;
			case R.id.select:
				db = mHelper.getReadableDatabase();
				Cursor cursor;
				// query 메서드로 읽기
				//cursor = db.query("smsjosu", new String[] {"iname", "telno"}, null, 
				//		null, null, null, null);
				// SQL 명령으로 읽기
				cursor = db.rawQuery("SELECT iname, telno FROM smsjuso", null);
			
				String Result = "";
				while (cursor.moveToNext()) {
					String iname = cursor.getString(0);
					String telno = cursor.getString(1);
					Result += (iname + " = " + telno + "\n");
				}

				if (Result.length() == 0) {
					mText.setText("Empyt Set");
				} else {
					mText.setText(Result);
				}
				cursor.close();
				mHelper.close();
				break;
			}
		}
		/*
		@Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.a, menu);
	        return true;
	    }
	    */		

	}

	class JusoDBHelper extends SQLiteOpenHelper {
		public JusoDBHelper(Context context) {
			super(context, "smsjuso.db", null, 1);
		}

		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE smsjuso ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"iname TEXT, telno TEXT);");
		}

		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS smsjuso");
			onCreate(db);
		}
	}
