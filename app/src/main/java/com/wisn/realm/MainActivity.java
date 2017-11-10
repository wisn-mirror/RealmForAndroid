package com.wisn.realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private CheckBox mCheckBox_asy;
    private CheckBox mCheckBox_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRealm();
    }

    @Override
    public void onClick(View v) {
        if(mCheckBox_asy.isChecked()){
            //异步任务
            AsyClick(v);
        }else{
            //同步任务
            SyncClick(v);
        }
    }
    //同步Synchronize
    public void  SyncClick(View v){
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
            final RealmResults<People> all = mRealm.where(People.class)
                                                   .findAll();
            updateView("result:"+all,false);
            if(all==null||all.size()==0){
                return;
            }
            mRealm.executeTransaction(new Realm.Transaction(){
                @Override
                public void execute(Realm realm) {
                    all.deleteFirstFromRealm();//删除第一个
                    all.deleteLastFromRealm();
                    all.deleteFromRealm(100);
                    // remove a single object
                    People people = all.get(3);
                    updateView(people.toString(),false);
                    people.deleteFromRealm();
                    all.deleteAllFromRealm();
                }
            });
        } else if (v == mUpdate) {
            mRealm.executeTransaction(new Realm.Transaction(){
                @Override
                public void execute(Realm realm) {
                    long start=System.nanoTime();
                    for(int i=0;i<1000;i++){
                        People people=new People();
                        people.setId(i);
                        people.setStringtest("wisn"+i+"update");
                        realm.copyToRealmOrUpdate(people);
                    }
                    long count=System.nanoTime()-start;
                    updateView("update 用时纳秒："+count,false);
                }
            });
        } else if (v == mQuery) {
            long start=System.nanoTime();
            RealmResults<People> all = mRealm.where(People.class)
                                             .findAll();
            long count=System.nanoTime()-start;
            Iterator<People> iterator = all.iterator();
            while(iterator.hasNext()){
                People next = iterator.next();
                updateView(next.toString(),true);
            }
            updateView("用时纳秒："+count,true);
        }
    }
    //异步Asynchronous
    private void AsyClick(View v) {
        if (v == mInsert) {
            mRealm.executeTransactionAsync(new Realm.Transaction(){
                @Override
                public void execute(Realm realm) {
                    for(int i=0;i<1000;i++){
                        People people=realm.createObject(People.class,i);
                        people.setStringtest("wisn"+i);
                    }
                }
            }, new Realm.Transaction.OnSuccess(){

                @Override
                public void onSuccess() {
                    updateView(" insert onSuccess",false);
                }
            }, new Realm.Transaction.OnError(){
                @Override
                public void onError(Throwable error) {
                    updateView(" insert onError",false);
                }
            });
        } else if (v == mDelete) {
            final RealmResults<People> all = mRealm.where(People.class)
                                                   .findAll();
            updateView("result:"+all,false);
            if(all==null||all.size()==0){
                return;
            }
            mRealm.executeTransactionAsync(new Realm.Transaction(){
                @Override
                public void execute(Realm realm) {
                    all.deleteFirstFromRealm();//删除第一个
                    all.deleteLastFromRealm();
                    all.deleteFromRealm(100);
                    // remove a single object
                    People people = all.get(3);
                    updateView(people.toString(),false);
                    people.deleteFromRealm();
                    all.deleteAllFromRealm();
                }
            }, new Realm.Transaction.OnSuccess(){
                @Override
                public void onSuccess() {
                    updateView(" mDelete onSuccess",false);
                }
            }, new Realm.Transaction.OnError(){
                @Override
                public void onError(Throwable error) {
                    updateView(" mDelete onError",false);
                }
            });
        } else if (v == mUpdate) {
            mRealm.executeTransactionAsync(new Realm.Transaction(){
                @Override
                public void execute(Realm realm) {
                    long start=System.nanoTime();
                    for(int i=0;i<1000;i++){
                        People people=new People();
                        people.setId(i);
                        people.setStringtest("wisn"+i+"update");
                        realm.copyToRealmOrUpdate(people);
                    }
                    long count=System.nanoTime()-start;
                    updateView("mUpdate 用时纳秒："+count,false);
                }
            }, new Realm.Transaction.OnSuccess(){

                @Override
                public void onSuccess() {
                    updateView(" mUpdate onSuccess",false);
                }
            }, new Realm.Transaction.OnError(){

                @Override
                public void onError(Throwable error) {
                    updateView(" mUpdate onError",false);
                }
            });
        } else if (v == mQuery) {
            long start=System.nanoTime();
            RealmResults<People> all = mRealm.where(People.class)
                                             .findAll();
            long count=System.nanoTime()-start;
            Iterator<People> iterator = all.iterator();
            while(iterator.hasNext()){
                People next = iterator.next();
                updateView(next.toString(),true);
            }
            updateView("用时纳秒："+count,true);

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
//                .modules()
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
        mCheckBox_asy = (CheckBox) findViewById(R.id.checkBox_Asy);
        mCheckBox_list = (CheckBox) findViewById(R.id.checkBox_list);
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
