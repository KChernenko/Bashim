package me.bitfrom.bashim.ui.base;

public class BasePresenter <T extends MvpView> implements Presenter<T> {

    private T mvpView;

    @Override
    public void attach(T mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detach() {
        mvpView = null;
    }

    public boolean isViewAttached() {return mvpView != null;}

    public T getMvpView() {return mvpView;}
}