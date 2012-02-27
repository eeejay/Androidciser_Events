/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mozilla.androidciser;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Service;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import java.util.List;

public class AndroidciserService extends AccessibilityService {

    /** Tag for logging from this service. */
    private static final String LOG_TAG = "Androidciser";

    private static final int EVENT_NOTIFICATION_TIMEOUT_MILLIS = 80;

    @Override
    public void onServiceConnected() {
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        info.notificationTimeout = EVENT_NOTIFICATION_TIMEOUT_MILLIS;
        info.packageNames = null;
        setServiceInfo(info);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i(LOG_TAG, eventToString(event));
        
    }

    @Override
    public void onInterrupt() {
    }

    private String eventToString(AccessibilityEvent event) {
        String str = "Event Type:  " + eventTypeToString(event.getEventType()) + "\n";
        str += " Package:       " + event.getPackageName() + "\n";
        str += " Class:         " + event.getClassName() + "\n";
        str += " Description:   " + event.getContentDescription() + "\n";
        str += " Text:          " + event.getText() + "\n";
        str += " Enabled:       " + event.isEnabled() + "\n";
        str += " Password:      " + event.isPassword() + "\n";
        str += " Checked:       " + event.isChecked() + "\n";
        str += " Current:       " + event.getCurrentItemIndex() + "\n";
        str += " From Index:    " + event.getFromIndex() + "\n";
        str += " Item Count:    " + event.getItemCount() + "\n";
        str += " Added Count:   " + event.getAddedCount() + "\n";
        str += " Removed Count: " + event.getRemovedCount() + "\n";
        str += " Before Text:   " + event.getBeforeText() + "\n";

        return str;
    }
    
    /* AccessibilityEvent.eventTypeToString in only in API version 14 */
    private static String eventTypeToString(int eventType) {
        switch (eventType) {
        case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
            return "TYPE_NOTIFICATION_STATE_CHANGED";
        case AccessibilityEvent.TYPE_VIEW_CLICKED:
            return "TYPE_VIEW_CLICKED";
        case AccessibilityEvent.TYPE_VIEW_FOCUSED:
            return "TYPE_VIEW_FOCUSED";
        case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
            return "TYPE_VIEW_LONG_CLICKED";
        case AccessibilityEvent.TYPE_VIEW_SELECTED:
            return "TYPE_VIEW_SELECTED";
        case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
            return "TYPE_VIEW_TEXT_CHANGED";
        case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
            return "TYPE_VIEW_WINDOW_STATE_CHANGED";
        default:
            return "TYPE_UNKNOWN";
        }
    }
}
