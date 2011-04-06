package com.jonas.smssender;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBHelper extends SQLiteOpenHelper {

	public static final String springTbName = "Spring";
	public static final String valentineTbName = "Valentine";
	public static final String nationTbName = "Nation";
	public static final String ID = "_id";
	public static final String MESSAGE = "message";

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// Create Spring table
		db.execSQL("CREATE TABLE IF NOT EXISTS " + springTbName + " (" + ID
				+ " INTEGER PRIMARY KEY," + MESSAGE + " VARCHAR)");
		// Insert Data
		db
				.execSQL("INSERT INTO "
						+ springTbName
						+ "("
						+ ID
						+ ","
						+ MESSAGE
						+ ") VALUES "
						+ "(1,'新的1年开始，祝好事接2连3，心情4季如春，生活5颜6色，7彩缤纷，偶尔8点小财，烦恼抛到9霄云外!请接受我10心10意的祝福。祝新春快乐!')");
		db
				.execSQL("INSERT INTO "
						+ springTbName
						+ "("
						+ ID
						+ ","
						+ MESSAGE
						+ ") VALUES "
						+ "(2,'春天的钟声响，新年的脚步迈，祝新年的钟声，敲响你心中快乐的音符，幸运与平安，如春天的脚步紧紧相随!春华秋实，我永远与你同在!')");
		db.execSQL("INSERT INTO " + springTbName + "(" + ID + "," + MESSAGE
				+ ") VALUES " + "(3,'岁月可以褪去记忆，却褪不去我们一路留下的欢声笑语。祝你新春快乐，岁岁安怡！')");
		db
				.execSQL("INSERT INTO "
						+ springTbName
						+ "("
						+ ID
						+ ","
						+ MESSAGE
						+ ") VALUES "
						+ "(4,'新年好运到，好事来得早!朋友微微笑，喜庆围你绕!花儿对你开，鸟儿向你叫。生活美满又如意!喜庆!喜庆!一生平安如意!')");
		db
				.execSQL("INSERT INTO "
						+ springTbName
						+ "("
						+ ID
						+ ","
						+ MESSAGE
						+ ") VALUES "
						+ "(5,'新春到来喜事多，合家团圆幸福多;心情愉快朋友多，身体健康快乐多;一切顺利福气多，新年吉祥生意多;祝您好事多!多!多!')");
		// Create Valentine table
		db.execSQL("CREATE TABLE IF NOT EXISTS " + valentineTbName + " (" + ID
				+ " INTEGER PRIMARY KEY," + MESSAGE + " VARCHAR)");
		// Insert Data
		db
				.execSQL("INSERT INTO "
						+ valentineTbName
						+ "("
						+ ID
						+ ","
						+ MESSAGE
						+ ") VALUES "
						+ "(1,'在这个与众不同的情人节里，我把誓言一生一世戴在你的手指上,纵然一生平平淡淡,同尝甘苦,我愿永远为你挡风遮雨共度朝朝暮暮。情人节快乐！')");
		db.execSQL("INSERT INTO " + valentineTbName + "(" + ID + "," + MESSAGE
				+ ") VALUES "
				+ "(2,'曾经爱你，是真的；依然爱你，也是真的。情人节里，让我把这份爱汇成涓涓的祝福，真诚的祝福你幸福、快乐永远！')");
		db.execSQL("INSERT INTO " + valentineTbName + "(" + ID + "," + MESSAGE
				+ ") VALUES "
				+ "(3,'真希望小路没有尽头，就这样手拉手一直走下去，让我们共同走完以后生命的每一个情人节，祝节日快乐！')");
		db.execSQL("INSERT INTO " + valentineTbName + "(" + ID + "," + MESSAGE
				+ ") VALUES "
				+ "(4,'真正的爱情必定孕育着苦难，只有在苦难中才能挖掘出莫大的喜悦。你是我的唯一。情人节快乐！ ')");
		db
				.execSQL("INSERT INTO "
						+ valentineTbName
						+ "("
						+ ID
						+ ","
						+ MESSAGE
						+ ") VALUES "
						+ "(5,'自己买玫瑰，算了，会被人笑的；买巧克力，算了，会胖；点上蜡烛，算了，没有情人；情人节，愿没有情人的你和我一起度过快乐情人节！')");
		// Create Nation table
		db.execSQL("CREATE TABLE IF NOT EXISTS " + nationTbName + " (" + ID
				+ " INTEGER PRIMARY KEY," + MESSAGE + " VARCHAR)");
		// Insert Data
		db.execSQL("INSERT INTO " + nationTbName + "(" + ID + "," + MESSAGE
				+ ") VALUES "
				+ "(1,'国庆国庆普天同庆,祝福你和嫂子在这个和平幸福的年代里节日快乐,身体健康,幸福永远。')");
		db.execSQL("INSERT INTO " + nationTbName + "(" + ID + "," + MESSAGE
				+ ") VALUES "
				+ "(2,'国庆节到了,我衷心祝愿我心中的你心情愉快,开心的时刻永远都在。上帝赐给人类的爱是永恒的。')");
		db
				.execSQL("INSERT INTO "
						+ nationTbName
						+ "("
						+ ID
						+ ","
						+ MESSAGE
						+ ") VALUES "
						+ "(3,'放眼中华,百业千姿竞发;举目九州,“四化”万马奔腾 奉天承运,皇帝召曰：你去年国庆借我五毛钱,至今未还,罚你三天不准拉屎,拉屎不准带纸,带纸不过三尺,直到憋死为止!钦此,领纸!')");
		db.execSQL("INSERT INTO " + nationTbName + "(" + ID + "," + MESSAGE
				+ ") VALUES "
				+ "(4,'刚刚送走了中秋的笑脸,又迎来了国庆的喜悦!愿你在这双重喜庆的包围下永远幸福、快乐!! ')");
		db
				.execSQL("INSERT INTO "
						+ nationTbName
						+ "("
						+ ID
						+ ","
						+ MESSAGE
						+ ") VALUES "
						+ "(5,'昌盛时代,山水腾跃诗画里;大兴年头,人民欢笑歌舞中 淡照霜飞的是一丝银菊,书写秋空的是一片月华,温润心田的是一抹恩爱,缘系今生的是一世情缘。国庆佳节,盼望与你相聚!')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// Update TB
		db.execSQL("DROP TABLE IF EXISTS " + springTbName);
		onCreate(db);
		db.execSQL("DROP TABLE IF EXISTS " + valentineTbName);
		onCreate(db);
		db.execSQL("DROP TABLE IF EXISTS " + nationTbName);
		onCreate(db);

	}

}
