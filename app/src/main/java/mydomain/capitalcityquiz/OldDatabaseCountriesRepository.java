package mydomain.capitalcityquiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.List;

public final class OldDatabaseCountriesRepository implements CountriesRepository {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private OldDatabaseCountriesRepository() {

    }

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "countries";
        public static final String NAME = "name";
        public static final String CAPITAL = "capital";
        public static final String CONTINENT = "continent";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.NAME + " TEXT," +
                    FeedEntry.CAPITAL + " TEXT, " +
                    FeedEntry.CONTINENT + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public static class ReadDataFromSQLite {

        public static Cursor getAllCountries(SQLiteDatabase db){
            //DO I HAVE TO COMMENT THIS???
            //db = dbHelper.getReadableDatabase();

            Cursor cursor = db.query(
                    OldDatabaseCountriesRepository.FeedEntry.TABLE_NAME,   // The table to query
                    null,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );
            return cursor;
        }
    }

    @Override
    public List<Country> getCountries() {
        return null;
    }

}