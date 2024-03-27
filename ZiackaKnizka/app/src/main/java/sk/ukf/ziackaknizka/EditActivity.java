package sk.ukf.ziackaknizka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class EditActivity extends AppCompatActivity {

    EditText editZnamky;
    EditText addZnamka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editZnamky = findViewById(R.id.editZnamky);
        addZnamka = findViewById(R.id.addZnamka);

        String text = getIntent().getStringExtra("znamky");
        editZnamky.setText(text);

        Button buttonConfirm = findViewById(R.id.button2);
        Button buttonAddZnamka = findViewById(R.id.button_add);

        buttonConfirm.setOnClickListener(view -> {
            String editedZnamky = editZnamky.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("editedZnamky", editedZnamky);
            setResult(RESULT_OK, resultIntent);

            finish();
        });

        buttonAddZnamka.setOnClickListener(view -> {
            String newZnamka = addZnamka.getText().toString();
            String currentZnamky = editZnamky.getText().toString();

            if (newZnamka.isEmpty()) {
                Toast.makeText(EditActivity.this, "Zadajte hodnotu znamky", Toast.LENGTH_SHORT).show();
                return;
            } else if (Integer.parseInt(newZnamka) > 5) {
                Toast.makeText(EditActivity.this, "Maximálna povolena hodnota je 5", Toast.LENGTH_SHORT).show();
                return;
            }else if (currentZnamky.isEmpty()) {
                currentZnamky += newZnamka;
            } else {
                currentZnamky += ", " + newZnamka;
            }

            addZnamka.setText("");
            editZnamky.setText(currentZnamky);
        });
    }
}
