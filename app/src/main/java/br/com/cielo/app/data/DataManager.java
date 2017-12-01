package br.com.cielo.app.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

@Singleton
public class DataManager {

    private final br.com.cielo.app.data.remote.RibotsService mRibotsService;
    private final br.com.cielo.app.data.local.DatabaseHelper mDatabaseHelper;
    private final br.com.cielo.app.data.local.PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(br.com.cielo.app.data.remote.RibotsService ribotsService, br.com.cielo.app.data.local.PreferencesHelper preferencesHelper,
                       br.com.cielo.app.data.local.DatabaseHelper databaseHelper) {
        mRibotsService = ribotsService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
    }

    public br.com.cielo.app.data.local.PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<br.com.cielo.app.data.model.Ribot> syncRibots() {
        return mRibotsService.getRibots()
                .concatMap(new Function<List<br.com.cielo.app.data.model.Ribot>, ObservableSource<? extends br.com.cielo.app.data.model.Ribot>>() {
                    @Override
                    public ObservableSource<? extends br.com.cielo.app.data.model.Ribot> apply(@NonNull List<br.com.cielo.app.data.model.Ribot> ribots)
                            throws Exception {
                        return mDatabaseHelper.setRibots(ribots);
                    }
                });
    }

    public Observable<List<br.com.cielo.app.data.model.Ribot>> getRibots() {
        return mDatabaseHelper.getRibots().distinct();
    }

}
