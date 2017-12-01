
package br.com.cielo.app;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import br.com.cielo.app.injection.component.DaggerApplicationComponent;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

;

public class CieloApp extends Application {

    br.com.cielo.app.injection.component.ApplicationComponent mApplicationComponent;

    public static CieloApp get(Context context) {
        return (CieloApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Fabric.with(this, new Crashlytics());
        }
    }

    public br.com.cielo.app.injection.component.ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new br.com.cielo.app.injection.module.ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(br.com.cielo.app.injection.component.ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
