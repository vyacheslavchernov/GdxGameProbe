package com.vych.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.managers.gameObjects.entities.BucketObject;
import com.vych.game.managers.gameObjects.entities.DropObject;
import com.vych.game.renderer.GameScreenRenderer;
import com.vych.game.renderer.SceneRenderer;

import java.lang.reflect.InvocationTargetException;

public class GameScreen implements Screen {
    private final SampleGame game;
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final SceneRenderer renderer;

    public GameScreen(final SampleGame game) {
        this.game = game;
        batch = game.getBatch();

        renderer = new GameScreenRenderer();
        renderer.loadAssets();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        GameObjectsManager gameObjectsManager = GameObjectsManager.getInstance();
        try {
            gameObjectsManager.instantiateGameObject(BucketObject.class, renderer, this).setCamera(camera);
            gameObjectsManager.instantiateGameObject(DropObject.class, renderer, this);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void render(float delta) {
        GameObjectsManager gameObjectsManager = GameObjectsManager.getInstance();

        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        gameObjectsManager.proceedStepInScene(this);

        batch.begin();
        renderer.renderScene(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        renderer.unloadAssets();
        batch.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
