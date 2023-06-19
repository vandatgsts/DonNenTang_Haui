package com.example.ontap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Demosql.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tblsv";

    private static final String KEY_MaSV = "masv";
    private static final String KEY_NAME = "tensv";
    private static final String KEY_LOP = "lop";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format(
                "CREATE TABLE %s(%s TEXT PRIMARY KEY, %s TEXT, %s TEXT)",
                TABLE_NAME, KEY_MaSV, KEY_NAME, KEY_LOP);
        db.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);
        onCreate(db);
    }
    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MaSV, student.getMaSV());
        values.put(KEY_NAME, student.getName());
        values.put(KEY_LOP, student.getLop());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public Student getStudent(String studentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_MaSV + " = ?", new String[] { String.valueOf(studentId) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Student student = new Student(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2));
        return student;
    }
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student>  studentList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Student student = new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            studentList.add(student);
            cursor.moveToNext();
        }
        return studentList;
    }
    public void updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_LOP, student.getLop());
        db.update(TABLE_NAME, values, KEY_MaSV + " = ?", new String[] { String.valueOf(student.getMaSV()) });
        db.close();
    }
    public void deleteStudent(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_MaSV + " = ?", new String[] { String.valueOf(studentId) });
        db.close();
    }

}