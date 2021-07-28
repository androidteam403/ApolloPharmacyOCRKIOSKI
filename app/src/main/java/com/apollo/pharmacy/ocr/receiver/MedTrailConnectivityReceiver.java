package com.apollo.pharmacy.ocr.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MedTrailConnectivityReceiver extends BroadcastReceiver {
    public static MedTrailConnectivityListener medtrailconnectivityListener;

    public interface MedTrailConnectivityListener {

    }

    public MedTrailConnectivityReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
        String extraData = intent.getStringExtra("extraData");
        int scanResult = intent.getIntExtra("scanResult", Integer.MIN_VALUE);

        Intent intent1 = new Intent("Scannedstatus");
        intent1.putExtra("scanResult", String.valueOf(scanResult));
        context.sendBroadcast(new Intent(intent1));
    }
}
