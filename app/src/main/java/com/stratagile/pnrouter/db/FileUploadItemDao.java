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
 * DAO for table "FILE_UPLOAD_ITEM".
*/
public class FileUploadItemDao extends AbstractDao<FileUploadItem, Long> {

    public static final String TABLENAME = "FILE_UPLOAD_ITEM";

    /**
     * Properties of entity FileUploadItem.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Depens = new Property(1, Integer.class, "depens", false, "DEPENS");
        public final static Property UserId = new Property(2, String.class, "UserId", false, "USER_ID");
        public final static Property Type = new Property(3, Integer.class, "type", false, "TYPE");
        public final static Property FileId = new Property(4, String.class, "fileId", false, "FILE_ID");
        public final static Property Size = new Property(5, Long.class, "size", false, "SIZE");
        public final static Property Md5 = new Property(6, String.class, "md5", false, "MD5");
        public final static Property FName = new Property(7, String.class, "fName", false, "F_NAME");
        public final static Property FKey = new Property(8, String.class, "fKey", false, "F_KEY");
        public final static Property FInfo = new Property(9, String.class, "fInfo", false, "F_INFO");
        public final static Property PathId = new Property(10, Integer.class, "pathId", false, "PATH_ID");
        public final static Property PathName = new Property(11, String.class, "pathName", false, "PATH_NAME");
    }


    public FileUploadItemDao(DaoConfig config) {
        super(config);
    }
    
    public FileUploadItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FILE_UPLOAD_ITEM\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"DEPENS\" INTEGER," + // 1: depens
                "\"USER_ID\" TEXT," + // 2: UserId
                "\"TYPE\" INTEGER," + // 3: type
                "\"FILE_ID\" TEXT," + // 4: fileId
                "\"SIZE\" INTEGER," + // 5: size
                "\"MD5\" TEXT," + // 6: md5
                "\"F_NAME\" TEXT," + // 7: fName
                "\"F_KEY\" TEXT," + // 8: fKey
                "\"F_INFO\" TEXT," + // 9: fInfo
                "\"PATH_ID\" INTEGER," + // 10: pathId
                "\"PATH_NAME\" TEXT);"); // 11: pathName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FILE_UPLOAD_ITEM\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FileUploadItem entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer depens = entity.getDepens();
        if (depens != null) {
            stmt.bindLong(2, depens);
        }
 
        String UserId = entity.getUserId();
        if (UserId != null) {
            stmt.bindString(3, UserId);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(4, type);
        }
 
        String fileId = entity.getFileId();
        if (fileId != null) {
            stmt.bindString(5, fileId);
        }
 
        Long size = entity.getSize();
        if (size != null) {
            stmt.bindLong(6, size);
        }
 
        String md5 = entity.getMd5();
        if (md5 != null) {
            stmt.bindString(7, md5);
        }
 
        String fName = entity.getFName();
        if (fName != null) {
            stmt.bindString(8, fName);
        }
 
        String fKey = entity.getFKey();
        if (fKey != null) {
            stmt.bindString(9, fKey);
        }
 
        String fInfo = entity.getFInfo();
        if (fInfo != null) {
            stmt.bindString(10, fInfo);
        }
 
        Integer pathId = entity.getPathId();
        if (pathId != null) {
            stmt.bindLong(11, pathId);
        }
 
        String pathName = entity.getPathName();
        if (pathName != null) {
            stmt.bindString(12, pathName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FileUploadItem entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer depens = entity.getDepens();
        if (depens != null) {
            stmt.bindLong(2, depens);
        }
 
        String UserId = entity.getUserId();
        if (UserId != null) {
            stmt.bindString(3, UserId);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(4, type);
        }
 
        String fileId = entity.getFileId();
        if (fileId != null) {
            stmt.bindString(5, fileId);
        }
 
        Long size = entity.getSize();
        if (size != null) {
            stmt.bindLong(6, size);
        }
 
        String md5 = entity.getMd5();
        if (md5 != null) {
            stmt.bindString(7, md5);
        }
 
        String fName = entity.getFName();
        if (fName != null) {
            stmt.bindString(8, fName);
        }
 
        String fKey = entity.getFKey();
        if (fKey != null) {
            stmt.bindString(9, fKey);
        }
 
        String fInfo = entity.getFInfo();
        if (fInfo != null) {
            stmt.bindString(10, fInfo);
        }
 
        Integer pathId = entity.getPathId();
        if (pathId != null) {
            stmt.bindLong(11, pathId);
        }
 
        String pathName = entity.getPathName();
        if (pathName != null) {
            stmt.bindString(12, pathName);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public FileUploadItem readEntity(Cursor cursor, int offset) {
        FileUploadItem entity = new FileUploadItem( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // depens
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // UserId
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // type
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // fileId
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5), // size
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // md5
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // fName
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // fKey
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // fInfo
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // pathId
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11) // pathName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FileUploadItem entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDepens(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setUserId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setType(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setFileId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSize(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
        entity.setMd5(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setFName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setFKey(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setFInfo(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setPathId(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setPathName(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FileUploadItem entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FileUploadItem entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FileUploadItem entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
