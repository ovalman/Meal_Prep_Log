package co.uk.loomknitting.mealpreplog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddMeal extends AppCompatActivity {

    CheckBox cbComplete;
    EditText etWeekNumber;
    Button btnPlus, btnNegative;

    private CheckBox lowCarb, lowFat, vegetarian, favourite, easy;
    private EditText mealName, mealDescription, mealIngredients;
    private int weekNumber=1;
    private LinearLayout llBottom, llTop;

    RecyclerView.Adapter mAdapter;

    RecyclerView mRecyclerView;
    List<DatabaseModel> dbList;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        dbList = new ArrayList<>();
        databaseHelper=new DatabaseHelper(this);
        dbList = databaseHelper.getDataFromDB();

        llBottom= findViewById(R.id.llBottom);
        llTop= findViewById(R.id.llTop);

        cbComplete = findViewById(R.id.cbCompleted);
        cbComplete.setOnClickListener(view -> {
            if(((CompoundButton) view).isChecked()){
                llBottom.setVisibility(View.VISIBLE);
                llTop.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Checked", Toast.LENGTH_SHORT).show();
            } else {
                llBottom.setVisibility(View.INVISIBLE);
                llTop.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Not Checked", Toast.LENGTH_SHORT).show();
            }
        });

        btnPlus=findViewById(R.id.btnPlus);
        btnNegative=findViewById(R.id.btnMinus);
        etWeekNumber=findViewById(R.id.etWeekNo);

        btnPlus.setOnClickListener(v -> {
            weekNumber++;
            etWeekNumber.setText((String.valueOf(weekNumber)));
        });
        btnNegative.setOnClickListener(v -> {
            weekNumber--;
            if(weekNumber<1){
                weekNumber=1;
            }
            etWeekNumber.setText((String.valueOf(weekNumber)));
        });

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdapter(this, dbList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addMeal(View view) {
        mealName=findViewById(R.id.mealName);
        String name=mealName.getText().toString();
        if(name.isEmpty()){
            Toast.makeText(this, "You must give this meal a name", Toast.LENGTH_LONG).show();
            return;
        }
        mealDescription=findViewById(R.id.mealDescription);
        String description=mealDescription.getText().toString();
        if(description.isEmpty()){
            Toast.makeText(this, "You must give this meal a description", Toast.LENGTH_LONG).show();
            return;
        }
        mealIngredients=findViewById(R.id.mealIngredients);
        String ingredients=mealIngredients.getText().toString();
        if(ingredients.isEmpty()){
            Toast.makeText(this, "You must give this meal a description", Toast.LENGTH_LONG).show();
            return;
        }
        lowCarb=findViewById(R.id.cbLowCarb);
        lowFat=findViewById(R.id.cbLowFat);
        vegetarian=findViewById(R.id.cbVegetarian);
        favourite=findViewById(R.id.cbFavourite);
        easy=findViewById(R.id.cbEasy);
        cbComplete=findViewById(R.id.cbCompleted);

        int lc=0, lf=0, vt=0, fv=0, ey=0, ct=0;
        if(lowCarb.isChecked()){
            lc=1;
        }
        if(lowFat.isChecked()){
            lf=1;
        }
        if(vegetarian.isChecked()){
            vt=1;
        }
        if(favourite.isChecked()){
            fv=1;
        }
        if(easy.isChecked()){
            ey=1;
        }
        if(cbComplete.isChecked()){
            ct=1;
        }

        RatingBar rb=findViewById(R.id.ratingBar);
        int rating= (int) rb.getRating();

        Spinner spinner=findViewById(R.id.spMealType);
        int type=spinner.getSelectedItemPosition();

        //MEAL_NAME + MEAL_TYPE + WEEK_NUMBER + MEAL_DESCRIPTION +
        //            MEAL_INGREDIENTS + LOW_CARB + LOW_FAT + VEGETARIAN + COMPLETED + STAR_RATING + FAVOURITE + EASY
        databaseHelper=new DatabaseHelper(AddMeal.this);
        databaseHelper.insertIntoDB(name, type, weekNumber, description, ingredients, lc, lf, vt, ct, rating, fv, ey);
        databaseHelper.close();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        dbList = databaseHelper.getDataFromDB();
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        // TODO: 22/12/2020 Get this fixed properly, poor use of code. 

        Toast.makeText(AddMeal.this, "Data Inserted", Toast.LENGTH_SHORT).show();
    }

}