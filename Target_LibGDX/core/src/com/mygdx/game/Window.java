package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

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
	public void render() {
		camera.setToOrtho(
				false,
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight()
		);

		ScreenUtils.clear(0, 0, 0, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		int numRows = 3;
		int numColumns = 3;
		Float yPadding = Gdx.graphics.getHeight() / (numRows + 1.0f);
		Float xPadding = Gdx.graphics.getWidth() / (numColumns + 1.0f);

		Float startX = xPadding;
		Float startY = Gdx.graphics.getHeight() - yPadding;

		Float currY = startY;
		Float currX = startX;

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				font.draw(
						batch,
						"Label " + (i * 3 + j + 1),
						currX,
						currY
				);

				currX += xPadding;
			}

			currY -= yPadding;
			currX = startX;
		}

		batch.end();
	}
	@Override
	public void dispose () {
		batch.dispose();
	}
}
