package co.uk.loomknitting.mealpreplog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab= findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, AddMeal.class);
            startActivity(intent);



        });

    }
}