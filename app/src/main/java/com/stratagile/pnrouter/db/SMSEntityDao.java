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
 * DAO for table "SMSENTITY".
*/
public class SMSEntityDao extends AbstractDao<SMSEntity, Long> {

    public static final String TABLENAME = "SMSENTITY";

    /**
     * Properties of entity SMSEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property SmsId = new Property(1, Integer.class, "smsId", false, "SMS_ID");
        public final static Property UUID = new Property(2, String.class, "UUID", false, "UUID");
        public final static Property Address = new Property(3, String.class, "address", false, "ADDRESS");
        public final static Property Person = new Property(4, Integer.class, "person", false, "PERSON");
        public final static Property PersonName = new Property(5, String.class, "personName", false, "PERSON_NAME");
        public final static Property Date = new Property(6, Long.class, "date", false, "DATE");
        public final static Property Read = new Property(7, Integer.class, "read", false, "READ");
        public final static Property Type = new Property(8, Integer.class, "type", false, "TYPE");
        public final static Property Subject = new Property(9, String.class, "subject", false, "SUBJECT");
        public final static Property Body = new Property(10, String.class, "body", false, "BODY");
        public final static Property Service_center = new Property(11, String.class, "service_center", false, "SERVICE_CENTER");
        public final static Property LastCheck = new Property(12, boolean.class, "lastCheck", false, "LAST_CHECK");
        public final static Property IsUpload = new Property(13, boolean.class, "isUpload", false, "IS_UPLOAD");
        public final static Property IsMultChecked = new Property(14, boolean.class, "isMultChecked", false, "IS_MULT_CHECKED");
    }


    public SMSEntityDao(DaoConfig config) {
        super(config);
    }
    
    public SMSEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SMSENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"SMS_ID\" INTEGER," + // 1: smsId
                "\"UUID\" TEXT," + // 2: UUID
                "\"ADDRESS\" TEXT," + // 3: address
                "\"PERSON\" INTEGER," + // 4: person
                "\"PERSON_NAME\" TEXT," + // 5: personName
                "\"DATE\" INTEGER," + // 6: date
                "\"READ\" INTEGER," + // 7: read
                "\"TYPE\" INTEGER," + // 8: type
                "\"SUBJECT\" TEXT," + // 9: subject
                "\"BODY\" TEXT," + // 10: body
                "\"SERVICE_CENTER\" TEXT," + // 11: service_center
                "\"LAST_CHECK\" INTEGER NOT NULL ," + // 12: lastCheck
                "\"IS_UPLOAD\" INTEGER NOT NULL ," + // 13: isUpload
                "\"IS_MULT_CHECKED\" INTEGER NOT NULL );"); // 14: isMultChecked
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SMSENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SMSEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer smsId = entity.getSmsId();
        if (smsId != null) {
            stmt.bindLong(2, smsId);
        }
 
        String UUID = entity.getUUID();
        if (UUID != null) {
            stmt.bindString(3, UUID);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(4, address);
        }
 
        Integer person = entity.getPerson();
        if (person != null) {
            stmt.bindLong(5, person);
        }
 
        String personName = entity.getPersonName();
        if (personName != null) {
            stmt.bindString(6, personName);
        }
 
        Long date = entity.getDate();
        if (date != null) {
            stmt.bindLong(7, date);
        }
 
        Integer read = entity.getRead();
        if (read != null) {
            stmt.bindLong(8, read);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(9, type);
        }
 
        String subject = entity.getSubject();
        if (subject != null) {
            stmt.bindString(10, subject);
        }
 
        String body = entity.getBody();
        if (body != null) {
            stmt.bindString(11, body);
        }
 
        String service_center = entity.getService_center();
        if (service_center != null) {
            stmt.bindString(12, service_center);
        }
        stmt.bindLong(13, entity.getLastCheck() ? 1L: 0L);
        stmt.bindLong(14, entity.getIsUpload() ? 1L: 0L);
        stmt.bindLong(15, entity.getIsMultChecked() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SMSEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer smsId = entity.getSmsId();
        if (smsId != null) {
            stmt.bindLong(2, smsId);
        }
 
        String UUID = entity.getUUID();
        if (UUID != null) {
            stmt.bindString(3, UUID);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(4, address);
        }
 
        Integer person = entity.getPerson();
        if (person != null) {
            stmt.bindLong(5, person);
        }
 
        String personName = entity.getPersonName();
        if (personName != null) {
            stmt.bindString(6, personName);
        }
 
        Long date = entity.getDate();
        if (date != null) {
            stmt.bindLong(7, date);
        }
 
        Integer read = entity.getRead();
        if (read != null) {
            stmt.bindLong(8, read);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(9, type);
        }
 
        String subject = entity.getSubject();
        if (subject != null) {
            stmt.bindString(10, subject);
        }
 
        String body = entity.getBody();
        if (body != null) {
            stmt.bindString(11, body);
        }
 
        String service_center = entity.getService_center();
        if (service_center != null) {
            stmt.bindString(12, service_center);
        }
        stmt.bindLong(13, entity.getLastCheck() ? 1L: 0L);
        stmt.bindLong(14, entity.getIsUpload() ? 1L: 0L);
        stmt.bindLong(15, entity.getIsMultChecked() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public SMSEntity readEntity(Cursor cursor, int offset) {
        SMSEntity entity = new SMSEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // smsId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // UUID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // address
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // person
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // personName
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6), // date
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // read
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // type
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // subject
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // body
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // service_center
            cursor.getShort(offset + 12) != 0, // lastCheck
            cursor.getShort(offset + 13) != 0, // isUpload
            cursor.getShort(offset + 14) != 0 // isMultChecked
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SMSEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSmsId(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setUUID(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAddress(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPerson(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setPersonName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDate(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
        entity.setRead(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setType(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setSubject(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setBody(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setService_center(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setLastCheck(cursor.getShort(offset + 12) != 0);
        entity.setIsUpload(cursor.getShort(offset + 13) != 0);
        entity.setIsMultChecked(cursor.getShort(offset + 14) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(SMSEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(SMSEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SMSEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
