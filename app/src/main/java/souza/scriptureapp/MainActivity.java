package souza.scriptureapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String INTENT_EXTRA_BOOK_NAME = "book";
    public final static String INTENT_EXTRA_CHAPTER_NAME = "chapter";
    public final static String INTENT_EXTRA_VERSE_NAME = "verse";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        // Do something
        EditText editText = (EditText) findViewById(R.id.editBook);
        String book = editText.getText().toString();

        editText = (EditText) findViewById(R.id.editChapter);
        String chapter = editText.getText().toString();

        editText = (EditText) findViewById(R.id.editVerse);
        String verse = editText.getText().toString();
        //Log message
        String message = "About to create intent with " + book + " " + chapter + ":" + verse;
        Log.d("Intent Creation ", message);
        //Create intent
        Intent intent = new Intent(this, MyNextActivity.class);
        intent.putExtra(INTENT_EXTRA_BOOK_NAME, book);
        intent.putExtra(INTENT_EXTRA_CHAPTER_NAME, chapter);
        intent.putExtra(INTENT_EXTRA_VERSE_NAME, verse);
        startActivity(intent);
    }

    public void loadFile(View view){

        SharedPreferences sharedPref = getSharedPreferences("Scripture", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String book = sharedPref.getString(INTENT_EXTRA_BOOK_NAME, "book");
        String chapter = sharedPref.getString(INTENT_EXTRA_CHAPTER_NAME, "chapter");
        String verse = sharedPref.getString(INTENT_EXTRA_VERSE_NAME, "verse");

        EditText editBook = (EditText) findViewById(R.id.editBook);
        EditText editChapter = (EditText) findViewById(R.id.editChapter);
        EditText editVerse = (EditText) findViewById(R.id.editVerse);

        editBook.setText(book);
        editChapter.setText(chapter);
        editVerse.setText(verse);
    }
}