package sg.edu.rp.c346.id22017424.p08_song;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTitle, editSingers, editYear;
    private RadioGroup radioGroup;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTitle = findViewById(R.id.etTitle);
        editSingers = findViewById(R.id.etName);
        editYear = findViewById(R.id.etYear);
        radioGroup = findViewById(R.id.radioGroup);

        dbHelper = new DBHelper(this);
    }

    public void saveSong(View view) {
        String title = editTitle.getText().toString();
        String singers = editSingers.getText().toString();
        int year = Integer.parseInt(editYear.getText().toString());
        int stars = getSelectedStars();

        Song song = new Song(title, singers, year, stars);
        dbHelper.addSong(song);

        Toast.makeText(this, "Song saved", Toast.LENGTH_SHORT).show();

        // Clear input fields
        editTitle.setText("");
        editSingers.setText("");
        editYear.setText("");
        radioGroup.clearCheck();
    }

    public void displaySongs(View view) {
        Intent intent = new Intent(MainActivity.this, SongsActivity.class);
        startActivity(intent);
    }

    private int getSelectedStars() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        String starsText = radioButton.getText().toString();
        return Integer.parseInt(starsText);
    }
}