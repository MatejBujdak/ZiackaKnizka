package sk.ukf.ziackaknizka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PridajActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pridaj);

        Button b = findViewById(R.id.button2);
        b.setOnClickListener(view -> {
            EditText et_ziak = findViewById(R.id.etuloha);
            EditText et_znamky = findViewById(R.id.etpopis);

            String ziak = et_ziak.getText().toString();
            String znamky = et_znamky.getText().toString();

            Intent intent = new Intent();
            intent.putExtra("ziak", ziak);
            intent.putExtra("znamky", znamky);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}