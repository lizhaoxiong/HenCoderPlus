package com.hencoder.plus.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.Nullable;

import android.util.AttributeSet;
import android.view.View;

import com.hencoder.plus.R;
import com.hencoder.plus.Utils;

public class ImageTextView extends View {
    private static final float IMAGE_WIDTH = Utils.dp2px(100);
    private static final float IMAGE_OFFSET = Utils.dp2px(80);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean justo sem, sollicitudin in maximus a, vulputate id magna. Nulla non quam a massa sollicitudin commodo fermentum et est. Suspendisse potenti. Praesent dolor dui, dignissim quis tellus tincidunt, porttitor vulputate nisl. Aenean tempus lobortis finibus. Quisque nec nisl laoreet, placerat metus sit amet, consectetur est. Donec nec quam tortor. Aenean aliquet dui in enim venenatis, sed luctus ipsum maximus. Nam feugiat nisi rhoncus lacus facilisis pellentesque nec vitae lorem. Donec et risus eu ligula dapibus lobortis vel vulputate turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In porttitor, risus aliquam rutrum finibus, ex mi ultricies arcu, quis ornare lectus tortor nec metus. Donec ultricies metus at magna cursus congue. Nam eu sem eget enim pretium venenatis. Duis nibh ligula, lacinia ac nisi vestibulum, vulputate lacinia tortor.";
    float[] cutWidth = new float[1];
    float[] points = {8, 8, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};
    float[] linePoints = {20,20,120,20,70,20,70,70,20,70,120,70};

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        //bitmap = getAvatar((int) Utils.dp2px(100));
        //paint.setTextSize(Utils.dp2px(14));
        //paint.getFontMetrics(fontMetrics);

    }

    //todo 请画出自己的名字
    //todo 用简单图形绘制

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(40);
        paint.setAntiAlias(true);
        //paint.setStrokeCap(Paint.Cap.SQUARE);
        //canvas.drawCircle(300,300,200,paint);
        //canvas.drawRect(100,100,200,200,paint);
        //canvas.drawRect(new Rect(100,100,200,200),paint);
        //canvas.drawPoint(200,200,paint);
        //canvas.drawPoints(points,paint);
        //canvas.drawPoints(points,3,7,paint);
        //canvas.drawOval(10,100,400,200,paint);
        //canvas.drawLine(100,100,200,200,paint);
        //canvas.drawLines(linePoints,paint);
        //canvas.drawRoundRect(100, 100, 500, 300, 50, 50, paint);
        //canvas.drawArc(100, 100, 800, 500, 0, 200, true, paint); // 绘制扇形
        //canvas.drawArc(200, 100, 800, 500, 20, 140, false, paint);
        //Path path = new Path();
        //path.setFillType(Path.FillType.EVEN_ODD);
        //path.addCircle(400,500,300, Path.Direction.CCW);
        //path.addCircle(700,500,300, Path.Direction.CCW);
        //path.addCircle(400,500,300, Path.Direction.CCW);
        //path.addCircle(700,500,300, Path.Direction.CCW);
        //path.addCircle(400,500,300, Path.Direction.CW);
        //path.lineTo(0,0);
        //path.rLineTo(0,500);
        //path.quadTo(200,0,0,0);
        //path.rQuadTo(500,600,0,1200);
        //path.rCubicTo(500,400,-500, 800,0,1200);

        //path.moveTo(500,500);
        //path.rMoveTo(0,-900);
        //path.rLineTo(500,1000);

        //path.moveTo(100,100);
        //path.lineTo(200,100);
        //path.lineTo(150,150);
        //path.close();



        //path.arcTo(100,100,300,300,-90,90,false);

        //path.addArc(200, 200, 400, 400, -225, 225);
        //path.lineTo(100,100);
        //path.arcTo(100, 100, 500, 300, 0, 350, true);
        //path.lineTo(400, 542);


        //path.setFillType(Path.FillType.EVEN_ODD);
        //canvas.drawPath(path, paint);
        //Bitmap  bitmap =((BitmapDrawable) getResources().getDrawable(R.drawable.avatar_rengwuxian)).getBitmap();
        //canvas.drawBitmap(bitmap,20,20,paint);
        canvas.drawText("你好啊",200,200,paint);

        //canvas.drawColor(Color.parseColor("#88880000")); // 半透明红色
        //canvas.drawARGB(50,136,0,0);

        //canvas.drawBitmap(bitmap, getWidth() - IMAGE_WIDTH, IMAGE_OFFSET, paint);
        //int length = text.length();
        //float verticalOffset = - fontMetrics.top;
        //for (int start = 0; start < length; ) {
        //    int maxWidth;
        //    float textTop = verticalOffset + fontMetrics.top;
        //    float textBottom = verticalOffset + fontMetrics.bottom;
        //    if (textTop > IMAGE_OFFSET && textTop < IMAGE_OFFSET + IMAGE_WIDTH
        //            || textBottom > IMAGE_OFFSET && textBottom < IMAGE_OFFSET + IMAGE_WIDTH) {
        //        // 文字和图片在同一行
        //        maxWidth = (int) (getWidth() - IMAGE_WIDTH);
        //    } else {
        //        // 文字和图片不在同一行
        //        maxWidth = getWidth();
        //    }
        //    int count = paint.breakText(text, start, length, true, maxWidth, cutWidth);
        //    canvas.drawText(text, start, start + count, 0, verticalOffset, paint);
        //    start += count;
        //    verticalOffset += paint.getFontSpacing();
        //}
    }

    //Bitmap getAvatar(int width) {
    //    BitmapFactory.Options options = new BitmapFactory.Options();
    //    options.inJustDecodeBounds = true;
    //    BitmapFactory.decodeResource(getResources(), R.drawable.avatar_rengwuxian, options);
    //    options.inJustDecodeBounds = false;
    //    options.inDensity = options.outWidth;
    //    options.inTargetDensity = width;
    //    return BitmapFactory.decodeResource(getResources(), R.drawable.avatar_rengwuxian, options);
    //}
}
