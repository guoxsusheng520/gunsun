package io.github.big.lwjgl3.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;

import java.util.Random;

public class DiceActor extends Actor {
    private Sprite sprite;
    // 用于展示该演员的纹理区域
    private TextureRegion region;
    // 假设你有多个纹理要切换
    private Texture[] textures;
    private PlayerActor player1;
    public Timer timer; // 用于控制纹理切换的计时器
//    private TextureRegion[] textures;

    private int currentIndex;

    private float switchInterval = 0.1f; // 纹理切换间隔
    private float totalDuration = 1.0f; // 总持续时间
    public int randomNumber = 0;
    public DiceActor(int currentTextureIndex,PlayerActor playerActor) {
//        super();
        player1 = playerActor;
        currentIndex = currentTextureIndex;
        // 初始化纹理数组
        textures = new Texture[]{
            new Texture(Gdx.files.internal("dice1.png")),
            new Texture(Gdx.files.internal("dice2.png")),
            new Texture(Gdx.files.internal("dice3.png")),
            new Texture(Gdx.files.internal("dice4.png")),
            new Texture(Gdx.files.internal("dice5.png")),
            new Texture(Gdx.files.internal("dice.png")),
            // 添加更多纹理...
        };
        sprite = new Sprite(textures[currentIndex]); // 初始化为第一个纹理
        sprite.setPosition(926,65);
        sprite.setSize(50,50);
    }

    public void switchTexture() {
        currentIndex = (currentIndex + 1) % textures.length; // 循环切换纹理
        sprite.setTexture(textures[currentIndex]);
    }
    public int startTextureSwitch() {
        // 创建一个计时器任务
        timer = new Timer();
        final int[] randomNumberHolder = new int[1];
        timer.scheduleTask(new Timer.Task() {
            private float elapsedTime = 0;

            @Override
            public void run() {
                elapsedTime += switchInterval;
                if (elapsedTime >= totalDuration) {
                    // 达到总持续时间，停止计时器
                    timer.stop();
                    timer.clear();
                    timer = null;
                    Random random = new Random();
                    int min = 0;
                    int max = 5;
                    randomNumberHolder[0] = random.nextInt(max - min + 1) + min;
                    sprite.setTexture(textures[randomNumberHolder[0]]);
//                    player1.moveTo(randomNumberHolder[0] + 1);
                    player1.move(2,true);

                } else {
                    // 每次切换纹理
                    switchTexture();

                }
            }
        }, 0, switchInterval); // 从0秒开始，每switchInterval秒执行一次
        return randomNumberHolder[0]; // 返回随机数
    }
    public void setRegion(TextureRegion region) {
        this.region = region;
        // 重新设置纹理区域后, 需要重新设置宽高
        setSize(this.region.getRegionWidth(), this.region.getRegionHeight());
    }

    /**
     * 演员的逻辑处理
     *
     * @param delta
     *		表示从渲染上一帧开始到现在渲染当前帧的时间间隔, 或者称为渲染的 时间步 / 时间差。单位: 秒
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        // 现在这里一般没有什么逻辑要处理
    }

    /**
     * 绘制演员
     *
     * @param batch
     * 		纹理画布, 用于绘制演员封装的纹理。SpriteBatch 就是实现了 Batch 接口
     *
     * @param parentAlpha
     * 		父节点的透明度, 处理透明度和演员的颜色属性有关, 稍微复杂, 这里暂时先不处理
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        // 如果 region 为 null 或者 演员不可见, 则直接不绘制
        if (sprite == null || !isVisible()) {
            return;
        }
        /*
         * 绘制纹理区域
         * 将演员中的 位置(position, 即 X, Y 坐标), 缩放和旋转支点(origin), 宽高尺寸, 缩放比, 旋转角度 应用到绘制中,
         * 最终 batch 会将综合结果绘制到屏幕上
         */
        batch.draw(
            sprite,
            getX(), getY(),
            getOriginX(), getOriginY(),
            getWidth(), getHeight(),
            getScaleX(), getScaleY(),
            getRotation()
        );
    }
}
