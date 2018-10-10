package fitme.ai.databasetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import fitme.ai.databasetest.api.ApiManager;
import fitme.ai.databasetest.api.ApiService;
import fitme.ai.databasetest.bean.Goods;
import fitme.ai.databasetest.utils.DBHelper;
import fitme.ai.databasetest.utils.L;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity {

    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private String appcode = "APPCODE 5353076bdf71427c8c8aa5ab97eec66c";

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
                Goods goods = new Goods(1L,"水溶c100",4.5f,"2342354235235");
                insertData(goods);
                break;
            case R.id.bt_delete:
                deleteData(1L);
                break;
            case R.id.bt_alter:
                Goods goods_new = new Goods(1L,"水溶c1010",5.5f,"35564352234234");
                updateData(goods_new);
                break;
            case R.id.bt_search:
                List<Goods> goodsList = queryAll();
                for (int i=0;i<goodsList.size();i++){
                    goodsList.get(i).toString();
                    Log.i("debug_message",goodsList.get(i).toString());
                }
                break;
            case R.id.bt_search_from_internet:
                ApiManager.serviceBarcode
                        .getGoodsDetails(appcode,"6938166920785")
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.newThread())
                        .subscribe(new Subscriber<ResponseBody>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                L.i("e:"+e.toString());
                            }

                            @Override
                            public void onNext(ResponseBody responseBody) {
                                try {
                                    L.i("res:"+responseBody.string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                /*String url = "https://ali-barcode.showapi.com/barcode?code=6938166920785";
                String appcode = "5353076bdf71427c8c8aa5ab97eec66c";
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .addHeader("Authorization","APPCODE " + appcode)
                        .url(url)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        L.i("e:"+e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        L.i("response:"+response.body().string());
                    }
                });*/

                break;
            default:
                break;
        }
    }

    private void insertData(Goods goods){
        MyApplication.getDaoSession().getGoodsDao().insertOrReplace(goods);
        Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
    }

    private void deleteData(long id) {
        MyApplication.getDaoSession().getGoodsDao().deleteByKey(id);
        Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
    }

    private void updateData(Goods goods) {
        MyApplication.getDaoSession().getGoodsDao().update(goods);
        Toast.makeText(this, "修改成功：", Toast.LENGTH_SHORT).show();
    }


    /**
     * 查询全部数据
     */
    public static List<Goods> queryAll() {
        return MyApplication.getDaoSession().getGoodsDao().loadAll();
    }
}
