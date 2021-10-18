package ru.capchik.personalproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import ru.capchik.personalproject.models.CompactBuildInfo;

public class CacheDb {

    private static final String TABLE_COMPACT_ITEMS_CACHE = "CompactItemsCache";

    private static final String COLUMN_DEFINITION_ID = "definitionId";
    private static final String COLUMN_SUCCEEDED = "succeeded";
    private static final String COLUMN_DEFINITION_NAME = "definitionName";
    private static final String COLUMN_BUILD_NUMBER = "buildNumber";
    private static final String COLUMN_COMMIT_MESSAGE = "commitMessage";
    private static final String COLUMN_FINISH_TIME = "finishTime";


    private static final TimeZone DATE_TIME_ZONE = TimeZone.getTimeZone("UTC");
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.US);

    static {
        DATE_FORMAT.setTimeZone(DATE_TIME_ZONE);
    }

    private final SQLiteDatabase database;

    public CacheDb(Context context) {
        CacheDbOpenHelper helper = new CacheDbOpenHelper(context);
        database = helper.getWritableDatabase();
    }

    public void updateCompactBuildInfo(ArrayList<CompactBuildInfo> newBuildInfo) {
        database.delete(TABLE_COMPACT_ITEMS_CACHE, null, null);
        database.beginTransaction();
        try {

            for (CompactBuildInfo bi : newBuildInfo) {
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_DEFINITION_ID, bi.getDefinitionId());
                cv.put(COLUMN_SUCCEEDED, bi.isSucceeded());
                cv.put(COLUMN_DEFINITION_NAME, bi.getDefinitionName());
                cv.put(COLUMN_BUILD_NUMBER, bi.getBuildNumber());
                cv.put(COLUMN_COMMIT_MESSAGE, bi.getCommitMessage());
                cv.put(COLUMN_FINISH_TIME, bi.getFinishTime().getTime());

                database.insert(TABLE_COMPACT_ITEMS_CACHE, null, cv);
            }

            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    public ArrayList<CompactBuildInfo> getCachedBuildItems() {
        ArrayList<CompactBuildInfo> result = new ArrayList<>();
        try (Cursor query = database.query(TABLE_COMPACT_ITEMS_CACHE, null, null, null, null, null, COLUMN_FINISH_TIME + " DESC")) {
            while (query.moveToNext()) {
                int definitionId = query.getInt(query.getColumnIndexOrThrow(COLUMN_DEFINITION_ID));
                boolean succeeded = query.getInt(query.getColumnIndexOrThrow(COLUMN_SUCCEEDED)) == 1;
                String definitionName = query.getString(query.getColumnIndexOrThrow(COLUMN_DEFINITION_NAME));
                String buildNumber = query.getString(query.getColumnIndexOrThrow(COLUMN_BUILD_NUMBER));
                String commitMessage = query.getString(query.getColumnIndexOrThrow(COLUMN_COMMIT_MESSAGE));

                long finishDateLong = query.getLong(query.getColumnIndexOrThrow(COLUMN_FINISH_TIME));
                Date finishTime = new Date(finishDateLong);

                result.add(new CompactBuildInfo(definitionId, succeeded, definitionName, buildNumber, commitMessage, finishTime));
            }
        }
        return result;
    }

    private static class CacheDbOpenHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "personalproject.db";
        private static final int DATABASE_VERSION = 2;

        public CacheDbOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String query = "create table " + TABLE_COMPACT_ITEMS_CACHE + "" +
                    "(" +
                    COLUMN_DEFINITION_ID + " int not null constraint " + TABLE_COMPACT_ITEMS_CACHE + "_pk primary key," +
                    COLUMN_SUCCEEDED + " boolean not null," +
                    COLUMN_DEFINITION_NAME + " TEXT not null," +
                    COLUMN_BUILD_NUMBER + " TEXT not null," +
                    COLUMN_COMMIT_MESSAGE + " TEXT not null," +
                    COLUMN_FINISH_TIME + " INTEGER not null" +
                    ");";
            sqLiteDatabase.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPACT_ITEMS_CACHE);
            onCreate(sqLiteDatabase);
        }
    }
}
