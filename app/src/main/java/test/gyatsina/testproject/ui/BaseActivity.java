package test.gyatsina.testproject.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextThemeWrapper;

/**
 * Created by gyatsina
 */
public class BaseActivity extends ActionBarActivity {
    protected ProgressDialog progressDialog;

    public void showProgressDialog(final String title, final String message, final boolean cancelable) {
        if (!cancelable) {
            showProgressDialog(title, message);
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    createProgressDialog();
                    progressDialog.setTitle(title);
                    progressDialog.setMessage(message);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface arg0) {
                            finish();
                        }
                    });

                    progressDialog.show();
                }
            });
        }
    }

    public void showProgressDialog(final String title, final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createProgressDialog();
                progressDialog.setTitle(title);
                progressDialog.setMessage(message);
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }
        });
    }

    protected void createProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(new ContextThemeWrapper(this, android.R.style.Theme_DeviceDefault_Light_Dialog));
        }
    }

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

    }
}
