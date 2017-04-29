package com.example.aluno.prjsnake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by aluno on 13/04/2017.
 */
public class Quadrado extends View {
    private Paint pincelRed, pincelGreen, pincelBlue, pincelBlack;
    private int x = 100, y = 100;
    private boolean selecionado;

    public Quadrado(Context context) {
        super(context);
        pincelRed = buildPincel(255, 255, 0, 0);
        pincelGreen = buildPincel(255, 0, 255, 0);
        pincelBlue = buildPincel(255, 0, 0, 255);

        pincelBlack = buildPincel(255, 0, 0, 0);
        setFocusable(true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(10, 10, 50, 50, pincelRed);
        canvas.drawRect(20, 20, 40, 40, pincelGreen);
        canvas.drawRect(20, 25, 35, 35, pincelBlue);

        canvas.drawRect(200, 200, 300, 300, pincelBlack);
        canvas.drawCircle(x, y, 40, pincelRed);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xt = event.getX();
        float yt = event.getY();
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN) {
            selecionado = Math.sqrt(Math.abs(xt-x) + Math.abs(yt-y)) < 40;
        }
        if(action == MotionEvent.ACTION_MOVE && selecionado) {
            x = (int) xt;
            y = (int) yt;
        }
        if(action == MotionEvent.ACTION_UP && selecionado) {
            selecionado = false;
        }
        invalidate();
        return true;
    }

    private Paint buildPincel(int alpha, int red, int green, int blue) {
        Paint pincel = new Paint();
        pincel.setARGB(alpha, red, green, blue);
        return pincel;
    }
}
