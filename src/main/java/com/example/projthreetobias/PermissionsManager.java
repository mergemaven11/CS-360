package com.example.projthreetobias;

import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;

public class PermissionsManager {

    private Context context;

    public PermissionsManager(Context context) {
        this.context = context;
    }

    public boolean checkSMSPermission() {
        return ActivityCompat.checkSelfPermission(context, android.Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED;
    }

    public void sendSMSAlert(String phoneNumber, String message) {
        if (checkSMSPermission()) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(context, "SMS sent!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "SMS permission denied.", Toast.LENGTH_SHORT).show();
        }
    }
}
