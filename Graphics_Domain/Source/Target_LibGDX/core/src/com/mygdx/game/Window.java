package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import static com.badlogic.gdx.Gdx.files;

public class Window extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;

	OrthographicCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new OrthographicCamera();

	}

	@Override
	public void render () {

		camera.setToOrtho(
				false,
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight()
		);

		ScreenUtils.clear(0, 0, 0, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		font.draw(
				batch,
				"Hello World!",
				Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2
		);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
