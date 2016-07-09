package me.bitfrom.bashim.ui.base;

public interface Presenter <V extends MvpView> {

    void attach(V mvpView);

    void detach();
}