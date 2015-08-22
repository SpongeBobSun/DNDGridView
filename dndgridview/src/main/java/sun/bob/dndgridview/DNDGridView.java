package sun.bob.dndgridview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * Created by bob.sun on 15/8/19.
 */
public class DNDGridView extends GridView {
    public DNDGridView(Context context) {
        super(context);
        this.setNumColumns(3);
    }

    public DNDGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setNumColumns(3);
    }

    /**
     * Set adapter of DNDGridView.
     * @param adapter Must be an instance of DNDAdapter or will throw.
     */
    @Override
    public void setAdapter(ListAdapter adapter){
        if ( !(adapter instanceof DNDAdapter) ){
                throw new IllegalArgumentException("adapter must be an instance of DNDAdapter!");
        }
        super.setAdapter(adapter);
    }
}
