package vuthiduong.m.bai8_2_internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements OnClickListener{
    Button btnread,btnwrite;
    EditText editdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();
        btnread.setOnClickListener(this);
        btnwrite.setOnClickListener(this);
    }

    private void getWidget() {
        btnread=(Button) findViewById(R.id.btnreaddata);
        btnwrite=(Button) findViewById(R.id.btnwritedata);
        editdata=(EditText) findViewById(R.id.editdata);
    }
    public void onClick(View v) {
        if(v.getId()==R.id.btnreaddata)
        {
            readData();//đọc file trong bộ nhớ điện thoại
//            readData2();//đọc file trong res của ứng dụng
        }
        else if(v.getId()==R.id.btnwritedata)
        {
            writeData();
        }
    }
    /**
     * Hàm đọc tập tin trong Android
     * Dùng openFileInput trong Android để đọc
     * ra FileInputStream
     */
    public void readData()
    {
        try {
            FileInputStream in= openFileInput("myfile.txt");
            BufferedReader reader=new BufferedReader(
                    new InputStreamReader(in));
            String data="";
            StringBuilder builder=new StringBuilder();
            while((data=reader.readLine())!=null)
            {
                builder.append(data);
                builder.append("\n");
            }
            in.close();
            editdata.setText(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Hàm ghi tập tin trong Android
     * dùng openFileOutput để ghi
     * openFileOutput tạo ra FileOutputStream
     */
    public void writeData()
    {
        try {
            FileOutputStream out= openFileOutput("myfile.txt",0);
            OutputStreamWriter writer= new OutputStreamWriter(out);
            writer.write(editdata.getText().toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * hàm này là đọc Resource File
     */
    public void readData2() {
        String data;
        InputStream in = getResources().openRawResource(R.raw.myfile);
        InputStreamReader inreader = new InputStreamReader(in);
        BufferedReader bufreader = new BufferedReader(inreader);
        StringBuilder builder = new StringBuilder();
        if (in != null) {
            try {
                while ((data = bufreader.readLine()) != null) {
                    builder.append(data);
                    builder.append("\n");
                }
                in.close();
                editdata.setText(editdata.getText() +builder.toString());
            } catch (IOException ex) {
                Log.e("ERROR", ex.getMessage());
            }
        }
    }
}