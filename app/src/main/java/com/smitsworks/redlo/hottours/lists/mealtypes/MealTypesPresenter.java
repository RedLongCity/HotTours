package com.smitsworks.redlo.hottours.lists.mealtypes;

import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.models.Meal_Type;
import com.smitsworks.redlo.hottours.data.source.FilterDataSource;
import com.smitsworks.redlo.hottours.data.source.FiltersRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 21.10.2017.
 * Listens to user actions from the UI, retrieves the data and upgrade the UI
 */

public class MealTypesPresenter implements MealTypesContract.Presenter {

    private final FiltersRepository filtersRepository;

    private final MealTypesContract.View mealTypesView;

    private boolean firstLoad = true;

    public MealTypesPresenter(@NonNull FiltersRepository filtersRepository,
                              @NonNull MealTypesContract.View mealTypesView) {
        this.filtersRepository = checkNotNull(filtersRepository);
        this.mealTypesView = checkNotNull(mealTypesView);

        mealTypesView.setPresenter(this);
    }

    @Override
    public void start() {
        loadMealTypes(false);
    }

    @Override
    public void loadMealTypes(boolean forceUpdate) {
        loadMealTypes(forceUpdate || firstLoad,true);
        firstLoad = false;
    }

    private void loadMealTypes(boolean forceUpdate, final boolean showLoadingUI){
        if(showLoadingUI){
            mealTypesView.setLoadingIndicator(true);
        }

        if(forceUpdate){
            filtersRepository.refreshFilters();
        }

        filtersRepository.getMealTypes(new FilterDataSource.LoadMealTypesCallback(){
            @Override
            public void onMealTypesLoaded(List<Meal_Type> types) {
                if(!mealTypesView.isActive()){
                    return;
                }
                if(showLoadingUI){
                    mealTypesView.setLoadingIndicator(false);
                }
                processMealTypes(types);
                mealTypesView.showSuccessfullyLoadedMessage();
            }

            @Override
            public void onDataNotAvailable() {
                if(!mealTypesView.isActive()){
                    return;
                }
                mealTypesView.showLoadingMealTypesError();
            }

            @Override
            public void onNotAvailableConnection() {
                if(!mealTypesView.isActive()){
                    return;
                }
                mealTypesView.showNotAwailableConnection();
            }
        });
    }

    private void processMealTypes(List<Meal_Type> types){
        if(types.isEmpty()){
            processEmptyMealTypes();
        }else{
            mealTypesView.showMealTypes(types);
        }
    }

    private void processEmptyMealTypes(){
        mealTypesView.showNoMealTypes();
    }

    @Override
    public void chooseMealType(@NonNull Meal_Type type) {
        mealTypesView.chooseMealTypeUI(type);
    }
}
