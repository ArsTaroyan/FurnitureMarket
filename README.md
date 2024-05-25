# FurnitureMarket

![NPM Version](https://img.shields.io/npm/v/kotlin?style=flat-square&label=kotlin)
![GitHub followers](https://img.shields.io/github/followers/ArsTaroyan?style=flat-square&logo=github)
![Static Badge](https://img.shields.io/badge/android--stiudio-gray?style=flat-square&logo=android&labelColor=grey&color=dark--green) 
[![API](https://img.shields.io/badge/Min%20SDK-23%20[Android%207.0]-blue.svg?style=flat-square)](https://github.com/AndroidSDKSources/android-sdk-sources-list) 
[![Target SDK](https://img.shields.io/badge/Target%20SDK-33%20[Android%2014]-blue.svg?style=flat-square)](https://developer.android.com/about/versions/13)

FurnitureMarket is an Android application designed for a furniture store, allowing users to easily browse and order furniture. The app is developed entirely in Kotlin using Android Studio, following the principles of Clean architecture, resulting in clean and maintainable code. 

## Key Features

- **Furniture Ordering**: Users can easily browse through available furniture and place orders.
- **Integration with Room Database**: Utilizes Room Persistence Library for efficient local data storage and management.
- **Navigation Component**: Utilizes Android Navigation Component for navigation within the app.
- **Asynchronous Programming with Coroutines**: Employes Coroutines for managing asynchronous tasks and database operations.
- **Dependency Injection with Koin**: Implements dependency injection using Koin for better code maintainability.
- **JSON Parsing with Gson**: Gson library is used for JSON parsing to handle network responses.
- **Image Loading with Glide**: Glide library is used for efficient image loading and caching.
- **Data Storage with Preferences DataStore**: Uses Preferences DataStore for storing user preferences.

## Technologies and Libraries Used

- **Room Database**: 
  - `implementation "androidx.room:room-ktx:2.5.1"`
  - `kapt "androidx.room:room-compiler:2.5.1"`
- **Navigation**: 
  - `implementation "androidx.navigation:navigation-fragment-ktx:2.5.3"`
  - `implementation "androidx.navigation:navigation-ui-ktx:2.5.3"`
- **Coroutine**: 
  - `implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1'`
- **Koin**: 
  - `implementation 'io.insert-koin:koin-android:3.1.5'`
- **Gson**: 
  - `implementation 'com.google.code.gson:gson:2.9.0'`
- **Glide**: 
  - `implementation 'com.github.bumptech.glide:glide:4.12.0'`
- **Preferences DataStore**: 
  - `implementation "androidx.datastore:datastore-preferences:1.0.0-alpha04"`

  ## Installation

1. **Clone the repository**
    ```bash
    git clone https://github.com/ArsTaroyan/FurnitureMarket.git
    cd Daily
    ```

2. **Open the project**
    Open the project in [Android Studio](https://developer.android.com/studio).

3. **Sync Gradle**
    The project uses Gradle to manage dependencies. Sync Gradle to download all required dependencies.

4. **Run the application**
    Connect your Android device or use an emulator to run the application.

## Project Structure

The project follows Clean Architecture principles, ensuring modularity and testability of the code. The main modules include:

- **data**: Contains data sources (Room database, SharedPreferences).
- **domain**: Contains business logic (models).
- **presentation**: Contains the user interface (Activity, Fragment, ViewModel).

## Project Description

FurnitureMarket was developed for a furniture store to enable users to easily browse and order furniture. It provides a simple and intuitive interface for users to place their orders hassle-free. The app utilizes Room database for efficient data storage, Navigation Component for seamless navigation, Coroutines for asynchronous operations, and Koin for dependency injection. Gson is used for JSON parsing, and Glide is used for efficient image loading. Preferences DataStore is used for storing user preferences.
