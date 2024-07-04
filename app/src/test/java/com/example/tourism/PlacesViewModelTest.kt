package com.example.tourism

import com.example.tourism.models.Place
import com.example.tourism.network.ApiPlaces
import com.example.tourism.viewmodel.PlacesViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

val mockPlace = Place(
    id = "3EW3tQJPNFKmThJeNfOr",
    title = "Museo Casa del Retablo",
    description = "place 1",
    rating = 4.5,
    longitude = -14.3,
    category = "places",
    latitude = -14.54,
    images = listOf("images"),
    thumbnail = "thumbnail",
    location = "location"
)

@ExperimentalCoroutinesApi
class PlacesViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: PlacesViewModel
    private lateinit var mockRepository: ApiPlaces

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        kotlinx.coroutines.Dispatchers.setMain(testDispatcher)

        mockRepository = mockk()
        viewModel = PlacesViewModel()
        viewModel.apiPlaces = mockRepository
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()

        kotlinx.coroutines.Dispatchers.resetMain()
    }

    // Obtención de todos los lugares exitosa
    @Test
    fun `testFetchPlaces_Successful`() = testDispatcher.runBlockingTest {

        coEvery { mockRepository.getPlaces(any()) } returns listOf(mockPlace)

        viewModel.apiPlaces = mockRepository

        viewModel.fetchPlaces()

        advanceUntilIdle()
    }


    // Obtencion de todos los lugares
    @Test
    fun `testFetchPlaces`() = testDispatcher.runBlockingTest {

        val mockRepository = mockk<ApiPlaces>()

        coEvery { mockRepository.getPlaces(any()) } returns emptyList()

        val viewModel = PlacesViewModel()

        viewModel.apiPlaces = mockRepository

        viewModel.fetchPlaces()

        advanceUntilIdle()
    }

    // Error al obtener lugar por id
    @Test
    fun `testFetchPlaceById_Error`() = testDispatcher.runBlockingTest {
        val mockRepository = mockk<ApiPlaces>()

        val viewModel = PlacesViewModel()

        coEvery { mockRepository.getPlace(any()) } throws Exception("API Error")
        viewModel.apiPlaces = mockRepository

        viewModel.fetchPlaceById("1")

        val result = viewModel.selectedPlace.first()

        assert(result == null)

        advanceUntilIdle()
    }

    // Busqueda de lugares por categoria exitosa
    @Test
    fun `testFetchPlaceByCategory_Successful`() = testDispatcher.runBlockingTest {

        val mockRepository = mockk<ApiPlaces>()

        val viewModel = PlacesViewModel()

        coEvery { mockRepository.getPlaceByCategory(any()) } returns listOf(mockPlace)

        viewModel.apiPlaces = mockRepository

        viewModel.fetchPlaceByCategory("places")

        advanceUntilIdle()
    }

    // Busqueda de lugares sugeridas por categoria exitosa
    @Test
    fun `testFetchSuggestionPlaces_Successful`() = testDispatcher.runBlockingTest {
        val mockRepository = mockk<ApiPlaces>()

        val viewModel = PlacesViewModel()

        coEvery { mockRepository.getPlaceByCategory(any()) } returns listOf(mockPlace)

        viewModel.fetchSuggestionPlaces("places")

        advanceUntilIdle()
    }

    // Busqueda de lugares sugeridas por categoria erronea
    @Test
    fun testFetchSuggestionPlaces_Error() = testDispatcher.runBlockingTest {

        coEvery { mockRepository.getPlaceByCategory(any()) } throws Exception("API Error")

        viewModel.apiPlaces = mockRepository

         viewModel.fetchSuggestionPlaces("places")

        advanceUntilIdle()
    }
}