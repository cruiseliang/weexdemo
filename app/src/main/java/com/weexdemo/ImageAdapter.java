package com.weexdemo;

import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

/**
 * Created by Liang on 2018/5/29 0029.
 */

public class ImageAdapter implements IWXImgLoaderAdapter {

    @Override
    public void setImage(final String url, final ImageView view, WXImageQuality quality, WXImageStrategy strategy) {
        WXSDKManager.getInstance().postOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(view==null||view.getLayoutParams()==null){
                    return;
                }
                if (TextUtils.isEmpty(url)) {
                    view.setImageBitmap(null);
                    return;
                }
                String temp = url;
                if (url.startsWith("//")) {
                    temp = "http:" + url;
                }
                if (view.getLayoutParams().width <= 0 || view.getLayoutParams().height <= 0) {
                    return;
                }
                Picasso.with(WXEnvironment.getApplication())
                        .load(temp)
                        .into(view);
            }
        },0);
        //实现你自己的图片下载，否则图片无法显示。
    }
}
