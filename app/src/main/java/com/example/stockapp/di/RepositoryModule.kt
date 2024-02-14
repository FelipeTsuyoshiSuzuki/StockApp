package com.example.stockapp.di

import com.example.stockapp.data.csv.CSVParser
import com.example.stockapp.data.csv.CompanyListParser
import com.example.stockapp.data.csv.IntradayInfoParser
import com.example.stockapp.data.repository.StockRepositoryImplementation
import com.example.stockapp.domain.model.CompanyListing
import com.example.stockapp.domain.model.IntradayInfo
import com.example.stockapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListParser(
        companyListParser: CompanyListParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindInfradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImplementation: StockRepositoryImplementation
    ): StockRepository
}