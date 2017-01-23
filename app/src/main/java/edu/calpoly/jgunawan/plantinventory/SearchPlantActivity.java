package edu.calpoly.jgunawan.plantinventory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import rx.subjects.PublishSubject;

public class SearchPlantActivity extends AppCompatActivity {

    ArrayList<Plant> mPlants;

    private static final String SAVED_LAYOUT_MANAGER = "layout-manager-state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_plant);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_search_plant);

        assert recyclerView != null;

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        if (savedInstanceState == null) {
            mPlants = Plant.getAllPlants();
        }
        else {
            mPlants = savedInstanceState.getParcelableArrayList("ANIMALS");
        }

        final PlantAdapter pa = new PlantAdapter(mPlants);
        recyclerView.setAdapter(pa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.searchmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public static class PlantAdapter extends RecyclerView.Adapter<PlantViewHolder> {

        private ArrayList<Plant> mPlants;
        private final PublishSubject<Plant> onClickSubject = PublishSubject.create();

        public PlantAdapter(ArrayList<Plant> plants) {
            this.mPlants = plants;
        }

        @Override
        public int getItemViewType(int position) {
            return R.layout.plant_item_view;
        }

        @Override
        public PlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PlantViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
        }

        @Override
        public void onBindViewHolder(PlantViewHolder holder, int position) {
            final Plant p = mPlants.get(position);
            holder.bind(p);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PlantInformationActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }

        public rx.Observable<Plant> getPositionClicks() {
            return onClickSubject.asObservable();
        }

        @Override
        public int getItemCount() {
            return mPlants.size();
        }
    }

    public static class PlantViewHolder extends RecyclerView.ViewHolder {

        private CardView mCv;
        private TextView mTv;

        public PlantViewHolder(View itemView) {
            super(itemView);
            mCv = (CardView) itemView.findViewById(R.id.plantItemCardView);
            mTv = (TextView) itemView.findViewById(R.id.plantItemTextView);
        }

        public void bind(Plant plant) {
            mTv.setText(plant.getCommonName());
        }
    }

    /*
    public void plantA(View view) {
        Intent intent = new Intent(this, PlantInformationActivity.class);
        startActivity(intent);
    }

    public void plantB(View view) {
        Intent intent = new Intent(this, PlantInformationActivity.class);
        startActivity(intent);
    }

    public void plantC(View view) {
        Intent intent = new Intent(this, PlantInformationActivity.class);
        startActivity(intent);
    }
    */
}
