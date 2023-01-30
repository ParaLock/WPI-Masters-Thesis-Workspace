package xyz.codingdaddy;

import xyz.codingdaddy.hud.window.WindowWithTopRightCornerCloseButton;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Application rendering.
 * 
 * @author serhiy
 */
public class WindowExampleMain extends ApplicationAdapter implements InputProcessor {

	private Stage stage;
	private WindowWithTopRightCornerCloseButton windowWithTopRightCornerCloseButton;
	
	@Override
	public void create () {
		stage = new Stage();

		windowWithTopRightCornerCloseButton = new WindowWithTopRightCornerCloseButton();
		windowWithTopRightCornerCloseButton.setSize(400, 300);
		windowWithTopRightCornerCloseButton.setModal(true);
		windowWithTopRightCornerCloseButton.setVisible(true);
		windowWithTopRightCornerCloseButton.setMovable(true);
		windowWithTopRightCornerCloseButton.setPosition(Gdx.graphics.getWidth()/2 - windowWithTopRightCornerCloseButton.getWidth()/2, Gdx.graphics.getHeight()/2 - windowWithTopRightCornerCloseButton.getHeight()/2);
		
		stage.addActor(windowWithTopRightCornerCloseButton);
		
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(this);
		multiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
		stage.act();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.NUM_1) {
			windowWithTopRightCornerCloseButton.setVisible(true);
			return true;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
