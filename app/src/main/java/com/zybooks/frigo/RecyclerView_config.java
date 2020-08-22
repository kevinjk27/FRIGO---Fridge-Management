package com.zybooks.frigo;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_config {
    private Context mContext;
    private ItemAdapter mItemAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Items> items, List<String>keys){
        mContext= context;
//        mItemAdapter = new ItemAdapter(items, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mItemAdapter);
    }

    class ItemView extends RecyclerView.ViewHolder {
        private TextView mItem;
        private TextView mQty;
        private TextView mDOP;
        private TextView mKeepDy;
        private TextView mNotes;


        private String key;

        public ItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.item_list, parent));

            mItem = (TextView) itemView.findViewById(R.id.textView12);
            mQty = (TextView) itemView.findViewById(R.id.textView13);
            mDOP = (TextView) itemView.findViewById(R.id.textView16);
            mKeepDy = (TextView) itemView.findViewById(R.id.textView17);
            mNotes = (TextView) itemView.findViewById(R.id.textView18);

        }

        public void bind (Items items, String key){
            mItem.setText((items.getItemName()));
            mQty.setText((items.getQuantity()));
            mDOP.setText((items.getDateOfPurchase()));
            mKeepDy.setText((items.getKeepDays()));
            mNotes.setText((items.getNotes()));
            this.key=key;

        }


    }

    class ItemAdapter extends RecyclerView.Adapter<ItemView>{

        private List<Items> mItemsList;
        private List<String> mKeys;




        @NonNull
        @Override
        public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemView holder, int position) {
            holder.bind(mItemsList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mItemsList.size();
        }
    }
}
