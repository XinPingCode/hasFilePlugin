package hasFilePlugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PermissionHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import java.io.File;
import android.content.pm.PackageManager;
import android.Manifest;
import android.os.Build;

/**
 * This class echoes a string called from JavaScript.
 */
public class hasFilePlugin extends CordovaPlugin {
    String flag = "unknow";
    CallbackContext cbc;
    String[] permissions = { Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE };

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("hasFile")) {
            String message = args.getString(0);
            this.hasFile(message, callbackContext);
            return true;
        }
        return false;
    }

    private void hasFile(String path, CallbackContext callbackContext) {
        getPermission();
        if (fileIsExists(path)) {
            this.flag = "true";
        } else {
            this.flag = "false";
        }
        callbackContext.success(this.flag);
    }

    public void getPermission() {
        if (hasPermisssion()) {
        } else {
            PermissionHelper.requestPermissions(this, 0, permissions);
        }
    }

    public boolean hasPermisssion() {
        for (String p : permissions) {
            if (!PermissionHelper.hasPermission(this, p)) {
                return false;
            }
        }
        return true;
    }

    public boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
