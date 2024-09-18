package io.github.big.lwjgl3.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.github.big.lwjgl3.MainGame;
import io.github.big.lwjgl3.actor.CountryActor;
import io.github.big.lwjgl3.actor.LogoActor;

public class StartScreen implements Screen {

    // 为了方便与 MainGame 进行交互, 创建 Screen 时将 MainGame 作为参数传进来
    private MainGame mainGame;

    private Texture logoTexture;

    private Stage stage;

    private LogoActor logoActor;



    // 渲染时间步累计变量（当前场景被展示的时间总和）
    private float deltaSum;

    public StartScreen(MainGame mainGame) {
        this.mainGame = mainGame;
        // 创游戏人物的纹理, 图片 badlogic.jpg 的宽高为 256 * 256
        logoTexture = new Texture(Gdx.files.internal("logo.png"));

        // 使用伸展视口创建舞台
        stage = new Stage(new StretchViewport(MainGame.WORLD_WIDTH, MainGame.WORLD_HEIGHT));

        // 创建游戏人物演员
        logoActor = new LogoActor(new TextureRegion(logoTexture));
        // 将演员设置到舞台中心
        logoActor.setPosition(
            stage.getWidth() / 2 - logoActor.getWidth() / 2,
            stage.getHeight() / 2 - logoActor.getHeight() / 2
        );

        // 添加演员到舞台
        stage.addActor(logoActor);

    }
    @Override
    public void show() {
        deltaSum = 0;
    }

    @Override
    public void render(float delta) {
        // 累计渲染时间步
        deltaSum += delta;

        if (deltaSum >= 3.0F) {
            // 开始场景展示时间超过 3 秒, 通知 MainGame 切换场景（启动主游戏界面）
            if (mainGame != null) {
                mainGame.showGameScreen();
                return;
            }
        }

        // 使用淡蓝色清屏
        Gdx.gl.glClearColor(0.75F, 1, 0.98F, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // 更新舞台逻辑
        stage.act();
        // 绘制舞台
        stage.draw();

    }

    @Override
    public void resize(int i, int i1) {

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

    @Override
    public void dispose() {
// 场景被销毁时释放资源
        if (stage != null) {
            stage.dispose();
        }
        if (logoTexture != null) {
            logoTexture.dispose();
        }
    }
}
