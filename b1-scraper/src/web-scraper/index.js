'use strict'
import { scraping } from './scraper.mjs'
import { compare } from './compare.js'

/**
 * This is the main code for the application.
 * @param {URL} url The url that is sent with that Node start command.
 */
async function main (url) {
  const data = await scraping(url)
  compare(data)
}

main(process.argv[2])
