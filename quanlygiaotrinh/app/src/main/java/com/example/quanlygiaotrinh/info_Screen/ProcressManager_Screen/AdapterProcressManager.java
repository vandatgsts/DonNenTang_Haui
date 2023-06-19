package com.example.quanlygiaotrinh.info_Screen.ProcressManager_Screen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiaotrinh.Database.GetTimeInPhone;
import com.example.quanlygiaotrinh.MainActivity;
import com.example.quanlygiaotrinh.R;

import java.util.ArrayList;

public class AdapterProcressManager extends RecyclerView.Adapter<AdapterProcressManager.ViewHolder> {
    ArrayList<Procress> list;
    Context mcontext;// man hinh
    public AdapterProcressManager(ArrayList<Procress> list, Context mcontext) {
        this.list = list;
        this.mcontext = mcontext;
    }
    // khoi tao theo file xml da custom
    @NonNull
    @Override
    public AdapterProcressManager.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterProcressManager.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_manager_procress,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProcressManager.ViewHolder holder, int position) {
        Procress item=list.get(position);
        holder.txtName.setText(item.getName());
        holder.txtTimeStart.setText(item.getTimeStart());
        if(item.getTimeEdit().isEmpty())
            item.getTimeEdit().add("null");
        holder.txtTimeEdit.setText(item.getTimeEdit().get(item.getTimeEdit().size()-1));
        holder.txtContent.setText(item.getContent());

        // tao spinner status
        String[] status={"Chưa làm","Đã nhận","Đã xong"};

        // tao adapter spiner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mcontext, android.R.layout.simple_spinner_item, status);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.statusSpinner.setAdapter(adapter);
        holder.statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // cap nhat status cua process
                if(position!=0) {
                    item.setStatus(position + 1);
                    item.getTimeEdit().add(GetTimeInPhone.GetDateTime());
                    holder.txtTimeEdit.setText(item.getTimeEdit().get(item.getTimeEdit().size() - 1));
                    MainActivity.dbHelper.updateProcress(item);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.isEmpty()?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtContent;
        Spinner statusSpinner;
        TextView txtTimeStart;
        TextView txtTimeEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.process_txt_name);
            txtContent=itemView.findViewById(R.id.process_txt_content);
            statusSpinner=itemView.findViewById(R.id.process_txt_status);
            txtTimeStart=itemView.findViewById(R.id.process_txt_time);
            txtTimeEdit=itemView.findViewById(R.id.process_txt_time2);
        }
    }

}
