package sun.bob.dndgridview;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob.sun on 15/8/19.
 */
public abstract class DNDAdapter extends ArrayAdapter {

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

    private ArrayList data;
    private boolean isCustomArray = false;

    public DNDAdapter(Context context, int resource) {
        super(context, resource);
    }


    @Override
    public abstract View getView(int posistion, View convertView, ViewGroup parent);

    @Override
    public int getCount(){
        return isCustomArray ? data.size() : super.getCount();
    }

    public void setCustomArray(ArrayList array){
        this.isCustomArray = true;
        this.data = array;
    }

    public void setUpDragNDrop(final int posistion, final View view){

        if (view.getTag() == null){
            view.setTag(new DNDViewHolder(posistion));
        } else {
            ((DNDViewHolder) view.getTag()).posistion = posistion;
        }

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent data = new Intent();
                data.putExtra("position", ((DNDViewHolder) view.getTag()).posistion);
                view.startDrag(ClipData.newIntent("data", data), new View.DragShadowBuilder(v), v, 0);
                return true;
            }
        });

        view.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        break;
                    case DragEvent.ACTION_DROP:
                        //Delete dragged item
                        int dragPosition = event.getClipData().getItemAt(0).getIntent().getIntExtra("position", -1);
                        if (dragPosition < 0) {
                            return false;
                        }
                        Object dragItem = isCustomArray ? data.get(dragPosition) : DNDAdapter.this.getItem(dragPosition);
                        if (isCustomArray){
                            data.remove(dragPosition);
                            data.add(((DNDViewHolder) v.getTag()).posistion, dragItem);
                        } else {
                            DNDAdapter.this.remove(dragPosition);
                            //Insert dropped item
                            DNDAdapter.this.insert(dragItem, ((DNDViewHolder) v.getTag()).posistion);
                        }

                        DNDAdapter.this.notifyDataSetChanged();
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        GridView parent = (GridView) v.getParent();
                        int pos = ((DNDViewHolder) v.getTag()).posistion;
                        if (pos > parent.getLastVisiblePosition() - parent.getNumColumns()){
                            parent.smoothScrollByOffset(1);
                        }
                        if (pos < parent.getFirstVisiblePosition() + parent.getNumColumns()){
                            parent.smoothScrollByOffset(-1);
                        }
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

    }
}