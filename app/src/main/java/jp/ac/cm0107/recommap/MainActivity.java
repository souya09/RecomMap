package jp.ac.cm0107.recommap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btn ;
    Intent intent;
    Spinner spn ;
    int category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btnMapStart);
        spn = findViewById(R.id.spnMode);
        intent = new Intent(this,MapsActivity.class);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(
                this,
                R.array.category,
                android.R.layout.simple_spinner_dropdown_item
        );
        spn.setAdapter(adapter);

        category = 0;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = (String) spn.getSelectedItem();
                if (item.equals("ラーメン")){
                    category = 1;
                } else if (item.equals("コンビニ")) {
                    category = 2;
                } else if (item.equals("学校")) {
                    category = 3;
                }
                else if (item.equals("全部")) {
                    category = 0;
                }
                intent.putExtra("type",category);


                startActivity(intent);
            }
        });
    }
}