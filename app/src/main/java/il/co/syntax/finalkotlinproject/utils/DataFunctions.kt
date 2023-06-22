package il.co.syntax.finalkotlinproject.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T,A> performFetchingAndSaving(localDbFetch: () -> LiveData<T>,
                                    remoteDbFetch: suspend () ->Resource<A>,
                                    localDbSave: suspend (A) -> Unit) : LiveData<Resource<T>> =

    liveData(Dispatchers.IO) {

        emit(Resource.loading())

        val source = localDbFetch().map { Resource.success(it) }
        emitSource(source)

        val fetchResource = remoteDbFetch()

        if(fetchResource.status is Success)
            localDbSave(fetchResource.status.data!!)

        else if(fetchResource.status is Error){
            emit(Resource.error(fetchResource.status.message))
            emitSource(source)
        }
    }

fun <A> performFetching(remoteDbFetch: suspend () ->Resource<A>) : LiveData<Resource<A>> =

    liveData(Dispatchers.IO) {

        emit(Resource.loading())

        val fetchResource = remoteDbFetch()

        when(fetchResource.status) {
            is Success -> {
                emit(Resource.success(fetchResource.status.data!!))
            }
            is Error -> {
                emit(Resource.error(fetchResource.status.message))
            }
        }
    }
