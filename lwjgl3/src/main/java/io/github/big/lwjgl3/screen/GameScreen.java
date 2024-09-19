package io.github.big.lwjgl3.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.github.big.lwjgl3.MainGame;
import io.github.big.lwjgl3.actor.CountryActor;
import io.github.big.lwjgl3.actor.DiceActor;
import io.github.big.lwjgl3.actor.PlayerActor;
import io.github.big.lwjgl3.listener.MyClickListener;

public class GameScreen extends ScreenAdapter {

    private Texture manTexture;

    private Stage stage;

    private CountryActor manActor;

    private DiceActor diceActor;

    private PlayerActor playerActor;

    private int currentTextureIndex;

    public GameScreen() {
        currentTextureIndex = 0;
        // 创游戏人物的纹理, 图片 badlogic.jpg 的宽高为 256 * 256
        manTexture = new Texture(Gdx.files.internal("R-C.jpg"));

        // 使用伸展视口创建舞台
        stage = new Stage(new StretchViewport(MainGame.WORLD_WIDTH, MainGame.WORLD_HEIGHT));

        // 首先必须注册输入处理器（stage）, 将输入的处理设置给 舞台（Stage 实现了 InputProcessor 接口）
        // 这样舞台才能接收到输入事件, 分发给相应的演员 或 自己处理。
        Gdx.input.setInputProcessor(stage);

        // 创建游戏人物演员
        manActor = new CountryActor(new TextureRegion(manTexture)); //地图
        playerActor = new PlayerActor();//玩家
        diceActor = new DiceActor(currentTextureIndex,playerActor);//骰子


        //点击事件
        diceActor.addListener(new MyClickListener(playerActor));
        manActor.addListener(new MyClickListener(playerActor));

        //骰子位置
        diceActor.setBounds(926,65,50,50);
        playerActor.setBounds(849 ,108,50,50);

        // 添加演员到舞台
        stage.addActor(manActor);
        stage.addActor(diceActor);
        stage.addActor(playerActor);
    }



    @Override
    public void render(float delta) {
        // 红色清屏
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // 更新舞台逻辑
        stage.act();
        // 绘制舞台
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        // 场景被销毁时释放资源
        if (stage != null) {
            stage.dispose();
        }
        if (manTexture != null) {
            manTexture.dispose();
        }
    }

}
