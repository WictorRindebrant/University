'use strict'
import { Person } from './classes/person.js'
import { Cinema } from './classes/cinema.js'
import { Restaurant } from './classes/restaurant.js'
import { printScrapingTextOK, printScrapingTextFAIL } from './modules/printer.js'
import parser from 'node-html-parser'

/**
 * Scraping all the information from the website
 * @param {URL} url to the server start page.
 * @returns {Array} All the important data that the scraper did pick up from the server
 */
export async function scraping (url) {
  const data = [] // persons, cinemas and restaurants
  const persons = [] // all the persons gets saved here
  const cinemas = [] // all the availible movies gets saved here
  const restaurants = [] // all the availible restaurant times gets saved here
  let mainLinks
  let calendarLinks

  // Scraping links function
  try {
    mainLinks = await scrapLinks(url) // Sends in main url
    calendarLinks = await scrapLinks(mainLinks[0]) // Sends in calendar links
    printScrapingTextOK('links...') // Print out that the links worked
  } catch (error) {
    printScrapingTextFAIL('links...') // Print out that the links did not work
  }

  // Scraping available days function
  try {
    for (const index in calendarLinks) {
      await scrapDays(calendarLinks[index], persons)
    }
    printScrapingTextOK('available days...') // Print out that the available days worked
  } catch (error) {
    printScrapingTextFAIL('available days...') // Print out that the available days did not work
  }

  // Scraping showtimes function
  try {
    await scrapTime(mainLinks[1], cinemas)
    printScrapingTextOK('showtimes...') // Print out that the showtimes did worked
  } catch (error) {
    printScrapingTextFAIL('showtimes...') // Print out that the showtimes did not work
  }

  // Scraping possible reservations function
  try {
    await scrapReservation(mainLinks[2], restaurants)
    printScrapingTextOK('reservations...') // Print out that the reservations did worked
  } catch (error) {
    printScrapingTextFAIL('reservations...') // Print out that the reservations did not work
  }

  data.push(persons)
  data.push(cinemas)
  data.push(restaurants)
  return data
}

/**
 * Fixes part of url (./url) to a full url.
 * @param {URL} url the full url.
 * @param {string} partOfUrl the ./ url.
 * @returns {URL} the url + the part of the url together.
 */
function fixFullUrl (url, partOfUrl) {
  return (url + partOfUrl.slice(2))
}

/**
 * Scaps the links from the url.
 * @param {URL} url url to obtain url links from.
 * @returns {URL} the new url link that it found insdie main url.
 */
async function scrapLinks (url) {
  const links = []
  const document = await createDocument(url)
  const linksA = document.querySelectorAll('a')
  for (const linkIndex in linksA) {
    const linkA = linksA[linkIndex]
    let link = linkA.getAttribute('href')
    if (!link.includes('://')) {
      link = fixFullUrl(url, link)
    }
    links.push(link)
  }
  return links
}

/**
 * Creates a document to work with from an url.
 * @param {URL} url the url that gets converted into a document.
 * @returns {Document} returns the document.
 */
async function createDocument (url) {
  const htmlContent = await fetch(url)
  const htmlContentText = await htmlContent.text()
  const document = parser.parse(htmlContentText)
  return document
}

/**
 * Creates a person, adds it to the person list and adds the persons available days to the person.
 * @param {URL} url url to the person available days page.
 * @param {Array} persons list with all the persons.
 */
async function scrapDays (url, persons) {
  const days = []
  const availables = []
  const document = await createDocument(url)
  const person = createPerson(document, persons)
  const ths = document.querySelectorAll('th')
  for (const thsIndex in ths) {
    const th = ths[thsIndex]
    const day = th.textContent
    days.push(day)
  }
  const tds = document.querySelectorAll('td')
  for (const tdsIndex in tds) {
    const td = tds[tdsIndex]
    const available = td.textContent
    availables.push(available)
  }
  addAvailableDays(days, availables, person)
}

/**
 * Creates a person with a name that is from the h2 context.
 * @param {Document} document for the persons available days page.
 * @param {Array} persons the list with all the persons.
 * @returns {Person} the created person.
 */
function createPerson (document, persons) {
  const h2 = document.querySelector('h2')
  const h2text = h2.text
  const person = new Person(h2text)
  persons.push(person)
  return person
}

/**
 * Checks for the available days and adds it to the person class.
 * @param {Array} days all the days that are shown in the person page.
 * @param {Array} availables the indicator on the person page of a day is available.
 * @param {Person} person the person to add the day available day ro.
 */
function addAvailableDays (days, availables, person) {
  for (const i in days) {
    if (!availables[i].includes('-')) {
      person.addDays(days[i])
    }
  }
}

/**
 * Function to collect the movies and their time on the different days.
 * @param {URL} url to the cinema page.
 * @param {Array} cinemas stores the cinemas in this array.
 */
