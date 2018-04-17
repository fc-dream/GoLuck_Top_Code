package top.goluck.mvp.factory;

import top.goluck.mvp.presenter.BasePresenter;

public interface PresenterFactory<P extends BasePresenter> {
    P createPresenter();
}
