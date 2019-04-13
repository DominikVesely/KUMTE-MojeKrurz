package cz.uhk.fim.mojekurzy.gui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cz.uhk.fim.cnbrates.data.Currency;
import cz.uhk.fim.mojekurzy.R;

public class CurrencyAdapter extends RecyclerView.Adapter {

    List<Currency> data;

    public CurrencyAdapter(List<Currency> data) {
        this.data = data;
    }

    public void setData(List<Currency> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // definovani jedne polozky seznamu - vytvoreni
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.currency_item, viewGroup, false);

        return new CurrenncyViewHolder(view); // vraci komponentu, ktera drzi jednu polozku seznamu
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        // recyklace dat, podle scrollovani
        CurrenncyViewHolder cvh = (CurrenncyViewHolder) viewHolder;
        Currency c = data.get(i);
        cvh.currCode.setText(c.getCode());
        cvh.currRate.setText(String.valueOf(c.getRate()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class CurrenncyViewHolder extends RecyclerView.ViewHolder {
        TextView currCode;
        TextView currRate;

        public CurrenncyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.currCode = itemView.findViewById(R.id.currCode);
            this.currRate = itemView.findViewById(R.id.currRate);
        }
    }
}
