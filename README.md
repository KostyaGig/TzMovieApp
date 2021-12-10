# TzMovieApp

## Description
Получение фильмов с интернета,отображение их в списке,динамическая подгрузка элементов,с помощью пагинации

## Main Stack : Retrofit,Coroutines,LiveData,Mvvm <br/>
Image loader: Picasso <br/>
SOLID,OOP,Clen architecture,JUnit tests<br/><br/>
Разбиение приложения на data, domain, presentation слои<br/>
Маппинг обЪектов через слои<br/><br/>
В качестве предоставления зависимостей я использовал паттерн "Service locator"<br/>

Необходимые классы покрыты unit - тестами

<img 
src="https://firebasestorage.googleapis.com/v0/b/brushapp-d54ab.appspot.com/o/tests_result.png?alt=media&token=594ef724-37c6-4e0a-8253-01eb25a4e133"/>

#### package core
 - [ExceptionMapperTest](https://github.com/KostyaGig/TzMovieApp/blob/master/app/src/test/java/com/zinoview/tzmovieapp/core/AbstractTest.kt)<br/>

#### package data
- [AbstractTest](https://github.com/KostyaGig/TzMovieApp/blob/master/app/src/test/java/com/zinoview/tzmovieapp/core/AbstractTest.kt)<br/>
- [ExceptionMapperTest](https://github.com/KostyaGig/TzMovieApp/blob/master/app/src/test/java/com/zinoview/tzmovieapp/data/ExceptionMapperTest.kt)<br/>
- [MovieRepositoryTest](https://github.com/KostyaGig/TzMovieApp/blob/master/app/src/test/java/com/zinoview/tzmovieapp/data/MovieRepositoryTest.kt)<br/>

### Разбиение приложение на фичи 

#### Feature MA01 show movies (Данную фичу можно протестировать согласно написанным тесткейсам)
 - [MA01TestCases](https://github.com/KostyaGig/TzMovieApp/blob/master/app/src/main/java/com/zinoview/tzmovieapp/testcases/MA01TestCases)<br/>
 
##### Splash screen (with image and text)
 <img src="https://firebasestorage.googleapis.com/v0/b/testprojectforexample.appspot.com/o/splash.jpg?alt=media&token=3facb5ad-028b-4393-9469-587377f01f31" width="300" height="550"/>

##### Failure state (no connection)
<img src="https://firebasestorage.googleapis.com/v0/b/testprojectforexample.appspot.com/o/failure.jpg?alt=media&token=405e3d8a-f9e6-4a3c-ae4e-bc0d9963e624" width="300" height="550"/>
 
##### Loading state  
<img src="https://firebasestorage.googleapis.com/v0/b/testprojectforexample.appspot.com/o/progress.jpg?alt=media&token=a7f21330-fdf7-4c4b-81d7-2c9352877f2e" width="300" height="550"/>
  
##### Scroll loading state
  
<img src="https://firebasestorage.googleapis.com/v0/b/testprojectforexample.appspot.com/o/paging_loading.jpg?alt=media&token=e3624249-a0da-4af8-9c57-35d2744c475a" width="300" height="550"/>

##### Success loading state

<img src="https://firebasestorage.googleapis.com/v0/b/testprojectforexample.appspot.com/o/end.jpg?alt=media&token=0b689460-d8b2-42a0-bad8-9d18aa753ff4" width="300" height="550"/>

## Rest API


### GET - https://api.nytimes.com/svc/movies/v2/reviews/all.json

