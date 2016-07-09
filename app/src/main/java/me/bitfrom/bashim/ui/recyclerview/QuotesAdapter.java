package me.bitfrom.bashim.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bitfrom.bashim.R;
import me.bitfrom.bashim.core.storage.entities.QuoteEntity;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuoteHolder> {

    private List<QuoteEntity> quoteEntities;

    @Inject
    public QuotesAdapter() {

    }

    public void setQuoteEntities(List<QuoteEntity> quoteEntities) {
        this.quoteEntities = quoteEntities;
    }

    @Override
    public QuoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_item, parent,
                false);
        return new QuoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(QuoteHolder holder, int position) {
        QuoteEntity quoteEntity = quoteEntities.get(position);
        holder.content.setText(quoteEntity.content());
    }

    @Override
    public int getItemCount() {
        return quoteEntities.size();
    }

    public static class QuoteHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.quote_content)
        TextView content;

        public QuoteHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}