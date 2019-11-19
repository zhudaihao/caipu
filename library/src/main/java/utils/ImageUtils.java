package utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.GifTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * 图片加载工具
 */

public class ImageUtils {
    //单利
    private ImageUtils() {

    }

    private static ImageUtils imageUtils;

    public static ImageUtils instance() {
        synchronized (ImageUtils.class) {
            if (imageUtils == null) {
                imageUtils = new ImageUtils();


            }
        }
        return imageUtils;
    }


    // url 加载的图片地址
    // error加载错误的图片
    // placeholder预加载图片
    //  animate动画
    // thumbnail用原图的1/10作为缩略图
    // bitmapTransformations ：图片特殊处理

    /**
     * 加载普通图片
     */

    public void loadImage(@NonNull Context context, @NonNull Object url, @NonNull final ImageView imageView, Integer placeholder, Integer error) {
        GlideImage(context, url, imageView, placeholder, error);

    }

    public void loadImage(@NonNull Context context, @NonNull Object url, @NonNull final ImageView imageView, Integer placeholder, Integer error, Integer animate) {
        GlideImage(context, url, imageView, placeholder, error, animate);

    }

    public void loadImage(@NonNull Fragment fragment, @NonNull Object url, @NonNull final ImageView imageView, Integer placeholder, Integer error, Integer animate) {
        GlideImage(fragment, url, imageView, placeholder, error, animate);

    }

    public void loadImage(@NonNull Context context, @NonNull Object url, @NonNull final ImageView imageView, Integer placeholder, Integer error, Integer animate, BitmapTransformation bitmapTransformation) {
        GlideImage(context, url, imageView, placeholder, error, animate, bitmapTransformation);

    }

    public void loadImage(@NonNull Fragment fragment, @NonNull Object url, @NonNull final ImageView imageView, Integer placeholder, Integer error, Integer animate, BitmapTransformation bitmapTransformation) {
        GlideImage(fragment, url, imageView, placeholder, error, animate, bitmapTransformation);

    }

    private void GlideImage(Context context, Object url, final ImageView imageView, Integer placeholder, Integer error) {
        DrawableTypeRequest<Object> load = Glide.with(context).load(url);
        if (placeholder != null) {
            load.placeholder(placeholder);
        } else if (error != null) {
            load.error(error);
        }

        load.centerCrop().thumbnail(0.1f).into(imageView);
    }

    private void GlideImage(Context context, Object url, final ImageView imageView, Integer placeholder, Integer error, Integer animate) {
        DrawableTypeRequest<Object> load = Glide.with(context).load(url);
        if (placeholder != null) {
            load.placeholder(placeholder);
        } else if (error != null) {
            load.error(error);
        } else if (animate != null) {
            load.animate(animate);
        }

        load.centerCrop().thumbnail(0.1f).into(imageView);
    }


    private void GlideImage(Fragment fragment, Object url, final ImageView imageView, Integer placeholder, Integer error, Integer animate) {
        DrawableTypeRequest<Object> load = Glide.with(fragment).load(url);

        if (placeholder != null) {
            load.placeholder(placeholder);
        } else if (error != null) {
            load.error(error);
        } else if (animate != null) {
            load.animate(animate);
        }

        load.centerCrop().thumbnail(0.1f).into(imageView);
    }


    private void GlideImage(Context context, Object url, final ImageView imageView, Integer placeholder, Integer error, Integer animate, BitmapTransformation bitmapTransformation) {
        DrawableTypeRequest<Object> load = Glide.with(context).load(url);
        if (placeholder != null) {
            load.placeholder(placeholder);
        } else if (error != null) {
            load.error(error);
        } else if (animate != null) {
            load.animate(animate);
        } else if (bitmapTransformation != null) {
            load.transform(bitmapTransformation);
        }

        load.centerCrop().thumbnail(0.1f).into(imageView);
    }


    private void GlideImage(Fragment fragment, Object url, final ImageView imageView, Integer placeholder, Integer error, Integer animate, BitmapTransformation bitmapTransformation) {
        DrawableTypeRequest<Object> load = Glide.with(fragment).load(url);

        if (placeholder != null) {
            load.placeholder(placeholder);
        } else if (error != null) {
            load.error(error);
        } else if (animate != null) {
            load.animate(animate);
        } else if (bitmapTransformation != null) {
            load.transform(bitmapTransformation);
        }

        load.centerCrop().thumbnail(0.1f).into(imageView);
    }


    public void loadImage(Context context, Fragment fragment, Object url, final ImageView imageView, Integer error, Integer placeholder, Integer animate, BitmapTransformation bitmapTransformation, Transformation<Bitmap>... transformations) {
        GlideImage(context, fragment, url, imageView, error, placeholder, animate, bitmapTransformation, transformations);

    }

    private void GlideImage(Context context, Fragment fragment, Object url, ImageView imageView, Integer error, Integer placeholder, Integer animate, BitmapTransformation bitmapTransformation, Transformation<Bitmap>... bitmapTransformations) {
        DrawableTypeRequest<Object> load = null;
        if (context != null) {
            load = Glide.with(context).load(url);
        } else if (fragment != null) {
            load = Glide.with(fragment).load(url);
        }

        if (error != null) {
            load.error(error);
        } else if (placeholder != null) {
            load.placeholder(placeholder);
        } else if (animate != null) {
            load.animate(animate);
        } else if (bitmapTransformation != null) {
            load.transform(bitmapTransformation);
        } else if (bitmapTransformations != null) {
            load.bitmapTransform(bitmapTransformations);
        }

        load.centerCrop().thumbnail(0.1f).into(imageView);
    }



    /**
     * GIF图
     */
    public void loadGIF(@NonNull Context context, @NonNull Object url, @NonNull ImageView imageView, Integer error) {
        GlideGIF(context, url, imageView, error);

    }

    public void loadGIF(@NonNull Fragment fragment, @NonNull Object url, @NonNull ImageView imageView, Integer error) {
        GlideGIF(fragment, url, imageView, error);

    }

    private void GlideGIF(Context context, Object url, ImageView imageView, Integer error) {
        GifTypeRequest<Object> load = Glide.with(context).load(url).asGif();

        if (error != null) {
            load.error(error);
        }

        load.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }


    private void GlideGIF(Fragment fragment, Object url, ImageView imageView, Integer error) {
        GifTypeRequest<Object> load = Glide.with(fragment).load(url).asGif();

        if (error != null) {
            load.error(error);
        }

        load.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }
}
