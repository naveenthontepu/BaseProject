package thontepu.naveen.baseproject.Activities;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import thontepu.naveen.baseproject.R;
import thontepu.naveen.baseproject.Utilities.SensorUtilities;
import thontepu.naveen.baseproject.Utilities.Utilities;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.profileImage)
    ImageView profileImage;
    @BindView(R.id.content_main)
    RelativeLayout contentMain;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.scaleET)
    EditText scaleET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Utilities.printLog("profile pic = " + DatabaseUtils.dumpCursorToString(getProfilePic()));
        try {
            showPic();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showPic() throws FileNotFoundException {
        profileImage.setImageBitmap(getBitmap());
    }

    private Bitmap getBitmap() throws FileNotFoundException {
        Bitmap bit = null;
        Cursor cursor = getProfilePic();
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                String pic = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI));
                bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.parse(pic)));
            }
            cursor.close();
        }
        return bit;
    }

    private void addLauncher(int scale) throws FileNotFoundException {
        Bitmap initialBitmap = getBitmap();
        Utilities.printLog("size of initial bitmap = " + initialBitmap.getByteCount() + " height = " + initialBitmap.getHeight() + " width = " + initialBitmap.getWidth());
        Bitmap getLauncher = BitmapFactory.decodeResource(getResources(), R.drawable.trial);
        getLauncher = Bitmap.createScaledBitmap(getLauncher, initialBitmap.getWidth() / scale, initialBitmap.getHeight() / scale, true);
        Utilities.printLog("size of launcher bitmap = " + getLauncher.getByteCount() + " height = " + getLauncher.getHeight() + " width = " + getLauncher.getWidth());
        Bitmap bitmap = Bitmap.createBitmap(initialBitmap.getWidth(), initialBitmap.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        int startX = (scale - 1) * initialBitmap.getWidth() / scale;
        int startY = (scale - 1) * initialBitmap.getHeight() / (scale * 2);
        canvas.drawBitmap(initialBitmap, 0, 0, null);
        canvas.drawBitmap(getLauncher, startX, startY, null);
        Utilities.printLog("size of combined image = " + bitmap.getByteCount());
        ByteBuffer byteBuffer = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(byteBuffer);
        byte[] by = byteBuffer.array();
        Utilities.printLog("byte = " + Arrays.toString(by));
        bitmap.copyPixelsFromBuffer(ByteBuffer.wrap(by));
        profileImage.setImageBitmap(bitmap);
    }

    public Cursor getProfilePic() {
//        Utilities.printLog(Constants.Tags.PROFILE, "uri = " + Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, ContactsContract.Contacts.Data.CONTENT_DIRECTORY));
        return getContentResolver().query(Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, ContactsContract.Contacts.Data.CONTENT_DIRECTORY), null, ContactsContract.CommonDataKinds.Photo.MIMETYPE + "=?", new String[]{ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE}, null);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        try {
            String scale = scaleET.getText().toString();
            Integer scal = Integer.parseInt(scale);
            addLauncher(scal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.stopVibration)
    public void stopVibration() {
        SensorUtilities.cancelVibration(getApplicationContext());
    }
}
