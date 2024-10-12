'use strict'
import { printCompareSuccessPrint, printRecommendationsText, printErrorAvailableDays, printErrorAvailableMovies, printErrorAvailableRestaurants } from './modules/printer.js'

/**
 * The main function to call all the comparing functions with the data
 * that the scraper did scrap up from the server.
 * @param {Array} data with all important information from the scraper.
 */
export function compare (data) {
  printRecommendationsText()
  const availableDays = compareDaysWithPersons(data[0])
  const availableMovies = compareDaysWithMovies(availableDays, data[1])
  compareCinemaWithRestaurant(availableMovies, data[2])
}

/**
 * Comapres all the persons available days to see what day is available
 * for all of the persons.
 * @param {Array} persons With a list with all the persons
 * @returns {Array} with all the available days that the persons have together.
 */
function compareDaysWithPersons (persons) {
  const allDays = []
  let availableDays = new Set() // Only stores unique objects.

  // Gets all the days from all the persons and saves it in allDays.
  for (const i in persons) {
    const days = persons[i].days
    for (const j in days) {
      allDays.push(days[j].day)
    }
  }

  // Checks the allDays if some days accure the same amount of time as all the persons
  // to be able to find a day that all of them are available.
  for (const i in allDays) {
    let daysFound = 0
    for (const j in allDays) {
      if (allDays[i] === allDays[j]) {
        daysFound++
      }
    }
    if (daysFound === persons.length) {
      availableDays.add(allDays[i]) // Comment out to test error message 1.
    }
  }
  availableDays = [...availableDays] // Turns it into an array to make it easier to work with.

  // Error message if no day(s) were available for everyone.
  if (availableDays.length === 0) {
    printErrorAvailableDays()
  }

  return availableDays
}

/**
 * Compare the available days with available movies for that day(s).
 * @param {Array} availableDays The avilable days for everyone together.
 * @param {Array} cinemas Contains all the available movies with times.
 * @returns {Array} with all the available movies during the days that the people have together.
 */
function compareDaysWithMovies (availableDays, cinemas) {
  const availableMovies = []

  for (const dayIndex in availableDays) {
    for (const cinemaIndex in cinemas) {
      const cinema = cinemas[cinemaIndex]
      const movies = cinema.movies
      for (const movieIndex in movies) {
        if (availableDays[dayIndex] === movies[movieIndex].day) {
          availableMovies.push(movies[movieIndex]) // Comment out to test error message 2.
        }
      }
    }
  }

  // Error message if no movie were found those day(s).
  if (availableMovies.length === 0 && availableDays.length > 0) {
    printErrorAvailableMovies()
  }

  return availableMovies
}

// Wants the cinema time and movie name from this aswell somehow...
/**
 * Compares the available movies + 2 hours after to see what available tables there are after the available movies that day.
 * @param {Array} availableMovies Stored with all the available movies on the available days.
 * @param {Array} restaurants Stored the class Restaurant with all of the available tables the different days and times.
 */
function compareCinemaWithRestaurant (availableMovies, restaurants) {
  let restaurantTimeFound = false
  for (const avMovies in availableMovies) {
    const movieName = availableMovies[avMovies].name
    const movieDay = availableMovies[avMovies].day
    const movieStartTime = availableMovies[avMovies].time.replace(':00', '')
    const movieEndTime = +movieStartTime + 2
    for (const restaurantIndex in restaurants) {
      const restaurant = restaurants[restaurantIndex]
      const restaurantDays = restaurant.days
      for (const restDayIndex in restaurantDays) {
        const restaurantStartTime = restaurantDays[restDayIndex].startTime
        const restaurantEndTime = restaurantDays[restDayIndex].endTime
        const restaurantDay = restaurantDays[restDayIndex].day
        if (movieEndTime <= restaurantStartTime && movieDay === restaurantDay) {
          restaurantTimeFound = true // Comment out to test error message 1. + the line below.
          printCompareSuccessPrint(movieDay, movieName, movieStartTime, restaurantStartTime, restaurantEndTime)
        }
      }
    }
  }

  // Error message if no restaurants were found 2 hours after the movie.
  if (restaurantTimeFound === false && availableMovies.length > 0) {
    printErrorAvailableRestaurants()
  }
}
