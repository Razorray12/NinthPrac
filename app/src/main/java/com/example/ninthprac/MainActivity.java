package com.example.ninthprac;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME_KEY = "file_name_key";
    private static final String FILE_CONTENT_KEY = "file_content_key";

    private EditText fileNameEditText;
    private EditText fileContentEditText;
    private Button createButton;
    private Button appendButton;
    private Button readButton;
    private Button deleteButton;
    private TextView outputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileNameEditText = findViewById(R.id.filename_edittext);
        fileContentEditText = findViewById(R.id.content_edittext);
        createButton = findViewById(R.id.save_button);
        //appendButton = findViewById(R.id.append_button);
        readButton = findViewById(R.id.read_button);
        deleteButton = findViewById(R.id.delete_button);
        outputTextView = findViewById(R.id.output_textview);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = fileNameEditText.getText().toString();
                String fileContent = fileContentEditText.getText().toString();
                try {
                    FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                    fos.write(fileContent.getBytes());
                    fos.close();
                    Toast.makeText(MainActivity.this, "Файл создан", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


//        appendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String fileName = fileNameEditText.getText().toString();
//                String fileContent = fileContentEditText.getText().toString();
//                try {
//                    FileOutputStream fos = openFileOutput(fileName, Context.MODE_APPEND);
//                    fos.write(fileContent.getBytes());
//                    fos.close();
//                    Toast.makeText(MainActivity.this, "Информация добавлена", Toast.LENGTH_SHORT).show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });


        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = fileNameEditText.getText().toString();
                try {
                    FileInputStream fis = openFileInput(fileName);
                    byte[] bytes = new byte[fis.available()];
                    fis.read(bytes);
                    fis.close();
                    String fileContent = new String(bytes);
                    outputTextView.setText(fileContent);
                    Toast.makeText(MainActivity.this, "Файл прочитан", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = fileNameEditText.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Удаление файла");
                builder.setMessage("Вы действительно хотите удалить файл " + fileName + "?");
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteFile(fileName);
                        Toast.makeText(MainActivity.this, "Файл удален", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

//        createButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String fileName = fileNameEditText.getText().toString();
//                String fileContent = fileContentEditText.getText().toString();
//                try {
//                    // Получаем путь к внешнему хранилищу
//                    File directory = Environment.getExternalStorageDirectory();
//
//                    // Создаем файл в нужной директории
//                    File file = new File(directory, fileName);
//
//                    // Открываем поток для записи в файл
//                    FileOutputStream fos = new FileOutputStream(file);
//
//                    // Записываем содержимое в файл
//                    fos.write(fileContent.getBytes());
//
//                    // Закрываем поток
//                    fos.close();
//
//                    Toast.makeText(MainActivity.this, "Файл создан", Toast.LENGTH_SHORT).show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        readButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String fileName = fileNameEditText.getText().toString();
//                try {
//                    // Получаем путь к внешнему хранилищу
//                    File directory = Environment.getExternalStorageDirectory();
//
//                    // Создаем файл в нужной директории
//                    File file = new File(directory, fileName);
//
//                    // Открываем поток для чтения из файла
//                    FileInputStream fis = new FileInputStream(file);
//
//                    // Читаем содержимое файла
//                    byte[] bytes = new byte[(int) file.length()];
//                    fis.read(bytes);
//
//                    // Закрываем поток
//                    fis.close();
//
//                    // Преобразуем байты в строку
//                    String fileContent = new String(bytes);
//
//                    // Выводим содержимое файла на экран
//                    outputTextView.setText(fileContent);
//
//                    Toast.makeText(MainActivity.this, "Файл прочитан", Toast.LENGTH_SHORT).show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        })
//        Button deleteButton = findViewById(R.id.delete_button);
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText fileNameEditText = findViewById(R.id.filename_edittext);
//                String fileName = fileNameEditText.getText().toString();
//
//                File directory = Environment.getExternalStorageDirectory();
//                File file = new File(directory, fileName);
//
//                if (file.exists()) {
//                    boolean success = file.delete();
//                    if (success) {
//                        Toast.makeText(MainActivity.this, "Файл удален", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(MainActivity.this, "Не удалось удалить файл", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(MainActivity.this, "Файл не существует", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fileNameEditText != null && fileContentEditText != null) {
            outState.putString(FILE_NAME_KEY, fileNameEditText.getText().toString());
            outState.putString(FILE_CONTENT_KEY, fileContentEditText.getText().toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            String fileName = savedInstanceState.getString(FILE_NAME_KEY);
            String fileContent = savedInstanceState.getString(FILE_CONTENT_KEY);
            fileNameEditText.setText(fileName);
            fileContentEditText.setText(fileContent);
        }
    }
}

