package souza.scriptureapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileWriter;

import static souza.scriptureapp.MainActivity.INTENT_EXTRA_BOOK_NAME;
import static souza.scriptureapp.MainActivity.INTENT_EXTRA_CHAPTER_NAME;
import static souza.scriptureapp.MainActivity.INTENT_EXTRA_VERSE_NAME;

public class MyNextActivity extends AppCompatActivity {
    private static FileWriter file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_next);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        if (intent != null) { // If nothing was sent, make sure we don't try to read it
            String book = intent.getStringExtra("book");     // If param not there, then it will be null
            String chapter = intent.getStringExtra("chapter");  // If param not there, then set it to 0
            String verse = intent.getStringExtra("verse");
            String message = "Received intent with " + book + " " + chapter + ":" + verse;
            Log.d("Intent Received ", message);
            // Capture the layout's TextView and set the string as its text
            TextView textView = findViewById(R.id.textView);
            textView.setText(book + " " + chapter + ":" + verse);
        } else {
            TextView textView = findViewById(R.id.textView);
            textView.setText("Oh, don't you have a favorite scripture? Try Ether 12:27");
        }

    }

    public void saveFile(View view) {
        //write in a file
        Intent intent = getIntent();
        SharedPreferences sharedPref = this.getSharedPreferences("Scripture",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(INTENT_EXTRA_BOOK_NAME, intent.getStringExtra("book"));
        editor.putString(INTENT_EXTRA_CHAPTER_NAME, intent.getStringExtra("chapter"));
        editor.putString(INTENT_EXTRA_VERSE_NAME, intent.getStringExtra("verse"));
        editor.commit();

        Toast toast = Toast.makeText(getApplicationContext(), "Scripture saved successfully", Toast.LENGTH_SHORT);
        toast.show();
        returnMain(view);
    }

    public void returnMain(View View){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}