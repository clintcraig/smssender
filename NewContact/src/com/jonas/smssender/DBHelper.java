package com.jonas.smssender;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBHelper extends SQLiteOpenHelper {

	
	public static final String TB_name = "Spring";
	public static final String ID = "_id";
	public static final String MESSAGE = "message";
	
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		// TODO Auto-generated method stub
		//Create Table
		db.execSQL("CREATE TABLE IF NOT EXISTS "   
                + TB_name + " ("   
                + ID + " INTEGER PRIMARY KEY,"   
                + MESSAGE + " VARCHAR)");
		//Insert Data
		db.execSQL("INSERT INTO "  
                + TB_name + "("  
                + ID + ","  
                + MESSAGE + ") VALUES "  
                + "(1,'�µ�1�꿪ʼ��ף���½�2��3������4���紺������5��6ɫ��7���ͷף�ż��8��С�ƣ������׵�9������!�������10��10���ף����ף�´�����!')");
		db.execSQL("INSERT INTO "  
				+ TB_name + "("  
				+ ID + ","  
				+ MESSAGE + ") VALUES "  
				+ "(2,'����������죬����ĽŲ�����ף��������������������п��ֵ�������������ƽ�����紺��ĽŲ���������!������ʵ������Զ����ͬ��!')");
		db.execSQL("INSERT INTO "  
				+ TB_name + "("  
				+ ID + ","  
				+ MESSAGE + ") VALUES "  
				+ "(3,'���¿�����ȥ���䣬ȴ�ʲ�ȥ����һ·���µĻ���Ц�ף���´����֣����갲����')");
		db.execSQL("INSERT INTO "  
				+ TB_name + "("  
				+ ID + ","  
				+ MESSAGE + ") VALUES "  
				+ "(4,'������˵�������������!����΢΢Ц��ϲ��Χ����!�������㿪���������С���������������!ϲ��!ϲ��!һ��ƽ������!')");
		db.execSQL("INSERT INTO "  
				+ TB_name + "("  
				+ ID + ","  
				+ MESSAGE + ") VALUES "  
				+ "(5,'�´�����ϲ�¶࣬�ϼ���Բ�Ҹ���;����������Ѷ࣬���彡�����ֶ�;һ��˳�������࣬���꼪�������;ף�����¶�!��!��!')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//Update TB
		db.execSQL("DROP TABLE IF EXISTS "+TB_name);  
        onCreate(db);  
	}
	
	

}
