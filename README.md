# NewsApp-
NewsApp - simple Android news application built with Clean Architecture and MVVM. The project demonstrates integration of Retrofit, Room, Kotlin Coroutines, Flow, Hilt (DI), Jetpack Compose (Material3), and Navigation.

## Features

- Fetch top news from public API  
- Search news in feed  
- Save / remove articles from favorites  
- Reactive favorites synchronization (Flow + Room)  
- Pull-to-refresh (Material3)  
- Detailed article screen  

## Architecture

The project follows MVVM and Clean Architecture principles.

Layers:

 data: 
  - Retrofit API  
  - DTO + Mappers  
  - Room (DAO, Entity, Database)  
  - Repository implementation  

 domain:  
  - Models  
  - Repository interfaces  
  - UseCases  

 presentation:  
  - Compose screens  
  - ViewModels  
  - UI state (StateFlow)  
  - Navigation  

Architecture highlights:

- Room as single source of truth  
- Reactive state synchronization using Kotlin Flow (combine)  
- Unidirectional data flow  
- Separation of concerns between layers  

## Tech Stack

- Kotlin  
- Jetpack Compose (Material3)  
- MVVM  
- Clean Architecture  
- Retrofit (network)  
- Room (local database)  
- Hilt (dependency injection)  
- Coroutines + Flow  
- Navigation Compose  
- Coil (image loading)  

## API

The project uses TheNewsAPI:

https://www.thenewsapi.com/

## Getting Started

1. Clone the repository  
2. Add your API key in `Constants.kt`  
3. Open project in Android Studio  
4. Run the app on emulator or device  

## What I practiced in this project

- Structuring project with Clean Architecture (data / domain / presentation)  
- Dependency Injection with Hilt  
- State handling with StateFlow  
- Reactive synchronization using Flow and combine()  
- Integrating Retrofit with Room  
- Implementing pull-to-refresh in Material3  
- Navigation in Jetpack Compose  

## License

This project is for educational purposes.
