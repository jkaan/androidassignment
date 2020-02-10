# App

For the second part of the assignment, we would like you to implement a simple app using the Foursquare Places API.
We've already added some code / functionality, but some parts may still be missing in order for it to work.

## Setup
Add your Foursquare client ID and secret to `local.gradle`. See `local.gradle.example` for details.
Tip: You can verify your credentials with `src/test/java/com/adyen/android/assignment/PlacesUnitTest.kt`


Ideas:
- Show a list of venues around the user's current location.
- Implement filter / search functionality.

## Notes of my assignment

Since I did not want to spend too much time on the assignment there were a few things which I didn't implement.

1. Loader when you search for recommendations for a particular location.
2. Pagination.
3. Different entities for what is being shown on the view; I now implemented Parcelize and Parcelable.
on the classes that are used to do mapping of the API and since those are used for a different purpose they should not be used for both.
4. Use the SavedStateViewModelFactory so that the fragment doesn't have to bother with the saved instance state.
5. More tests for the repository and also for the query builder.
6. UI tests

Keeping this all into account though I think this gives a very good image of how I develop code on a daily basis :).
