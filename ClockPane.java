package lecturepractice.javafx;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.time.LocalDateTime;

public class ClockPane extends Pane {

    private int sec;
    private int min;
    private int hour;

    public ClockPane(){
        setCurrentTime();
    }

    void setCurrentTime(){
        LocalDateTime now=LocalDateTime.now();
        hour=now.getHour();
        min=now.getMinute();
        sec=now.getSecond();
        paintClock();

    }

    public void paintClock(){
        double centerX=getWidth()*0.5;
        double centerY=getHeight()*0.5;
        double radius=Math.min(getHeight(),getWidth())* 0.8 * 0.5;

        Circle circle=new Circle(centerX,centerY,radius);
        circle.setFill(Color.WHITE);  //içinin rengi
        circle.setStroke(Color.BLACK); //kenar renk
        circle.setStrokeWidth(3);  //kenar kalınlığı

        double seclength=radius*0.9;
        double secX=centerX+seclength*Math.sin(sec*2*Math.PI/60);
        double secY=centerX-seclength*Math.cos(sec*2*Math.PI/60);
        Line secLine=new Line(centerX,centerY,secX,secY);
        secLine.setStroke(Color.GREEN);

        double minlength=radius*0.7;
        double minX=centerX+minlength*Math.sin(min*2*Math.PI/60);
        double minY=centerX-minlength*Math.cos(min*2*Math.PI/60);
        Line minLine=new Line(centerX,centerY,minX,minY);
        minLine.setStroke(Color.RED);
        minLine.setStrokeWidth(3);


        double hourlength=radius*0.5;
        double hourX=centerX+hourlength*Math.sin((hour%12+min/60.0)*2*Math.PI/12.0);
        double hourY=centerY-hourlength*Math.cos((hour%12+min/60.0)*2*Math.PI/12.0);
        Line hourLine=new Line(centerX,centerY,hourX,hourY);
        hourLine.setStroke(Color.BLUE);
        hourLine.setStrokeWidth(5);

        //SAAT ÜSTÜNE 12-3-6-9 YAZMA

        Text text12 = new Text(centerX -8, centerY - radius * 0.9, "12");
        Text text3 = new Text(centerX + radius * 0.9, centerY + 5, "3");
        Text text6 = new Text(centerX - 5, centerY + radius * 0.9 + 8, "6");
        Text text9 = new Text(centerX - radius * 0.9 - 8, centerY + 5, "9");
        text12.setFont(Font.font(15));
        text3.setFont(Font.font(15));
        text6.setFont(Font.font(15));
        text9.setFont(Font.font(15));



        // Dakika çizgilerini ekleyin
        for (int i = 0; i < 60; i++) {
            double angle = Math.PI * 2 / 60 * i;
            double startX = centerX + radius * Math.cos(angle);
            double startY = centerY + radius * Math.sin(angle);
            double endX = centerX + (radius - 10) * Math.cos(angle); // Çizginin uzunluğu
            double endY = centerY + (radius - 10) * Math.sin(angle); // Çizginin uzunluğu
            Line line = new Line(startX, startY, endX, endY);
            getChildren().add(line);
        }


        getChildren().clear();
        getChildren().addAll(circle,hourLine,minLine,secLine,text12,text9,text6,text3);
    }

    @Override
    protected void setWidth(double v) {
        super.setWidth(v);
        paintClock();
    }

    @Override
    protected void setHeight(double v) {
        super.setHeight(v);
        paintClock();
    }
}
