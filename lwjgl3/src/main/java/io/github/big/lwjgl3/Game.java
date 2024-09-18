package io.github.big.lwjgl3;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public abstract class Game implements ApplicationListener {

    // Game 中的当前需要被渲染的场景
    protected Screen screen;

    @Override
    public void dispose () {
        if (screen != null) {
            // 当应用被销毁时调用一次当前 Screen 的 hide() 方法
            screen.hide();

            // 注意: 这里没有调用 Screen 的 dispose() 方法, 所以需要在适当时机自己手动调用
        }
    }

    @Override
    public void pause () {
        if (screen != null) {
            screen.pause();
        }
    }

    @Override
    public void resume () {
        if (screen != null) {
            screen.resume();
        }
    }

    @Override
    public void render () {
        if (screen != null) {
            // 游戏被渲染时调用当前 Screen 的 render() 方法, 并将渲染时间步（delta）传递给 screen
            screen.render(Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void resize (int width, int height) {
        if (screen != null) {
            screen.resize(width, height);
        }
    }

    /**
     * 设置 Game 的当前需要渲染的场景时;
     * 先调用之前 Game 中旧的当前场景的 hide() 方法;
     * 然后调用新设置到 Game 中的当前场景的 show() 方法, 并接着调用一次 resize() 方法;
     */
    public void setScreen (Screen screen) {
        if (this.screen != null) {
            this.screen.hide();
        }
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    public Screen getScreen () {
        return screen;
    }
}
