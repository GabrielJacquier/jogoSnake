package com.example.aluno.prjsnake;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.provider.Contacts;

/**
 * Created by aluno on 18/04/2017.
 */
public class Snake {
    public static enum DirecoesEnum {
        DIREITA,
        ACIMA,
        BAIXO,
        ESQUERDA,
        PARADO
    }

    int x[] = new int[300];
    int y[] = new int[300];
    int tam = 3;
    private int widthScreen;
    private int heightScreen;

    private DirecoesEnum direcao = DirecoesEnum.PARADO;

    public Snake(int x, int y) {
        this.x[0] = x;  // cabeça da cobra em x
        this.y[0] = y;  // cabeça da cobra em y
        this.x[1] = x + 50;
        this.y[1] = y;
        this.x[2] = x + 100;
        this.y[2] = y;
        tam = 3;
    }

    public void draw(Drawable draw, Canvas canvas) {
        for (int i = 0; i < tam; i++) {
            draw.setBounds(x[i], y[i], x[i]+50, y[i]+50);
            draw.draw(canvas);
        }
    }

    private void moverCorpo() {
        for (int i = tam; i > 0 ; i--) {
            x[i] = x[i -1];
            y[i] = y[i -1];
        }
    }

    public void moverDireita() {
        moverCorpo();
        x[0] = x[0] + 50;
    }

    public void moverEsquerda() {
        moverCorpo();
        x[0] = x[0] - 50;
    }

    public void moverBaixo() {
        moverCorpo();
        y[0] = y[0] + 50;
    }

    public void moverCima() {
        moverCorpo();
        y[0] = y[0] - 50;
    }

    public void setDirecao(DirecoesEnum direcao) {
        this.direcao = direcao;
    }

    public void moverSnake() {
        switch (direcao) {
            case DIREITA:
                if((x[0] + 50) <= widthScreen)
                    moverDireita();
                break;
            case ESQUERDA:
                if((x[0] - 50) >= 0)
                    moverEsquerda();
                break;
            case ACIMA:
                if((y[0] - 50) >= 0)
                    moverCima();
                break;
            case BAIXO:
                if((y[0] - 50) <= (heightScreen - 450))
                    moverBaixo();
                break;
        }
    }

    public void setWidthScreen(int widthScreen) {
        this.widthScreen = widthScreen;
    }

    public void setHeightScreen(int heightScreen) {
        this.heightScreen = heightScreen;
    }

}
