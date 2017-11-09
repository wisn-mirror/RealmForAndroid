package com.wisn.realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wisn.realm.bean.People;

import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "MainActivity";
    private Realm mRealm;
    private ScrollView mMScroll_info;
    private TextView mTextResult;
    private TextView mShareText;
    private TextView mShareTextdelete;
    private Button mInsert;
    private Button mDelete;
    private Button mUpdate;
    private Button mQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRealm();

    }

    @Override
    public void onClick(View v) {
        if (v == mInsert) {
            mRealm.executeTransaction(new Realm.Transaction(){
                @Override
                public void execute(Realm realm) {
                    for(int i=0;i<1000;i++){
                        People people=realm.createObject(People.class,i);
                        people.setStringtest("wisn"+i);
                    }
                }
            });
        } else if (v == mDelete) {

        } else if (v == mUpdate) {

        } else if (v == mQuery) {
            long start=System.currentTimeMillis();
            RealmResults<People> all = mRealm.where(People.class)
                                             .findAll();
            long count=System.currentTimeMillis()-start;
            Iterator<People> iterator = all.iterator();
            while(iterator.hasNext()){
                People next = iterator.next();
                updateView(next.toString(),true);
            }
            updateView("用时毫秒："+count,true);

        }
    }

    private void initRealm() {
        Realm.init(this);
        byte[] bytes = "938310fe8463d77715043a29f9643b3a4e85d675950a9006f79f965cb9abc0d2".getBytes();
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("aaa.realm")
                .schemaVersion(0)
//                .inMemory()//非持久化，在内存中
//                .encryptionKey(bytes)//指定数据库的密钥
                .deleteRealmIfMigrationNeeded()// 声明版本冲突时自动删除原数据库。
                .build();

//        Realm mRealm=Realm.getDefaultInstance();
        mRealm = Realm.getInstance(config);
        Log.d(TAG, "getPath:     " + mRealm.getPath());
        Log.d(TAG, "getVersion:  " + mRealm.getVersion());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }


    private void initView() {
        mMScroll_info = (ScrollView) findViewById(R.id.scroll_info);
        mTextResult = (TextView) findViewById(R.id.testResult);
        mShareText = (TextView) findViewById(R.id.shareText);
        mShareTextdelete = (TextView) findViewById(R.id.shareTextdelete);
        mInsert = (Button) findViewById(R.id.insert);
        mDelete = (Button) findViewById(R.id.delete);
        mUpdate = (Button) findViewById(R.id.update);
        mQuery = (Button) findViewById(R.id.query);
        mInsert.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mQuery.setOnClickListener(this);
        mShareTextdelete.setOnClickListener(this);
        mShareText.setOnClickListener(this);
    }

    public void updateView(final String msg, final boolean isAppend) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isAppend) {
                    mTextResult.append(msg + "\n");
                } else {
                    mTextResult.setText(msg);
                }
                mMScroll_info.fullScroll(ScrollView.FOCUS_DOWN);//滚动到底部
            }
        });
    }


}
