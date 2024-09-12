package io.github.big.lwjgl3.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import static org.apache.tools.ant.taskdefs.Antlib.TAG;

/**
 * 输入事件监听器（包括触屏, 鼠标点击, 键盘按键 的输入）
 */

public class MyInputListener extends InputListener {
    /**
     * 当有键盘按键被按下时调用, 参数 keycode 是被按下的按键的键值,
     * 所有键盘按键的键值常量定义在 com.badlogic.gdx.Input.Keys 类中
     */
    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        switch (keycode) {
            case Input.Keys.UP: {
                Gdx.app.log(TAG, "被按下的按键: 方向上键");
                break;
            }
            case Input.Keys.DOWN: {
                Gdx.app.log(TAG, "被按下的按键: 方向下键 ");
                break;
            }
            case Input.Keys.A: {
                Gdx.app.log(TAG, "被按下的按键: A键");
                break;
            }
            case Input.Keys.ENTER: {
                Gdx.app.log(TAG, "被按下的按键: 回车键");
                break;
            }
            default: {
                Gdx.app.log(TAG, "其他按键, KeyCode: " + keycode);
                break;
            }
        }
        return false;
    }


    /**
     * 手指/鼠标 按下时调用
     *
     * @param x
     *      按下时的 X 轴坐标, 相对于被触摸对象（监听器注册者）的左下角
     *
     * @param y
     * 		按下时的 Y 轴坐标, 相对于被触摸对象（监听器注册者）的左下角
     *
     * @param pointer
     *      按下手指的ID, 用于多点触控时辨别按下的是第几个手指,
     *      一般情况下第一只手指按下时 pointer 为 0, 手指未抬起前又有一只手指按下, 则后按下的手指 pointer 为 1。
     *      同一只手指的 按下（touchDown）, 拖动（touchDragged）, 抬起（touchUp）属于同一次序列动作（pointer 值相同）,
     *      pointer 的值在 按下 时被确定, 之后这只手指产生的的 拖动 和 抬起 动作将会把该已确定的 pointer 值传递给其事件方法
     *      touchDragged() 和 touchUp() 方法。
     *
     * @return
     *      返回值为 boolean 类型, 用于告诉上一级当前对象（演员/舞台）是否需要处理该次事件。 <br/><br/>
     *
     *      返回 true: 表示当前对象需要处理该次事件, 则之后这只手指产生的 拖动（touchDragged）和 抬起（touchUp）事件
     *          也会传递到当前对象。<br/><br/>
     *
     *      返回 false: 表示当前对象不处理该次事件, 既然不处理, 则之后这只手指产生的 拖动（touchDragged）和 抬起（touchUp）事件
     *          也将不会再传到到当前对象。<br/><br/>
     *
     *      PS: 当前对象是否处理一只手指的触摸事件（按下, 拖动, 抬起）只在 按下时（touchDown）确定,
     *          所以之后的 touchDragged() 和 touchUp() 方法中就不再判断, 因此返回类型为 void。
     */
    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Gdx.app.log(TAG, "touchDown: " + x + ", " + y + "; pointer: " + pointer);
        return true;
    }

    /**
     * 手指/鼠标 按下后拖动时调用
     */
    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        Gdx.app.log(TAG, "touchDragged: " + x + ", " + y + "; pointer: " + pointer);
    }

    /**
     * 手指/鼠标 抬起时调用
     */
    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        Gdx.app.log(TAG, "touchUp: " + x + ", " + y + "; pointer: " + pointer);
    }
}
