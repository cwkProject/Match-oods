package com.port.ocean.shipping.database;
/**
 * Created by 超悟空 on 2015/9/21.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据库工具
 *
 * @author 超悟空
 * @version 1.0 2016/3/16
 * @since 1.0
 */
public class DBSQLiteHelper extends SQLiteOpenHelper {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "DBSQLiteHelper.";

    /**
     * 引用计数器
     */
    private AtomicInteger openCounter = new AtomicInteger();

    /**
     * 单例连接，用于写访问
     */
    private static DBSQLiteHelper sqLiteOpenHelper = null;

    /**
     * 获取数据库连接实例
     *
     * @param context 上下文
     *
     * @return 连接对象
     */
    public synchronized static DBSQLiteHelper getSqLiteOpenHelper(Context context) {
        if (sqLiteOpenHelper == null) {
            sqLiteOpenHelper = new DBSQLiteHelper(context);
        }
        return sqLiteOpenHelper;
    }

    public DBSQLiteHelper(Context context) {
        super(context, CommonConst.DB_NAME, null, CommonConst.DB_VERSION);
        Log.i(LOG_TAG + "DBSQLiteHelper", "DBSQLiteHelper is invoked");
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);

        db.enableWriteAheadLogging();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(LOG_TAG + "onCreate", "onCreate is invoked");

        // 建表
        for (String sql : TableConst.CREATE_TABLE_SQL_ARRAY) {
            db.execSQL(sql);
            Log.i(LOG_TAG + "onCreate", "create table sql is " + sql);
        }

        Log.i(LOG_TAG + "onCreate", "onCreate end");
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        // 计数加一
        openCounter.incrementAndGet();

        Log.i(LOG_TAG + "getWritableDatabase", "now open database count is " + openCounter.get());

        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        // 计数加一
        openCounter.incrementAndGet();

        Log.i(LOG_TAG + "getReadableDatabase", "now open database count is " + openCounter.get());

        return super.getReadableDatabase();
    }

    @Override
    public synchronized void close() {
        // 计数减一
        if (openCounter.decrementAndGet() <= 0) {
            super.close();
        }

        Log.i(LOG_TAG + "close", "now open database count is " + openCounter.get());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
