package com.diabin.festec.uimake;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.diabin.festec.uimake.sample.CommentItem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hearsilent.discreteslider.DiscreteSlider;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

import static android.app.Activity.RESULT_OK;

public class DecorateFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private Context context;
    private int position;
    private final int REQUEST_PHOTO = 20000;
    private final int REQUEST_SAVE = 20001;

    private final int POSITION_ONE = 10001;
    private final int POSITION_TWO = 10002;


    private ImageView button_1;
    private ImageView button_2;
    private ImageView img;
    private ImageView img_2;
    private DiscreteSlider value;
    private RadioGroup radio_group;
    private RadioButton radioButton;
    private RadioButton radioButton_2;
    private int radio_1;
    private int radio_2;
    private CheckBox check_1;
    private CheckBox check_2;
    private CheckBox check_3;
    private boolean canCheck;
    private Button submit;

    //结果区域
    private LinearLayout resultLayout;
    private ImageView button_result;
    private ImageView img_result;

    //保存图片的流
    private Bitmap bitmap;
//    private FlexboxLayout img_container;
//    private FlexboxLayout img_container2;

    public DecorateFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.decorate, container, false);
        button_1 = view.findViewById(R.id.button_1);
        button_2 = view.findViewById(R.id.button_2);
        img = view.findViewById(R.id.img);
        img_2 = view.findViewById(R.id.img_2);
        value = view.findViewById(R.id.value);
        radio_group = view.findViewById(R.id.radio_group);
        radio_1 = R.id.radio_1;
        radio_2 = R.id.radio_2;
        radioButton = view.findViewById(radio_1);
        radioButton_2 = view.findViewById(radio_2);
        radioButton.setChecked(true);
        check_1 = view.findViewById(R.id.check_1);
        check_2 = view.findViewById(R.id.check_2);
        check_3 = view.findViewById(R.id.check_3);
        canCheck = false;
        submit = view.findViewById(R.id.submit);

        resultLayout = view.findViewById(R.id.resultLayout);
        button_result = view.findViewById(R.id.button_result);
        img_result = view.findViewById(R.id.img_result);
        resultLayout.setVisibility(View.INVISIBLE);

