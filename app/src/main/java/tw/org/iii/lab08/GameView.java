package tw.org.iii.lab08;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class GameView extends View{
    private Resources res;
    private int viewW, viewH;
    private boolean isInit;
    private Bitmap bmpBall;
    private Matrix matrix;
    private float ballw, ballh, ballx, bally;
    private float dx, dy;
    Timer timer;
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        res = context.getResources();
        matrix = new Matrix();
        timer = new Timer();
    }
    private void init() {
        viewH = getHeight();
        viewW = getWidth();
        //Log.v("brad",viewW + "x" + viewH);
        dx = dy = 10;
        bmpBall = BitmapFactory.decodeResource(res,R.drawable.b0);
        ballw = viewW / 10f;
        ballh = bmpBall.getHeight()/(bmpBall.getWidth()/ballw);
        bmpBall = resizeBmp(bmpBall,ballw,ballh);

        timer.schedule(new BallTask(),1000,60);
        isInit = true;
    }
    private class BallTask extends TimerTask {
        @Override
        public void run() {
            if (ballx<0 || ballx+ballw > viewW){
                dx *= -1;
            }
            if (bally<0 || bally+ballh > viewH){
                dy *= -1;
            }
            ballx += dx;
            bally += dy;
            postInvalidate();
        }
    }
    Bitmap resizeBmp(Bitmap src, float width, float height){
        matrix.reset();
        matrix.postScale(width/src.getWidth(),height/src.getHeight());
        return Bitmap.createBitmap(src,0,0,src.getWidth(),src.getHeight(),matrix,false);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit) init();

        canvas.drawBitmap(bmpBall,ballx,bally,null);
    }
}
