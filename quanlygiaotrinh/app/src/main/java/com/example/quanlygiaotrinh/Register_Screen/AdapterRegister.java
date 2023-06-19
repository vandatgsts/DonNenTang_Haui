package com.example.quanlygiaotrinh.Register_Screen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiaotrinh.Database.Book;
import com.example.quanlygiaotrinh.MainActivity;
import com.example.quanlygiaotrinh.R;

import java.util.ArrayList;

public class AdapterRegister extends RecyclerView.Adapter<AdapterRegister.ViewHolder> {
    ArrayList<Book> listBook;
    Context mContext;

    public AdapterRegister(ArrayList<Book> listBook, Context mContext) {
        this.listBook = listBook;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AdapterRegister.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterRegister.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_register,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRegister.ViewHolder holder, int position) {
        Book item=listBook.get(position);
        holder.textView.setText(item.getName());
        holder.layout.setOnClickListener(v -> {
            showMenu(item,holder.layout);
        });

    }

    @Override
    public int getItemCount() {
        return listBook.isEmpty()?0:listBook.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.register_item_txt);
            layout=itemView.findViewById(R.id.register_item_LayoutItem);
        }
    }
    void showMenu(Book book,View view){
        PopupMenu popupMenu = new PopupMenu(mContext, view); // context là Context hiện tại, view là View mà bạn muốn gắn PopupMenu vào

// Gắn menu.xml vào PopupMenu
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_register, popupMenu.getMenu());

// Xử lý sự kiện khi một mục trong PopupMenu được chọn
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.menu_AC){
                    showDialog(book);
                }
                else {
                    listBook.remove(book);
                    MainActivity.dbHelper.deleteBook(book.getId());
                }
                notifyDataSetChanged();
                return true;
            }
        });

// Hiển thị PopupMenu
        popupMenu.show();
    }
    void showDialog(Book book){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Duyet giao trinh này ?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng chọn đồng ý
                book.setStatus(2);
                MainActivity.dbHelper.updateBook(book);
                listBook.remove(book);
                notifyDataSetChanged();

            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng chọn hủy
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
