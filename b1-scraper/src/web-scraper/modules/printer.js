'use strict'

const def = '\x1b[0m'
const blue = '\x1b[34m'
const purple = '\x1b[35m'
const red = '\x1b[31m'
const green = '\x1b[32m'
const orange = '\x1b[33m'
const lightPink = '\x1b[38;5;206m'

/**
 * Scrap console log for when the scraping did work.
 * @param {string} txt what it was that worked to scrap.
 */
export function printScrapingTextOK (txt) {
  console.log(blue + 'Scraping' + def, txt + purple + 'OK' + def)
}

/**
 * Scrap console log for when the scraping did not work.
 * @param {string} txt what it was that did not worked to scrap.
 */
export function printScrapingTextFAIL (txt) {
  console.log(blue + 'Scraping' + def, txt + red + 'FAIL' + def)
}

/**
 * Prints out the Recommendation headline.
 */
export function printRecommendationsText () {
  console.log()
  console.log(green + 'Recommendations' + def)
  console.log('===============')
}

/**
 * Prints the final message if everything did go secessfull and it did find a day, movie and restaurant.
 * @param {string} movieDay The name of the day.
 * @param {string} movieName The name of the movie.
 * @param {string} movieStartTime The time that the movie starts.
 * @param {string} restaurantStartTime The time that the available table at the restaurant are available from.
 * @param {string} restaurantEndTime The end time that the available table at the restaurant ends.
 */
export function printCompareSuccessPrint (movieDay, movieName, movieStartTime, restaurantStartTime, restaurantEndTime) {
  console.log('*', blue + 'On', movieDay, def + 'the movie "' + orange + movieName + def + '" starts at', lightPink + movieStartTime + ':00', def + 'and there is a free table between', lightPink + restaurantStartTime + ':00-' + restaurantEndTime + ':00' + def + '.')
}

/**
 * Prints a short error message if it did not find a day(s) that everyone is availalbe
 */
export function printErrorAvailableDays () {
  console.log(red + 'No Available Day(s) ' + def + 'were found when everyone had time.')
}

/**
 * Prints a short error message if it did not find a movie(s) on the available day(s).
 */
export function printErrorAvailableMovies () {
  console.log(red + 'No Available Movie(s) ' + def + 'were found when everyone had time.')
}

/**
 * Prints a short error message if it did not find a table 2 hours after the availalble movie.
 */
export function printErrorAvailableRestaurants () {
  console.log(red + 'No Available Restaurant(s) ' + def + 'were found when everyone had time after the movie.')
}
