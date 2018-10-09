package fitme.ai.databasetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import fitme.ai.databasetest.utils.DBHelper;

public class MainActivity extends Activity {

    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        sqLiteDatabase = dbHelper.getWritableDatabase();     //新建数据库
    }

    public void click(View view){
        switch (view.getId()){
            case R.id.bt_add:
                insertData();
                break;
            case R.id.bt_delete:
                deleteData();
                break;
            case R.id.bt_alter:
                updateData();
                break;
            case R.id.bt_search:
                queryData();
                break;
            default:
                break;
        }
    }

    private void insertData(){
        ContentValues values = new ContentValues();
        values.put(DBHelper.GOODS_NAME,"水溶C100");
        values.put(DBHelper.PRICE,4.5f);
        values.put(DBHelper.GOODS_BAR_CODE,"6921168500956");
        sqLiteDatabase.insert(DBHelper.TABLE_NAME,null,values);
        Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
    }

    private void deleteData() {
        int count = sqLiteDatabase.delete(DBHelper.TABLE_NAME, DBHelper.GOODS_NAME + " = ?", new String[]{"水溶C100"});
        Toast.makeText(this, "删除数量："+count, Toast.LENGTH_SHORT).show();
    }

    private void updateData() {
        ContentValues values = new ContentValues();
        values.put(DBHelper.GOODS_NAME, "小茗同学");
        values.put(DBHelper.PRICE, 5f);
        values.put(DBHelper.GOODS_BAR_CODE, "1234568500956");
        int count = sqLiteDatabase.update(DBHelper.TABLE_NAME, values, DBHelper.GOODS_NAME + " = ?", new String[]{"水溶C100"});
        Toast.makeText(this, "修改成功：" + count, Toast.LENGTH_SHORT).show();
    }

    private void queryData() {
        sqLiteDatabase.execSQL("select "+DBHelper.GOODS_NAME+" from "+DBHelper.TABLE_NAME);
    }

}
