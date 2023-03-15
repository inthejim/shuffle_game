package edu.skku.map.jimin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private Button buttonStartGame;
    private int[] index;
    private int emptyX;
    private int emptyY;
    private int state;
    private Bitmap pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStartGame = findViewById(R.id.button);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state=3;
                emptyX = 2;
                emptyY = 2;
                index=new int[9];
                Bitmap[] imgPic = new Bitmap[9];
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic1);

                int Origin_H = bitmap.getHeight();
                int Origin_W = bitmap.getWidth();

                int PicW = Origin_W / 3;
                int PicH = Origin_H / 3;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        imgPic[i * 3 + j] = Bitmap.createBitmap(bitmap, j * PicW, i * PicH, PicW, PicH);
                        index[i*3+j]=i*3+j;
                    }
                }

                gridView = findViewById(R.id.grid_view);
                gridView.setNumColumns(3);
                MyBaseAdapter baseAdapter = new MyBaseAdapter(MainActivity.this, imgPic,emptyX*3+emptyY);
                gridView.setAdapter(baseAdapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        int x = position / 3;
                        int y = position % 3;
                        int a=index[x*3+y];
                        Bitmap tem1;
                        if ((Math.abs(x - emptyX) == 1 && emptyY == y) || (Math.abs(y - emptyY) == 1 && emptyX == x)) {
                            tem1 = imgPic[3 * x + y];
                            imgPic[3 * x + y] = imgPic[emptyX * 3 + emptyY];
                            imgPic[emptyX * 3 + emptyY] = tem1;
                            index[x*3+y]=8;
                            index[emptyX*3+emptyY]=a;
                            emptyX = x;
                            emptyY = y;
                            MyBaseAdapter baseAdapter2 = new MyBaseAdapter(MainActivity.this, imgPic,emptyX*3+emptyY);
                            gridView.setAdapter(baseAdapter2);
                            checkFinish(3);
                        }
                    }
                });

            }
        });

        buttonStartGame = findViewById(R.id.button2);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                emptyX = 3;
                emptyY = 3;
                state = 4;
                index=new int[16];

                Bitmap[] imgPic = new Bitmap[16];
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic1);
                int Origin_H = bitmap.getHeight();
                int Origin_W = bitmap.getWidth();

                int PicW = Origin_W / 4;
                int PicH = Origin_H / 4;

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        imgPic[i * 4 + j] = Bitmap.createBitmap(bitmap, j * PicW, i * PicH, PicW, PicH);
                        index[i * 4 + j] = i * 4 + j;
                    }
                }

                gridView = findViewById(R.id.grid_view);
                gridView.setNumColumns(4);
                MyBaseAdapter baseAdapter = new MyBaseAdapter(MainActivity.this, imgPic,emptyX*4+emptyY);
                gridView.setAdapter(baseAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        int x = position / 4;
                        int y = position % 4;
                        int a = index[x * 4 + y];
                        Bitmap tem1;
                        if ((Math.abs(x - emptyX) == 1 && emptyY == y) || (Math.abs(y - emptyY) == 1 && emptyX == x)) {
                            tem1 = imgPic[4 * x + y];
                            imgPic[4 * x + y] = imgPic[emptyX * 4 + emptyY];
                            imgPic[emptyX * 4 + emptyY] = tem1;
                            index[x * 4 + y] = 15;
                            index[emptyX * 4 + emptyY] = a;
                            emptyX = x;
                            emptyY = y;
                            MyBaseAdapter baseAdapter2 = new MyBaseAdapter(MainActivity.this, imgPic,emptyX*4+emptyY);
                            gridView.setAdapter(baseAdapter2);
                            checkFinish(4);
                        }
                    }
                });
            }
        });

        buttonStartGame = findViewById(R.id.button3);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(state==3){
                    int num[]=new int[9];
                    for(int i=0;i<9;i++) {
                        Random random = new Random();
                        int a = random.nextInt(9);
                        if (i == 0) num[0] = a;
                        else {
                            int check = 0;
                            for (int j = 0; j < i; j++) {
                                if (num[j] == a) {
                                    i--;
                                    check = 1;
                                    break;
                                }
                            }
                            if (check == 1) continue;
                        }
                        num[i]=a;
                    }

                    Bitmap[] imgPic = new Bitmap[9];
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic1);

                    int Origin_H = bitmap.getHeight();
                    int Origin_W = bitmap.getWidth();

                    int PicW = Origin_W / 3;
                    int PicH = Origin_H / 3;

                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            imgPic[i * 3 + j] = Bitmap.createBitmap(bitmap, j * PicW, i * PicH, PicW, PicH);
                        }
                    }

                    Bitmap[] data=new Bitmap[9];
                    for(int i=0;i<9;i++){
                        System.out.println(num[i]);
                        data[i]=imgPic[num[i]];
                        index[i]=num[i];
                        if(num[i]==8){
                            emptyX=i/3;
                            emptyY=i%3;
                        }
                    }
                    gridView = findViewById(R.id.grid_view);
                    gridView.setNumColumns(3);
                    MyBaseAdapter baseAdapter = new MyBaseAdapter(MainActivity.this, data,emptyX*3+emptyY);
                    gridView.setAdapter(baseAdapter);

                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                            int x = position / 3;
                            int y = position % 3;
                            int a = index[x * 3 + y];
                            Bitmap tem1;
                            if ((Math.abs(x - emptyX) == 1 && emptyY == y) || (Math.abs(y - emptyY) == 1 && emptyX == x)) {
                                tem1 = data[3 * x + y];
                                data[3 * x + y] = data[emptyX * 3 + emptyY];
                                data[emptyX * 3 + emptyY] = tem1;
                                index[x * 3 + y] = 8;
                                index[emptyX * 3 + emptyY] = a;
                                emptyX = x;
                                emptyY = y;
                                MyBaseAdapter baseAdapter2 = new MyBaseAdapter(MainActivity.this, data,emptyX*3+emptyY);
                                gridView.setAdapter(baseAdapter2);
                                checkFinish(3);
                            }
                        }
                    });



                }
                if(state==4){
                    int num2[]=new int[16];
                    for(int i=0;i<16;i++) {
                        Random random = new Random();
                        int a = random.nextInt(16);
                        if (i == 0) num2[0] = a;
                        else {
                            int check = 0;
                            for (int j = 0; j < i; j++) {
                                if (num2[j] == a) {
                                    i--;
                                    check = 1;
                                    break;
                                }
                            }
                            if (check == 1) continue;
                        }
                        num2[i]=a;
                    }

                    Bitmap[] imgPic2 = new Bitmap[16];
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic1);

                    int Origin_H = bitmap.getHeight();
                    int Origin_W = bitmap.getWidth();

                    int PicW = Origin_W / 4;
                    int PicH = Origin_H / 4;

                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            imgPic2[i * 4 + j] = Bitmap.createBitmap(bitmap, j * PicW, i * PicH, PicW, PicH);
                        }
                    }

                    Bitmap[] data2=new Bitmap[16];
                    for(int i=0;i<16;i++){
                        System.out.println(num2[i]);
                        data2[i]=imgPic2[num2[i]];
                        index[i]=num2[i];
                        if(num2[i]==15){
                            emptyX=i/4;
                            emptyY=i%4;
                        }
                    }
                    gridView = findViewById(R.id.grid_view);
                    gridView.setNumColumns(4);
                    MyBaseAdapter baseAdapter3 = new MyBaseAdapter(MainActivity.this, data2,emptyX*4+emptyY);
                    gridView.setAdapter(baseAdapter3);

                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                            int x = position / 4;
                            int y = position % 4;
                            int a = index[x * 4 + y];
                            Bitmap tem1;
                            if ((Math.abs(x - emptyX) == 1 && emptyY == y) || (Math.abs(y - emptyY) == 1 && emptyX == x)) {
                                tem1 = data2[4 * x + y];
                                data2[4 * x + y] = data2[emptyX * 4 + emptyY];
                                data2[emptyX * 4 + emptyY] = tem1;
                                index[x * 4 + y] = 15;
                                index[emptyX * 4 + emptyY] = a;
                                emptyX = x;
                                emptyY = y;
                                MyBaseAdapter baseAdapter4 = new MyBaseAdapter(MainActivity.this, data2,emptyX*4+emptyY);
                                gridView.setAdapter(baseAdapter4);
                                checkFinish(4);
                            }
                        }
                    });

                }

            }
        });
    }

    private void checkFinish(int a){
        boolean fin=false;
        if(emptyX==(a-1)&&emptyY==(a-1)){
            for(int i=0;i<(a*a-1);i++){
                if(index[i]==i){
                    fin=true;
                }else{
                    fin=false;
                    break;
                }
            }
        }
        if(fin){
            Toast.makeText(MainActivity.this,"FINISHED!",Toast.LENGTH_SHORT).show();
        }
    }


}
