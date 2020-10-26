package com.choicely.click.garden.db;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Singleton Realm usage class
 */
public class RealmHelper {

    private static final String TAG = "RealmHelper";

    private static final String REALM_NAME = "learn2code.realm";
    private static final int REALM_VERSION = RealmHistory.VERSION_1;

    private Realm realm;

    private static class RealmHistory {

        static final int VERSION_1 = 1;

    }

    private static RealmHelper instance;

    private RealmHelper() {
    }

    public static RealmHelper getInstance() {
        if (instance == null) {
            throw new IllegalStateException(TAG + " is not initialized!");
        }
        return instance;
    }

    public static void init(Context context) {
        if (instance != null) {
            throw new IllegalStateException(TAG + " is already initialized!");
        }

        instance = new RealmHelper();

        // Initialize Realm (just once per application)
        Realm.init(context);

        // The RealmConfiguration is created using the builder pattern.
        // The Realm file will be located in Context.getFilesDir() with name "myrealm.realm"
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(REALM_NAME)
                .schemaVersion(REALM_VERSION)
                .allowWritesOnUiThread(true)
                .deleteRealmIfMigrationNeeded()
                .build();
        // Use the config
        instance.realm = Realm.getInstance(config);
    }

    public Realm getRealm() {
        return realm;
    }
}
