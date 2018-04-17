package top.goluck.mvp.view;

import android.os.Bundle;
import top.goluck.mvp.util.ParcelFn;
import android.support.annotation.Nullable;
import top.goluck.mvp.factory.PresenterFactory;
import top.goluck.mvp.factory.PresenterStorage;
import top.goluck.mvp.presenter.BasePresenter;

public final class PresenterLifecycleDelegate<P extends BasePresenter> {

    private static final String PRESENTER_KEY = "presenter";
    private static final String PRESENTER_ID_KEY = "presenter_id";

    @Nullable private PresenterFactory<P> presenterFactory;
    @Nullable private P presenter;
    @Nullable private Bundle bundle;

    private boolean presenterHasView;

    public PresenterLifecycleDelegate(@Nullable PresenterFactory<P> presenterFactory) {
        this.presenterFactory = presenterFactory;
    }

    @Nullable
    public PresenterFactory<P> getPresenterFactory() {
        return presenterFactory;
    }

    public void setPresenterFactory(@Nullable PresenterFactory<P> presenterFactory) {
        if (presenter != null)
            throw new IllegalArgumentException("setPresenterFactory() should be called before onResume()");
        this.presenterFactory = presenterFactory;
    }

    public P getPresenter() {
        if (presenterFactory != null) {
            if (presenter == null && bundle != null)
                presenter = PresenterStorage.INSTANCE.getPresenter(bundle.getString(PRESENTER_ID_KEY));

            if (presenter == null) {
                presenter = presenterFactory.createPresenter();
                PresenterStorage.INSTANCE.add(presenter);
                presenter.create(bundle == null ? null : bundle.getBundle(PRESENTER_KEY));
            }
            bundle = null;
        }
        return presenter;
    }

      public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        getPresenter();
        if (presenter != null) {
            Bundle presenterBundle = new Bundle();
            presenter.save(presenterBundle);
            bundle.putBundle(PRESENTER_KEY, presenterBundle);
            bundle.putString(PRESENTER_ID_KEY, PresenterStorage.INSTANCE.getId(presenter));
        }
        return bundle;
    }

     public void onRestoreInstanceState(Bundle presenterState) {
        if (presenter != null)
            throw new IllegalArgumentException("onRestoreInstanceState() should be called before onResume()");
        this.bundle = ParcelFn.unmarshall(ParcelFn.marshall(presenterState));
    }

    public void onResume(Object view) {
        getPresenter();
        if (presenter != null && !presenterHasView) {
            //noinspection unchecked
            presenter.takeView(view);
            presenterHasView = true;
        }
    }

    public void onDropView() {
        if (presenter != null && presenterHasView) {
            presenter.dropView();
            presenterHasView = false;
        }
    }

    public void onDestroy(boolean isFinal) {
        if (presenter != null && isFinal) {
            presenter.destroy();
            presenter = null;
        }
    }
}
