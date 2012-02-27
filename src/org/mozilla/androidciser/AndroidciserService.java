/* -*- Mode: Java; c-basic-offset: 4; tab-width: 20; indent-tabs-mode: nil; -*-
 * ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Mozilla Android code.
 *
 * The Initial Developer of the Original Code is Mozilla Foundation.
 * Portions created by the Initial Developer are Copyright (C) 2011
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Eitan Isaacson <eitan@monotonous.org>
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */

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
