# Order Application Module

## Architecture

- App.kt : Application
- OrderAppNavigation.kt : Navigate to route
- routes : 1 route - 1 screen - 1 viewmodel
  - ~~Route.kt : Handling navigation event and ViewModel handling
  - ~~ViewModel.kt : Handling UI logic
  - ~~Screen.kt : Show non logical and state handling UiState
  - ~~UiState : State data of show UI
  - views
    - ~~View.kt : Show non logic parts of screen view. ex. Screen empty state data. 
- components : Common some screens view parts