package com.smitsworks.redlo.hottours.lists.mealtypes;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.BasePresenter;
import com.smitsworks.redlo.hottours.BaseView;
import com.smitsworks.redlo.hottours.data.models.Meal_Type;

import java.util.List;

/**
 * Created by redlongcity on 21.10.2017.
 * This interface declares the relationships between the view and the presenter
 */

public interface MealTypesContract {

    interface View extends BaseView<Presenter>{

        void setLoadingIndicator(boolean active);

        void showLoadingMealTypesError();

        void showMealTypes(List<Meal_Type> types);

        void showNoMealTypes();

        void chooseMealTypeUI(Meal_Type type);

        boolean isActive();

    }

    interface Presenter extends BasePresenter{

        void loadMealTypes(boolean forceUpdate);

        void chooseMealType(@NonNull Meal_Type type);
    }

}
