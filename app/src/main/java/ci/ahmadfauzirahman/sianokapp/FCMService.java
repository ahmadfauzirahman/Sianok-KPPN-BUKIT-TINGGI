package ci.ahmadfauzirahman.sianokapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

import ci.ahmadfauzirahman.sianokapp.utils.SessionManager;

public class FCMService extends FirebaseMessagingService {

    Intent intent;
    SessionManager sessionManager;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("LOg", remoteMessage.toString());
//        String click_action =  remoteMessage.getNotification().getClickAction();
        notif(remoteMessage.getData().get("message"), remoteMessage.getData().get("status"), remoteMessage.getData().get("type"));

    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.e("Token Log", s);
        sessionManager = new SessionManager(this);
        sessionManager.saveToken(s);
    }

    public void notif(String title, String Content, String Type) {

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getBaseContext());
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getBaseContext(), "channel1");
        notification
                .setContentTitle(title)
                .setSmallIcon(R.drawable.logo_sianok)
                .setContentText("KPPN BUKIT TINGGI")
//                .setContentInfo(Content).setPriority(NotificationCompat.PRIORITY_HIGH).build();
//                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(Content))
                .setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_HIGH).build();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotifcationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel("channel1", "NOTIFICATION_CHANNEL_BUKIT_TINGGI", NotificationManager.IMPORTANCE_HIGH);
            notification.setSmallIcon(R.drawable.logo_sianok);
            notification.setStyle(new NotificationCompat.BigTextStyle().bigText(Content));
            if (mNotifcationManager != null) {
                mNotifcationManager.createNotificationChannel(notificationChannel);
            }
        }
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        notificationManager.notify(m, notification.build());
    }


}
