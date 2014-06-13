package com.example.projtest;

import java.util.Random;

import org.andengine.audio.music.Music;
import org.andengine.engine.Engine;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;

import com.badlogic.gdx.physics.box2d.Body;

// TODO: Auto-generated Javadoc
/**
 * Klasa odpowiedzialna za obiekt jednego balona.
 */
public class Cbaloon {
	
	/** Generator liczb losowych. */
	Random generator = new Random();
	
	/** sprite czyli obiekt do ktorego pozniej wczytujemy tekstury, przemieszczamy go itp. */
	private Sprite sprite;
	
	/** tekstura balona. */
	BitmapTextureAtlas playerTexture;
	
	/** Region tekstury balona. */
	ITextureRegion playerTexureRegion;
	
	/** �wiat fizyki. */
	PhysicsWorld physicsWorld;
	
	/** Silnik gry. */
	Engine mEngine;
	
	/** Cia�o obiektu. */
	Body body;
	
	/** d�wi�k zabicia balona. */
	Music musicPop;
	
	/** ilosc wszystkich obiektow tej klasy w ca�ej aplikacji. */
	static int baloonInstances = 0;
	
	/** Zabezpieczenie, dzi�ki ktoremu mamy pewno�� �e nast�pi�o tylko jedno przechwycenie naci�ci�cia na obiekt. */
	private boolean onlyOneEvent = false;
	
	/**
	 * Konstruktor inicjuj�cy nowy balon.
	 *
	 * @param sprite - obiekt do ktorego pozniej wczytujemy tekstury, przemieszczamy go itp
	 * @param playerTexureRegion - tekstura balona
	 * @param mEngine - silnik gry
	 */
	public Cbaloon(Sprite sprite, ITextureRegion playerTexureRegion, Engine mEngine) {
		
		baloonInstances++;
		this.sprite = sprite;
		this.playerTexureRegion = playerTexureRegion;
		this.mEngine = mEngine;

		this.sprite = new Sprite(generator.nextInt(800), generator.nextInt(480),
				this.playerTexureRegion, this.mEngine.getVertexBufferObjectManager()){
			@Override
		    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y) 
		    {
		        if (pSceneTouchEvent.isActionUp() && onlyOneEvent==false)
		        { 		        	
		        	Cbaloon.this.sprite.detachSelf();
		        	Cbaloon.this.sprite = null;
		        	MainActivity.musicPop.play(); 
		            Cbaloon.baloonInstances--;
		            onlyOneEvent = true;
		        }
		        return true;
		    };
		};				
	}

	/**
	 * Gets the sprite.
	 *
	 * @return the sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * Gets the body.
	 *
	 * @return cia�o tekstury
	 */
	public Body getBody(){
		return this.body;
	}
	

}
