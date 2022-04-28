package com.efounder.JEnterprise.zhdg.entity;

public class StreamEntity {
    private static final long serialVersionUID = 1L;
    private String streamId;
    //rtsp流地址
    private String rtspUrl;
    //http-flv流地址
    private String httpFlvUrl;
    //rtmp流地址
    private String rtmpUrl;

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public String getRtspUrl() {
        return rtspUrl;
    }

    public void setRtspUrl(String rtspUrl) {
        this.rtspUrl = rtspUrl;
    }

    public String getHttpFlvUrl() {
        return httpFlvUrl;
    }

    public void setHttpFlvUrl(String httpFlvUrl) {
        this.httpFlvUrl = httpFlvUrl;
    }

    public String getRtmpUrl() {
        return rtmpUrl;
    }

    public void setRtmpUrl(String rtmpUrl) {
        this.rtmpUrl = rtmpUrl;
    }
}
