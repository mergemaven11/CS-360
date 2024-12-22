package com.example.projthreetobias;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.SmsManager;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class NotificationService {

    private static final int SMS_PERMISSION_CODE = 101;

    /**
     * Checks if the app has the permission to send SMS.
     * @param context The context in which the permission is checked.
     * @return true if permission is granted, false otherwise.
     */
    public static boolean hasSmsPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(context, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    /**
     * Requests the user to grant SMS permission.
     * @param context The context in which the permission request is made.
     */
    public static void requestSmsPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions((MainActivity) context, new String[]{android.Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        }
    }

    /**
     * Sends an SMS notification to the user if permission is granted.
     * @param context The context to send SMS from.
     * @param phoneNumber The phone number to send the SMS to.
     * @param message The message to send in the SMS.
     */
    public static void sendSmsNotification(Context context, String phoneNumber, String message) {
        if (hasSmsPermission(context)) {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                Toast.makeText(context, "SMS sent successfully!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(context, "Failed to send SMS. Please try again.", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context, "Permission to send SMS is required.", Toast.LENGTH_SHORT).show();
            requestSmsPermission(context);
        }
    }
}
