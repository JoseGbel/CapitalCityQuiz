package mydomain.capitalcityquiz;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.util.LinkedList;
import java.util.List;

public class DatabaseCountriesRepository implements CountriesRepository {

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "countries";
        public static final String NAME = "name";
        public static final String CAPITAL = "capital";
        public static final String CONTINENT = "continent";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseCountriesRepository.FeedEntry.TABLE_NAME + " (" +
                    DatabaseCountriesRepository.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    DatabaseCountriesRepository.FeedEntry.NAME + " TEXT," +
                    DatabaseCountriesRepository.FeedEntry.CAPITAL + " TEXT, " +
                    DatabaseCountriesRepository.FeedEntry.CONTINENT + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseCountriesRepository.FeedEntry.TABLE_NAME;

    private final DatabaseHelper databaseHelper;

    public DatabaseCountriesRepository(Context context) {

        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }

    @Override
    public List<Country> getCountries() {
        try {
            List<Country> list = new LinkedList<>();
            SQLiteDatabase db = databaseHelper.getReadableDatabase();

            Cursor cursor = db.query(
                    FeedEntry.TABLE_NAME,   // The table to query
                    null,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );

            cursor.moveToFirst();
            while(cursor.moveToNext()) {
                Country t = new Country(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                list.add(t);
            }
            cursor.close();

            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Boom");
        }
    }
}
