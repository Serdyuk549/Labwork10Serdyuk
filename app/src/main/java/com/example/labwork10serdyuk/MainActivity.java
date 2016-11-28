package com.example.labwork10serdyuk;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MainActivity extends Activity implements View.OnTouchListener {

    Paint p; //кисть
    int mywidth=0, myheight=0; //ширина и высота экрана в пикселях
    float x,y;
    int k=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pp=new painter(this);
        setContentView(pp);
        pp.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = (int) event.getX(); // текущая координата X пальца
        y = (int) event.getY(); // текущая координата Y пальца
        pp.invalidate(); //перерисовка экрана (вызов onDraw)
        return true;
    }

    class painter extends View {

        public painter(Context context) {
            super(context);
            p = new Paint();
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            mywidth = w;
            myheight = h;
            super.onSizeChanged(w, h, oldw, oldh);
        }


        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);
            int y1=100,y2=200;
            int xb1 = 0, xb2 = 100;
            int xw1 = 100,xw2 = 200;
            int num=1;
            while(y1<(myheight-200)) {
                while (xb1 < mywidth) {
                    p.setColor(Color.BLACK);
                    canvas.drawRect(xb1, y1, xb2, y2, p); //заполнить прямоугольник черным
                    xb1 += 200;
                    xb2 += 200;
                }
                while (xw1 < mywidth) {
                    p.setColor(Color.WHITE);
                    canvas.drawRect(xw1, y1, xw2, y2, p); //заполнить прямоугольник белым
                    xw1 += 200;
                    xw2 += 200;
                }
                y1 += 100;
                y2 += 100;
                num++;
                if(num%2==1){
                    xb1=0;
                    xb2=100;
                    xw1=100;
                    xw2=200;
                }
                if(num%2==0) {
                    xb1 = 100;
                    xb2 = 200;
                    xw1 = 0;
                    xw2 = 100;
                }
            }
            p.setTextSize(50); //размер шрифта текста - 50
            p.setTextAlign(Paint.Align.CENTER); //центровка текста
            int mw = myheight-(myheight%100)-200;
            int mh = mywidth-(mywidth%100);
            int sy = myheight - 100;
            int sx = mywidth - (mywidth / 2);
            if(x==0 && y==0) {
                String s = "Screen size " + mw + " " + mh;
                p.setColor(Color.BLACK);
                canvas.drawText(s, sx, sy, p); //выводит строку текста
            }
            if(x>0 && y>0) {
                invalidate(sx,sy,sx,sy);
                p.setColor(Color.BLACK);
                k++;
                String s = x + " " + y + " число нажатий " + k;
                canvas.drawText(s, sx, sy, p);
            }
        }
    }


    painter pp;
}
