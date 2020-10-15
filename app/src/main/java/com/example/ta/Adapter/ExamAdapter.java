package com.example.ta.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ta.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ListViewHolder> {


    private JSONArray datas;
    public OnItemClickListener listener;

    public ExamAdapter(JSONArray datas, OnItemClickListener listener) {
        this.datas = datas;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_exam, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        try{
            holder.tvClasses.setText(datas.getJSONObject(position).getString("class_name"));
            holder.tvDate.setText(datas.getJSONObject(position).getString("date"));
            holder.tvTime.setText(datas.getJSONObject(position).getString("start_hour")+ " - "+datas.getJSONObject(position).getString("ending_hour"));
            holder.tvRoom.setText(datas.getJSONObject(position).getString("room"));
            holder.bind(datas.getJSONObject(position), listener);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return datas.length();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView tvClasses, tvDate, tvTime, tvRoom;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvClasses = itemView.findViewById(R.id.tv_class);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvRoom = itemView.findViewById(R.id.tv_room);
        }

        public void bind(final JSONObject item, final OnItemClickListener l){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        listener.onItemClick(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(JSONObject item) throws JSONException;
    }
}
