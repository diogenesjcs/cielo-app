package br.com.cielo.app.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import br.com.cielo.app.data.DataManager;
import br.com.cielo.app.data.SyncService;
import br.com.cielo.app.data.local.DatabaseHelper;
import br.com.cielo.app.data.local.PreferencesHelper;
import br.com.cielo.app.data.remote.RibotsService;
import br.com.cielo.app.injection.ApplicationContext;
import br.com.cielo.app.injection.module.ApplicationModule;
import br.com.cielo.app.util.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext
    Context context();

    Application application();

    RibotsService ribotsService();

    PreferencesHelper preferencesHelper();

    DatabaseHelper databaseHelper();

    DataManager dataManager();

    RxEventBus eventBus();

}
