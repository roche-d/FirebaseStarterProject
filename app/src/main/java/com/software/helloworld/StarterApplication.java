/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.software.helloworld;

import android.content.Intent;

import com.firebase.client.Firebase;

public class StarterApplication extends android.app.Application {

  @Override
  public void onCreate() {

    super.onCreate();

    Firebase.setAndroidContext(this);

    Intent intent = new Intent(StarterApplication.this, FirebaseService.class);
    startService(intent);
  }

  @Override
  public void onTerminate() {
    super.onTerminate();

    Intent intent = new Intent(StarterApplication.this, FirebaseService.class);
    stopService(intent);
  }
}
