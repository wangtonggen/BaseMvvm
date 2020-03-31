package com.example.basemvvm.db.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.basemvvm.base.BaseApplication;
import com.example.basemvvm.db.dao.UserDao;
import com.example.basemvvm.db.entity.UserEntity;
import com.tencent.wcdb.database.SQLiteCipherSpec;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.room.db.WCDBDatabase;
import com.tencent.wcdb.room.db.WCDBOpenHelperFactory;

/**
 * author: wtg
 * date:2020/3/31 0031
 * desc:
 */
@Database(entities = {UserEntity.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final byte[] PASS_PWD = "luyuan".getBytes();//数据库加密的密码短语
    private static final int PAGE_SIZE = 4096;//设置要使用的页面大小。页面大小应该是2的幂。
    private static final int KDF_TIMES = 64000;//设置要使用的KDF迭代时间
    private static volatile AppDatabase INSTANCE;
    private static volatile SQLiteCipherSpec sqLiteCipherSpec;

    /**
     * 数据库升级
     */
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //此处对于数据库中的所有更新都需要写下面的代码
            database.execSQL("ALTER TABLE users "
                    + " ADD COLUMN last_update INTEGER");
        }
    };


    public abstract UserDao userDao();

    /**
     * 单例模式
     * @return database实例对象
     */
    public static AppDatabase getDatabase() {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    iniSQLiteCipherSpec();
                    WCDBOpenHelperFactory factory = new WCDBOpenHelperFactory()
                            .passphrase(PASS_PWD)      // 指定加密DB密钥，非加密DB去掉此行
                            .cipherSpec(sqLiteCipherSpec)               // 指定加密方式，使用默认加密可以省略
//                            .writeAheadLoggingEnabled(true)     // 打开WAL以及读写并发，可以省略让Room决定是否要打开
                            .asyncCheckpointEnabled(true);        // 打开异步Checkpoint优化，不需要可以省略

                    INSTANCE = Room.databaseBuilder(BaseApplication.instance, AppDatabase.class, "luyuan_database")
                            .allowMainThreadQueries()//允许在主线程使用
//                            .addMigrations(MIGRATION_1_2)
                            .openHelperFactory(factory)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 初始化 SQLiteCipherSpec
     */
    private static void iniSQLiteCipherSpec(){
        sqLiteCipherSpec = new SQLiteCipherSpec()
                .setPageSize(PAGE_SIZE)
                .setKDFIteration(KDF_TIMES);
    }

    /**
     * 监听数据库的状态 create/open/
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
//            new PopulateDbAsync(INSTANCE).execute();
        }
    };

}
