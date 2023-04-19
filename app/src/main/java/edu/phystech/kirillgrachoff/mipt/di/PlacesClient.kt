package edu.phystech.kirillgrachoff.mipt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.phystech.kirillgrachoff.mipt.ui.viewmodels.places.Client

@Module
@InstallIn(SingletonComponent::class)
class PlacesClient {
    @Provides
    fun placesClient(): Client = Client()
}