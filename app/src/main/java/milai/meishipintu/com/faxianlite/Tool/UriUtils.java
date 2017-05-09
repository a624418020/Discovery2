package milai.meishipintu.com.faxianlite.Tool;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/9/29.
 */

public class UriUtils {

    //将ContentUri装华为FileUri
    /*
        在Android4.4以后，系统通过选择相册图片返回的Uri为Content开头的Uri，而裁剪图片只能识别File开头的Uri
        但是部分SDK做了修改，此处实现Uri的转换
     */
    public static Uri convertUri(Uri contentUri, Context context) {
        if ("file".equals(contentUri.getScheme())) {
            //本身是FileUri开头不需要转换
            return contentUri;
        } else {
            //通过ContentResolver查询文件记录，再获取filepath，最后转换为FileUri返回
            Cursor cursor = context.getContentResolver().query(contentUri
                    , new String[]{android.provider.MediaStore.Images.ImageColumns.DATA}
                    , null, null, null);
            cursor.moveToFirst();
            String filePath = cursor.getString(0);
            cursor.close();

            return Uri.fromFile(new File(filePath));
        }
    }


    /**
     * 将Bitmap写入SD卡中的一个文件中,并返回写入文件的Uri
     * @param bm
     * @return file开头Uri
     *
     * 因为应用可用内存最大值问题，如果不做保存直接使用Uri.parse(MediaStore.Images
     * .Media.insertImage(getContentResolver(), bitmap, null,null))转换，并将此Uri传给
     * 裁剪应用，图片质量会大幅下降，因此先将图片保存，并通过文件生成Uri
     */
    public static void saveBitmap(Bitmap bm, File tempFile) {
        try {
            //打开文件输出流
            FileOutputStream fos = new FileOutputStream(tempFile);
            //将bitmap压缩后写入输出流(参数依次为图片格式、图片质量和输出流)
            bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
            //刷新输出流
            fos.flush();
            //关闭输出流
            fos.close();
            //返回File类型的Uri
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
