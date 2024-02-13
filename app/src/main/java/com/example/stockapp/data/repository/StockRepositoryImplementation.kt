package com.example.stockapp.data.repository

import com.example.stockapp.data.csv.CSVParser
import com.example.stockapp.data.local.StockDatabase
import com.example.stockapp.data.mapper.toCompanyListing
import com.example.stockapp.data.mapper.toCompanyListingEntity
import com.example.stockapp.data.remote.StockApi
import com.example.stockapp.domain.model.CompanyListing
import com.example.stockapp.domain.repository.StockRepository
import com.example.stockapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImplementation @Inject constructor(
    val api: StockApi,
    val db: StockDatabase,
    val companyListingParser: CSVParser<CompanyListing>
): StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListing = dao.searchCompanyListing(query)
            emit(Resource.Success(
                    data = localListing.map {it.toCompanyListing()}
                ))

            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListing = try {
                val response = api.getListing()
                companyListingParser.parse(response.byteStream())
            } catch(e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Could't load data"))
                null
            } catch(e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Could't load data"))
                null
            }

            remoteListing?.let { listings ->
                dao.clearCompanyListing()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListingEntity() }
                )
                emit(Resource.Success(
                    data = dao.searchCompanyListing("").map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))
            }

        }
    }

}