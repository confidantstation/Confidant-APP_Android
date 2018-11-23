package com.stratagile.pnrouter.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ROUTER_ENTITY".
*/
public class RouterEntityDao extends AbstractDao<RouterEntity, Long> {

    public static final String TABLENAME = "ROUTER_ENTITY";

    /**
     * Properties of entity RouterEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property RouterId = new Property(1, String.class, "routerId", false, "ROUTER_ID");
        public final static Property UserSn = new Property(2, String.class, "userSn", false, "USER_SN");
        public final static Property Username = new Property(3, String.class, "username", false, "USERNAME");
        public final static Property UserId = new Property(4, String.class, "userId", false, "USER_ID");
        public final static Property RouterName = new Property(5, String.class, "routerName", false, "ROUTER_NAME");
        public final static Property DataFileVersion = new Property(6, Integer.class, "dataFileVersion", false, "DATA_FILE_VERSION");
        public final static Property DataFilePay = new Property(7, String.class, "dataFilePay", false, "DATA_FILE_PAY");
        public final static Property LastCheck = new Property(8, boolean.class, "lastCheck", false, "LAST_CHECK");
        public final static Property LoginKey = new Property(9, String.class, "loginKey", false, "LOGIN_KEY");
    }


    public RouterEntityDao(DaoConfig config) {
        super(config);
    }
    
    public RouterEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ROUTER_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ROUTER_ID\" TEXT," + // 1: routerId
                "\"USER_SN\" TEXT," + // 2: userSn
                "\"USERNAME\" TEXT," + // 3: username
                "\"USER_ID\" TEXT," + // 4: userId
                "\"ROUTER_NAME\" TEXT," + // 5: routerName
                "\"DATA_FILE_VERSION\" INTEGER," + // 6: dataFileVersion
                "\"DATA_FILE_PAY\" TEXT," + // 7: dataFilePay
                "\"LAST_CHECK\" INTEGER NOT NULL ," + // 8: lastCheck
                "\"LOGIN_KEY\" TEXT);"); // 9: loginKey
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ROUTER_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, RouterEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String routerId = entity.getRouterId();
        if (routerId != null) {
            stmt.bindString(2, routerId);
        }
 
        String userSn = entity.getUserSn();
        if (userSn != null) {
            stmt.bindString(3, userSn);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(4, username);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(5, userId);
        }
 
        String routerName = entity.getRouterName();
        if (routerName != null) {
            stmt.bindString(6, routerName);
        }
 
        Integer dataFileVersion = entity.getDataFileVersion();
        if (dataFileVersion != null) {
            stmt.bindLong(7, dataFileVersion);
        }
 
        String dataFilePay = entity.getDataFilePay();
        if (dataFilePay != null) {
            stmt.bindString(8, dataFilePay);
        }
        stmt.bindLong(9, entity.getLastCheck() ? 1L: 0L);
 
        String loginKey = entity.getLoginKey();
        if (loginKey != null) {
            stmt.bindString(10, loginKey);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, RouterEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String routerId = entity.getRouterId();
        if (routerId != null) {
            stmt.bindString(2, routerId);
        }
 
        String userSn = entity.getUserSn();
        if (userSn != null) {
            stmt.bindString(3, userSn);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(4, username);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(5, userId);
        }
 
        String routerName = entity.getRouterName();
        if (routerName != null) {
            stmt.bindString(6, routerName);
        }
 
        Integer dataFileVersion = entity.getDataFileVersion();
        if (dataFileVersion != null) {
            stmt.bindLong(7, dataFileVersion);
        }
 
        String dataFilePay = entity.getDataFilePay();
        if (dataFilePay != null) {
            stmt.bindString(8, dataFilePay);
        }
        stmt.bindLong(9, entity.getLastCheck() ? 1L: 0L);
 
        String loginKey = entity.getLoginKey();
        if (loginKey != null) {
            stmt.bindString(10, loginKey);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public RouterEntity readEntity(Cursor cursor, int offset) {
        RouterEntity entity = new RouterEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // routerId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // userSn
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // username
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // userId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // routerName
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // dataFileVersion
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // dataFilePay
            cursor.getShort(offset + 8) != 0, // lastCheck
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // loginKey
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, RouterEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRouterId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUserSn(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUsername(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setUserId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setRouterName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDataFileVersion(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setDataFilePay(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setLastCheck(cursor.getShort(offset + 8) != 0);
        entity.setLoginKey(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(RouterEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(RouterEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(RouterEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
