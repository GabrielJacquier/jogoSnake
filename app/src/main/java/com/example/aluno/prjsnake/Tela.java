package com.example.aluno.prjsnake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by aluno on 18/04/2017.
 */
public class Tela extends View {
    private Paint paint;
    private Drawable imgFundo;
    private Drawable imgQuadrado;
    private int width, height;
    private Snake cobra;
    private Rect btnDireita;
    private Rect btnEsquerda;
    private Rect btnBaixo;
    private Rect btnCima;

    public Tela(Context context) {
        super(context);
        imgFundo = context.getResources().getDrawable(R.drawable.koala);
        imgQuadrado = context.getResources().getDrawable(R.drawable.quadrado);
        paint = new Paint();
        paint.setARGB(255, 0, 0, 255);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        cobra = new Snake(150, 150);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        imgFundo.setBounds(0, 0, this.width, this.height);
        imgFundo.draw(canvas);
        btnBaixo = new Rect(width - 300, height - 100, width - 200, height);
        btnDireita = new Rect(width - 200, height - 200, width - 100, height - 100);
        btnEsquerda = new Rect(width - 400, height - 200, width - 300, height - 100);
        btnCima = new Rect(width - 300, height - 300, width - 200, height - 200);
        canvas.drawRect(btnDireita, paint);
        canvas.drawRect(btnEsquerda, paint);
        canvas.drawRect(btnBaixo, paint);
        canvas.drawRect(btnCima, paint);
        cobra.draw(imgQuadrado, canvas);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        cobra.setHeightScreen(this.height);
        cobra.setWidthScreen(this.width);
        float xt = event.getX();
        float yt = event.getY();
        int acao = event.getAction();
        if(acao == MotionEvent.ACTION_DOWN) {
            if(btnDireita.contains((int) xt, (int) yt)) {
                cobra.setDirecao(Snake.DirecoesEnum.DIREITA);
                cobra.moverSnake();
            }

            if(btnEsquerda.contains((int) xt, (int) yt)) {
                cobra.setDirecao(Snake.DirecoesEnum.ESQUERDA);
                cobra.moverSnake();
            }

            if(btnBaixo.contains((int) xt, (int) yt)) {
                cobra.setDirecao(Snake.DirecoesEnum.BAIXO);
                cobra.moverSnake();
            }

            if(btnCima.contains((int) xt, (int) yt)) {
                cobra.setDirecao(Snake.DirecoesEnum.ACIMA);
                cobra.moverSnake();
            }
        }
        return true;
    }
}
