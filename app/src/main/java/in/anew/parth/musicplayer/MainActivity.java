package in.anew.parth.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    MediaPlayer mp;
    ArrayAdapter<String> myadaptersong;
    ArrayAdapter<String> myadaptersearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mp = new MediaPlayer();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final String[] music={"Behti hawa sa tha vo",
                                "Gie me some sunshiine",
                                "jane nai denge tuje",
                                "Kuch to tha",
                                "lag jaa gale",
                                "Maa tuje salam",
                                "O heerie",
                                "Offo",
                                "rabta",
                                "Rr",
                                "Yeh jo dekh he"
                                };
        final int[] musicID={R.raw.behti_hawa_sa_tha_woh,R.raw.gie_me_some_sunshine,R.raw.jaane_nahin_denge,R.raw.kuch_to_tha,
        R.raw.lag_ja_gale,R.raw.maa_tuje_salam,R.raw.o_heerie,R.raw.offo,R.raw.rabta,R.raw.rr,R.raw.yeh_jo_desh};

        lv=(ListView) findViewById(R.id.lv);
        myadaptersong = new ArrayAdapter<String>(   this,
                                                    android.R.layout.simple_list_item_1,
                                                    music);
        lv.setAdapter(myadaptersong);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mp.reset();
                mp=MediaPlayer.create(MainActivity.this,musicID[position]);
                mp.start();
            }
        });

        SearchView searchView=(SearchView)findViewById(R.id.search);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    lv.setAdapter(null);

                    String[] final_songs={"o_heerie","offo"};

                    myadaptersearch=new ArrayAdapter<String>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            final_songs
                    );

                    lv.setAdapter(myadaptersearch);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.release();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
