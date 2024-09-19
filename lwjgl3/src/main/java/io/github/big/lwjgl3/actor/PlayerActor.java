package io.github.big.lwjgl3.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class PlayerActor  extends Actor {
    // 用于展示该演员的纹理区域
    private TextureRegion region;
    private Sprite sprite;
    private int totalCount = 0;
    // 初始化 Map 数组
    private final float[][] mapArray = {
        {854.0f, 254.0f,0},// totalCount=0
        {842.0f, 240.0f,0},// totalCount=1
        {835.0f, 310.0f,0},//totalCount=2
        {835.0f, 390.0f,0},//totalCount=3
        {835.0f, 460.0f,2},//totalCount=4
        {700.0f, 496.0f,0},
        {619.0f, 494.0f,0},
        {623.0f, 409.0f,0},
        {545.0f, 411.0f,0},
        {469.0f, 412.0f,-2},
        {386.0f, 413.0f,0},
        {311.0f, 415.0f,0},
        {311.0f, 491.0f,1},
        {241.0f, 487.0f,0},
        {158.0f, 496.0f,0},
        {159.0f, 414.0f,-2},
        {158.0f, 332.0f,0},
        {233.0f, 333.0f,0},
        {237.0f, 258.0f,3},
        {233.0f, 184.0f,0},
        {234.0f, 108.0f,-2},
        {315.0f, 101.0f,0},
        {395.0f, 103.0f,1},
        {470.0f, 106.0f,0},
        {545.0f, 103.0f,0},
        {663.0f, 124.0f,-2},
        {675.0f, 139,0}
    };
    public PlayerActor() {
        super();
//        this.region = region;
//        mapArray = new HashMap[{"x":842, "y":137}]

        // 将演员的宽高设置为纹理区域的宽高（必须设置, 否则宽高默认都为 0, 绘制后看不到）
//        setSize(this.region.getRegionWidth(), this.region.getRegionHeight());
        sprite = new Sprite( new Texture(Gdx.files.internal("player1.png")));
//        sprite.setPosition(849 ,108);
//        sprite.setSize(50,50);
    }


    public void move(int steps,Boolean forward){
        SequenceAction sequence = new SequenceAction();
        if(forward){
            for (int i = 1; i < steps+1; i++) {
                ++totalCount;
                float x = mapArray[totalCount][0] ;
                float y = mapArray[totalCount][1] ;
                System.out.println(totalCount);
                sequence.addAction(Actions.moveTo(x, y, 0.2F));
                if(i<steps){
                    sequence.addAction(Actions.delay(0.2F));
                }
            }
            this.addAction(sequence);
        }
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
