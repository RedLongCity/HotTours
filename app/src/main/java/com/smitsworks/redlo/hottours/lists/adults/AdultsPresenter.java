package com.smitsworks.redlo.hottours.lists.adults;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 23.10.2017.
 * listens to user actions from UI, retrieves the data and upgrade the UI
 */

public class AdultsPresenter implements AdultsContract.Presenter {

    private AdultsContract.View adultsView;

    public AdultsPresenter(@NonNull AdultsContract.View adultsView) {
        this.adultsView = checkNotNull(adultsView);

        adultsView.setPresenter(this);
    }


    @Override
    public void chooseAdultsAmount(@NonNull Integer amount) {
        adultsView.chooseAdultsAmountUI(amount);
    }

    @Override
    public void loadAdults() {
        adultsView.showAdults();
        adultsView.setLoadingIndicator(false);
    }

    @Override
    public void start() {

    }
}
