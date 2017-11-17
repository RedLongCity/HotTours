package com.smitsworks.redlo.hottours.lists.children;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;

/**
 * Created by redlongcity on 23.10.2017.
 * This interface declares the relationships between the view and the presenter
 */

public interface ChildrenContract {

    interface View extends BaseView<Presenter>{

        void setLoadingIndicator(boolean active);

        void showChildren();

        void chooseChildrenAmountUI(Integer amount);

        boolean isActive();

    }

    interface Presenter extends BasePresenter{

        void loadChildren();

        void chooseChildrenAmount(@NonNull Integer amount);

    }
}
