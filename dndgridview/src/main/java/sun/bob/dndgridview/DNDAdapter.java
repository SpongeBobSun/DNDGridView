package sun.bob.dndgridview;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by bob.sun on 15/8/19.
 */
public class DNDAdapter extends ArrayAdapter {
    public DNDAdapter(Context context, int resource) {
        super(context, resource);
    }

    public DNDAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public DNDAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
    }

    public DNDAdapter(Context context, int resource, int textViewResourceId, Object[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public DNDAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    public DNDAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
