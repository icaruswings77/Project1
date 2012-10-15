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
				// insert �޼���� ����
				row = new ContentValues();
				row.put("iname", "���±�");
				row.put("telno", "01012345678");
				db.insert("smsjuso", null, row);
				// SQL ������� ����
				db.execSQL("INSERT INTO smsjuso VALUES (null, '�Կ��', '01088887777');");
				mHelper.close();
				mText.setText("Insert Success");
				break;
			case R.id.delete:
				db = mHelper.getWritableDatabase();
				// delete �޼���� ����
				db.delete("smsjuso", null, null);
				// SQL ������� ����
				//db.execSQL("DELETE FROM smsjuso;");
				mHelper.close();
				mText.setText("Delete Success");
				break;
			case R.id.update:
				db = mHelper.getWritableDatabase();
				// update �޼���� ����
				row = new ContentValues();
				row.put("���±�", "01012345678");
				//db.update("smsjuso", row, "iname = '������'", null);
				// SQL ������� ����
				db.execSQL("UPDATE smsjuso SET iname = '�̿���' WHERE telno = '01088887777';");
				mHelper.close();
				mText.setText("Update Success");
				break;
			case R.id.select:
				db = mHelper.getReadableDatabase();
				Cursor cursor;
				// query �޼���� �б�
				//cursor = db.query("smsjosu", new String[] {"iname", "telno"}, null, 
				//		null, null, null, null);
				// SQL ������� �б�
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
