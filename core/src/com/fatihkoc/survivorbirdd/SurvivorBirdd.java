package com.fatihkoc.survivorbirdd;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class SurvivorBirdd extends ApplicationAdapter {
		SpriteBatch batch;
		Texture bird,background,background2,ufo,ufo1,ufo2,ufo3;
		float birdX,birdY;
		int gameState=0;
		float velocity=0;
		float gravity=0.85f;
		int numberOfEnemySet=4;
		float[] enemyX=new float[numberOfEnemySet];
		float distance=0;
		float[] enemyY=new float[numberOfEnemySet];
		float[] enemyY1=new float[numberOfEnemySet];
		float[] enemyY2=new float[numberOfEnemySet];
		float[] enemyY3=new float[numberOfEnemySet];
		BitmapFont font,font2;
		Random random=new Random();
		int randomSayi=0;
		Circle birdCircle;
		Circle [] enemyCircles;
		Circle [] enemyCircles1;
		Circle [] enemyCircles2;
		Circle [] enemyCircles3;
		ShapeRenderer shapeRenderer;
		int skor=0;
		int isMusicPlayer=0;
		int scoredenemy=0;
		Sound sound;
		Music music;



	@Override
	public void create () {
		batch=new SpriteBatch();
		bird=new Texture("bird.png");
		ufo=new Texture("ufo.png");
		ufo1=new Texture("ufo.png");
		ufo2=new Texture("ufo.png");
		ufo3=new Texture("ufo.png");
		background=new Texture("background.png");
		birdX=Gdx.graphics.getWidth()/4;
		birdY=Gdx.graphics.getHeight()/2;
		birdCircle=new Circle();
		enemyCircles=new Circle[numberOfEnemySet];
		enemyCircles1=new Circle[numberOfEnemySet];
		enemyCircles2=new Circle[numberOfEnemySet];
		enemyCircles3=new Circle[numberOfEnemySet];
		sound=Gdx.audio.newSound(Gdx.files.internal("ss.wav"));
		music=Gdx.audio.newMusic(Gdx.files.internal("gameover.wav"));
		music.setVolume(0.5f);
		font=new BitmapFont();
		font2=new BitmapFont();
		font2.setColor(Color.RED);
		font2.getData().setScale(6);
		font.setColor(Color.BROWN);
		font.getData().setScale(4);
		shapeRenderer=new ShapeRenderer();
		distance=Gdx.graphics.getWidth()/3;
		for(int i=0;i<numberOfEnemySet;i++){
			enemyX[i]=Gdx.graphics.getWidth()+(i*distance);
			randomSayi = random.nextInt(((Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/6) - Gdx.graphics.getHeight()/6 ) + 1) + Gdx.graphics.getHeight()/6;
			enemyY[i]=randomSayi;
			if(enemyY[i]<Gdx.graphics.getHeight()/3){
				enemyY1[i]= (float) (enemyY[i]+(Gdx.graphics.getHeight()/7*3));
				enemyY2[i]=enemyY1[i]+Gdx.graphics.getHeight()/7;
				enemyY3[i]=enemyY2[i]+Gdx.graphics.getHeight()/7;
			}else if(enemyY[i]>Gdx.graphics.getHeight()/3*2){
				enemyY1[i]= (float) (enemyY[i]-(Gdx.graphics.getHeight()/7*3));
				enemyY2[i]=enemyY1[i]-(Gdx.graphics.getHeight()/7);
				enemyY3[i]=enemyY2[i]-Gdx.graphics.getHeight()/7;
			}else{
				enemyY1[i]=enemyY[i]+Gdx.graphics.getHeight()/7;
				enemyY2[i]=enemyY[i]-Gdx.graphics.getHeight()/7;
				enemyY3[i]= (float) (enemyY2[i]-Gdx.graphics.getHeight()/7);
			}
			enemyCircles[i]=new Circle();
			enemyCircles1[i]=new Circle();
			enemyCircles2[i]=new Circle();
			enemyCircles3[i]=new Circle();
		}

	}

	@Override
	public void render () {
		batch.begin();

		batch.draw(background, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if(Gdx.input.justTouched()){
			gameState=1;
			long id=sound.play(0.5f);
			sound.setPitch(id, 1.6f);
			sound.setLooping(id, false);

		}
		if(gameState==1){
			isMusicPlayer=1;

			if(enemyX[scoredenemy]< birdX){
				skor++;
				if(scoredenemy<numberOfEnemySet-1){
					scoredenemy++;
				}else{
					scoredenemy=0;
				}
			}
			for(int i=0;i<numberOfEnemySet;i++){

				enemyX[i]=enemyX[i]-Gdx.graphics.getWidth()/200;
				if(enemyX[i]<0){
					enemyX[i]=numberOfEnemySet*distance;
					randomSayi = random.nextInt(((Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/6) - Gdx.graphics.getHeight()/6 ) + 1) + Gdx.graphics.getHeight()/6;
					enemyY[i]=randomSayi;
					if(enemyY[i]<Gdx.graphics.getHeight()/3){
						enemyY1[i]= (float) (enemyY[i]+(Gdx.graphics.getHeight()/7*3));
						enemyY2[i]=enemyY1[i]+Gdx.graphics.getHeight()/7;
						enemyY3[i]=enemyY2[i]+Gdx.graphics.getHeight()/7;
					}else if(enemyY[i]>Gdx.graphics.getHeight()/3*2){
						enemyY1[i]= (float) (enemyY[i]-(Gdx.graphics.getHeight()/7*3));
						enemyY2[i]=enemyY1[i]-(Gdx.graphics.getHeight()/7);
						enemyY3[i]=enemyY2[i]-Gdx.graphics.getHeight()/7;
					}else{
						enemyY1[i]=enemyY[i]+Gdx.graphics.getHeight()/7;
						enemyY2[i]=enemyY[i]-Gdx.graphics.getHeight()/7;
						enemyY3[i]= (float) (enemyY2[i]-Gdx.graphics.getHeight()/7);
					}

				}

			batch.draw(ufo, enemyX[i], enemyY[i], Gdx.graphics.getWidth()/17,Gdx.graphics.getHeight()/10);
			batch.draw(ufo1, enemyX[i], enemyY1[i],Gdx.graphics.getWidth()/17,Gdx.graphics.getHeight()/10);
			batch.draw(ufo2, enemyX[i], enemyY2[i], Gdx.graphics.getWidth()/17,Gdx.graphics.getHeight()/10);
			batch.draw(ufo3, enemyX[i], enemyY3[i], Gdx.graphics.getWidth()/17,Gdx.graphics.getHeight()/10);
			enemyCircles[i]=new Circle(enemyX[i]+Gdx.graphics.getWidth()/35,enemyY[i]+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/34);
			enemyCircles1[i]=new Circle(enemyX[i]+Gdx.graphics.getWidth()/35,enemyY1[i]+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/34);
			enemyCircles2[i]=new Circle(enemyX[i]+Gdx.graphics.getWidth()/35,enemyY2[i]+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/34);
			enemyCircles3[i]=new Circle(enemyX[i]+Gdx.graphics.getWidth()/35,enemyY3[i]+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/34);
			}

			if(Gdx.input.justTouched()){
				velocity=-Gdx.graphics.getHeight()/48;
			}
			if(birdY>0) {
				velocity = velocity + gravity;
				birdY = birdY - velocity;
			}else{
				gameState=2;
			}
		} else if(gameState==2){
			if(isMusicPlayer==1){
				music.play();
			}
			isMusicPlayer=0;
			font2.draw(batch, "Game Over", Gdx.graphics.getWidth()/3, (float) (Gdx.graphics.getHeight()/1.8));
			if(Gdx.input.justTouched()){
				gameState=1;
			}
			birdY=birdY=Gdx.graphics.getHeight()/2;
			for(int i=0;i<numberOfEnemySet;i++){
				enemyX[i]=Gdx.graphics.getWidth()+(i*distance);
				randomSayi = random.nextInt(((Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/6) - Gdx.graphics.getHeight()/6 ) + 1) + Gdx.graphics.getHeight()/6;
				enemyY[i]=randomSayi;
				if(enemyY[i]<Gdx.graphics.getHeight()/3){
					enemyY1[i]= (float) (enemyY[i]+(Gdx.graphics.getHeight()/7*3));
					enemyY2[i]=enemyY1[i]+Gdx.graphics.getHeight()/7;
					enemyY3[i]=enemyY2[i]+Gdx.graphics.getHeight()/7;
				}else if(enemyY[i]>Gdx.graphics.getHeight()/3*2){
					enemyY1[i]= (float) (enemyY[i]-(Gdx.graphics.getHeight()/7*3));
					enemyY2[i]=enemyY1[i]-(Gdx.graphics.getHeight()/7);
					enemyY3[i]=enemyY2[i]-Gdx.graphics.getHeight()/7;
				}else{
					enemyY1[i]=enemyY[i]+Gdx.graphics.getHeight()/7;
					enemyY2[i]=enemyY[i]-Gdx.graphics.getHeight()/7;
					enemyY3[i]= (float) (enemyY2[i]-Gdx.graphics.getHeight()/7);
				}
				enemyCircles[i]=new Circle();
				enemyCircles1[i]=new Circle();
				enemyCircles2[i]=new Circle();
				enemyCircles3[i]=new Circle();
			}
			velocity=0;
			skor=0;
			scoredenemy=0;
		}

		batch.draw(bird, birdX, birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/9);
		font.draw(batch, String.valueOf(skor), 100, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/10);
		batch.end();
		birdCircle.set(birdX+Gdx.graphics.getWidth()/30,birdY+Gdx.graphics.getHeight()/18,Gdx.graphics.getWidth()/40);
		/*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.circle(birdCircle.x, birdCircle.y, birdCircle.radius); */

		for(int i=0;i<numberOfEnemySet;i++){
			/*shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth()/35,enemyY[i]+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/34);
			shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth()/35,enemyY1[i]+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/34);
			shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth()/35,enemyY2[i]+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/34);
			shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth()/35,enemyY3[i]+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/34);*/
			if(Intersector.overlaps(birdCircle,enemyCircles[i])||Intersector.overlaps(birdCircle, enemyCircles1[i])||Intersector.overlaps(birdCircle, enemyCircles2[i])||Intersector.overlaps(birdCircle, enemyCircles3[i])){

				gameState=2;

			}
		}

		shapeRenderer.end();
	}

	@Override
	public void dispose () {

	}
}
