package com.example.mirunime.paging

import retrofit2.Response

class PaginationFactory<Key, Item>(
    private val initialPage: Key,

    private  inline val onLoadUpdated:(Boolean)->Unit,

    private  inline val onRequest:suspend (nextPage: Key)->Response<Item>,

    private  inline val getNextKey:suspend (Item)->Key,

    private inline val onError: suspend (Throwable?) -> Unit,

    private inline val onSuccess: suspend (item: Item, newPage:Key) -> Unit
):Pagination<Key,Item> {

    private var currentKey = initialPage
    private var isMakingRequest = false

    override suspend fun loadNextPage() {
        if (isMakingRequest){
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        try {
            val response = onRequest(currentKey)
            if (response.isSuccessful){
                isMakingRequest = false
                val animeList = response.body()!!
                currentKey = getNextKey(animeList)!!
                onSuccess(animeList,currentKey)
                onLoadUpdated(false)
            }
        }catch (e:Exception){
            onError(e)
            onLoadUpdated(false)
        }
    }

    override fun reset() {
        currentKey = initialPage
    }

}

