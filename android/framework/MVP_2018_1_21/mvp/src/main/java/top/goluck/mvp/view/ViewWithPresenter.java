package top.goluck.mvp.view;

import top.goluck.mvp.presenter.BasePresenter;

public interface ViewWithPresenter<P extends BasePresenter> {
    P getPresenter();
}
