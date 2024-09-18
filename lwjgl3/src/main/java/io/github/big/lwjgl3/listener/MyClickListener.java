package io.github.big.lwjgl3.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Timer;
import io.github.big.lwjgl3.actor.DiceActor;
import io.github.big.lwjgl3.listener.inter.MyClickListenerInter;

import java.util.HashMap;
import java.util.Map;

import static org.apache.tools.ant.taskdefs.Antlib.TAG;

public class MyClickListener extends MyClickListenerInter {

//    private Map<String,Object>[] mappoint;
//    private int integer = 0;
    /**
     * 对象（演员/舞台）被点击时调用
     *
     * @param x
     * 		点击时（手指抬起时）的 X 轴坐标, 相对于被点击对象（监听器注册者）的左下角
     *
     * @param y
     * 		点击时（手指抬起时）的 Y 轴坐标, 相对于被点击对象（监听器注册者）的左下角
     */
    @Override
    public void clicked(InputEvent event, float x, float y) {
        // 获取响应这个点击事件的演员
        Actor actor = event.getListenerActor();

        Gdx.app.log(TAG, "被点击: " + x + ", " + y + "; Actor: " + actor.getClass().getSimpleName());

        Map<String,Object> map = new HashMap<>();
        map.put("x",x);
        map.put("y",y);
        Gdx.app.log(TAG, "mappoint: " + map.toString());
//        if(integer<27){
//            mappoint[integer] = map;
//        }else{
//            Gdx.app.log(TAG, "mappoint: " + mappoint.toString());
//        }
//        integer++;

        String action = actor.getClass().getSimpleName();
        if (action.equals("DiceActor")) {
            if (actor instanceof DiceActor) {
                DiceActor diceActor = (DiceActor) actor;
                // 创建一个定时器，在三秒内每0.1秒切换一次纹理
                if (diceActor.timer != null) {
                    diceActor.timer.stop(); // 如果计时器正在运行，先停止它
                } else {
                    diceActor.startTextureSwitch();
                }


            }
        }
    }
}
