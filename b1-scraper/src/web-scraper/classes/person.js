'use strict'

class Day {
  constructor (day) {
    this.day = day
  }
}

export class Person {
  constructor (name) {
    this.name = name
    this.days = []
  }

  addDays (day) {
    const classDay = new Day(day)
    this.days.push(classDay)
  }
}
