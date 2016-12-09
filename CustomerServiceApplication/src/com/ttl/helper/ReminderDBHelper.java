<<<<<<< HEAD
package com.ttl.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ttl.adapter.ResponseCallback;
import com.ttl.customersocialapp.Reminder_Fragment;
import com.ttl.model.ReminderContract.Reminder;
import com.ttl.model.Reminder_Child;
import com.ttl.model.Reminder_Parent;
import com.ttl.model.ServiceHandler;
import com.ttl.model.UserDetails;
import com.ttl.webservice.AWS_WebServiceCall;
import com.ttl.webservice.Constants;

public class ReminderDBHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "reminder"+UserDetails.getUser_id();

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";

	private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
			+ Reminder.TABLE_NAME + "(" + Reminder.COLUMN_NAME_REMINDER_ID
			+ " INTEGER PRIMARY KEY   AUTOINCREMENT" + COMMA_SEP +Reminder.COLUMN_NAME_REMINDER_VNUMBER
			+ TEXT_TYPE + COMMA_SEP + Reminder.COLUMN_NAME_REGISTRATION_VTYPE
			+ TEXT_TYPE + COMMA_SEP
			+ Reminder.COLUMN_NAME_VEHICLE_REMINDER_DATE + TEXT_TYPE
			+ COMMA_SEP + Reminder.COLUMN_NAME_VEHICLE_REMINDER_TIME
			+ TEXT_TYPE + ")";

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ Reminder.TABLE_NAME;
	Context context;
	public ReminderDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_CREATE_ENTRIES);
		String DATABASE_VERSION = String
				.valueOf(ReminderDBHelper.DATABASE_VERSION);
		Log.d(DATABASE_VERSION, "database version");
		PackageManager manager = context.getPackageManager();
		PackageInfo info = null;
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*	String version = info.versionName;
		Log.d(version, "appversion");
		String req = "http://tmlmobilityservices.co.in:8080/CustomerApp_Restws/customerapp/productServices/setVersions";
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

		nameValuePairs
				.add(new BasicNameValuePair("dbVersion", DATABASE_VERSION));
		nameValuePairs.add(new BasicNameValuePair("appVersion", version));
		nameValuePairs.add(new BasicNameValuePair("user_id",UserDetails.getUser_id()));

		new AWS_WebServiceCall(context, req, ServiceHandler.POST,
				Constants.setVersions, nameValuePairs,
				new ResponseCallback() {

					@Override
					public void onResponseReceive(Object object) {
						// TODO Auto-generated method stub
						Toast.makeText(context, "send.",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onErrorReceive(String string) {
						// TODO Auto-generated method stub

					}
				}).execute();*/

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		// This database is only a cache for online data, so its upgrade policy
		// is
		// to simply to discard the data and start over

		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);

	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		super.onDowngrade(db, oldVersion, newVersion);

	}

	public static int insertReminder(String vNumber, String vtype,
			String reminderDate, String remindertime, Context ctx) {

		ReminderDBHelper mDbHelper;
		SQLiteDatabase mdb;

		mDbHelper = new ReminderDBHelper(ctx);
		mdb = mDbHelper.getReadableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(Reminder.COLUMN_NAME_REMINDER_VNUMBER, vNumber);
		values.put(Reminder.COLUMN_NAME_REGISTRATION_VTYPE, vtype);
		values.put(Reminder.COLUMN_NAME_VEHICLE_REMINDER_DATE, reminderDate);
		values.put(Reminder.COLUMN_NAME_VEHICLE_REMINDER_TIME, remindertime);

		// Insert the new row, returning the primary key value of the new row
		long newRowId;
		newRowId = mdb.insert(Reminder.TABLE_NAME, null, values);

		if (newRowId != -1) {
			Log.d("record inserted at", newRowId + " row created ");
			// Toast.makeText(getApplicationContext(),
			// " Account Successfully Created ", Toast.LENGTH_LONG).show();
			return 1;
		} else {
			Log.d("record not inserted at", " 0 row created ");
			return 0;
			// Toast.makeText(getApplicationContext(),
			// "Failed to Create Account ! Please try again ..",
			// Toast.LENGTH_LONG).show();
		}
	}

	public static void loadReminders(Context ctx) {
		ReminderDBHelper mDbHelper;
		SQLiteDatabase mdb;

		mDbHelper = new ReminderDBHelper(ctx);
		mdb = mDbHelper.getReadableDatabase();

		Reminder_Fragment.insList = new Vector<Reminder_Child>();

		Cursor c = mdb.rawQuery("SELECT * FROM " + Reminder.TABLE_NAME, null);

		// ***
		Reminder_Fragment.insList.removeAllElements();
		Reminder_Fragment.pList.removeAllElements();
		if (c.moveToFirst()) {

			while (c.isAfterLast() == false) {

				String name = c.getString(1);
				Log.d(" reminder :", name);

				Reminder_Child i = new Reminder_Child();
				i.setReminder_type(c.getString(1));
				i.setRem_vehicleno(c.getString(0));
				i.setRem_date(c.getString(2));
				i.setRem_time(c.getString(3));

				Reminder_Fragment.insList.add(i);

				/*
				 * Reminder_Parent j = new Reminder_Parent();
				 * j.setRemindertitle(c.getString(0));
				 * Reminder_Fragment.pList.add(j);
				 */
				c.moveToNext();
			}
		}

		Cursor reminder_types = mdb.rawQuery(
				"SELECT DISTINCT registration_number  FROM "
						+ Reminder.TABLE_NAME, null);

		if (reminder_types.moveToFirst()) {
			while (reminder_types.isAfterLast() == false) {
				String name1 = reminder_types.getString(0);
				Log.d("remainder name", name1);
				Reminder_Parent p = new Reminder_Parent();

				p.setRemindertitle(reminder_types.getString(0));
				Log.d(" parent :", reminder_types.getString(0));
				Reminder_Fragment.pList.add(p);
				reminder_types.moveToNext();
			}
		}

	}
}
=======
package com.ttl.helper;

