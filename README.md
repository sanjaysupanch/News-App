## A modern **Android News App** built using **Jetpack Compose, MVVM, and Hilt**, allowing users to fetch, read, save and share news articles.

---

## Features
* Fetches news articles from API  
* Displays articles in a **scrollable list**  
* Opens full article in **WebView**  
* Allows **saving articles to Room Database**  
* Uses **MVVM architecture with Repository Pattern**  
* **Dependency Injection with Hilt**  
* **Jetpack Compose for UI**  
* **Bottom Navigation for navigation**  

---

## Tech Stack
| Component            | Library Used |
|----------------------|-------------|
| **UI Framework**    | Jetpack Compose  |
| **Architecture**    | MVVM  |
| **Dependency Injection** | Hilt  |
| **Networking**      | Retrofit  |
| **Local Database**  | Room  |
| **Navigation**      | Jetpack Navigation  |
| **Image Loading**   | Coil  |

---

## Folder Structure

![Screenshot 2025-03-02 at 6 31 41 AM](https://github.com/user-attachments/assets/6a89e5ef-1e22-4da5-a192-5cf2e58da031)


* Follows Clean Architecture Principles.  
* Separates concerns (UI, Data, Business Logic).  
* Uses Repository pattern for better testability. 

---

## API Usage
The app fetches news articles using **NewsAPI**.

- **Base URL:** `https://newsapi.org/v2/top-headlines`
- **Authentication:** API Key stored securely in `gradle.properties`
- **Example API Call:**
  ```kotlin
  interface NewsApiService {
      @GET("v2/top-headlines")
      suspend fun getNews(
          @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
      ): Response<NewsResponse>
  }

* Uses BuildConfig.NEWS_API_KEY instead of hardcoding.
*  Ensures API key security using gradle.properties.

## Feature How It Works?
* Fetch News	Fetches news using Retrofit from API
* Display News	Shows articles in a LazyColumn using Jetpack Compose
* Save Article	Saves articles to Room Database
* Read Full Article	Opens full article in WebView
* Navigation Uses BottomNavigationBar for switching between screens
* User can share content.
* Dark mode enabled.

## App Demo
* Link: https://drive.google.com/file/d/19vqCF-YW2tz67iwrc8-xeaLDZblfnu2t/view
