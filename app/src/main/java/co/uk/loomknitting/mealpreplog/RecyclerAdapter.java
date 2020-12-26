package co.uk.loomknitting.mealpreplog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static List<DatabaseModel> dbList;
    static Context context;
    RecyclerAdapter(Context context, List<DatabaseModel> dbList){
        this.dbList = new ArrayList<>();
        this.context = context;
        this.dbList = dbList;

    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.single_list_item, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {

        holder.week_number.setText(String.valueOf(dbList.get(position).getWeeknumber()));
        holder.meal_name.setText(String.valueOf(dbList.get(position).getName()));
        holder.meal_type.setText(String.valueOf(dbList.get(position).getType()));
        holder.meal_description.setText(String.valueOf(dbList.get(position).getDescription()));
        holder.meal_ingredients.setText(String.valueOf(dbList.get(position).getIngredients()));
        holder.low_carb.setText(String.valueOf(dbList.get(position).getLow_carb()));
        holder.low_fat.setText(String.valueOf(dbList.get(position).getLow_fat()));
        holder.vegetarian.setText(String.valueOf(dbList.get(position).getVegetarian()));

        int comp=dbList.get(position).getCompleted();
        if(comp==1){
            holder.completed.setText(String.valueOf(dbList.get(position).getCompleted()));
            holder.favourite.setText(String.valueOf(dbList.get(position).getFavourite()));
            holder.easy.setText(String.valueOf(dbList.get(position).getEasy()));

            int rbStars=dbList.get(position).getStars();
            holder.ratingbar.setRating(rbStars);//setNumStars(rbStars);
        }
        else{

        }
    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        public TextView week_number;
        public TextView meal_name;
        public TextView meal_type;
        public TextView meal_description;
        public TextView meal_ingredients;
        public TextView low_carb;
        public TextView low_fat;
        public TextView vegetarian;
        public TextView favourite;
        public TextView easy;
        public TextView completed;
        public RatingBar ratingbar;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            week_number = itemLayoutView.findViewById(R.id.tvWeekNumber);
            meal_name = itemLayoutView.findViewById(R.id.tvName);
            meal_type = itemLayoutView.findViewById(R.id.tvType);
            meal_description = itemLayoutView.findViewById(R.id.tvDescription);
            meal_ingredients = itemLayoutView.findViewById(R.id.tvIngredients);
            low_carb = itemLayoutView.findViewById(R.id.tvLowCarb);
            low_fat = itemLayoutView.findViewById(R.id.tvLowFat);
            vegetarian = itemLayoutView.findViewById(R.id.tvVegetarian);
            favourite = itemLayoutView.findViewById(R.id.tvFavourite);
            easy = itemLayoutView.findViewById(R.id.tvEasy);
            completed = itemLayoutView.findViewById(R.id.tvCompleted);
            ratingbar = itemLayoutView.findViewById(R.id.rbStars);

            itemLayoutView.setOnClickListener(this);
            itemLayoutView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {

            return true;
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            int position = dbList.get(adapterPosition).get_id();
            Bundle extras = new Bundle();
            extras.putInt("position", position);
            Intent intent = new Intent(context, AddMeal.class);

            // If there are any problems with Recyclerview the following 4 lines were added to stop a double tap.
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            intent.putExtras(extras);
            context.startActivity(intent);
        }

    }
}