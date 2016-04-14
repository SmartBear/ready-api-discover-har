package com.smartbear.ready.plugin.discovery.har.json;

import java.util.List;

public class Request {
    public String url;
    public String method;
    public List<Param> headers;
    public String httpVersion;
    public PostData postData;
}
