package me.bitfrom.bashim.injection.components;

import dagger.Subcomponent;
import me.bitfrom.bashim.injection.PerActivity;
import me.bitfrom.bashim.injection.modules.ActivityModule;
import me.bitfrom.bashim.ui.MainActivity;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}