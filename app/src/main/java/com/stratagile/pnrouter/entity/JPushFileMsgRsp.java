package com.stratagile.pnrouter.entity;

import android.util.Base64;

import com.stratagile.pnrouter.utils.Base58;
import com.stratagile.pnrouter.utils.RxEncodeTool;

public class JPushFileMsgRsp extends BaseEntity {

    /**
     * params : {"Action":"PushFile","FromId":"8EDE2DD3C5A84F14A386155233AE44AD1DB9752DF9FE744A562548A896A30913BCB70A123ADE","ToId":"8A9A37275400CE381F80C738235440350FB8322824988565DED2793AE83BFF377F0D95AC5A74","FileName":"magazine-unlock-01-2.3.1131-_211A988272E926949BCDC9C360716C53.jpg","FileMD5":"05219c43a8b97cbccde994fd93136193","FilePath":"/user19/magazine-unlock-01-2.3.1131-_211A988272E926949BCDC9C360716C53.jpg","MsgId":1539336951}
     */

    private ParamsBean params;

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public static class ParamsBean {
        /**
         * Action : PushFile
         * FromId : 8EDE2DD3C5A84F14A386155233AE44AD1DB9752DF9FE744A562548A896A30913BCB70A123ADE
         * ToId : 8A9A37275400CE381F80C738235440350FB8322824988565DED2793AE83BFF377F0D95AC5A74
         * FileName : magazine-unlock-01-2.3.1131-_211A988272E926949BCDC9C360716C53.jpg
         * FileMD5 : 05219c43a8b97cbccde994fd93136193
         * FilePath : /user19/magazine-unlock-01-2.3.1131-_211A988272E926949BCDC9C360716C53.jpg
         * MsgId : 1539336951
         */

        private String Action;
        private String FromId;
        private String ToId;
        private String FilePath;
        private String FileName;
        private String FileMD5;

        public String getFileInfo() {
            return FileInfo;
        }

        public void setFileInfo(String fileInfo) {
            FileInfo = fileInfo;
        }

        private int MsgId;
        private String SrcKey;
        private String DstKey;
        private String FileInfo;



        private int FileType;

        public String getAction() {
            return Action;
        }

        public void setAction(String Action) {
            this.Action = Action;
        }

        public String getFromId() {
            return FromId;
        }

        public void setFromId(String FromId) {
            this.FromId = FromId;
        }

        public String getToId() {
            return ToId;
        }

        public void setToId(String ToId) {
            this.ToId = ToId;
        }

        public String getFileName() {
            //base58解码
            //String FileNameOld = new String(RxEncodeTool.base64Decode(FileName.getBytes()));
            String FileNameOld = new String(Base58.decode(FileName));
            return FileNameOld;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public String getFileMD5() {
            return FileMD5;
        }

        public void setFileMD5(String FileMD5) {
            this.FileMD5 = FileMD5;
        }

        public String getFilePath() {
            return FilePath;
        }

        public void setFilePath(String FilePath) {
            this.FilePath = FilePath;
        }

        public int getMsgId() {
            return MsgId;
        }

        public void setMsgId(int MsgId) {
            this.MsgId = MsgId;
        }
        public String getSrcKey() {
            return SrcKey;
        }

        public void setSrcKey(String srcKey) {
            SrcKey = srcKey;
        }

        public String getDstKey() {
            return DstKey;
        }

        public void setDstKey(String dstKey) {
            DstKey = dstKey;
        }
        public int getFileType() {
            return FileType;
        }

        public void setFileType(int fileType) {
            FileType = fileType;
        }

    }
}
