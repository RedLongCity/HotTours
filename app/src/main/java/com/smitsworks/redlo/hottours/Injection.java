package com.smitsworks.redlo.hottours;

import android.content.Context;
import android.support.annotation.NonNull;

import com.smitsworks.redlo.hottours.data.source.datasource.TourAdvancedDataSource;
import com.smitsworks.redlo.hottours.data.source.remote.FeedBackRemoteDataSource;
import com.smitsworks.redlo.hottours.data.source.remote.TourAdvancedRemoteDataSource;
import com.smitsworks.redlo.hottours.data.source.repositories.FeedBackRepository;
import com.smitsworks.redlo.hottours.data.source.repositories.FiltersRepository;
import com.smitsworks.redlo.hottours.data.source.repositories.OrderRepository;
import com.smitsworks.redlo.hottours.data.source.repositories.TourAdvancedRepository;
import com.smitsworks.redlo.hottours.data.source.repositories.ToursRepository;
import com.smitsworks.redlo.hottours.data.source.remote.FilterRemoteDataSource;
import com.smitsworks.redlo.hottours.data.source.remote.OrderRemoteDataPoster;
import com.smitsworks.redlo.hottours.data.source.remote.ToursRemoteDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by redlongcity on 08.10.2017.
 * Enables injection of production implementations for ToursDataSource at compile time
 */

public class Injection {

    public static ToursRepository provideToursRepository(){
        return ToursRepository.getInstance(ToursRemoteDataSource.getInstance());
    }

    public static OrderRepository provideOrderRepository(){
        return OrderRepository.getInstance(OrderRemoteDataPoster.getInstance());
    }

    public static FiltersRepository provideFilterRepository(){
        return FiltersRepository.getInstance(FilterRemoteDataSource.getInstance());
    }

    public static FeedBackRepository provideFeedBackRepository(){
        return FeedBackRepository.getInstance(FeedBackRemoteDataSource.getInstance());
    }

    public static TourAdvancedRepository provideTourAdvancedRepository(){
        return TourAdvancedRepository.getINSTANCE(TourAdvancedRemoteDataSource.getInstance());
    }
}
