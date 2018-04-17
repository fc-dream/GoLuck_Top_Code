package top.goluck.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 作者：luck on 2018/1/18 18:18
 * 邮箱：fc_dream@163.com
 * MVP_2018_1_21
 */
public class BasePresenter<View> {

    @Nullable private View view;
    private CopyOnWriteArrayList<OnDestroyListener> onDestroyListeners = new CopyOnWriteArrayList<>();

    protected void onCreate(@Nullable Bundle savedState) {
    }

    protected void onDestroy() {
    }
    public interface OnDestroyListener {
        void onDestroy();
    }


    public void addOnDestroyListener(OnDestroyListener listener) {
        onDestroyListeners.add(listener);
    }

    public void removeOnDestroyListener(OnDestroyListener listener) {
        onDestroyListeners.remove(listener);
    }

    protected void onSave(Bundle state) {
    }

    protected void onTakeView(View view) {
    }

    protected void onDropView() {
    }

    @Nullable
    public View getView() {
        return view;
    }

    public void create(Bundle bundle) {
        onCreate(bundle);
    }

    public void destroy() {
        for (OnDestroyListener listener : onDestroyListeners) {
            listener.onDestroy();
        }
        onDestroy();
    }

    public void save(Bundle state) {
        onSave(state);
    }

    public void takeView(View view) {
        this.view = view;
        onTakeView(view);
    }

    public void dropView() {
        onDropView();
        this.view = null;
    }

}
