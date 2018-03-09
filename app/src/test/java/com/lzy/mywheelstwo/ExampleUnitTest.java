package com.lzy.mywheelstwo;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Config;
import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest extends Activity{
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


//    public void viewUrl(String url, String mimeType) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.parse(url), mimeType);
//        if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ ONLY) != null) {
//            try {
//                startActivity(intent);
//            } catch (ActivityNotFoundException e) {
//                if (Config.LOGD) {
//                    Log.d(LOGTAG, "activity not found for " + mimeType + " over " +
//                            Uri.parse(url).getScheme(), e);
//                }
//            }
//        }
//    }

}