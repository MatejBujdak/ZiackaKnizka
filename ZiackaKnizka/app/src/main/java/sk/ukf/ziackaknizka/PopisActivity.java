package sk.ukf.ziackaknizka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PopisActivity extends AppCompatActivity {

    TextView tv_ziak;
    TextView tv_znamky;
    String znamky;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popis);

        tv_ziak = findViewById(R.id.textView);
        tv_znamky = findViewById(R.id.textView2);

        Intent intent = getIntent();
        String ziak = intent.getStringExtra("ziak");
        znamky = intent.getStringExtra("znamky");
        id = intent.getStringExtra("id");
        tv_ziak.setText(ziak);
        tv_znamky.setText(znamky);

        Button b = findViewById(R.id.button);
        b.setOnClickListener(view -> finish());

        Button b_edit = findViewById(R.id.editovat);
        b_edit.setOnClickListener(view -> {
            Intent intent1 = new Intent(PopisActivity.this, EditActivity.class);
            intent1.putExtra("znamky", znamky);
            startActivityForResult(intent1, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                znamky = data.getStringExtra("editedZnamky");
                tv_znamky.setText(znamky);

                Intent intent = new Intent();
                intent.putExtra("editedZnamky", znamky);
                intent.putExtra("id", id);
                setResult(RESULT_OK, intent);
            }
        }
    }




}
