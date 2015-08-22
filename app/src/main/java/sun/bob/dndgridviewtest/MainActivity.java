package sun.bob.dndgridviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import sun.bob.dndgridview.DNDAdapter;
import sun.bob.dndgridview.DNDGridView;
import sun.bob.dndgridview.DNDViewHolder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((DNDGridView) findViewById(R.id.id_dndgridview)).setAdapter(new DNDAdapter(this, R.layout.dnd_item) {
            ArrayList data;
            {
                data = new ArrayList();
                for (int i = 0; i < 100; i++) {
                    data.add("" + i);
                }
                this.setCustomArray(data);

            }
            @Override
            public View getView(int posistion, View convertView, ViewGroup parent) {
                View ret = convertView;
                ViewHolder tag;
                if (convertView == null) {
                    ret = View.inflate(getContext(), R.layout.dnd_item, null);
                    tag = new ViewHolder(posistion);
                    tag.imageView = (ImageView) ret.findViewById(R.id.id_imageview);
                    tag.textView = (TextView) ret.findViewById(R.id.id_textview);
                    ret.setTag(tag);
                } else {
                    tag = (ViewHolder) ret.getTag();
                }
                tag.imageView.setImageResource(R.drawable.img0);
                tag.textView.setText((String) data.get(posistion));
                setUpDragNDrop(posistion,ret);
                return ret;
            }
        });
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

class ViewHolder extends DNDViewHolder {
    public ViewHolder(int posistion){
        super(posistion);
    }
    Object data;
    ImageView imageView;
    TextView textView;
}
