# Intro

This project uses Android studio 3.5 and targets api 28

To run please sync with gradle to grab dependencies

Rick Morty API was used in this implementation

## Project Structure

This follows a modular design, separating the presentation, domain, data layer
it applies the use of interfaces as a binding contract to provide the minimum required
feature target of a class.

## Libraries

- Ketro https://smilecs.github.io/ketro/ (Personal library available on my github and Jcenter) : For request wrapping and error handling
- Coroutines : For background processing
- Dagger : Dependency injection
- Coil: For Image loading (Uses Coroutines internally)

Please set your sdk location in local.properties file

  
