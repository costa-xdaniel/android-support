package st.domain.support.android.sql;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import st.domain.support.android.sql.builder.*;
import st.domain.support.android.sql.builder.Insert;
import st.domain.support.android.sql.builder.Select;
import st.domain.support.android.sql.sqlite.AssetsDatabase;
import st.domain.support.android.sql.sqlite.Query;
import st.domain.support.android.sql.sqlite.SQLResources;
import st.domain.support.android.sql.sqlite.UpdatableSQL;
import st.domain.support.android.sql.type.DateCharSequence;
import st.domain.support.android.sql.type.DoubleCharSequence;
import st.domain.support.android.sql.type.FloatCharSequence;
import st.domain.support.android.sql.type.IntegerCharSequence;
import st.domain.support.android.sql.type.LongCharSequence;


/**
 *
 * Created by xdata on 7/25/16.
 */
public class LiteDatabase {

    private final Context context;
    private final AssetsDatabase SQLite;
    private final Query query;
    private final UpdatableSQL updatableSQL;

    private SQLResources liteResources;

    public LiteDatabase(Context context, String dataBaseName, int version) {
        this.context = context;
        this.SQLite =  new AssetsDatabase(context, dataBaseName, version);
        this.liteResources = new SQLResources(this.SQLite.getDataBase());

        this.query = new Query(this.getDataBase());
        this.updatableSQL = new UpdatableSQL(this.getDataBase());
    }

    public LiteDatabase query( CharSequence query ) {
        this.query.execute( query );
        return this;
    }

    public void query(String query, Object ... argments) {
        this.query.execute(query, argments);
    }


    public void execute( CharSequence charSequence ) {
        this.updatableSQL.execute( charSequence );
    }

    public void execute(String sql, Object ... argments) {
        this.updatableSQL.execute( sql, argments );
    }

    public st.domain.support.android.sql.Insert.ResultInsertInto insertInto(String table) {
        return new Insert().insertInto(table);
    }

    public void onExecutResult(UpdatableSQL.OnResultExecute onResultExecute) {
        this.updatableSQL.onExecuteResult (onResultExecute);
    }

    public Long getResultExecut () {
        return this.updatableSQL.getResultExecute();
    }

    public UpdatableSQL.ExecuteType getExecuteTypeExecute () {
        return this.updatableSQL.getResultType();
    }

    public void onQueryResult(OnQueryResult onCatch){
        this.query.forLoopCursor(onCatch);
    }

    public List<SQLRow> onCatchResult(OnQueryResult onCatchSQLRow) {
        return this.query.catchAllResult(onCatchSQLRow);
    }

    public List<SQLRow> catchAllResult () {
        return this.query.catchAllResult();
    }

    public Select select(CharSequence ... columns){
        return new Select(columns);
    }



    public CharSequence sum (String ... columns){
        return AggregateFunction.function("sum", columns);
    }

    public CharSequence count (CharSequence ... columns){
        return AggregateFunction.function("count", (String[]) columns);
    }

    public CharSequence max (CharSequence ... columns){
        return AggregateFunction.function("max", (String[]) columns);
    }

    public CharSequence min (CharSequence ... columns){
        return AggregateFunction.function("min", (String[]) columns);
    }

    public CharSequence avg (CharSequence ... columns){
        return AggregateFunction.function("avg", (String[]) columns);
    }



    public CharSequence strftime (String format, Object values) {
        return Function.function("strftime", format, values);
    }

    public Function upper (Object values) {
        return Function.function("upper", values);
    }

    public CharSequence lower (Object values) {
        return Function.function("lower", values);
    }

    public CharSequence column (String column){
        return st.domain.support.android.sql.Column.column(column);
    }

    public CharSequence value(Byte value){
        return String.valueOf(value);
    }

    public CharSequence value(Integer value){
        return new IntegerCharSequence(value);
    }

    public CharSequence value(Long value){
        return new LongCharSequence(value);
    }

    public CharSequence value(Float value){
        return new FloatCharSequence( value );
    }

    public CharSequence value(Double value){
        return new DoubleCharSequence( value );
    }

    public DateCharSequence value(java.util.Date date) {
        return new DateCharSequence(date.getTime());
    }

    public SQLResources getResources()
    {
        return this.liteResources;
    }

    public Context getContext()
    {
        return context;
    }


    public SQLiteDatabase getDataBase() {
        return this.SQLite.getWritableDatabase();
    }

    public void cloneDatabase() {
        this.SQLite.outputDatabase();
    }

    public enum Operaction {
        INSERT
    }

    public abstract class OnAllQueryResults implements OnQueryResult {

        @Override
        public boolean accept(SQLRow row) {
            this.onRow(row);
            return true;
        }

        protected abstract void onRow(SQLRow row);
    }

}