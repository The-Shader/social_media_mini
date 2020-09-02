# Mini Social Media

## Project Notes

The following features and techniques have been implemented/used:
* Persisting data with `Room` for offline mode
* Network caching with `OkHttp`
* Repository pattern for updating the database based on the data from the network
* MVI architecture is used in the feature modules for separating responsibilities, utilising [Orbit2](https://github.com/babylonhealth/orbit-mvi)
* Unittests with `JUnit5`, `Mockito` and `Mockito kotlin` for the following classes: [DetailedPostViewModel](https://github.com/The-Shader/social_media_mini/blob/develop/detail/src/test/java/com/fireblade/detail/DetailedPostViewModelTest.kt) , [FeedViewModel](https://github.com/The-Shader/social_media_mini/blob/develop/feed/src/test/java/com/fireblade/feed/FeedViewModelTest.kt))
* CoordinatorLayout for full screen comment scrolling in the Detailed screen
* `Dagger2` for dependency injection
* `RxJava2` streams for async data loading both from the database and from the network
* Using `AndroidX` libraries

Missing/can be improved:
* Migration to coroutines which Orbit2 uses internally anyway
* Better state handling with loading indicators
* Start using styles and themes
* Introduce `Dagger Hilt` for `AssistedInject` and `ViewModelInject` in order to get rid of the `ViewModelFactory` and remove the dependencies from the `Activities/Fragments`
* Much more unittests and UI tests
* Bitrise integration for continuous integration and automatic deploy into the Play store
* Firebase integration for crash and user analytics
* Visitor pattern for the RecyclerViews -> moving the PostViewHolder and PostAdapter to the core module similarly to the comments once it doesn't depend on the details module
* Navigation component for handling transitions between screens/activities
* Paging for Posts and Comments for "infinite scrolling"
* Better animations for the CoordinatorLayout
* Persisting the Avatar Color for each user
* Option for customizing the Avatar image
* Expandable comment section in the Feed View under each post

## Screenshots

Feed View | Detailed View
--------- | -------------
![Feed page image](https://github.com/The-Shader/social_media_mini/blob/develop/feed_screen.png) | ![Detailed page image](https://github.com/The-Shader/social_media_mini/blob/develop/detailed_screen.png)
