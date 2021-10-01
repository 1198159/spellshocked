package com.spellshocked.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.spellshocked.game.input.FunctionalInput;
import com.spellshocked.game.input.InputScheduler;
import com.spellshocked.game.world.World;

import static com.badlogic.gdx.Input.Keys;

public class Spellshocked extends ApplicationAdapter {
	private World world;
	private SpriteBatch b;
	private OrthographicCamera c;
	@Override
	public void create() {
		world = new World(64, 64);
		b = new SpriteBatch();
		c = new OrthographicCamera(800, 480);
		c.position.set(c.viewportWidth / 2f, c.viewportHeight / 2f, 30);
		scheduleInputs();
	}

	private void scheduleInputs() {
		FunctionalInput.fromKeyPress(Keys.A).onTrue(()->c.zoom+=0.02);
		FunctionalInput.fromKeyPress(Keys.Q).onTrue(()->c.zoom-=0.02);
		FunctionalInput.fromKeyPress(Keys.LEFT).onTrue(()->c.translate(-3, 0, 0));
		FunctionalInput.fromKeyPress(Keys.RIGHT).onTrue(()->c.translate(3, 0, 0));
		FunctionalInput.fromKeyPress(Keys.UP).onTrue(()->c.translate(0, 3, 0));
		FunctionalInput.fromKeyPress(Keys.DOWN).onTrue(()->c.translate(0, -3, 0));
	}

	@Override
	public void render() {
		InputScheduler.getInstance().run();
		ScreenUtils.clear(0, 0, 0.2f, 1);
		c.update();
		b.setProjectionMatrix(c.combined);
		b.begin();
		world.draw(b, c.position);
		b.end();
	}

	@Override
	public void dispose() {
		b.dispose();
	}
}