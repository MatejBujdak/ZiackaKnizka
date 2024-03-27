package sk.ukf.ziackaknizka;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<String> ziaci = new ArrayList<>();
    ArrayList<String> znamky = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listview);
        loadData();
        addListener();

    }

    // metoda ktora vytvara actionbar s 3 bodkami ktore predstavuju menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //metoda ktora sa vola automaticky, ked aktivita bola spustená pomocou startActivityForResult a vracia výsledok cez setResult()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                String ziak = data.getStringExtra("ziak");
                String id = data.getStringExtra("id");
                String znamky_ziaka = data.getStringExtra("znamky");
                String editedZnamky = data.getStringExtra("editedZnamky");

                if (editedZnamky != null && id != null) {
                    znamky.set(Integer.parseInt(id), editedZnamky);
                }

                if (ziak != null) {
                    znamky.add(znamky_ziaka);
                    adapter.add(ziak);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    // funkcia ktora sa vola pri kliknuti na polozku v actionbare
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.pridat) {
            startActivityForResult(new Intent(MainActivity.this, PridajActivity.class), 1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    nacitanie zakladnych dat
    public void loadData() {
        ziaci.add("Jozo");
        znamky.add("1, 2, 2, 3");

        ziaci.add("Marek");
        znamky.add("2, 3, 4, 2");

        ziaci.add("Peter");
        znamky.add("5, 2, 2, 3");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ziaci);

        lv.setAdapter(adapter);
    }

    // metoda na kontrolu kliknutia na prvok v ListView
    public void addListener() {
        AdapterView.OnItemClickListener listener = (adapterView, view, i, l) -> {
            Intent intent = new Intent(MainActivity.this, PopisActivity.class);
            String ziak = ziaci.get(i);
            String znamka = znamky.get(i);
            intent.putExtra("ziak", ziak);
            intent.putExtra("znamky", znamka);
            intent.putExtra("id", String.valueOf(i));

            startActivityForResult(intent, 1);
        };
        // ListView na ktory chcem definovat konkretny onclick ktory je ulozeny v listener
        lv.setOnItemClickListener(listener);
    }

}