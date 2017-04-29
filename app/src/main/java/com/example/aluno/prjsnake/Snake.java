package com.example.aluno.prjsnake;

import android.graphics.Canvas;
import android.graphics.Rect;
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

    boolean perdeu = false;
    int x[] = new int[300];
    int y[] = new int[300];
    int tam = 3;
    private int widthScreen;
    private int heightScreen;

    private DirecoesEnum direcao = DirecoesEnum.PARADO;

    public Snake(int x, int y) {
        this.x[0] = x + 100;  // cabeça da cobra em x
        this.y[0] = y;  // cabeça da cobra em y
        this.x[1] = x + 50;
        this.y[1] = y;
        this.x[2] = x;
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

    private void moverDireita() {
        moverCorpo();
        x[0] = x[0] + 50;
    }

    private void moverEsquerda() {
        moverCorpo();
        x[0] = x[0] - 50;
    }

    private void moverBaixo() {
        moverCorpo();
        y[0] = y[0] + 50;
    }

    private void moverCima() {
        moverCorpo();
        y[0] = y[0] - 50;
    }

    public void setDirecao(DirecoesEnum direcao) {
        this.direcao = direcao;
    }

    public void moverSnake() {
        if(!perdeu) {
            switch (direcao) {
                case DIREITA:
                    if ((x[0] + 50) <= widthScreen)
                        moverDireita();
                    break;
                case ESQUERDA:
                    if ((x[0] - 50) >= 0)
                        moverEsquerda();
                    break;
                case ACIMA:
                    if ((y[0] - 50) >= 0)
                        moverCima();
                    break;
                case BAIXO:
                    if ((y[0] + 50) <= (heightScreen - 310))
                        moverBaixo();
                    break;
            }
        }
        perdeu = cabecaBateuNoCorpo(x[0], y[0]);
    }

    public boolean comeuPonto(Rect ponto) {
        if(ponto.contains(x[0], y[0])) {
            this.tam += 1;

            return true;
        }
        return false;
    }

    public boolean cabecaBateuNoCorpo(int x, int y) {
        for (int i = tam - 1; i > 0 ; i--) {
            if(this.x[i] == x && this.y[i] == y) {
                return true;
            }
        }
        return false;
    }

    public void setWidthScreen(int widthScreen) {
        this.widthScreen = widthScreen;
    }

    public void setHeightScreen(int heightScreen) {
        this.heightScreen = heightScreen;
    }

    public boolean posicaoPertenceASnake(int x, int y) {
        for (int i = tam - 1; i >= 0 ; i--) {
            if(this.x[i] == x && this.y[i] == y) {
                return true;
            }
        }
        return false;
    }

    public boolean isPerdeu() {
        return perdeu;
    }

}
