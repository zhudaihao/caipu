package com.wq.photo;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.wq.photo.util.StatusBarCompat;
import com.wq.photo.widget.PickConfig;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

import static com.wq.photo.widget.PickConfig.MODE_MULTIP_PICK;
import static com.yalantis.ucrop.UCrop.REQUEST_CROP;


/**
 * 调用媒体选择库
 * 需要在inten中传递2个参数
 * 1. 选择模式 chose_mode  0  //单选 1多选
 * 2. 选择张数 max_chose_count  多选模式默认 9 张
 */
public class MediaChoseActivity extends AppCompatActivity {

    public int max_chose_count = 1;
    public LinkedHashMap imasgemap = new LinkedHashMap();
    public LinkedHashSet imagesChose = new LinkedHashSet();
    PhotoGalleryFragment photoGalleryFragment;
    int chosemode = PickConfig.MODE_SINGLE_PICK;

    boolean isneedCrop = false;
    boolean isNeedfcamera = false;
    int spanCount;
    private int actionBarcolor;
    private int statusBarcolor;
    private boolean isSquareCrop;
    private UCrop.Options options = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_chose);
        Toolbar toobar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toobar);

        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        Bundle bundle = getIntent().getBundleExtra(PickConfig.EXTRA_PICK_BUNDLE);
        statusBarcolor = bundle.getInt(PickConfig.EXTRA_STATUS_BAR_COLOR);
        actionBarcolor = bundle.getInt(PickConfig.EXTRA_ACTION_BAR_COLOR);
        spanCount = bundle.getInt(PickConfig.EXTRA_SPAN_COUNT, PickConfig.DEFAULT_SPANCOUNT);
        chosemode = bundle.getInt(PickConfig.EXTRA_PICK_MODE, PickConfig.MODE_SINGLE_PICK);
        max_chose_count = bundle.getInt(PickConfig.EXTRA_MAX_SIZE, PickConfig.DEFAULT_PICKSIZE);
        isneedCrop = bundle.getBoolean(PickConfig.EXTRA_IS_NEED_CROP, false);
        isNeedfcamera = bundle.getBoolean(PickConfig.EXTRA_IS_NEED_CAMERA, true);
        options = bundle.getParcelable(PickConfig.EXTRA_UCROP_OPTIONS);
        isSquareCrop = bundle.getBoolean(PickConfig.EXTRA_IS_SQUARE_CROP);
        if (chosemode == MODE_MULTIP_PICK) {
            isneedCrop = false;
        }

        StatusBarCompat.compat(this, statusBarcolor);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(actionBarcolor));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        photoGalleryFragment = PhotoGalleryFragment.newInstance();
        photoGalleryFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.container, photoGalleryFragment, PhotoGalleryFragment.class.getSimpleName());
        fragmentTransaction.commit();
        if (savedInstanceState != null) {
            int chosemode = savedInstanceState.getInt("chosemode");
            if (chosemode == PickConfig.MODE_SINGLE_PICK) {
                currentfile = new File(savedInstanceState.getString("ImageFilePath"));
                boolean isneedCrop = savedInstanceState.getBoolean("isneedCrop");
                if (isneedCrop && !isCropOver) {
                    sendStarCrop(currentfile.getAbsolutePath());
                } else {
                    Intent intent = new Intent();
                    ArrayList<String> img = new ArrayList<>();
                    img.add(currentfile.getAbsolutePath());
                    intent.putExtra("data", img);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                insertImage(currentfile.getAbsolutePath());
            } else {
                getImageChoseMap().put(currentfile.getAbsolutePath(), currentfile.getAbsolutePath());
                invalidateOptionsMenu();
                insertImage(currentfile.getAbsolutePath());
            }
        }

    }

    boolean isPriview = false;

    public void starPriview(LinkedHashMap map, String currentimage) {
        if (isneedCrop && !isCropOver) {
            sendStarCrop(currentimage);
        } else {
            Set<String> keys = map.keySet();
            ArrayList<String> ims = new ArrayList<>();
            int pos = 0;
            int i = 0;
            for (String s : keys) {
                ims.add((String) map.get(s));
                if (map.get(s).equals(currentimage)) {
                    pos = i;
                }
                i++;
            }
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, ImagePreviewFragemnt.newInstance(ims, pos), ImagePreviewFragemnt.class.getSimpleName());
            fragmentTransaction.addToBackStack("con");
            fragmentTransaction.commit();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            isPriview = true;
            invalidateOptionsMenu();
        }
    }

    public Fragment getCurrentFragment(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    public LinkedHashMap getImageChoseMap() {
        return imasgemap;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.photo_gallery_menu, menu);
        if (isPriview && (chosemode == MODE_MULTIP_PICK)) {
            menu.findItem(R.id.menu_photo_delete).setVisible(true);
        } else {
            menu.findItem(R.id.menu_photo_delete).setVisible(false);
        }
        if (imasgemap.size() < 1) {
            menu.findItem(R.id.menu_photo_count).setEnabled(false);
            menu.findItem(R.id.menu_photo_count).setVisible(false);
        } else {
            menu.findItem(R.id.menu_photo_count).setEnabled(true);
            menu.findItem(R.id.menu_photo_count).setVisible(true);
            if (chosemode == MODE_MULTIP_PICK) {
                menu.findItem(R.id.menu_photo_count).setTitle("确定(" + imasgemap.size() + "/" + max_chose_count + ")");
            } else {
                menu.findItem(R.id.menu_photo_count).setTitle("确定(1)");
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (isPriview) {
                popFragment();
            } else {
                finish();
            }
        } else if (item.getItemId() == R.id.menu_photo_delete) {
            ImagePreviewFragemnt fragemnt = (ImagePreviewFragemnt) getCurrentFragment(ImagePreviewFragemnt.class.getSimpleName());
            if (fragemnt != null) {
                String img = fragemnt.delete();
                Iterator iterator = imasgemap.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    if (imasgemap.get(key).equals(img)) {
                        iterator.remove();
                    }
                }
                invalidateOptionsMenu();
            }
        } else if (item.getItemId() == R.id.menu_photo_count) {
            sendImages();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        FragmentManager fm = getSupportFragmentManager();
        if (keyCode == KeyEvent.KEYCODE_BACK && fm.getBackStackEntryCount() > 0) {
            popFragment();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void log(String msg) {
        Log.i("gallery", msg);
    }

    public void popFragment() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStackImmediate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        isPriview = false;
        invalidateOptionsMenu();
        if (photoGalleryFragment != null && chosemode == MODE_MULTIP_PICK) {
            photoGalleryFragment.notifyDataSetChanged();
        }
    }

    boolean isCropOver = false;

    public void sendImages() {
        if (isneedCrop && !isCropOver) {
            Iterator iterator = imasgemap.keySet().iterator();
            File file = new File(iterator.next().toString());
            if (!file.exists()) {
                Toast.makeText(this, "获取文件失败", Toast.LENGTH_SHORT).show();
            }
            sendStarCrop(file.getAbsolutePath());
        } else {
            Intent intent = new Intent();
            ArrayList<String> img = new ArrayList<>();
            Iterator iterator = imasgemap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                img.add((String) imasgemap.get(key));
            }
            intent.putExtra("data", img);
            setResult(RESULT_OK, intent);
            finish();
        }
    }


    public void insertImage(String fileName) {
        try {
            MediaStore.Images.Media.insertImage(getContentResolver(),
                    fileName, new File(fileName).getName(),
                    new File(fileName).getName());
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(new File(fileName));
            intent.setData(uri);
            sendBroadcast(intent);
            MediaScannerConnection.scanFile(this, new String[]{fileName}, new String[]{"image/jpeg"}, new MediaScannerConnection.MediaScannerConnectionClient() {
                @Override
                public void onMediaScannerConnected() {
                }

                @Override
                public void onScanCompleted(String path, Uri uri) {
                    photoGalleryFragment.addCaptureFile(path);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static final int REQUEST_CODE_CAMERA = 2001;
    public static final int REQUEST_CODE_CROP = 2002;
    private File currentfile;

    //点击照相机
    public void sendStarCamera() {
        // 拍照权限
        PermissionGen.needPermission(this,
                REQUEST_CODE_CAMERA,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}
        );

    }

    //相机
    @PermissionFail(requestCode = REQUEST_CODE_CAMERA)
    private void showTip1() {
        Toast.makeText(this, "获取权限才可以使用相机", Toast.LENGTH_SHORT).show();
    }

    @PermissionSuccess(requestCode = REQUEST_CODE_CAMERA)
    private void takePhoto() {
        getPhoto();

    }


    /**
     * 拍照获取
     */
    public void getPhoto() {
        currentfile = getTempFile();
        Uri imgUri = null;

        //        if (Build.VERSION.SDK_INT >= 24) {//这里用这种传统的方法无法调起相机
        //            imgUri = FileProvider.getUriForFile(mActivity, AUTHORITIES, imgFile);
        //        } else {
        //            imgUri = Uri.fromFile(imgFile);
        //        }
        /*
        * 1.现象
            在项目中调用相机拍照和录像的时候，android4.x,Android5.x,Android6.x均没有问题,在Android7.x下面直接闪退
           2.原因分析
            Android升级到7.0后对权限又做了一个更新即不允许出现以file://的形式调用隐式APP，需要用共享文件的形式：content:// URI
           3.解决方案
            下面是打开系统相机的方法，做了android各个版本的兼容:
        * */

        if (Build.VERSION.SDK_INT < 24) {
            // 从文件中创建uri
            imgUri = Uri.fromFile(currentfile);
        } else {
            //兼容android7.0 使用共享文件的形式
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, currentfile.getAbsolutePath());
            imgUri = getApplication().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        }


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            Intent intent = new Intent();
            ArrayList<String> img = new ArrayList<>();
            String crop_path = resultUri.getPath();
            isCropOver = true;
            if (crop_path != null && new File(crop_path) != null) {
                img.add(crop_path);
                intent.putExtra("data", img);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, "截取图片失败", Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CAMERA && (chosemode == PickConfig.MODE_SINGLE_PICK)) {
            if (currentfile != null && currentfile.exists() && currentfile.length() > 10) {
                if (isneedCrop && !isCropOver) {
                    sendStarCrop(currentfile.getAbsolutePath());
                } else {
                    insertImage(currentfile.getAbsolutePath());
                    Intent intent = new Intent();
                    ArrayList<String> img = new ArrayList<>();
                    img.add(currentfile.getAbsolutePath());
                    intent.putExtra("data", img);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            } else {
                Toast.makeText(MediaChoseActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CAMERA && (chosemode == MODE_MULTIP_PICK)) {
            if (currentfile != null && currentfile.exists() && currentfile.length() > 10) {
                getImageChoseMap().put(currentfile.getAbsolutePath(), currentfile.getAbsolutePath());
                invalidateOptionsMenu();
                insertImage(currentfile.getAbsolutePath());
            } else {
                Toast.makeText(MediaChoseActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void sendStarCrop(String path) {
        UCrop uCrop = UCrop.of(Uri.fromFile(new File(path)), Uri.fromFile(new File(getCropFile().getAbsolutePath())));
        if (isSquareCrop) {
            uCrop = uCrop.withAspectRatio(1, 1);
        } else {
            uCrop = uCrop.useSourceImageAspectRatio();
        }
        if (options == null) {
            options = new UCrop.Options();
        }
        options.setStatusBarColor(statusBarcolor);
        options.setToolbarColor(actionBarcolor);
        uCrop.withOptions(options);
        uCrop.start(this);

    }

    public File getTempFile() {
        String str = null;
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        date = new Date(System.currentTimeMillis());
        str = format.format(date);
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "IMG_" + str + ".jpg");
    }

    public File getCropFile() {
        return new File(getTmpPhotos());
    }

    /**
     * 获取tmp path
     *
     * @return
     */
    public String getTmpPhotos() {
        return new File(getCacheFile(), ".tmpcamara" + System.currentTimeMillis() + ".jpg").getAbsolutePath();
    }

    /**
     * 临时缓存目录
     *
     * @return
     */
    public String getCacheFile() {
        return getDir("post_temp", Context.MODE_PRIVATE).getAbsolutePath();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (currentfile != null && currentfile.exists()) {
            outState.putString("ImageFilePath", currentfile.getAbsolutePath());
            outState.putInt("chosemode", chosemode);
            outState.putBoolean("isneedCrop", isneedCrop);
        }
    }


}
