package com.smitsworks.redlo.hottours.lists.children;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 23.10.2017.
 * listens to user actions, updates the UI
 */

public class ChildrenPresenter implements ChildrenContract.Presenter {

    private ChildrenContract.View childrenView;

    public ChildrenPresenter(@NonNull ChildrenContract.View childrenView) {
        this.childrenView = checkNotNull(childrenView);

        childrenView.setPresenter(this);
    }

    @Override
    public void chooseChildrenAmount(@NonNull Integer amount) {
        childrenView.chooseChildrenAmountUI(amount);
    }

    @Override
    public void start() {

    }
}
