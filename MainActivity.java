package com.mc.eenadunap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText edt_ronumber,edt_clientname,edt_clientmobile,edt_nooflines,edt_amount,edt_edtext;
    private TextView txt_date,testtxt,txt_submit,txt_edtcount,capture,txt_pictext;
    private LinearLayout llimage;
    private int mYear, mMonth, mDay, i;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    Bitmap bitmap, bitmap1;
    private String realPath, rwalpath1;
    private String encodedImage, encodedImage1;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;

    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "NAP Camera";

    private Uri fileUri; // file url to store image/video

    private ImageView imgPreview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_ronumber = (EditText)findViewById(R.id.edt_ronumber);
        edt_clientname = (EditText)findViewById(R.id.edt_clientname);
        edt_clientmobile = (EditText)findViewById(R.id.edt_clientmobile);
        edt_nooflines = (EditText)findViewById(R.id.edt_nooflines);
        edt_amount = (EditText)findViewById(R.id.edt_amount);
        edt_edtext = (EditText)findViewById(R.id.edt_edtext);
        txt_date = (TextView)findViewById(R.id.txt_date);
        testtxt = (TextView)findViewById(R.id.testtxt);
        capture = (TextView)findViewById(R.id.capture);
        txt_edtcount = (TextView)findViewById(R.id.txt_edtcount);
        txt_submit = (TextView)findViewById(R.id.txt_submit);
        imgPreview = (ImageView)findViewById(R.id.imgPreview);
        llimage = (LinearLayout)findViewById(R.id.llimage);
        txt_pictext = (TextView)findViewById(R.id.txt_pictext);
        edt_edtext.addTextChangedListener(mTextEditorWatcher);

        requestRuntimePermission();
        txt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dpd = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                c.set(year, monthOfYear, dayOfMonth);
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                String dateString = dateFormat.format(c.getTime());
                                c.add(Calendar.DAY_OF_YEAR, 0);
                                Date nextdate = c.getTime();
                                String nextd = dateFormat.format(nextdate);
                                txt_date.setText(dateString);
                            }
                        }, mYear, mMonth, mDay);

                dpd.show();
            }
        });

        //surya prakash


        testtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);
            }
        });

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();
            }
        });

        // Checking camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device does't have camera
            finish();
        }

        txt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Grono = edt_ronumber.getText().toString();
                String Gpubdate = txt_date.getText().toString();
                String Gclientname = edt_clientname.getText().toString();
                String Gclientmobile = edt_clientmobile.getText().toString();
                String Gnoofclients = edt_nooflines.getText().toString();
                String Gamount = edt_amount.getText().toString();
                String Gremarks = edt_edtext.getText().toString();
                String Gimage = testtxt.getText().toString();

                if(Grono.equalsIgnoreCase("")||Grono.equalsIgnoreCase("null")&&Gpubdate.equalsIgnoreCase("")||Gpubdate.equalsIgnoreCase("null")
                        && Gclientname.equalsIgnoreCase("")||Gclientname.equalsIgnoreCase("null")&& Gclientmobile.equalsIgnoreCase("")||Gclientmobile.equalsIgnoreCase("null")
                        && Gnoofclients.equalsIgnoreCase("")||Gnoofclients.equalsIgnoreCase("null")&& Gamount.equalsIgnoreCase("")||Gamount.equalsIgnoreCase("null")
                        && Gremarks.equalsIgnoreCase("")||Gremarks.equalsIgnoreCase("null")&& Gimage.equalsIgnoreCase("")||Gimage.equalsIgnoreCase("null")){
                    Toast.makeText(MainActivity.this, "Please Enter All Fields To Save!....", Toast.LENGTH_SHORT).show();
                }else if(Grono.equalsIgnoreCase("")||Grono.equalsIgnoreCase("null")){
                    Toast.makeText(MainActivity.this, "Please Enter Ro Number", Toast.LENGTH_SHORT).show();
                } else if(Gpubdate.equalsIgnoreCase("")||Gpubdate.equalsIgnoreCase("null")){
                    Toast.makeText(MainActivity.this, "Please Select Published Date", Toast.LENGTH_SHORT).show();
                } else if(Gclientname.equalsIgnoreCase("")||Gclientname.equalsIgnoreCase("null")){
                    Toast.makeText(MainActivity.this, "Please Enter Client Name", Toast.LENGTH_SHORT).show();
                } else if(Gclientmobile.equalsIgnoreCase("")||Gclientmobile.equalsIgnoreCase("null")){
                    Toast.makeText(MainActivity.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                } else if(Gnoofclients.equalsIgnoreCase("")||Gnoofclients.equalsIgnoreCase("null")){
                    Toast.makeText(MainActivity.this, "Please Enter No.of Clients", Toast.LENGTH_SHORT).show();
                } else if(Gamount.equalsIgnoreCase("")||Gamount.equalsIgnoreCase("null")){
                    Toast.makeText(MainActivity.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                }else if(Gimage.equalsIgnoreCase("")||Gimage.equalsIgnoreCase("null")){
                    Toast.makeText(MainActivity.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Service Call. Work In Progress!...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            txt_edtcount.setText(String.valueOf(s.length()));
        }

        public void afterTextChanged(Editable s) {
        }
    };

    /**
     * Checking device has camera hardware or not
     * */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /*
	 * Capturing Camera Image will lauch camera app requrest image capture
	 */
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    /*
	 * Here we store the file url as it will be null after returning from camera
	 * app
	 */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }



    /**
     * Receiving activity result method will be called after closing the camera
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            Log.e("Result","Result--->"+requestCode+"---"+resultCode+"---"+data.getData());
            if (requestCode == 0 && resultCode == RESULT_OK && data != null && data.getData() != null) {

                Uri filePath = data.getData();
                realPath = RealPathUtil.getPath(this, data.getData());
                Log.e("realPath", "realPath===>" + realPath);
                File f = new File(realPath);
                String imageName = f.getName();
                Log.e("imageName", "imageName===>" + imageName);
                txt_pictext.setText(imageName);
                try {
                    //Getting the Bitmap from Gallery
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
                    byte[] imageBytes = baos.toByteArray();
                    encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    Log.e("encodedimg","encoedeimg--->"+encodedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    /*
     * Display image from a path to ImageView
     */
    private void previewCapturedImage() {
        try {

            imgPreview.setVisibility(View.VISIBLE);

            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

            imgPreview.setImageBitmap(bitmap);

            //Getting the Bitmap from Gallery
            //bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
            byte[] imageBytes = baos.toByteArray();
            encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            Log.e("encodedImage","encodedImage==>"+encodedImage);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * ------------ Helper Methods ----------------------
     * */

	/*
	 * Creating file uri to store image/video
	 */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    public void requestRuntimePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    /*
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { android.Manifest.permission.READ_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }
}
