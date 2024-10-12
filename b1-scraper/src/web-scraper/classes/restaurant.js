'use strict'

class Day {
  constructor (day, startTime, endTime) {
    this.day = day
    this.startTime = startTime
    this.endTime = endTime
  }
}

export class Restaurant {
  constructor (name) {
    this.name = name
    this.days = []
  }

  addDays (day, startTime, endTime) {
    const classDay = new Day(day, startTime, endTime)
    this.days.push(classDay)
  }
}