import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ttl.customersocialapp.Reminder_Fragment;
import com.ttl.model.ReminderContract.Reminder;
import com.ttl.model.Reminder_Child;
import com.ttl.model.Reminder_Parent;
import com.ttl.model.UserDetails;

public class ReminderDBHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "reminder"+UserDetails.getUser_id();

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";

	private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
			+ Reminder.TABLE_NAME + "(" + Reminder.COLUMN_NAME_REMINDER_ID
			+ " INTEGER PRIMARY KEY   AUTOINCREMENT" + COMMA_SEP +Reminder.COLUMN_NAME_REMINDER_VNUMBER
			+ TEXT_TYPE + COMMA_SEP + Reminder.COLUMN_NAME_REGISTRATION_VTYPE
			+ TEXT_TYPE + COMMA_SEP
			+ Reminder.COLUMN_NAME_VEHICLE_REMINDER_DATE + TEXT_TYPE
			+ COMMA_SEP + Reminder.COLUMN_NAME_VEHICLE_REMINDER_TIME
			+ TEXT_TYPE + ")";

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ Reminder.TABLE_NAME;
	Context context;
	public ReminderDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	
		db.execSQL(SQL_CREATE_ENTRIES);
		String DATABASE_VERSION = String
				.valueOf(ReminderDBHelper.DATABASE_VERSION);
	
		PackageManager manager = context.getPackageManager();
		PackageInfo info = null;
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
	
			e.printStackTrace();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);

	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		super.onDowngrade(db, oldVersion, newVersion);

	}

	public static int insertReminder(String vNumber, String vtype,
			String reminderDate, String remindertime, Context ctx) {

		ReminderDBHelper mDbHelper;
		SQLiteDatabase mdb;

		mDbHelper = new ReminderDBHelper(ctx);
		mdb = mDbHelper.getReadableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(Reminder.COLUMN_NAME_REMINDER_VNUMBER, vNumber);
		values.put(Reminder.COLUMN_NAME_REGISTRATION_VTYPE, vtype);
		values.put(Reminder.COLUMN_NAME_VEHICLE_REMINDER_DATE, reminderDate);
		values.put(Reminder.COLUMN_NAME_VEHICLE_REMINDER_TIME, remindertime);

		// Insert the new row, returning the primary key value of the new row
		long newRowId;
		newRowId = mdb.insert(Reminder.TABLE_NAME, null, values);

		if (newRowId != -1) {


			return 1;
		} else {

			return 0;

		}
	}

	public static void loadReminders(Context ctx) {
		ReminderDBHelper mDbHelper;
		SQLiteDatabase mdb;

		mDbHelper = new ReminderDBHelper(ctx);
		mdb = mDbHelper.getReadableDatabase();

		Reminder_Fragment.insList = new Vector<Reminder_Child>();

		Cursor c = mdb.rawQuery("SELECT * FROM " + Reminder.TABLE_NAME, null);


		Reminder_Fragment.insList.removeAllElements();
		Reminder_Fragment.pList.removeAllElements();
		if (c.moveToFirst()) {

			while (c.isAfterLast() == false) {

				String name = c.getString(1);


				Reminder_Child i = new Reminder_Child();
				i.setReminder_type(c.getString(1));
				i.setRem_vehicleno(c.getString(0));
				i.setRem_date(c.getString(2));
				i.setRem_time(c.getString(3));

				Reminder_Fragment.insList.add(i);


				c.moveToNext();
			}
		}

		Cursor reminder_types = mdb.rawQuery(
				"SELECT DISTINCT registration_number  FROM "
						+ Reminder.TABLE_NAME, null);

		if (reminder_types.moveToFirst()) {
			while (reminder_types.isAfterLast() == false) {
				String name1 = reminder_types.getString(0);
				Log.d("remainder name", name1);
				Reminder_Parent p = new Reminder_Parent();

				p.setRemindertitle(reminder_types.getString(0));
				Log.d(" parent :", reminder_types.getString(0));
				Reminder_Fragment.pList.add(p);
				reminder_types.moveToNext();
			}
		}

	}
}
>>>>>>> TMSC QA 3.4V
