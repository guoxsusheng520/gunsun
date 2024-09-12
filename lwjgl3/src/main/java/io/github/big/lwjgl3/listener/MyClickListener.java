package io.github.big.lwjgl3.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.github.big.lwjgl3.listener.inter.MyClickListenerInter;
import static org.apache.tools.ant.taskdefs.Antlib.TAG;

public class MyClickListener extends MyClickListenerInter {
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
    }
}
