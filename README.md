# f
Final Project

Mobile Application Programming
Shiyuan Zhang

Main Function:
  Find information about the movie the user needs (actors, staff, release date, overview, scores）

Features:
  1. There is a Slider show on the home page.
  2. The Search can ignore the problem of capital and small letters.
  3. User can get the movie information by searching the film's name or just click the title of the movie.
  4. It has a huge film database.
  5. User can check the actors and the staff by entering the movie detailed information page and clicking Character or Staff.
  
Skills used in this project:
  1. Android Image Slider
      
      The way to create a Image Slider can refer to this website: https://github.com/daimajia/AndroidImageSlider
      
      *You have to change the compileSdkVersion to 27. After that you may still have errors about the version. Just add these codes 
      at the end of the codes:
          configurations.all {
          resolutionStrategy.eachDependency { DependencyResolveDetails details ->
          def requested = details.requested
          if (requested.group == 'com.android.support') {
            if (!requested.name.startsWith("multidex")) {
                details.useVersion '27.1.0'
              }
          }
          }
          }
          
    2. Retriever JSON from Url
    3. Parse JSON
          
         
Youtube Link: https://www.youtube.com/watch?v=T4RDzeteXJQ&t=15s
