package com.example.menu;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button, showPopup;
    Button buttonPopup;
    private PopupWindow popupWindow;
    private TextView menuItem1, menuItem2;

    // Khai báo các hằng số để thiết lập kích thước cho PopupWindow
    private static final int POPUP_WINDOW_WIDTH = 1000;
    private static final int POPUP_WINDOW_HEIGHT = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//
        button = (Button) findViewById(R.id.button_test);
        buttonPopup = findViewById(R.id.button_popup);
        registerForContextMenu(buttonPopup);// khai bao bien bam vao de hien thi
        buttonPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, buttonPopup);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                // Inflating the Popup menu using java code
                popup.getMenu().add(0, 1, 0, "popup menu");
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle() == "popup menu")
                            Toast.makeText(MainActivity.this, "if else ", Toast.LENGTH_SHORT).show();
                        switch (item.getItemId()) {
                            case R.id.one:
                            case 1:
                            case R.id.two:
                            case R.id.three:
                                Toast.makeText(MainActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });
        showPopup = findViewById(R.id.anchor_view);
        showPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomPopupMenu(v);
            }
        });

    }
    // khoi tao contextmenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
//        menu.setHeaderTitle("Context Menu");
//        menu.add(0, 1, 2, "Upload");
//      menu.add(0, 2, 1, "Search");
        //groud id: id de nhan biet menu cha con
        // item id: id rieng dê nhan biet
        //order: thu tu xuat hien tren xuong duoi
        // title
        // com.android.internal.view.menu.ContextMenuBuilder
    }
    // khoi tao option Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);// convert xml ve de code hieu duọc
        return true;
        //super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "Uploadtitle", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "Search Title", Toast.LENGTH_SHORT).show();
            case R.id.menuItem_bookmark:
                Toast.makeText(this, "Bookmark", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuItem_upload:
                Toast.makeText(this, "Upload", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuItem_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Toast.makeText(getBaseContext(), "item1", Toast.LENGTH_LONG).show();
                break;
            case R.id.item2:
                Toast.makeText(getBaseContext(), "item2", Toast.LENGTH_LONG).show();
                break;
            case R.id.item3:
                Toast.makeText(getBaseContext(), "item3", Toast.LENGTH_LONG).show();
                break;
            case R.id.item3_1:
                Toast.makeText(getBaseContext(), "item3.1", Toast.LENGTH_LONG).show();
            case R.id.item3_2:
                Toast.makeText(getBaseContext(), "item3.2", Toast.LENGTH_LONG).show();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    //custom layout PopupMenu
    private void setupPopupMenu() {
        // Khởi tạo PopupWindow với giao diện được định nghĩa trong popup_menu.xml
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_menu, null);
        popupWindow = new PopupWindow(popupView, POPUP_WINDOW_WIDTH, POPUP_WINDOW_HEIGHT, true);

        // Lấy các thành phần giao diện trong popup_menu.xml để tương tác với chúng
        menuItem1 = popupView.findViewById(R.id.menu_item_1);
        menuItem2 = popupView.findViewById(R.id.menu_item_2);

        // Thiết lập các sự kiện cho các thành phần giao diện trong PopupWindow
        menuItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi người dùng nhấp vào Menu Item 1
                Toast.makeText(MainActivity.this, "Item Menu1", Toast.LENGTH_SHORT).show();
                // Đóng PopupWindow sau khi hoàn thành xử lý sự kiện
                popupWindow.dismiss();
            }
        });
        menuItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi người dùng nhấp vào Menu Item 2
                Toast.makeText(MainActivity.this, "Item Menu2", Toast.LENGTH_SHORT).show();
                // Đóng PopupWindow sau khi hoàn thành xử lý sự kiện
                popupWindow.dismiss();
            }
        });
    }
    //code mac dinh
    private void showPopupMenu(View anchorView) {
        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1] - popupWindow.getHeight();
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, x, y);
    }

    private void showCustomPopupMenu(View anchorView) {
        setupPopupMenu();
        showPopupMenu(anchorView);
    }

}