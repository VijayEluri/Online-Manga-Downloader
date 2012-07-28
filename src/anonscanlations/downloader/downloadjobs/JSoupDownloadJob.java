package anonscanlations.downloader.downloadjobs;

import java.io.*;
import java.util.*;
import java.net.*;

import org.jsoup.*;
import org.jsoup.nodes.*;

import anonscanlations.downloader.*;

/**
 *
 * @author /a/non <anonymousscanlations@gmail.com>
 */
public class JSoupDownloadJob extends DownloadJob
{
    private boolean init;

    protected boolean postOverride;
    protected URL url;
    protected Connection conn;
    protected Connection.Response response;
    private Map<String, String> cookies;

    public JSoupDownloadJob(String _desc, URL _url)
    {
        super(_desc);
        postOverride = false;
        url = _url;
        init = false;
        cookies = new HashMap<String, String>();
    }

    protected void init() throws Exception
    {
        DownloaderUtils.debug("JSoupDJ (" + getClass() + "): Init");
        init = true;
        conn = Jsoup.connect(url.toString()).followRedirects(true).timeout(10000);
        for(Map.Entry<String, String> e : headers.entrySet())
        {
            DownloaderUtils.debug("JSoupDJ (" + getClass() + "): Header: " + e.getKey() + ", " + e.getValue());
            conn.header(e.getKey(), e.getValue());
        }
        if(cookies != null)
        {
            for(Map.Entry<String, String> cookie : cookies.entrySet())
            {
                DownloaderUtils.debug("JSoupDJ (" + getClass() + "): Cookie: " + cookie.getKey() + ", " + cookie.getValue());
                conn.cookie(cookie.getKey(), cookie.getValue());
            }
        }
        if(!data.isEmpty() || postOverride)
            conn.data(data).method(Connection.Method.POST);
        else
            conn.method(Connection.Method.GET);
    }

    public void run() throws Exception
    {
        DownloaderUtils.debug("JSoupDJ (" + getClass() + "): Run: " + init);
        if(!init)
            init();
        DownloaderUtils.debug("JSoupDJ (" + getClass() + "): URL: " + url);
        response = conn.execute();
        DownloaderUtils.debug("JSoupDJ (" + getClass() + "): Response Headers: " + response.headers());
        //long length = Long.parseLong(response.header("Content-Length"));
    }

    public byte[] getBytes()
    {
        return(response.bodyAsBytes());
    }

    public void addRequestCookies(Map<String, String> _cookies)
    {
        cookies.putAll(_cookies);
    }
    
    protected Map<String, String> getRequestCookies()
    {
        return(cookies);
    }
    
    public Map<String, String> getResponseCookies()
    {
        return(response.cookies());
    }
}
