package com.example.aluno.prjsnake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by aluno on 18/04/2017.
 */
public class Tela extends View {

    private Paint paintRed;
    private Paint paintBlack;
    private Drawable imgFundo;
    private Drawable imgQuadrado;
    private int width, height;
    private Snake cobra;
    private Rect btnDireita;
    private Rect btnEsquerda;
    private Rect btnBaixo;
    private Rect btnCima;
    private Rect ponto = new Rect(350, 350, 400, 400);;

    public Tela(Context context) {
        super(context);
        imgQuadrado = context.getResources().getDrawable(R.drawable.quadrado);
        paintRed = new Paint();
        paintRed.setARGB(255, 0, 0, 255);
        paintRed.setStyle(Paint.Style.FILL);

        paintBlack = new Paint();
        paintBlack.setARGB(255, 0, 0, 0);
        paintBlack.setStyle(Paint.Style.FILL);
        cobra = new Snake(150, 150);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        btnBaixo = new Rect(width - 300, height - 100, width - 200, height);
        btnDireita = new Rect(width - 200, height - 200, width - 100, height - 100);
        btnEsquerda = new Rect(width - 400, height - 200, width - 300, height - 100);
        btnCima = new Rect(width - 300, height - 300, width - 200, height - 200);
        canvas.drawRect(btnDireita, paintRed);
        canvas.drawRect(btnEsquerda, paintRed);
        canvas.drawRect(btnBaixo, paintRed);
        canvas.drawRect(btnCima, paintRed);
        canvas.drawRect(ponto, paintBlack);
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
        if (acao == MotionEvent.ACTION_DOWN) {
            if (btnDireita.contains((int) xt, (int) yt)) {
                cobra.setDirecao(Snake.DirecoesEnum.DIREITA);
                cobra.moverSnake();
            }

            if (btnEsquerda.contains((int) xt, (int) yt)) {
                cobra.setDirecao(Snake.DirecoesEnum.ESQUERDA);
                cobra.moverSnake();
            }

            if (btnBaixo.contains((int) xt, (int) yt)) {
                cobra.setDirecao(Snake.DirecoesEnum.BAIXO);
                cobra.moverSnake();
            }

            if (btnCima.contains((int) xt, (int) yt)) {
                cobra.setDirecao(Snake.DirecoesEnum.ACIMA);
                cobra.moverSnake();
            }
            if(cobra.comeuPonto(ponto)) {
                int posicaoWidth;
                int posicaoHeight;
                do {
                    int qtdPontosWidth = width / 50;
                    int qtdPontosHeight = height / 50;
                    Random randomGenerator = new Random();
                    posicaoWidth = randomGenerator.nextInt(qtdPontosWidth - 1) * 50;
                    posicaoHeight = randomGenerator.nextInt(qtdPontosHeight - 1) * 50;
                } while(cobra.posicaoPertenceASnake(posicaoWidth - 50, posicaoHeight - 50)
                        || novaPosicaoPontoInvalida(posicaoWidth, posicaoHeight));
                ponto.set(posicaoWidth - 50, posicaoHeight - 50, posicaoWidth, posicaoHeight);
            }

            if(cobra.isPerdeu()) {
                Toast.makeText(this.getContext(), "Você perdeu!", Toast.LENGTH_LONG).show();
            }
        }
        return true;
    }

    private boolean novaPosicaoPontoInvalida(int x, int y) {
        if((this.height - 310) < y) {
            return true;
        }
        if(x <= 0 || y <= 0) {
            return true;
        }
        return false;
    };
}
