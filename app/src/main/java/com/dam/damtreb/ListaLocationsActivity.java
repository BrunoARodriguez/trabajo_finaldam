package com.dam.damtreb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.dam.damtreb.dao.LocationDao;
import com.dam.damtreb.dao.RepositoryDataBase;
import com.dam.damtreb.domain.Location;
import com.dam.damtreb.recyclerview.LocationRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListaLocationsActivity extends AppCompatActivity {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
private List<Location> locations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_locations);
        mRecycler = (RecyclerView) findViewById(R.id.recyclerViewLista);
        mRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mLayoutManager);

locations        = new ArrayList<>();

final  Runnable threadUpdateLista = new Runnable() {
    @Override
    public void run() {
        mRecyclerAdapter = new LocationRecyclerAdapter(locations, ListaLocationsActivity.this);
        mRecycler.setAdapter(mRecyclerAdapter);
    }
};

final  Runnable cargarLocations = new Runnable() {
    @Override
    public void run() {
        LocationDao dao = RepositoryDataBase.getInstance(ListaLocationsActivity.this)
                .getMyDataBase().locationDao();
        locations = dao.buscarLocation();
runOnUiThread(threadUpdateLista);
    }
};
Thread t1 = new Thread(cargarLocations);
t1.start();

    }

    public class DeleteTast extends AsyncTask<Location, Void, Void> {

        private Context myContext;
        private ProgressDialog progressDialog;

        public DeleteTast(Context context) {
            this.myContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(myContext, "Cargando favoritas", "Espere por favor...");
        }

        @Override
        protected Void doInBackground(Location... location) {
            LocationDao dao = RepositoryDataBase.getInstance(myContext).getMyDataBase().locationDao();
            dao.deleteLocation(location[0]);
            locations.clear();
            locations.addAll(dao.buscarLocation());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mRecyclerAdapter.notifyDataSetChanged();


        }
    }// cierra clase de asynkTast

}

