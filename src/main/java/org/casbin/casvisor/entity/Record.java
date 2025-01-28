package org.casbin.casvisor.entity;

public class Record {

    public int id;
    public String owner;
    public String name;
    public String createdTime;
    public String organization;
    public String clientIp;
    public String user;
    public String method;
    public String requestUri;
    public String action;
    public String language;
    public String object;
    public String response;
    public String provider;
    public String block;
    public boolean isTriggered;

    public Record(){}

    public Record(String owner, String name, String createdTime, String organization, String clientIp,
                  String user, String method, String requestUri, String action, String object, String language,
                  String response, boolean isTriggered){
        this.owner = owner;
        this.name = name;
        this.createdTime = createdTime;
        this.organization = organization;
        this.clientIp = clientIp;
        this.user = user;
        this.method = method;
        this.requestUri = requestUri;
        this.action = action;
        this.object = object;
        this.language = language;
        this.response = response;
        this.isTriggered = isTriggered;
    }
}
