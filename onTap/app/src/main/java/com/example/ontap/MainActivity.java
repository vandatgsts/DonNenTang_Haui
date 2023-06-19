package com.example.ontap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.os.Bundle;
// thêm thư viện để thao tác với CSDL
import android.view.*;
import android.database.sqlite.*;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // code tạo csdl Demosql.db
    // khai bao database là đối tượng lớp SQLiteDatabase
    SQLiteDatabase database;
    DatabaseHandler databaseHandler;
    EditText edtMaSV,edtHoten,edtLop;
    StudentAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student selectedStudent = (Student) parent.getItemAtPosition(position);
                // Xử lý sự kiện click vào đối tượng Student tại vị trí 'position'
                // Do something with the selected student
                edtHoten.setText(selectedStudent.getName());
                edtMaSV.setText( selectedStudent.getMaSV());
                edtLop.setText(selectedStudent.getLop());
                // ... Xử lý thêm theo nhu cầu
            }
        });
    }
    public void getWidget(){
        // lấy dữ liệu từ giao diện vào các biến
        edtMaSV=findViewById(R.id.edtMasv);
        edtHoten=findViewById(R.id.edtHoten);
        edtLop=findViewById(R.id.edtLop);
        listView=findViewById(R.id.listSV);
    }
    // câu lệnh openOrCreateDatabase dùng để mở ra nếu có sẵn hoặc tạo mới nếu chưa có
    public void btnTaoDB(View v){
       // database=openOrCreateDatabase("Demosql.db",MODE_PRIVATE,null);
        databaseHandler=new DatabaseHandler(getApplicationContext());
        Toast.makeText(this,"Tạo CSDL thành công",Toast.LENGTH_LONG).show();
    }
    //sử dụng câu lệnh SQL tạo bảng
    // lưu ý SQLite chỉ thao tác trên file lên sử dụng string sql và thực thi bằng câu lệnh execSQL
    public  void btnTaoBangSV(View v){
        Toast.makeText(this,"Tạo bảng thành công",Toast.LENGTH_LONG).show();
    }
    // thêm sinh viên
    public void btnThemSV(View v){
        // sử dung ContentValues để lưu trữ các giá trị
        ContentValues sv= new ContentValues();
        // sử dụng phương thức put để truyền giá trí
//        sv.put("masv",edtMaSV.getText().toString());
//        sv.put("tensv",edtHoten.getText().toString());
//        sv.put("lop",edtLop.getText().toString());
        databaseHandler.addStudent(new Student(edtHoten.getText().toString(),edtMaSV.getText().toString(),edtLop.getText().toString()));
        // sử dụng database.insert để chèn vào bảng
        // nếu kq trả về = -1 là thêm không thành công trái lại đã thêm thành công
            Toast.makeText(this,"Thêm sv thành công",Toast.LENGTH_LONG).show();
    }
    // xem ds sv
    public void btnXemdssv(View v){
        ArrayList<Student> listStudent=new ArrayList<>();
        // Cursor dùng để lưu kết quả truy vấn
//        Cursor c=database.query("tblsv",null,null,null,null,null,null);
//        // chuyển con trỏ về bản ghi đầu tiên để duyệt từ đầu
//        c.moveToFirst();
//        // vòng lặp duyệt trong cursor kết thúc khi c ở sau bản ghi cuối
//        while (!c.isAfterLast()){
//            // gán kết quả cho data
//            Student student=new Student(c.getString(0),c.getString(1),c.getString(2));
//            // tiến thêm 1 bản ghi
//            edtHoten.setText(student.getName());
//            edtLop.setText(student.getLop());
//            edtLop.setText(student.getMaSV());
//            listStudent.add(student);
//            c.moveToNext();
//        }
        listStudent=databaseHandler.getAllStudents();
        // đóng cursor lại
        adapter=new StudentAdapter(getApplicationContext(),listStudent);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        // hiện thị dssv

       // Toast.makeText(this,data,Toast.LENGTH_LONG).show();
    }
    public void btnUpdateSv(View v){
        // dùng contentValues lữu trữ giá trị
        Student student=new Student();
        // cập nhật tensv
        student.setMaSV(edtMaSV.getText().toString());
        student.setName(edtHoten.getText().toString());
        student.setLop(edtLop.getText().toString());
        databaseHandler.updateStudent(student);
        // sử dụng câu lệnh update có điều kiện tai masv truyền vào
        Toast.makeText(this,"Cập nhật thành công",Toast.LENGTH_LONG).show();
    }
    public void btnXoaSV(View v){
        // xóa sv nếu có masv bằng masv truyền vào
       databaseHandler.deleteStudent(edtMaSV.getText().toString());
    }
}