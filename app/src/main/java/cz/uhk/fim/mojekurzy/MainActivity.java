package cz.uhk.fim.mojekurzy;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.uhk.fim.cnbrates.data.Currency;
import cz.uhk.fim.cnbrates.data.CurrencyProvider;
import cz.uhk.fim.mojekurzy.gui.CurrencyAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CurrencyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CurrencyAdapter(new ArrayList<Currency>());
        recyclerView.setAdapter(adapter);
    }

    public void btnLoadClick(View view) {
        new UpdateCurrenciesTask(this).execute(new Date());
    }

    static class UpdateCurrenciesTask extends AsyncTask<Date, Integer, List<Currency>> {
        WeakReference<MainActivity> mainActivity;

        public UpdateCurrenciesTask(MainActivity mainActivity) {
            this.mainActivity = new WeakReference<>(mainActivity);
        }

        @Override
        protected List<Currency> doInBackground(Date... dates) {
            return CurrencyProvider.getInstance().getAllCurrencies(dates[0]);
        }

        @Override
        protected void onPostExecute(List<Currency> currencies) {
            if (mainActivity.get() != null)
            {
                mainActivity.get().adapter.setData(currencies);
                //recyclerView.invalidate();
                mainActivity.get().recyclerView.setAdapter(mainActivity.get().adapter);
            }
        }
    }
}
