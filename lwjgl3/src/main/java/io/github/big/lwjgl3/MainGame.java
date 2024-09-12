package io.github.big.lwjgl3;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.github.big.lwjgl3.actor.CountryActor;
import io.github.big.lwjgl3.actor.PlayerActor;
import io.github.big.lwjgl3.listener.MyClickListener;
import io.github.big.lwjgl3.listener.MyInputListener;

/**
 * 游戏主程序的启动入口类, 实现 ApplicationListener 接口
 */
public class MainGame implements ApplicationListener {

    // 纹理画布
    private SpriteBatch batch;
    // 纹理
    private Texture texture;

    // 纹理
    private Texture playerTexture;
    // 精灵
    private Sprite palyerSprite;

    private Pixmap pixmap;

    //自定义的演员
    private CountryActor countryActor;

    private PlayerActor playerActor;
    // 舞台
    private Stage stage;

    @Override
    public void create() {
        // 设置 Log 输出级别
        Gdx.app.setLogLevel(Application.LOG_DEBUG);


        // 创建纹理画布
        batch = new SpriteBatch();



        /*
         * 使用 assets 文件夹中的图片 badlogic.jpg 创建纹理,
         * 文件路径相对于 assets 文件夹根目录, 如果图片放在子目录, 则路径为 "xxx/badlogic.jpg"
         */
        texture = new Texture("R-C.jpg");
        //画布大小


        // 创建玩家演员纹理
        playerTexture = new Texture(Gdx.files.internal("player1.png"));
        // 创建玩家演员
        playerActor = new PlayerActor(new TextureRegion(playerTexture));
        /* 设置玩家演员属性的值 */
        // 设置玩家演员的坐标
        playerActor.setPosition(863, 133);
        playerActor.setScale(0.25F, 0.25F);
        playerActor.setOrigin(0,0);
        playerActor.addListener(new MyClickListener());

        // 创建演员纹理
        Texture countryTexture = new Texture(Gdx.files.internal("Ivory-Coast.png"));
        // 创建演员
        countryActor = new CountryActor(new TextureRegion(countryTexture));
        /* 设置演员属性的值 */
        // 设置演员的坐标
        countryActor.setPosition(218, 83);
        countryActor.setScale(0.20F, 0.20F);
        countryActor.setOrigin(0,0);

        // 使用默认的构造方法创建舞台, 宽高默认为屏幕宽高
        stage = new Stage();
        // 将 演员 添加到舞台中, 由舞台去更新演员的逻辑和绘制
        stage.addActor(countryActor);
        stage.addActor(playerActor);
        // 首先必须注册输入处理器（stage）, 将输入的处理设置给 舞台（Stage 实现了 InputProcessor 接口）
        // 这样舞台才能接收到输入事件, 分发给相应的演员 或 自己处理。
        Gdx.input.setInputProcessor(stage);

        // 给舞台添加输入监听器（包括触屏, 鼠标点击, 键盘按键 的输入）
        stage.addListener(new MyInputListener());
        // 给演员添加一个 点击 监听器（只包括 手指点击 或 鼠标点击）
        countryActor.addListener(new MyClickListener());
        // 只有需要监听输入的舞台/演员才需要添加监听器
        // 如果要移除指定监听器, 可以调用相应的 removeListener(listener) 方法

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void render() {
        // 设置清屏颜色为红色（RGBA）
        Gdx.gl.glClearColor(1, 0, 0, 1);
        // 清屏
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // 更新演员逻辑
        countryActor.act(Gdx.graphics.getDeltaTime());
        playerActor.act(Gdx.graphics.getDeltaTime());
        // 更新舞台逻辑，并批处理舞台中的演员（自动逐个调用演员的 act() 方法更新演员逻辑）
        stage.act();
        // 绘制舞台，并批处理舞台中的演员（自动逐个调用演员的 draw() 方法绘制演员）
        stage.draw();

        batch.begin();				// 绘制开始
        batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());	// 在屏幕左下角绘制纹理
        playerActor.draw(batch, 1.0F);
        countryActor.draw(batch, 1.0F);
        batch.end();				// 绘制结束
    }

    @Override
    public void dispose() {

        // 释放纹理资源
        if (batch != null) {
            batch.dispose();
        }
        // 释放纹理资源
        if (playerTexture != null) {
            playerTexture.dispose();
        }
        // 释放纹理资源
        if (texture != null) {
            texture.dispose();
        }
        // 释放舞台资源
        if (stage != null) {
            stage.dispose();
        }
    }

}