//        img_container = view.findViewById(R.id.img_container);
//        img_container2 = view.findViewById(R.id.img_container2);
        listenerRegister();
        return view;
    }


    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!canCheck) {
                buttonView.setChecked(false);
            } else {
                //不可以什么都不选--choosing nothing is not allowed
                if (!check_3.isChecked() && !check_1.isChecked() && !check_2.isChecked()) {
                    buttonView.setChecked(true);
                }
            }
        }
    };

    ;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1:
                position = POSITION_ONE;
                permissionRequest(permissionType.PHOTO);
                break;
            case R.id.button_2:
                position = POSITION_TWO;
                permissionRequest(permissionType.PHOTO);
                break;
            case R.id.submit:
                submitImage();
                break;
            case R.id.button_result:
                permissionRequest(permissionType.SAVE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == POSITION_ONE) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // 处理你自己的逻辑 ....
                String path1 = path.get(0);
                Log.i("路径", path1);
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(path1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
//                ContentResolver cr = getContext().getContentResolver();
                Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
                Log.i("长度", String.valueOf(bitmap.getByteCount()));
//                ImageView imageView = new ImageView(getContext());
//                imageView.setImageBitmap(bitmap);
//                img_container.addView(imageView);
                img.setImageBitmap(bitmap);
                Log.i("高", String.valueOf(img.getHeight()));
                Log.i("宽", String.valueOf(img.getWidth()));
            }
        } else if (requestCode == POSITION_TWO) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // 处理你自己的逻辑 ....
                String path1 = path.get(0);
                Log.i("路径", path1);
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(path1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
//                ContentResolver cr = getContext().getContentResolver();
                Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
//                ImageView imageView = new ImageView(getContext());
//                imageView.setImageBitmap(bitmap);
//                img_container2.addView(imageView);
                img_2.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PHOTO:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    selectPhoto(position);
                }
                break;
            case REQUEST_SAVE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    savePhotoToGallery();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == radio_1) {
            canCheck = false;
            clearChecks();
        } else if (checkedId == radio_2) {
            canCheck = true;
            setCheckDefault();
        }
    }

    private void clearChecks() {
        check_1.setChecked(false);
        check_2.setChecked(false);
        check_3.setChecked(false);
    }

    private void setCheckDefault() {
        check_1.setChecked(true);
    }

    private void listenerRegister() {
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        submit.setOnClickListener(this);
        radio_group.setOnCheckedChangeListener(this);
        check_1.setOnCheckedChangeListener(onCheckedChangeListener);
        check_2.setOnCheckedChangeListener(onCheckedChangeListener);
        check_3.setOnCheckedChangeListener(onCheckedChangeListener);
        submit.setOnClickListener(this);
        button_result.setOnClickListener(this);
    }

    private void selectPhoto(int position) {
        // Multi image selector form an Activity
        MultiImageSelector.create(getContext())
                .showCamera(true) // 是否显示相机. 默认为显示
                .single() // 单选模式
                .start(this, position);
    }

    private void permissionRequest(permissionType type) {
        if (Build.VERSION.SDK_INT >= 23) {
            boolean hasLocationPermission =
                    ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
            boolean hasLocationPermission2 = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
            if (!hasLocationPermission && !hasLocationPermission2) {
                if (type == permissionType.PHOTO)
                    this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PHOTO);
                else if (type == permissionType.SAVE)
                    this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_SAVE);
            } else {
                Log.e("权限", "已经有权限");
                if (type == permissionType.PHOTO)
                    selectPhoto(position);
                else if (type == permissionType.SAVE)
                    savePhotoToGallery();
            }
        }
    }

    private void submitImage() {
        Drawable image = img.getDrawable();
        Drawable image2 = img_2.getDrawable();
        int levelDegree = value.getCount();
        int type = getType();
        sendRequest(image, image2, levelDegree, type);
        showResultImage();
    }

    /**
     * 结果图显示到屏幕上
     */
    private void showResultImage() {
        Resources resources = getContext().getResources();
        Drawable female = resources.getDrawable(R.drawable.female);
        img_result.setImageDrawable(female);
//        Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
//        img_result.setImageBitmap(bitmap);
        resultLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 发送网络请求端,返回类型不确定，可能是图片相关类型
     */
    private void sendRequest(Drawable image, Drawable image2, int levelDegree, int type) {
        //TODO 请求函数体

    }

    /**
     * 获得上妆类型
     * 0-全局;1-眼影;2-嘴唇;4-面部
     *
     * @return 0-全局化妆;1-局部化妆眼影;3-局部化妆眼影+嘴唇;5-局部化妆眼影+面部;6-局部化妆嘴唇+面部;7-局部化妆眼影+嘴唇+面部
     */
    private int getType() {
        if (radioButton.isChecked())
            return 0;
        else if (radioButton_2.isChecked()) {
            int type = 0;
            if (check_1.isChecked())
                type += 1;
            if (check_2.isChecked())
                type += 2;
            if (check_3.isChecked())
                type += 4;
            return type;
        } else
            return 0;
    }


    /**
     * 将Bitmap图片保存到本地相册
     */
    private void savePhotoToGallery(/*final Context context, final Bitmap bitmap*/) {
        //TODO 将生成的图片转化未Bitmap，这里用库图片代替
        Resources resources = getContext().getResources();
        Drawable female = resources.getDrawable(R.drawable.female);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) female;
        bitmap = bitmapDrawable.getBitmap();

        //处理
        if (bitmap == null) {
            Toast.makeText(context, "未获取到图片", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 其次把文件插入到系统图库
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// HH:mm:ss
                //获取当前时间
                Date date = new Date(System.currentTimeMillis());
                String fileName = simpleDateFormat.format(date);
                try {
                    MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap,
                            fileName, "测试 图集"); // 名字和描述没用，系统会自动更改
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "图片保存至相册", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "图片保存失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Log.e("图片保存异常：", e.getMessage());
                }
            }
        }).start();
    }

    enum permissionType {
        PHOTO,
        SAVE
    }
}
