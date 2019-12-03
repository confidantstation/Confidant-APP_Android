package com.stratagile.pnrouter.db;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class LocalFileItem implements Parcelable {
    @Id(autoincrement = true)
    private Long id;

    private String fileName;
    private String filePath;
    private Long fileSize;
    private Long creatTime;
    // 文件类型0--所有文件
    //1--图片
    //2--语音
    //3--视频
    //4--文档
    //5--其他文件
    private Integer fileType;
    private Integer fileFrom;//0本地，1服务器
    private String autor;//作者
    private Long fileId;//属于哪个文件夹
    private String srcKey;
    private String fileMD5;

    public LocalFileItem() {
    }


    protected LocalFileItem(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        fileName = in.readString();
        filePath = in.readString();
        if (in.readByte() == 0) {
            fileSize = null;
        } else {
            fileSize = in.readLong();
        }
        if (in.readByte() == 0) {
            creatTime = null;
        } else {
            creatTime = in.readLong();
        }
        if (in.readByte() == 0) {
            fileType = null;
        } else {
            fileType = in.readInt();
        }
        if (in.readByte() == 0) {
            fileFrom = null;
        } else {
            fileFrom = in.readInt();
        }
        autor = in.readString();
        if (in.readByte() == 0) {
            fileId = null;
        } else {
            fileId = in.readLong();
        }
        srcKey = in.readString();
        fileMD5 = in.readString();
    }


    @Generated(hash = 459452525)
    public LocalFileItem(Long id, String fileName, String filePath, Long fileSize,
            Long creatTime, Integer fileType, Integer fileFrom, String autor, Long fileId,
            String srcKey, String fileMD5) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.creatTime = creatTime;
        this.fileType = fileType;
        this.fileFrom = fileFrom;
        this.autor = autor;
        this.fileId = fileId;
        this.srcKey = srcKey;
        this.fileMD5 = fileMD5;
    }

    public static final Creator<LocalFileItem> CREATOR = new Creator<LocalFileItem>() {
        @Override
        public LocalFileItem createFromParcel(Parcel in) {
            return new LocalFileItem(in);
        }

        @Override
        public LocalFileItem[] newArray(int size) {
            return new LocalFileItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(fileName);
        dest.writeString(filePath);
        if (fileSize == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(fileSize);
        }
        if (creatTime == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(creatTime);
        }
        if (fileType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(fileType);
        }
        if (fileFrom == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(fileFrom);
        }
        dest.writeString(autor);
        if (fileId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(fileId);
        }
        dest.writeString(srcKey);
        dest.writeString(fileMD5);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public Integer getFileFrom() {
        return fileFrom;
    }

    public void setFileFrom(Integer fileFrom) {
        this.fileFrom = fileFrom;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getSrcKey() {
        return srcKey;
    }

    public void setSrcKey(String srcKey) {
        this.srcKey = srcKey;
    }

    public String getFileMD5() {
        return fileMD5;
    }

    public void setFileMD5(String fileMD5) {
        this.fileMD5 = fileMD5;
    }
}
