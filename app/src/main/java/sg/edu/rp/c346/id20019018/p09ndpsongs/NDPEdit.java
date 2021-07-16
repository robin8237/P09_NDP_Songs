package sg.edu.rp.c346.id20019018.p09ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NDPEdit extends AppCompatActivity {

    EditText etSong,etSinger,etYear, etID;
    RadioGroup rgStars;
    Button btnUpd,btnDel,btnCan;
    RadioButton rb1,rb2,rb3,rb4,rb5;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_d_p_edit);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID = findViewById(R.id.etID);
        etSong = findViewById(R.id.etSong);
        etSinger = findViewById(R.id.etSing);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rg);
        btnCan = findViewById(R.id.btnCan);
        btnDel = findViewById(R.id.btnDel);
        btnUpd = findViewById(R.id.btnUpd);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        DBHelper dbh = new DBHelper(NDPEdit.this);

        etID.setText(data.getId()+"");
        etID.setEnabled(false);
        etSinger.setText(data.getSingers());
        etSong.setText(data.getTitle());
        etYear.setText(data.getYear()+"");

        if(data.getStars() == 1)
        {
            rb1.setChecked(true);
        }
        else if(data.getStars() == 2)
        {
            rb2.setChecked(true);
        }
        else if(data.getStars() == 3)
        {
            rb3.setChecked(true);
        }
        else if(data.getStars() == 4)
        {
            rb4.setChecked(true);
        }
        else if(data.getStars() == 5)
        {
            rb5.setChecked(true);
        }

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbh.deleteSong(data.getId());
                finish();
            }
        });

        btnCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(NDPEdit.this,NDPList.class);
                startActivity(k);
            }
        });

        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = etSong.getText().toString().trim();
                String singer = etSinger.getText().toString().trim();
                int year = -1;
                boolean strEmp = false;
                boolean strYear = false;
                if (etYear.getText().toString().trim().length() == 0) {
                    strYear = false;
                } else {
                    strYear = true;
                    year = Integer.parseInt(etYear.getText().toString().trim());
                }

                if (title.length() == 0 || singer.length() == 0) {
                    Toast.makeText(NDPEdit.this, "Data Incomplete", Toast.LENGTH_SHORT).show();
                    strEmp = false;
                    return;
                } else {
                    strEmp = true;
                }

                int stars = -1;
                if (rgStars.getCheckedRadioButtonId() == R.id.rb1) {
                    stars = 1;
                } else if (rgStars.getCheckedRadioButtonId() == R.id.rb2) {
                    stars = 2;
                } else if (rgStars.getCheckedRadioButtonId() == R.id.rb3) {
                    stars = 3;
                } else if (rgStars.getCheckedRadioButtonId() == R.id.rb4) {
                    stars = 4;
                } else if (rgStars.getCheckedRadioButtonId() == R.id.rb5) {
                    stars = 5;
                } else {
                    stars = -1;
                }

                if (strEmp == true && strYear == true && stars != -1)
                {
                    data.setTitle(title);
                    data.setSingers(singer);
                    data.setYear(year);
                    data.setStars(stars);
                    dbh.updateSong(data);
                    dbh.close();
                    Toast.makeText(NDPEdit.this,"Song Updated",Toast.LENGTH_SHORT).show();
                    Intent j = new Intent(NDPEdit.this,NDPList.class);
                    startActivity(j);
                }
                else
                {
                    Toast.makeText(NDPEdit.this,"Data Incomplete",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}