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
                + "(1,'新的1年开始，祝好事接2连3，心情4季如春，生活5颜6色，7彩缤纷，偶尔8点小财，烦恼抛到9霄云外!请接受我10心10意的祝福。祝新春快乐!')");
		db.execSQL("INSERT INTO "  
				+ TB_name + "("  
				+ ID + ","  
				+ MESSAGE + ") VALUES "  
				+ "(2,'春天的钟声响，新年的脚步迈，祝新年的钟声，敲响你心中快乐的音符，幸运与平安，如春天的脚步紧紧相随!春华秋实，我永远与你同在!')");
		db.execSQL("INSERT INTO "  
				+ TB_name + "("  
				+ ID + ","  
				+ MESSAGE + ") VALUES "  
				+ "(3,'岁月可以褪去记忆，却褪不去我们一路留下的欢声笑语。祝你新春快乐，岁岁安怡！')");
		db.execSQL("INSERT INTO "  
				+ TB_name + "("  
				+ ID + ","  
				+ MESSAGE + ") VALUES "  
				+ "(4,'新年好运到，好事来得早!朋友微微笑，喜庆围你绕!花儿对你开，鸟儿向你叫。生活美满又如意!喜庆!喜庆!一生平安如意!')");
		db.execSQL("INSERT INTO "  
				+ TB_name + "("  
				+ ID + ","  
				+ MESSAGE + ") VALUES "  
				+ "(5,'新春到来喜事多，合家团圆幸福多;心情愉快朋友多，身体健康快乐多;一切顺利福气多，新年吉祥生意多;祝您好事多!多!多!')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//Update TB
		db.execSQL("DROP TABLE IF EXISTS "+TB_name);  
        onCreate(db);  
	}
	
	

}