async function scrapTime (url, cinemas) {
  const cinema = new Cinema('Ghost town cinema')
  const document = await createDocument(url)
  const days = []
  const movies = []
  let choice
  const options = document.querySelectorAll('option')
  for (let i = 0; i < options.length; i++) {
    const option = options[i]
    const optionText = option.textContent
    const optionValue = option.attributes.value
    if (optionText === '--- Pick a day ---') {
      choice = 'day'
    } else if (optionText === '--- Pick a Movie ---') {
      choice = 'movie'
    } else if (choice === 'day') {
      days.push([optionText, optionValue])
    } else if (choice === 'movie') {
      movies.push([optionText, optionValue])
    }
  }

  for (const i in days) {
    for (const j in days) {
      await checkMovieTime(url, days[i], movies[j], cinema)
    }
  }
  cinemas.push(cinema)
}

/**
 * Function to check a movies time on that day and that specific movie.
 * @param {URL} url to the page where you can see all available movies.
 * @param {Array} day array with day name and the days id.
 * @param {Array} movie array with movie name and the movie id.
 * @param {Cinema} cinema stores the movies in this cinema Class.
 */
async function checkMovieTime (url, day, movie, cinema) {
  // ("GET", "cinema/check?day=" + a + "&movie=" + b)
  const htmlContent = await fetch(url + '/check?day=' + day[1] + '&movie=' + movie[1])
  const jsonForm = await htmlContent.json()
  for (const i in jsonForm) {
    if (jsonForm[i].status === 1) {
      const movieName = findMovieName(jsonForm[i].movie)
      const movieDay = findMovieDay(jsonForm[i].day)
      const movieTime = jsonForm[i].time
      cinema.addMovies(movieName, movieDay, movieTime)
    }
  }
}

/**
 * Finds out what day name in string format from an id of that day.
 * @param {string} id the id of a particular day.
 * @returns {string} the day name in string format.
 */
function findMovieDay (id) {
  let day
  if (id === '05') {
    day = 'Friday'
  } else if (id === '06') {
    day = 'Saturday'
  } else if (id === '07') {
    day = 'Sunday'
  }

  return day
}

/**
 * Finds out what movie name in string format from an id of that movie.
 * @param {string} id the id of a particular movie.
 * @returns {string} the movie name in string format.
 */
function findMovieName (id) {
  let movie
  if (id === '01') {
    movie = 'The Flying Deuces'
  } else if (id === '02') {
    movie = 'Keep Your Seats, Please'
  } else if (id === '03') {
    movie = 'A Day at the Races'
  }

  return movie
}

/**
 * Function to get all the information on what days and times are available.
 * for reservation at the restaurant by the given url.
 * @param {URL} url to the restaurant login page.
 * @param {Array} restaurants all restaurants gets saved in this array.
 */
async function scrapReservation (url, restaurants) {
  const restaurant = new Restaurant('Zekes Bar')
  const urlAndCookie = await restaurantLogin(url)
  const fetched = await fetch(urlAndCookie[0], {
    headers: { Cookie: urlAndCookie[1] }
  })
  const htmlContentText = await fetched.text()
  const document = parser.parse(htmlContentText)
  const availableTimes = document.querySelectorAll('input')
  for (const i in availableTimes) {
    if (availableTimes[i].getAttribute('value').length === 7) {
      restaurantAvailableTime(availableTimes[i].getAttribute('value'), restaurant)
    }
  }
  restaurants.push(restaurant)
}

/**
 * Takes a short string with day and time of a movie, splits it up and
 * creates a new day with that day name, start time and end time.
 * @param {string} dayTime short string that contains day, start time and end time.
 * @param {Restaurant} restaurant class to save the available restaurant days and times to.
 */
function restaurantAvailableTime (dayTime, restaurant) {
  const day = fixDay(dayTime.slice(0, 3))
  const startTime = dayTime.slice(3, 5)
  const endTime = dayTime.slice(5, 7)
  restaurant.addDays(day, startTime, endTime)
}

/**
 * Takes first part of a day name and converts it to the whole day name.
 * @param {string} day first part of the day name
 * @returns {string} full name of the day.
 */
function fixDay (day) {
  let fixedDay
  if (day === 'fri') {
    fixedDay = 'Friday'
  } else if (day === 'sat') {
    fixedDay = 'Saturday'
  } else if (day === 'sun') {
    fixedDay = 'Sunday'
  }
  return fixedDay
}

/**
 * Handles the restaurant login Post request and returns the important
 * information that is given from the post response.
 * @param {url} url to the restaurant login site.
 * @returns {Array} url to the restaurants booking site and the authentication cookies for that site.
 */
async function restaurantLogin (url) {
  const document = await createDocument(url)
  const form = document.querySelector('form')
  const formAction = form.attributes.action
  const postPage = formAction.replace('./', '')
  const bodyData = new URLSearchParams()
  bodyData.append('username', 'zeke')
  bodyData.append('password', 'coys')
  bodyData.append('submit', 'login')
  const respons = await fetch(url + postPage, {
    method: 'POST',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    body: bodyData,
    redirect: 'manual'
  })
  const location = respons.headers.get('Location')
  const loggedinUrl = url + location
  const cookie = respons.headers.get('set-cookie')
  const urlAndCookie = [loggedinUrl, cookie]
  return urlAndCookie
}
