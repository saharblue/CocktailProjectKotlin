package il.co.syntax.finalkotlinproject.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <A> performFetchingFromRemote(remoteDbFetch: suspend () ->Resource<A>) : LiveData<Resource<A>> =

    liveData(Dispatchers.IO) {

        emit(Resource.loading())

        val fetchResource = remoteDbFetch()

        emit(fetchResource)

    }

fun <T> performFetchingFromLocal(localDbFetch: () -> LiveData<T>): LiveData<Resource<T>> =

    liveData(Dispatchers.IO) {

        emit(Resource.loading())

        val source = localDbFetch().map { Resource.success(it) }
        emitSource(source)
    }




