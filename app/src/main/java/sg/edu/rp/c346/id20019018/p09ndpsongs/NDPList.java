package sg.edu.rp.c346.id20019018.p09ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class NDPList extends AppCompatActivity {

    ArrayList<Song> al;
    ListView lv;
    ArrayAdapter<Song> aa;
    Button show , back, showAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_d_p_list);

        show = findViewById(R.id.btnShow);
        back = findViewById(R.id.btnBack);
        showAll = findViewById(R.id.btnshowA);
        lv = findViewById(R.id.lv);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(NDPList.this);
                al.clear();

                String rating  = "5";
                al.addAll(dbh.getAllSongs(rating));


                aa.notifyDataSetChanged();
            }
        });

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(NDPList.this);
                al.clear();

                al.addAll(dbh.getAllSongs());

                aa.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song data = al.get(position);
                Intent i = new Intent(NDPList.this,
                        NDPEdit.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(NDPList.this, MainActivity.class);
                startActivity(j);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        showAll.performClick();
    }
}