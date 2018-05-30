/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.weexdemo;

import android.app.Application;

import com.taobao.weex.InitConfig;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.adapter.DefaultWXHttpAdapter;

public class WXApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();


    InitConfig config = new InitConfig.Builder().
            setImgAdapter(new ImageAdapter()).//对于图片加载可以自定义框架
            setHttpAdapter(new DefaultWXHttpAdapter()).//网络请求可以自定义框架
            build(); //还可以自定义的参数阿里官网和demon有详细解析
//      WXSDKEngine.registerModule("hotelModule", HotelModule.class);//交互用 后面说
      WXSDKEngine.initialize(this, config); //初始化 必填
  }

  /**
   *@param connectable debug server is connectable or not.
   *               if true, sdk will try to connect remote debug server when init WXBridge.
   *
   * @param debuggable enable remote debugger. valid only if host not to be "DEBUG_SERVER_HOST".
   *               true, you can launch a remote debugger and inspector both.
   *               false, you can  just launch a inspector.
   * @param host the debug server host, must not be "DEBUG_SERVER_HOST", a ip address or domain will be OK.
   *             for example "127.0.0.1".
   */
  private void initDebugEnvironment(boolean connectable, boolean debuggable, String host) {
    if (!"DEBUG_SERVER_HOST".equals(host)) {
      WXEnvironment.sDebugMode = connectable;
//      WXEnvironment.sRemoteDebugMode = debuggable;
      WXEnvironment.sDebugWsUrl = "ws://" + host + ":8088/debugProxy/native";
    }
  }

}
