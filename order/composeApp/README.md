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

Thinking Points

- Route内で、Screenのエラー状態など表現する際にScreen事変えたいことが稀なため、UiStateをただ渡すだけになる
  - 複数の状態で中身を変えたい場合もScreenでNavBarを取り扱っているため、中身のみを切り替えるのができない
  - 実態は同じことかもしれないが、二重にコードを書く形となる
  - UiStateをsealed classとし、多くの状態を持つような形はそもそも避ける
  - ただ、エラー表示や画面の表示箇所による表現はUI Stateで行いたい
- Screenないでタブが分かれる場合などに、ViewModelのハンドリングをどうするか？
  - Screen > SubScreenみたいな感じにするなど？

まぁ下手に階層化せずに、フラットに書いていってあとから変えるでいいかもしれない