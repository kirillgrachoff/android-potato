package edu.phystech.kirillgrachoff.mipt.ui.screens.places

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import edu.phystech.kirillgrachoff.mipt.R
import edu.phystech.kirillgrachoff.mipt.ui.viewmodels.places.ListUiState
import edu.phystech.kirillgrachoff.mipt.ui.viewmodels.places.Place
import edu.phystech.kirillgrachoff.mipt.ui.viewmodels.places.PlacesViewModel

@Composable
fun PlacesScreen(
    placesViewModel: PlacesViewModel = viewModel()
) {
    val placesViewState by placesViewModel.uiState.collectAsState()
    Surface(Modifier.fillMaxSize()) {
        PlacesView(placesViewState)
    }
}

@Preview
@Composable
fun PlacesView(placesViewState: ListUiState = ListUiState()) {
    Column {
        LazyRowOf("Nearest", placesViewState.nearest)
        LazyRowOf("Popular", placesViewState.popular)
        if (placesViewState.commercial != null) {
            with (placesViewState.commercial) {
                AsyncImage(
                    model = this.picture,
                    contentDescription = "advertisement"
                )
            }
        }
    }
}

@Composable
fun LazyRowOf(name: String, list: List<Place>) {
    Column {
        Text(name)
        LazyRow {
            items(list) {
                PlaceCard(it)
            }
        }
    }
}

@Composable
fun PlaceCard(card: Place) {
    Card {
        Text(card.name)
        AsyncImage(
            model = card.image,
            contentDescription = card.name,
            error = painterResource(id = R.drawable.broken_image)
        )
    }
}