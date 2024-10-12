'use strict'

class Movie {
  constructor (name, day, time) {
    this.name = name
    this.day = day
    this.time = time
  }
}

export class Cinema {
  constructor (name) {
    this.name = name
    this.movies = []
  }

  addMovies (name, day, time) {
    const classMovie = new Movie(name, day, time)
    this.movies.push(classMovie)
  }
}
