package co.uk.loomknitting.mealpreplog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "meal_preps";
    private static final int DATABASE_VERSION = 1;

    private static final String MEAL_TABLE = "meal";
    private static final String MEAL_NAME = "name TEXT, ";
    private static final String WEEK_NUMBER = " number INTEGER, ";
    private static final String MEAL_TYPE = " type INTEGER, ";
    private static final String MEAL_DESCRIPTION = " description TEXT, ";
    private static final String MEAL_INGREDIENTS = " ingredients TEXT, ";
    private static final String LOW_CARB = " lowcarb INTEGER, ";
    private static final String LOW_FAT = " lowfat INTEGER, ";
    private static final String VEGETARIAN = " vegetarian INTEGER, ";
    private static final String COMPLETED = " completed INTEGER, ";
    private static final String STAR_RATING = " stars INTEGER, ";
    private static final String FAVOURITE = " favourite INTEGER, ";
    private static final String EASY = " easy INTEGER";

    private static final String MEAL_PREPS = "create table " + MEAL_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + MEAL_NAME + MEAL_TYPE + WEEK_NUMBER + MEAL_DESCRIPTION +
            MEAL_INGREDIENTS + LOW_CARB + LOW_FAT + VEGETARIAN + COMPLETED + STAR_RATING + FAVOURITE + EASY + " )";

    Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MEAL_PREPS);
    }

    public void onUpgrade(
            final SQLiteDatabase db, final int oldVersion,
            final int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MEAL_TABLE);

        onCreate(db);
/*  Implement the following if the table needs changed in production. Remember to upgrade the database version also
        int upgradeTo = oldVersion + 1;
        while (upgradeTo == newVersion)
        {
            switch (upgradeTo)
            {
                case 1:
                    db.execSQL("ALTER TABLE " + CUSTOMER_DONE_TABLE + " ADD COLUMN pph_done TEXT ");
                    break;
                case 2:
                    db.execSQL("ALTER TABLE " + CUSTOMER_TABLE + " ADD COLUMN house_image BLOB ");
                    break;
                case 3:
                    db.execSQL("ALTER TABLE " + CUSTOMER_DONE_TABLE + " ADD COLUMN clean_type INTEGER ");
                    break;
            }
            upgradeTo++;
        }
    }*/
    }

    /* Insert into database*/
    public void insertIntoDB(String name, int type, int week, String description, String ingredients, int low_carb, int low_fat,
                             int vegetarian, int completed, int star_rating, int favourite, int easy ) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();

        // _id is 0
        values.put("name", name);// ** 1
        values.put("type", type);//2
        values.put("number", week);//3
        values.put("description", description);//4
        values.put("ingredients", ingredients);//5
        values.put("lowcarb", low_carb);//6
        values.put("lowfat", low_fat);//7
        values.put("vegetarian", vegetarian);//8
        values.put("completed", completed);//9
        values.put("stars", star_rating);//10
        values.put("favourite", favourite);//11
        values.put("easy", easy);//12

        // 3. insert
        db.insert(MEAL_TABLE, null, values);
        // 4. close
        db.close();
    }


    // Used along with the DatabaseModel to fill a single list item and then insert into Recyclerview
    public List<DatabaseModel> getDataFromDB() {
        List<DatabaseModel> modelList = new ArrayList<DatabaseModel>();
        String query = "select * from " + MEAL_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                DatabaseModel model = new DatabaseModel();
                model.set_id(cursor.getInt(0));
                model.setName(cursor.getString(1));
                model.setWeeknumber(cursor.getInt(2));
                model.setType(cursor.getString(3));
                model.setDescription(cursor.getString(4));
                model.setIngredients(cursor.getString(5));
                model.setLow_carb(cursor.getInt(6));
                model.setLow_fat(cursor.getInt(7));
                model.setVegetarian(cursor.getInt(8));
                model.setCompleted(cursor.getInt(9));
                model.setStars(cursor.getInt(10));
                model.setFavourite(cursor.getInt(11));
                model.setEasy(cursor.getInt(12));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return modelList;
    }
}
