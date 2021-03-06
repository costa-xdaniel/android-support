package st.zudamue.support.android.component.adapter;

import android.content.Context;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xdaniel on 6/26/17.
 * @author Daniel Costa <costa.xdaniel@gmail.com/>
 */

public class FilterableRecyclerViewAdapter  extends RecyclerViewAdapter {

    private List< ItemDataSet > listResult;
    private RecyclerViewFilter filter;

    public FilterableRecyclerViewAdapter(Context context) {
        super(context);
    }
    public FilterableRecyclerViewAdapter() {
        this.listResult = new LinkedList<>();
    }

    @Override
    @Deprecated
    public boolean addItem(ItemDataSet itemDataSet) {
        return false;
    }

    @Override
    @Deprecated
    public boolean addItem(int index, ItemDataSet newItemDataSet) {
        return false;
    }

    @Override
    @Deprecated
    public boolean addItem(List<ItemDataSet> itemDataSetList) {
        return false;
    }

    @Override
    @Deprecated
    public boolean setItem(int index, ItemDataSet newItemDataSet) {
        return false;
    }

    @Override
    @Deprecated
    public boolean moveDataSet(int indexFrom, int indexTo) {
        return false;
    }

    @Override
    @Deprecated
    public ItemDataSet removeItem(int index) {
        return null;
    }


    @Override
    public List<ItemDataSet> getListItem() {
        return super.getListItem();
    }

    @Override
    public int getItemCount() {
        return this.listResult.size();
    }

    @Override
    protected ItemDataSet getItemDataSet(int index) {
        return this.listResult.get( index );
    }

    public void setNewDataSet(List<ItemDataSet> list ){
        super.clear();
        super.addItem( list );
        if( filter == null ) this.unFilter();
        else filter();
    }

    public FilterableRecyclerViewAdapter setFilter(RecyclerViewFilter filter) {
        this.filter = filter;
        return this;
    }

    public void filter(){
        this.listResult.clear();
        for ( ItemDataSet itemDataSet :  super.getListItem() ){
            if( this.filter.accept( itemDataSet ) ){
                this.listResult.add( itemDataSet );
            }
        }
        this.notifyDataSetChanged();
    }

    public void unFilter ( ) {
        this.listResult.clear();
        for ( ItemDataSet itemDataSet :  super.getListItem() ){
            this.listResult.add( itemDataSet );
        }
        this.notifyDataSetChanged();
    }
}
