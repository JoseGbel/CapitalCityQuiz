package mydomain.capitalcityquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WriteDataToSQLite {
    //TODO Move this class into the DatabaseCountriesRepository
    public static void dropDatatoDB (Context c, SQLiteDatabase db){
        try {
            //FileReader file = new FileReader(getString(R.string.filePathCountries));
            //FileReader file = new FileReader(fileName);
            //BufferedReader buffer = new BufferedReader(file);
            InputStream file = c.getResources().openRawResource(R.raw.allcountries);
            InputStreamReader reader = new InputStreamReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String line = "";
            String tableName ="COUNTRIES";
            String columns = "NAME, CAPITAL, CONTINENT";
            String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
            String str2 = ");";

            db.beginTransaction();

            while ((line = buffer.readLine()) != null) {
                StringBuilder sb = new StringBuilder(str1);
                String[] str = line.split(",");
                sb.append("'" + str[0] + "','");
                sb.append(str[1] + "','");
                sb.append(str[2] + "'");
                sb.append(str2);
                db.execSQL(sb.toString());
            }

            db.setTransactionSuccessful();
            db.endTransaction();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
