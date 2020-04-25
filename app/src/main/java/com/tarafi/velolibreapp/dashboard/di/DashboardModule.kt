package com.tarafi.velolibreapp.dashboard.di

import com.tarafi.velolibreapp.common.app.activity.MainActivity
import com.tarafi.velolibreapp.common.data.net.RetrofitApi
import com.tarafi.velolibreapp.common.di.PerFragment
import com.tarafi.velolibreapp.dashboard.data.repository.StationsRepositoryImpl
import com.tarafi.velolibreapp.dashboard.data.retrofitapi.StationsService
import com.tarafi.velolibreapp.dashboard.domain.interactor.GetStationsUseCase
import com.tarafi.velolibreapp.dashboard.domain.interactor.GetStationsUseCaseImpl
import com.tarafi.velolibreapp.dashboard.domain.repository.StationsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DashboardModule(val mainActivity: MainActivity) {

    @Provides
    @PerFragment
    fun provideActivity(): MainActivity {
        return mainActivity
    }

    @Provides
    @PerFragment
    fun provideGetStationsUseCase(getStationsUseCaseImpl: GetStationsUseCaseImpl):GetStationsUseCase{
        return getStationsUseCaseImpl
    }

    @Provides
    @PerFragment
    fun provideStationsRepository(stationsRepositoryImpl: StationsRepositoryImpl):StationsRepository{
        return stationsRepositoryImpl
    }

    @Provides
    @PerFragment
    fun provideStationsService(): StationsService {
        return RetrofitApi.createService(StationsService::class.java)
    }
}