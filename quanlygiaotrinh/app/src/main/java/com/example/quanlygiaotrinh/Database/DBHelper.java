package com.example.quanlygiaotrinh.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quanlygiaotrinh.Database.Book;
import com.example.quanlygiaotrinh.info_Screen.Procress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "book_procress1.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BOOK = "Book";
    private static final String COLUMN_BOOK_ID = "id";
    private static final String COLUMN_BOOK_NAME = "name";
    private static final String COLUMN_BOOK_CODE = "code";
    private static final String COLUMN_BOOK_CREDITS = "credits";
    private static final String COLUMN_BOOK_AUTHORS = "authors";
    private static final String COLUMN_BOOK_TIME = "time";
    private static final String COLUMN_BOOK_STATUS = "status";
    private static final String COLUMN_BOOK_ANS = "ans";

    public static final String COLUMN_BOOK_IDEAL = "ideal";

    private static final String TABLE_PROCRESS = "Procress";
    private static final String COLUMN_PROCRESS_ID = "id";
    private static final String COLUMN_PROCRESS_NAME = "name";
    private static final String COLUMN_PROCRESS_CONTENT = "content";
    private static final String COLUMN_PROCRESS_TIMESTART = "timeStart";
    private static final String COLUMN_PROCRESS_TIMEEDIT = "timeEdit";
    private static final String COLUMN_PROCRESS_STATUS = "status";
    private static final String COLUMN_PROCRESS_BOOKID = "bookId";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Book
        String createBookTable = "CREATE TABLE " + TABLE_BOOK + " (" +
                COLUMN_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BOOK_NAME + " TEXT, " +
                COLUMN_BOOK_CODE + " TEXT, " +
                COLUMN_BOOK_CREDITS + " INTEGER, " +
                COLUMN_BOOK_AUTHORS + " TEXT, " +
                COLUMN_BOOK_TIME + " TEXT, " +
                COLUMN_BOOK_IDEAL + " TEXT, " +  // Thêm cột "ideal" vào đây
                COLUMN_BOOK_STATUS + " INTEGER, " +
                COLUMN_BOOK_ANS + " INTEGER)";
        db.execSQL(createBookTable);

        // Tạo bảng Procress
        String createProcressTable = "CREATE TABLE " + TABLE_PROCRESS + " (" +
                COLUMN_PROCRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PROCRESS_NAME + " TEXT," +
                COLUMN_PROCRESS_CONTENT + " TEXT," +
                COLUMN_PROCRESS_TIMESTART + " TEXT," +
                COLUMN_PROCRESS_TIMEEDIT + " TEXT," +
                COLUMN_PROCRESS_STATUS + " INTEGER," +
                COLUMN_PROCRESS_BOOKID + " INTEGER," +
                " FOREIGN KEY (" + COLUMN_PROCRESS_BOOKID + ") REFERENCES " +
                TABLE_BOOK + "(" + COLUMN_BOOK_ID + ")" +
                ")";
        db.execSQL(createProcressTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROCRESS);

        // Tạo lại cơ sở dữ liệu
        onCreate(db);
    }

    // Thêm sách vào cơ sở dữ liệu
    public void addBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOK_NAME, book.getName());
        values.put(COLUMN_BOOK_CODE, book.getCode());
        values.put(COLUMN_BOOK_CREDITS, book.getCredits());
        values.put(COLUMN_BOOK_AUTHORS, convertListToString(book.getAuthors()));
        values.put(COLUMN_BOOK_TIME, book.getTime());
        values.put(COLUMN_BOOK_STATUS, book.getStatus());
        values.put(COLUMN_BOOK_ANS, book.getAns());
        values.put(COLUMN_BOOK_IDEAL,book.getIdeal());
        db.insert(TABLE_BOOK, null, values);
        db.close();
    }

    // Thêm quy trình vào cơ sở dữ liệu
    public void addProcress(Procress procress) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROCRESS_NAME, procress.getName());
        values.put(COLUMN_PROCRESS_CONTENT, procress.getContent());
        values.put(COLUMN_PROCRESS_TIMESTART, procress.getTimeStart());
        values.put(COLUMN_PROCRESS_TIMEEDIT, convertListToString(procress.getTimeEdit()));
        values.put(COLUMN_PROCRESS_STATUS, procress.getStatus());
        values.put(COLUMN_PROCRESS_BOOKID, procress.getBookId());
        db.insert(TABLE_PROCRESS, null, values);
        db.close();
    }

    // Sửa thông tin sách trong cơ sở dữ liệu
    public void updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOK_NAME, book.getName());
        values.put(COLUMN_BOOK_CODE, book.getCode());
        values.put(COLUMN_BOOK_CREDITS, book.getCredits());
        values.put(COLUMN_BOOK_AUTHORS, convertListToString(book.getAuthors()));
        values.put(COLUMN_BOOK_TIME, book.getTime());
        values.put(COLUMN_BOOK_STATUS, book.getStatus());
        values.put(COLUMN_BOOK_ANS, book.getAns());
        values.put(COLUMN_BOOK_IDEAL,book.getIdeal());
        db.update(TABLE_BOOK, values, COLUMN_BOOK_ID + " = ?",
                new String[]{String.valueOf(book.getId())});
        db.close();
    }

    // Sửa thông tin quy trình trong cơ sở dữ liệu
    public void updateProcress(Procress procress) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROCRESS_NAME, procress.getName());
        values.put(COLUMN_PROCRESS_CONTENT, procress.getContent());
        values.put(COLUMN_PROCRESS_TIMESTART, procress.getTimeStart());
        values.put(COLUMN_PROCRESS_TIMEEDIT, convertListToString(procress.getTimeEdit()));
        values.put(COLUMN_PROCRESS_STATUS, procress.getStatus());
        values.put(COLUMN_PROCRESS_BOOKID, procress.getBookId());
        db.update(TABLE_PROCRESS, values, COLUMN_PROCRESS_ID + " = ?",
                new String[]{String.valueOf(procress.getId())});
        db.close();
    }

    // Xóa sách khỏi cơ sở dữ liệu
    public void deleteBook(int bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOK, COLUMN_BOOK_ID + " = ?",
                new String[]{String.valueOf(bookId)});
        db.close();
    }

    // Xóa quy trình khỏi cơ sở dữ liệu
    public void deleteProcress(int procressId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROCRESS, COLUMN_PROCRESS_ID + " = ?",
                new String[]{String.valueOf(procressId)});
        db.close();
    }

    // Lấy danh sách tất cả các sách
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_BOOK;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_BOOK_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_BOOK_NAME);
            int codeIndex = cursor.getColumnIndex(COLUMN_BOOK_CODE);
            int creditsIndex = cursor.getColumnIndex(COLUMN_BOOK_CREDITS);
            int authorsIndex = cursor.getColumnIndex(COLUMN_BOOK_AUTHORS);
            int timeIndex = cursor.getColumnIndex(COLUMN_BOOK_TIME);
            int statusIndex = cursor.getColumnIndex(COLUMN_BOOK_STATUS);
            int ansIndex = cursor.getColumnIndex(COLUMN_BOOK_ANS);
            int idealIndex= cursor.getColumnIndex(COLUMN_BOOK_IDEAL);

            do {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                String code = cursor.getString(codeIndex);
                int credits = cursor.getInt(creditsIndex);
                String authorsString = (authorsIndex != -1) ? cursor.getString(authorsIndex) : "";
                String time = cursor.getString(timeIndex);
                int statusValue = cursor.getInt(statusIndex);
                int ansValue = cursor.getInt(ansIndex);
                ArrayList<String> authors = convertStringToList(authorsString);
                String idealValue=cursor.getString(idealIndex);
                Book book = new Book(id, name, code, credits, authors, time, statusValue, ansValue,idealValue);
                book.setId(id);
                bookList.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bookList;
    }

    // lấy danh sách theo status
    public ArrayList<Book> getBooksByStatus(int status) {
        ArrayList<Book> bookList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_BOOK + " WHERE " + COLUMN_BOOK_STATUS + " = ?";
        String[] selectionArgs = {String.valueOf(status)};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_BOOK_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_BOOK_NAME);
            int codeIndex = cursor.getColumnIndex(COLUMN_BOOK_CODE);
            int creditsIndex = cursor.getColumnIndex(COLUMN_BOOK_CREDITS);
            int authorsIndex = cursor.getColumnIndex(COLUMN_BOOK_AUTHORS);
            int timeIndex = cursor.getColumnIndex(COLUMN_BOOK_TIME);
            int statusIndex = cursor.getColumnIndex(COLUMN_BOOK_STATUS);
            int ansIndex = cursor.getColumnIndex(COLUMN_BOOK_ANS);
            int idealIndex= cursor.getColumnIndex(COLUMN_BOOK_IDEAL);
            do {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                String code = cursor.getString(codeIndex);
                int credits = cursor.getInt(creditsIndex);
                String authorsString = (authorsIndex != -1) ? cursor.getString(authorsIndex) : "";
                String time = cursor.getString(timeIndex);
                int statusValue = cursor.getInt(statusIndex);
                int ansValue = cursor.getInt(ansIndex);
                ArrayList<String> authors = convertStringToList(authorsString);
                String idealValue=cursor.getString(idealIndex);
                Book book = new Book(id, name, code, credits, authors, time, statusValue, ansValue,idealValue);
                book.setId(id);
                bookList.add(book);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return bookList;
    }

    // Lấy danh sách tất cả các quy trình
    public ArrayList<Procress> getProcessesByBookId(int bookId) {
        ArrayList<Procress> processList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PROCRESS + " WHERE " + COLUMN_PROCRESS_BOOKID + " = ?";
        String[] selectionArgs = {String.valueOf(bookId)};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_PROCRESS_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_PROCRESS_NAME);
            int contentIndex = cursor.getColumnIndex(COLUMN_PROCRESS_CONTENT);
            int timeStartIndex = cursor.getColumnIndex(COLUMN_PROCRESS_TIMESTART);
            int timeEditIndex = cursor.getColumnIndex(COLUMN_PROCRESS_TIMEEDIT);
            int statusIndex = cursor.getColumnIndex(COLUMN_PROCRESS_STATUS);

            do {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                String content = cursor.getString(contentIndex);
                String timeStart = cursor.getString(timeStartIndex);
                String timeEdit = cursor.getString(timeEditIndex);
                ArrayList<String> listTimeEdit = convertStringToList(timeEdit);
                int status = cursor.getInt(statusIndex);
                Procress procress = new Procress(id, name, content, timeStart, listTimeEdit, status, bookId);
                processList.add(procress);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return processList;
    }
    // clear Database
    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOK, null, null);
        db.delete(TABLE_PROCRESS, null, null);
        db.close();
    }

    // Chuyển đổi danh sách chuỗi thành chuỗi
    private String convertListToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append(",");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1); // Xóa dấu phẩy cuối cùng
        }
        return sb.toString();
    }

    // Chuyển đổi chuỗi thành danh sách chuỗi
    private ArrayList<String> convertStringToList(String s) {
        return new ArrayList<>(Arrays.asList(s.split(",")));
    }
}
