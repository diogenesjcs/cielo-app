package br.com.cielo.app.injection.component;

import dagger.Subcomponent;
import br.com.cielo.app.injection.PerActivity;
import br.com.cielo.app.injection.module.ActivityModule;
import br.com.cielo.app.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
