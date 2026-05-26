package com.mapyourmove.app.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mapyourmove.app.presentation.theme.PrimaryBlue
import com.mapyourmove.app.presentation.theme.SecondaryTeal
import com.mapyourmove.app.presentation.theme.AccentOrange

@Composable
fun HomeScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) } 

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Map, contentDescription = "Discover") },
                    label = { Text("Discover") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Hotel, contentDescription = "Bookings") },
                    label = { Text("Bookings") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Create new itinerary */ },
                containerColor = PrimaryBlue,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "New Itinerary")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            when (selectedTab) {
                0 -> HomeTabContent()
                1 -> DiscoverTabContent()
                2 -> BookingsTabContent()
                3 -> ProfileTabContent()
            }
        }
    }
}

@Composable
fun HomeTabContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Welcome Back!",
            style = MaterialTheme.typography.displaySmall,
            color = PrimaryBlue,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Your Recent Itineraries",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Sample Itinerary Cards
        repeat(3) {
            ItineraryCard()
        }
    }
}

@Composable
fun DiscoverTabContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Discover",
            style = MaterialTheme.typography.displaySmall,
            color = PrimaryBlue,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Trending Destinations",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Sample Destination Cards
        repeat(4) {
            DestinationCard()
        }
    }
}

@Composable
fun BookingsTabContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "My Bookings",
            style = MaterialTheme.typography.displaySmall,
            color = PrimaryBlue,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Sample Booking Cards
        repeat(3) {
            BookingCard()
        }
    }
}

@Composable
fun ProfileTabContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.displaySmall,
            color = PrimaryBlue,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Profile content
        Surface(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            color = SecondaryTeal.copy(alpha = 0.1f)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("User Profile", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}

@Composable
fun ItineraryCard() {
    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        color = PrimaryBlue.copy(alpha = 0.1f)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Paris, France", style = MaterialTheme.typography.headlineSmall)
            Text("5 Days", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun DestinationCard() {
    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        color = SecondaryTeal.copy(alpha = 0.1f)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Tokyo, Japan", style = MaterialTheme.typography.headlineSmall)
            Text("⭐ 4.8/5", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun BookingCard() {
    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        color = AccentOrange.copy(alpha = 0.1f)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Flight Ticket", style = MaterialTheme.typography.headlineSmall)
            Text("Departure: May 30, 2026", style = MaterialTheme.typography.bodySmall)
        }
    }
}
