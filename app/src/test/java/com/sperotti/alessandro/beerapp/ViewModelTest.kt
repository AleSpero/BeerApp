package com.sperotti.alessandro.beerapp

import com.sperotti.alessandro.beerapp.viewmodel.BeerViewModel
import com.sperotti.alessandro.beerapp.viewmodel.BeersRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTest {

    val fakeApi = FakePunkEndpoint()
    val errorEndpoint = ErrorEndpoint()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun beerListCallSuccessful() = testDispatcher.runBlockingTest {
        val fakeRepo = BeersRepo(fakeApi)
        val viewModel = BeerViewModel(fakeRepo)

        val detailResult = viewModel.getBeers(1, 20, null)
            .last()


        assert(detailResult.size == 7)
        assert(detailResult.all { it.name.isNotEmpty() })
    }

    @Test
    fun beerListCallFailed() = testDispatcher.runBlockingTest {
        val fakeRepo = BeersRepo(errorEndpoint)
        val viewModel = BeerViewModel(fakeRepo)

        val detailResult = viewModel.getBeers(1, 20, null)
            .catch { emit(emptyList()) }
            .last()

        assert(detailResult.isEmpty())
    }

}