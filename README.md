# Mini Social Media

## Project Notes

The following features and techniques have been implemented/used:
* Persisting data with `Room` for offline mode
* Network caching with `OkHttp`
* Repository pattern for updating the database based on the data from the network
* MVP architecture is used in the feature modules for separating responsibilities
* Unittests with `JUnit4`, `Mockito` and `mockk` for the following classes: [CommentItem](https://github.com/The-Shader/social_media_mini/blob/master/core/src/test/java/com/fireblade/core/CommentItemUnitTest.kt) , [PostItem](https://github.com/The-Shader/social_media_mini/blob/master/core/src/test/java/com/fireblade/core/PostItemUnitTest.kt), [FeedPresenter](https://github.com/The-Shader/social_media_mini/blob/master/feed/src/test/java/com/fireblade/feed/FeedPresenterUnitTest.kt)
* CoordinatorLayout for full screen comment scrolling in the Detailed screen
* `Dagger2` for dependency injection
* `RxJava2` streams for async data loading both from the database and from the network
* Using `AndroidX` libraries
* Scheduler Injection -> more suitable for testing Rx, avoiding overriding the Schedulers of `RxAndroidPlugins` and `RxJavaPlugins`, also making room for custom scheduler implementation

Missing/can be improved:
* Much more unittests and UI tests
* Bitrise integration for continuous integration and automatic deploy into the Play store
* Firebase integration for crash and user analytics
* Visitor pattern for the RecyclerViews -> moving the PostViewHolder and PostAdapter to the core module similarly to the comments once it doesn't depend on the details module
* More modern architecture for better responsibility separation and scalability: MVVM, MVI
* Navigation component for handling transitions between screens/activities
* Paging for Posts and Comments for "infinite scrolling"
* Better animations for the CoordinatorLayout
* Persisting the Avatar Color for each user
* Option for customizing the Avatar image
* Expandable comment section in the Feed View under each post

## Screenshots

Feed View | Detailed View
--------- | -------------
![Feed page image](https://github.com/The-Shader/social_media_mini/blob/master/feed_screen.png) | ![Detailed page image](https://github.com/The-Shader/social_media_mini/blob/master/detailed_screen.png)
