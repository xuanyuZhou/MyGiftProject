package com.example.dllo.mygiftproject.db;

import android.database.sqlite.SQLiteDatabase;

import com.example.dllo.mygiftproject.ui.activity.MyApp;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by dllo on 16/7/26.
 * 数据库的单例类  自己封装的
 */
public class DBTool implements IDBTool<LikeBean> {
    // 定义静态对象
    private static DBTool mInstance;
    /**
     * 数据库辅助类 3个
     */
    private DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase database;
    private DaoMaster master;
    /**
     * 数据库操作类 2个
     */
    private DaoSession daoSession;
    private LikeBeanDao likeBeanDao;
    /**
     * 不可修改的静态常量,我的数据库名字
     */
    private static final String DB_NAME = "giftproject.db";

    /**
     * 查询数据库条件类
     */
    private QueryBuilder queryBuilder;

    // 对外提供获取对象的方法
    public static DBTool getInstance() {
        if (mInstance == null) {
            synchronized (DBTool.class) {
                if (mInstance == null) {
                    mInstance = new DBTool();
                }
            }
        }
        return mInstance;
    }

    /**
     * 私有化构造方法
     */
    private DBTool() {
        // 初始化操作类
        likeBeanDao = getLikeBeanDao();
        // 初始化游标
        queryBuilder = likeBeanDao.queryBuilder();
    }

    /**
     * 内部方法 获取数据库帮助类
     *
     * @return
     */
    private DaoMaster.DevOpenHelper getHelper() {
        if (helper == null) {
            // 三个参数 context, 数据库名, 游标(因为greenDao有 所以设置为空)
            helper = new DaoMaster.DevOpenHelper(MyApp.getContext(), DB_NAME, null);
        }
        return helper;
    }

    /**
     * 内部方法 获得database
     */
    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = getHelper().getWritableDatabase();
        }
        return database;
    }

    /**
     * 内部方法 获得DaoMaster
     */
    private DaoMaster getMaster() {
        if (master == null) {
            master = new DaoMaster(getDatabase());
        }
        return master;
    }

    /**
     * 内部方法 获得daoSession
     */
    private DaoSession getDaoSession() {
        if (daoSession == null) {
            daoSession = getMaster().newSession();
        }
        return daoSession;
    }

    /**
     * 上方几个private的get方法都是为本方法服务的,通过上方的4个方法的嵌套,来获得一个数据库操作对象
     * 获得操作对象的过程中创建了数据库,内部方法 获取数据的方法 likeBeanDao数据库操作类
     */
    private LikeBeanDao _getLikeBeanDao() {
        if (likeBeanDao == null) {
            likeBeanDao = getDaoSession().getLikeBeanDao();
        }
        return likeBeanDao;
    }

    /**
     * 对外提供获取数据的方法
     */
    public LikeBeanDao getLikeBeanDao() {
        return _getLikeBeanDao();
    }


    /**************************对外提供接口****************************/
    /**
     * 添加单条数据
     *
     * @param likeBean
     */
    @Override
    public void insert(LikeBean likeBean) {
        getInstance()._insert(likeBean);
    }

    /**
     * 对内提供的方法
     *
     * @param likeBean
     */
    private void _insert(LikeBean likeBean) {
        likeBeanDao.insert(likeBean);
    }

    /**
     * 对内提供添加集合的方法
     *
     * @param likeBeen
     */
    private void _insert(List<LikeBean> likeBeen) {
        likeBeanDao.insertOrReplaceInTx(likeBeen);
    }

    /**
     * 对外提供添加集合的方法
     *
     * @param likeBeen
     */
    @Override
    public void insert(List<LikeBean> likeBeen) {
        getInstance()._insert(likeBeen);
    }

    /**
     * 对内提供通过单条(用户)查找的方法
     *
     * @param userName
     * @return
     */
    private List<LikeBean> _queryBy(String userName) {
        queryBuilder.where(LikeBeanDao.Properties.UserName.eq(userName));
        queryBuilder.limit(20);
        return queryBuilder.list();
    }

    /**
     * 对外提供
     *
     * @param userName
     * @return
     */
    @Override
    public List<LikeBean> queryBy(String userName) {
        return getInstance()._queryBy(userName);
    }

    // 通过集合查找
    @Override
    public List<LikeBean> queryBy(List<LikeBean> likeBeen) {
        return null;
    }

    /**
     * 删除的方法
     */
    private void _deleteBy(String imageUrl) {
        queryBuilder.where(LikeBeanDao.Properties.ImageUrl.eq(imageUrl));
        List<LikeBean> ss = queryBuilder.list();
        likeBeanDao.deleteInTx(ss);
    }
    // 对外删除方法
    public static void deleteBy(String imageUrl) {
        getInstance()._deleteBy(imageUrl);
    }

    // 删除方法
    public void delete(LikeBean bean){
        likeBeanDao.delete(bean);
    }
}

