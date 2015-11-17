package ru.delightfire.delight.app;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import ru.delightfire.delight.utils.LruBitmapCache;

/**
 * Created by sergei on 15.11.2015.
 */
public class NetworkController extends Application {

    private static final String TAG = NetworkController.class.getSimpleName();
    private static NetworkController networkSingle;
    private static RequestQueue requestQueue;
    private static ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        networkSingle = this;
    }
    public static synchronized NetworkController getInstance(){
//        if(requestQueue == null){
//            networkSingle = new NetworkSingle();
//        }
        return networkSingle;
    }
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (imageLoader == null) {
            imageLoader = new ImageLoader(this.requestQueue,
                    new LruBitmapCache());
        }
        return this.imageLoader;
    }
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }
}
