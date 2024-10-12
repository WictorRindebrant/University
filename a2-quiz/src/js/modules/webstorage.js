/* Wictor Rindebrant */
'use strict'

/**
 * Adds a keypair to the local storage and sorts it.
 * @param {string} name Is the name of the player.
 * @param {number} time Is the time it took for the player.
 */
export function addToStorage (name, time) {
  let players = []

  if (getStorage()) {
    players = [...getStorage()]
  }

  const player = {
    name,
    time
  }

  players.push(player)

  localStorage.setItem('players', JSON.stringify(players))
  sortStorage()
}

/**
 * Gets the array that is stored on the local storage.
 * @returns {Array|null} Stores all the players in this array.
 */
export function getStorage () {
  const sbStorage = JSON.parse(localStorage.getItem('players'))
  return sbStorage
}

/**
 *  Gets the top 5 fastest times that is stored on the local storage.
 *  @returns {Array|null} Stores all the top 5 players in this array.
 */
export function getTop5Storage () {
  const top5Storage = []
  if (getStorage()) {
    const tempStorage = [...getStorage()]

    for (let c = 0; c < tempStorage.length && c < 5; c++) {
      top5Storage.push(tempStorage[c])
    }
  }

  return top5Storage
}

/**
 *  Gets the storage, sorts it and send it back to the local storage again.
 */
function sortStorage () {
  const notSortedStorage = [...getStorage()]
  const sortedStorage = notSortedStorage.sort((a, b) => a.time - b.time)
  localStorage.setItem('players', JSON.stringify(sortedStorage))
}
