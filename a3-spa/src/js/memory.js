/* Wictor Rindebrant */
'use strict'

import memoryImgUrl from '../img/toolbar/memory.png'

import dhImgUrl from '../img/memory/dh.png'
import dkImgUrl from '../img/memory/dk.png'
import druidImgUrl from '../img/memory/druid.png'
import mageImgUrl from '../img/memory/mage.png'
import paladinImgUrl from '../img/memory/paladin.png'
import priestImgUrl from '../img/memory/priest.png'
import rogueImgUrl from '../img/memory/rogue.png'
import shamanImgUrl from '../img/memory/shaman.png'
import wowImgUrl from '../img/memory/wow.png'

import { setElements } from '../js/modules/draggable.js'
import { layerOnTop, newWindow } from './modules/window.js'

const parentElement = document.getElementById('desktop')
const memoryBtn = document.getElementById('memoryBtn')
let idnumb = 0

// Everything that will happend when you press the Memory Application button.
memoryBtn.addEventListener('click', e => {
  const clicks = 0
  const flip = 0
  const inProgress = false
  let gameTimerInterv
  const keyboardindex = -1
  const dragWindow = createWindow(clicks)
  const memorycards = createMemroyCardsDiv()

  const memorysizes = document.createElement('div')
  memorysizes.className = 'memorysizes'

  const smallsize = document.createElement('button')
  smallsize.className = 'sizebtn'
  smallsize.textContent = '2x2'
  smallsize.addEventListener('click', e => {
    memoryReset(dragWindow, memorycards, clicks, flip, inProgress, keyboardindex)
    createCardsSmall(dragWindow, memorycards, clicks, flip, inProgress, keyboardindex)
    gameTimer()
  })

  const mediumlsize = document.createElement('button')
  mediumlsize.className = 'sizebtn'
  mediumlsize.textContent = '2x4'
  mediumlsize.addEventListener('click', e => {
    memoryReset(dragWindow, memorycards, clicks, flip, inProgress, keyboardindex)
    createCardMedium(dragWindow, memorycards, clicks, flip, inProgress, keyboardindex)
    gameTimer()
  })

  const largesize = document.createElement('button')
  largesize.className = 'sizebtn'
  largesize.textContent = '4x4'
  largesize.addEventListener('click', e => {
    memoryReset(dragWindow, memorycards, clicks, flip, inProgress, keyboardindex)
    createCardLarge(dragWindow, memorycards, clicks, flip, inProgress, keyboardindex)
    gameTimer()
  })

  memorysizes.appendChild(smallsize)
  memorysizes.appendChild(mediumlsize)
  memorysizes.appendChild(largesize)
  dragWindow.appendChild(memorysizes)
  dragWindow.appendChild(memorycards)

  /**
   * The timer that start when a game start and stops when you win.
   */
  function gameTimer () {
    let gameTimer = 0
    dragWindow.children[0].children[1].textContent = 'Time: ' + gameTimer
    clearInterval(gameTimerInterv)
    gameTimerInterv = setInterval(timerTick, 1000)

    /**
     * Part of the game timer that adds 1 for every tick, which is set to 1 sec above.
     * And updates the textContent for the Time, for the user to see when playing.
     */
    function timerTick () {
      gameTimer++
      dragWindow.children[0].children[1].textContent = 'Time: ' + gameTimer
    }
  }
})

/**
 * Creates the Memorywindow with all its elements.
 * @param {number} clicks Is how many clicks the user is doing to calculate the attempts.
 * @returns {HTMLElement} The created window.
 */
function createWindow (clicks) {
  const dragWindow = document.createElement('div')
  dragWindow.id = 'dragwindow' + idnumb
  dragWindow.className = 'dragwindow'
  dragWindow.setAttribute('tabindex', '0')
  dragWindow.setAttribute('draggable', 'true')
  dragWindow.addEventListener('mousedown', (e) => {
    layerOnTop(dragWindow)
  })

  const dragHeader = document.createElement('header')
  dragHeader.id = 'dragheader' + idnumb
  dragHeader.className = 'dragheader'
  dragHeader.setAttribute('draggable', 'false')

  const img = document.createElement('img')
  img.src = memoryImgUrl
  img.className = 'dragheadimg'

  const h3 = document.createElement('h3')
  h3.className = 'dragh3'
  h3.textContent = 'Memory Game'

  const closebutton = document.createElement('button')
  closebutton.className = 'dragclosebtn'
  closebutton.textContent = 'X'
  closebutton.addEventListener('click', e => {
    parentElement.removeChild(dragWindow)
  })

  const wininfo = document.createElement('div')
  wininfo.className = 'wininfo'
  wininfo.id = 'wininfo'

  const welcomeText = document.createElement('h2')
  welcomeText.className = 'welcomeText'
  welcomeText.id = 'welcomeText'
  welcomeText.textContent = 'Welcome to the Memory Game!'

  const explainStart = document.createElement('h4')
  explainStart.className = 'explainStart'
  explainStart.id = 'explainStart'
  explainStart.textContent = 'Press one of the size buttons at the top of the window to start the game.'

  memroyInfo(dragWindow, clicks)
  dragHeader.appendChild(img)
  dragHeader.appendChild(h3)
  dragHeader.appendChild(closebutton)
  dragWindow.appendChild(dragHeader)
  wininfo.appendChild(welcomeText)
  wininfo.appendChild(explainStart)
  dragWindow.appendChild(wininfo)

  parentElement.appendChild(dragWindow)
  newWindow(dragWindow)
  layerOnTop(dragWindow)
  setElements(dragWindow)
  idnumb++

  return dragWindow
}

/**
 * Resets the MemoryApplications varibles. (Good for restart games).
 * @param {HTMLElement} dragWindow The main application window.
 * @param {HTMLElement} memorycards The div for the memory cards.
 * @param {number} clicks How many clicks the user made.
 * @param {number} flip How many cards that are flipped.
 * @param {boolean} inProgress If a move/attempts is in progress.
 * @param {number} keyboardindex Div index from when playing with the keyboard.
 */
function memoryReset (dragWindow, memorycards, clicks, flip, inProgress, keyboardindex) {
  memorycards.innerHTML = ''
  clicks = 0
  flip = 0
  keyboardindex = 0
  inProgress = false
  dragWindow.children[0].children[0].style.opacity = '1'
  dragWindow.children[0].children[1].style.opacity = '1'
  dragWindow.children[2].innerHTML = ''
  attemptsUpdate(dragWindow, clicks)
}

/**
 * Just creates a Div for memorycards and return it.
 * @returns {HTMLElement} Returns the created memorycard DIV.
 */
function createMemroyCardsDiv () {
  const memorycards = document.createElement('div')
  memorycards.className = 'memorycards'
  return memorycards
}

/**
 * Creates all the cards for the 2x4 game and their functionallity.
 * @param {HTMLElement} dragWindow The main application window.
 * @param {HTMLElement} memorycards The div for the memory cards.
 * @param {number} clicks How many clicks the user made.
 * @param {number} flip How many cards that are flipped.
 * @param {boolean} inProgress If a move/attempts is in progress.
 * @param {number} keyboardindex Div index from when playing with the keyboard.
 */
function createCardsSmall (dragWindow, memorycards, clicks, flip, inProgress, keyboardindex) {
  const memoryCardsArray = []
  memorycards.className = 'memorycards'

  addImgToCard(dhImgUrl, '0')
  addImgToCard(dhImgUrl, '1')
  addImgToCard(dkImgUrl, '2')
  addImgToCard(dkImgUrl, '3')

  const shuffledArray = arrayShuffle(memoryCardsArray)

  shuffledArray.forEach((divCard) => {
    memorycards.appendChild(divCard)
  })

  keyboardHandlerSmall(dragWindow, memorycards, keyboardindex)

  /**
   * Fixing all the main functionallity for the cards in the 2x2 game.
   * @param {HTMLElement} dragWindow The main application window.
   * @param {HTMLElement} memorycards The div for the memory cards.
   * @param {number} keyboardindex Div index from when playing with the keyboard.
   */
  function keyboardHandlerSmall (dragWindow, memorycards, keyboardindex) {
    dragWindow.addEventListener('mousedown', (e) => {
      keyboardindex = -1
    })

    dragWindow.addEventListener('keydown', (e) => {
      if (keyboardindex === -1) {
        keyboardindex = 0
        memorycards.children[keyboardindex].focus()
      }
    })

    memorycards.addEventListener('keydown', (e) => {
      const backupindex = keyboardindex
      if (e.key === 'ArrowRight') {
        keyboardindex++
      } else if (e.key === 'ArrowLeft') {
        keyboardindex--
      } else if (e.key === 'ArrowUp') {
        keyboardindex -= 2
      } else if (e.key === 'ArrowDown') {
        keyboardindex += 2
      }
      if (keyboardindex < 0 || keyboardindex > memorycards.childElementCount - 1) {
        keyboardindex = backupindex
      } else {
        memorycards.children[keyboardindex].focus()
      }
    })
  }

  /**
   * This function adds the Images to the cards.
   * @param {URL} imgUrl Img URL that we want for the card
   */
  function addImgToCard (imgUrl) {
    const memorycardsmall = document.createElement('div')
    memorycardsmall.id = imgUrl
    memorycardsmall.className = 'memorycardsmall'
    memorycardsmall.setAttribute('tabindex', '0')
    memorycardsmall.addEventListener('mousedown', cardClick)
    // The keyboard enter or space eventListener
    memorycardsmall.addEventListener('keydown', (e) => {
      if (e.key === 'Enter' || e.key === ' ') {
        cardClick()
      }
    })

    /**
     * Everything that will happen when you click on a single card.
     * @param {Event} e The event.
     */
    function cardClick (e) {
      if (window.getComputedStyle(memorycardsmall).opacity === '0.4') {
        memorycardsmall.removeEventListener('mousedown', cardClick)
      } else if (inProgress === false && !memorycardsmall.children[0].src.includes(wowImgUrl)) {
        clicks++
        if (flip < 2) {
          swapImg(memorycardsmall, flip)
          flip++
        }
        if (flip > 1) {
          attemptsUpdate(dragWindow, clicks)
          inProgress = true
          setTimeout(function () {
            inProgress = false
            correctImg(memorycards)
            resetImg(memorycards)
            checkWin(dragWindow, memorycards)
            flip = 0
          }, 500)
        }
      }
    }

    const newImageshow = new Image()
    newImageshow.src = imgUrl

    const memorycardImgshow = document.createElement('img')
    memorycardImgshow.src = newImageshow.src
    memorycardImgshow.className = 'memorycardsmallshow'
    memorycardsmall.appendChild(memorycardImgshow)

    const newImagehide = new Image()
    newImagehide.src = wowImgUrl

    const memorycardImghide = document.createElement('img')
    memorycardImghide.src = newImagehide.src
    memorycardImghide.className = 'memorycardsmallhide'
    memorycardsmall.appendChild(memorycardImghide)

    memoryCardsArray.push(memorycardsmall)
  }
}

/**
 * Creates all the cards for the 2x4 game and their functionallity.
 * @param {HTMLElement} dragWindow The main application window.
 * @param {HTMLElement} memorycards The div for the memory cards.
 * @param {number} clicks How many clicks the user made.
 * @param {number} flip How many cards that are flipped.
 * @param {boolean} inProgress If a move/attempts is in progress.
 * @param {number} keyboardindex Div index from when playing with the keyboard.
 */
function createCardMedium (dragWindow, memorycards, clicks, flip, inProgress, keyboardindex) {
  const memoryCardsArray = []
  memorycards.className = 'memorycardsmedium'

  addImgToCard(dhImgUrl)
  addImgToCard(dhImgUrl)
  addImgToCard(dkImgUrl)
  addImgToCard(dkImgUrl)
  addImgToCard(druidImgUrl)
  addImgToCard(druidImgUrl)
  addImgToCard(mageImgUrl)
  addImgToCard(mageImgUrl)

  const shuffledArray = arrayShuffle(memoryCardsArray)

  shuffledArray.forEach((divCard) => {
    memorycards.appendChild(divCard)
  })

  keyboardHandlerMedium(dragWindow, memorycards, keyboardindex)

  /**
   * Fixing all the main functionallity for the cards in the 2x4 game.
   * @param {HTMLElement} dragWindow The main application window.
   * @param {HTMLElement} memorycards The div for the memory cards.
   * @param {number} keyboardindex Div index from when playing with the keyboard.
   */
  function keyboardHandlerMedium (dragWindow, memorycards, keyboardindex) {
    dragWindow.addEventListener('mousedown', (e) => {
      keyboardindex = -1
    })

    dragWindow.addEventListener('keydown', (e) => {
      if (keyboardindex === -1) {
        keyboardindex = 0
        memorycards.children[keyboardindex].focus()
      }
    })

    memorycards.addEventListener('keydown', (e) => {
      const backupindex = keyboardindex
      if (e.key === 'ArrowRight') {
        keyboardindex++
      } else if (e.key === 'ArrowLeft') {
        keyboardindex--
      } else if (e.key === 'ArrowUp') {
        keyboardindex -= 4
      } else if (e.key === 'ArrowDown') {
        keyboardindex += 4
      }
      if (keyboardindex < 0 || keyboardindex > memorycards.childElementCount - 1) {
        keyboardindex = backupindex
      } else {
        memorycards.children[keyboardindex].focus()
      }
    })
  }

  /**
   * This function adds the Images to the cards.
   * @param {URL} imgUrl Img URL that we want for the card
   */
  function addImgToCard (imgUrl) {
    const memorycardmedium = document.createElement('div')
    memorycardmedium.id = imgUrl
    memorycardmedium.className = 'memorycardmedium'
    memorycardmedium.setAttribute('tabindex', '0')
    memorycardmedium.addEventListener('mousedown', cardClick)
    // The keyboard enter or space eventListener
    memorycardmedium.addEventListener('keydown', (e) => {
      if (e.key === 'Enter' || e.key === ' ') {
        cardClick()
      }
    })

    /**
     * Everything that will happen when you click on a single card.
     * @param {Event} e The event.
     */
    function cardClick (e) {
      if (window.getComputedStyle(memorycardmedium).opacity === '0.4') {
        memorycardmedium.removeEventListener('mousedown', cardClick)
      } else if (inProgress === false && !memorycardmedium.children[0].src.includes(wowImgUrl)) {
        clicks++
        if (flip < 2) {
          swapImg(memorycardmedium, flip)
          flip++
        }
        if (flip > 1) {
          attemptsUpdate(dragWindow, clicks)
          inProgress = true
          setTimeout(function () {
            inProgress = false
            correctImg(memorycards)
            resetImg(memorycards)
            checkWin(dragWindow, memorycards)
            flip = 0
          }, 500)
        }
      }
    }

    const newImageshow = new Image()
    newImageshow.src = imgUrl

    const memorycardImgshow = document.createElement('img')
    memorycardImgshow.src = newImageshow.src
    memorycardImgshow.className = 'memorycardmediumshow'
    memorycardmedium.appendChild(memorycardImgshow)

    const newImagehide = new Image()
    newImagehide.src = wowImgUrl

    const memorycardImghide = document.createElement('img')
    memorycardImghide.src = newImagehide.src
    memorycardImghide.className = 'memorycardmediumhide'
    memorycardmedium.appendChild(memorycardImghide)

    memoryCardsArray.push(memorycardmedium)
  }
}

/**
 * Creates all the cards for the 4x4 game and their functionallity.
 * @param {HTMLElement} dragWindow The main application window.
 * @param {HTMLElement} memorycards The div for the memory cards.
 * @param {number} clicks How many clicks the user made.
 * @param {number} flip How many cards that are flipped.
 * @param {boolean} inProgress If a move/attempts is in progress.
 * @param {number} keyboardindex Div index from when playing with the keyboard.
 */
function createCardLarge (dragWindow, memorycards, clicks, flip, inProgress, keyboardindex) {
  const memoryCardsArray = []
  memorycards.className = 'memorycardslarge'

  addImgToCard(dhImgUrl)
  addImgToCard(dhImgUrl)
  addImgToCard(dkImgUrl)
  addImgToCard(dkImgUrl)
  addImgToCard(druidImgUrl)
  addImgToCard(druidImgUrl)
  addImgToCard(mageImgUrl)
  addImgToCard(mageImgUrl)
  addImgToCard(paladinImgUrl)
  addImgToCard(paladinImgUrl)
  addImgToCard(priestImgUrl)
  addImgToCard(priestImgUrl)
  addImgToCard(rogueImgUrl)
  addImgToCard(rogueImgUrl)
  addImgToCard(shamanImgUrl)
  addImgToCard(shamanImgUrl)

  const shuffledArray = arrayShuffle(memoryCardsArray)

  shuffledArray.forEach((divCard) => {
    memorycards.appendChild(divCard)
  })

  keyboardHandlerLarge(dragWindow, memorycards, keyboardindex)

  /**
   * Fixing all the main functionallity for the cards in the 4x4 game.
   * @param {HTMLElement} dragWindow The main application window.
   * @param {HTMLElement} memorycards The div for the memory cards.
   * @param {number} keyboardindex Div index from when playing with the keyboard.
   */
  function keyboardHandlerLarge (dragWindow, memorycards, keyboardindex) {
    dragWindow.addEventListener('mousedown', (e) => {
      keyboardindex = -1
    })

    dragWindow.addEventListener('keydown', (e) => {
      if (keyboardindex === -1) {
        keyboardindex = 0
        memorycards.children[keyboardindex].focus()
      }
    })

    memorycards.addEventListener('keydown', (e) => {
      const backupindex = keyboardindex
      if (e.key === 'ArrowRight') {
        keyboardindex++
      } else if (e.key === 'ArrowLeft') {
        keyboardindex--
      } else if (e.key === 'ArrowUp') {
        keyboardindex -= 4
      } else if (e.key === 'ArrowDown') {
        keyboardindex += 4
      }
      if (keyboardindex < 0 || keyboardindex > memorycards.childElementCount - 1) {
        keyboardindex = backupindex
      } else {
        memorycards.children[keyboardindex].focus()
      }
    })
  }

  /**
   * This function adds the Images to the cards.
   * @param {URL} imgUrl Img URL that we want for the card
   */
  function addImgToCard (imgUrl) {
    const memorycardlarge = document.createElement('div')
    memorycardlarge.id = imgUrl
    memorycardlarge.className = 'memorycardlarge'
    memorycardlarge.setAttribute('tabindex', '0')
    memorycardlarge.addEventListener('mousedown', cardClick)
    // The keyboard enter or space eventListener
    memorycardlarge.addEventListener('keydown', (e) => {
      if (e.key === 'Enter' || e.key === ' ') {
        cardClick()
      }
    })

    /**
     * Everything that will happen when you click on a single card.
     * @param {Event} e The event.
     */
    function cardClick (e) {
      if (window.getComputedStyle(memorycardlarge).opacity === '0.4') {
        memorycardlarge.removeEventListener('mousedown', cardClick)
      } else if (inProgress === false && !memorycardlarge.children[0].src.includes(wowImgUrl)) {
        clicks++
        if (flip < 2) {
          swapImg(memorycardlarge, flip)
          flip++
        }
        if (flip > 1) {
          attemptsUpdate(dragWindow, clicks)
          inProgress = true
          setTimeout(function () {
            inProgress = false
            correctImg(memorycards)
            resetImg(memorycards)
            checkWin(dragWindow, memorycards)
            flip = 0
          }, 500)
        }
      }
    }

    const newImageshow = new Image()
    newImageshow.src = imgUrl

    const memorycardImgshow = document.createElement('img')
    memorycardImgshow.src = newImageshow.src
    memorycardImgshow.className = 'memorycardlargeshow'
    memorycardlarge.appendChild(memorycardImgshow)

    const newImagehide = new Image()
    newImagehide.src = wowImgUrl

    const memorycardImghide = document.createElement('img')
    memorycardImghide.src = newImagehide.src
    memorycardImghide.className = 'memorycardlargehide'
    memorycardlarge.appendChild(memorycardImghide)

    memoryCardsArray.push(memorycardlarge)
  }
}

/**
 * Function that swaps the two Images inside a memorycardDiv.
 * @param {HTMLElement} memorycards The div for the memorycard.
 * @param {number} flip How many cards that are flipped.
 */
function swapImg (memorycards, flip) {
  const img1 = memorycards.children[0].src
  const img2 = memorycards.children[1].src
  if (img2.includes(wowImgUrl)) {
    memorycards.children[0].src = img2
    memorycards.children[1].src = img1
  }
}

/**
 * Resets the Img of the cards to the backside of the card.
 * @param {HTMLElement} memorycards The div for the memorycard.
 */
function resetImg (memorycards) {
  const divCards = memorycards.children

  for (let i = 0; i < divCards.length; i++) {
    const card = divCards[i]
    const img1 = card.children[0].src
    const img2 = card.children[1].src
    if (img1.includes(wowImgUrl)) {
      card.children[0].src = img2
      card.children[1].src = img1
    }
  }
}

/**
 * Checks if the turned up cards are the same or not.
 * @param {HTMLElement} memorycards The div for the memorycard.
 */
function correctImg (memorycards) {
  const divCards = memorycards.children
  let correctCard1 = ''
  let correctCard2 = ''
  let cardFound = 0

  for (let i = 0; i < divCards.length; i++) {
    const card = divCards[i]
    const img1 = card.children[0].src
    if (img1.includes(wowImgUrl)) {
      if (cardFound === 0) {
        correctCard1 = card
        cardFound++
      } else if (cardFound === 1) {
        correctCard2 = card
        cardFound++
      }
    }
  }
  if (correctCard1.id === correctCard2.id) {
    correctCard1.style.opacity = '0.4'
    correctCard2.style.opacity = '0.4'
  }
}

/**
 * Checks if won, by checking so that all cards are no longer opacity 1.
 * @param {HTMLElement} dragWindow The main application window.
 * @param {HTMLElement} memorycards The div for the memory cards.
 */
function checkWin (dragWindow, memorycards) {
  const divCards = memorycards.children
  let cardFound = false

  for (let i = 0; i < divCards.length; i++) {
    const card = divCards[i]

    if (window.getComputedStyle(card).opacity === '1') {
      cardFound = true
    }
  }
  if (cardFound === false) {
    const attempts = dragWindow.children[0].children[0]
    const time = dragWindow.children[0].children[1]
    attempts.style.opacity = '0'
    time.style.opacity = '0'
    memoryWin(dragWindow, attempts.textContent, time.textContent)
  }
}

/**
 * Creates the information about attempts and time at the bottom of the game.
 * @param {HTMLElement} dragWindow The main application window.
 * @param {number} clicks How many clicks the user made.
 */
function memroyInfo (dragWindow, clicks) {
  const memoryinfo = document.createElement('div')
  memoryinfo.className = 'memoryinfo'
  memoryinfo.id = 'memoryinfo'

  const memoryattempts = document.createElement('h3')
  memoryattempts.className = 'memoryattempts'
  memoryattempts.id = 'memoryattempts'
  memoryattempts.textContent = 'Attempts: ' + clicks / 2

  const memorytime = document.createElement('h3')
  memorytime.className = 'memorytime'
  memorytime.id = 'memorytime'
  memorytime.textContent = 'Time: 0'

  memoryinfo.appendChild(memoryattempts)
  memoryinfo.appendChild(memorytime)
  dragWindow.appendChild(memoryinfo)
}

/**
 * Updates how many attempts the user have done by dividing clicks by 2.
 * @param {HTMLElement} dragWindow The main application window.
 * @param {number} clicks How many clicks the user made.
 */
function attemptsUpdate (dragWindow, clicks) {
  dragWindow.children[0].children[0].textContent = 'Attempts: ' + clicks / 2
}

/**
 * When the game is won, it shows a some text about attempts, time and instructions for a new game.
 * @param {HTMLElement} dragWindow The main application window.
 * @param {HTMLElement} attempts Contains how many attempts the user have made.
 * @param {HTMLElement} time Contains how long time the game did take.
 */
function memoryWin (dragWindow, attempts, time) {
  const wininfo = dragWindow.children[2]
  dragWindow.children[4].innerHTML = ''

  const congratulationText = document.createElement('h2')
  congratulationText.className = 'congratulationText'
  congratulationText.id = 'congratulationText'
  congratulationText.textContent = 'Congratulation YOU WON!'

  const winattempts = document.createElement('h3')
  winattempts.className = 'winattempts'
  winattempts.id = 'winattempts'
  winattempts.textContent = attempts

  const wintime = document.createElement('h3')
  wintime.className = 'wintime'
  wintime.id = 'wintime'
  wintime.textContent = time

  const memoryagain = document.createElement('h4')
  memoryagain.className = 'memoryagain'
  memoryagain.id = 'memoryagain'
  memoryagain.textContent = 'If you want to GO AGAIN, then just press one of the size buttons at the top.'

  wininfo.appendChild(congratulationText)
  wininfo.appendChild(winattempts)
  wininfo.appendChild(wintime)
  wininfo.appendChild(memoryagain)
}

/**
 * Randomly shuffles an array that is sent in to the function.
 * @param {Array} memoryArray An non sorted array.
 * @returns {Array} Returns a randomly shuffeled array.
 */
function arrayShuffle (memoryArray) {
  for (let i = memoryArray.length - 1; i > 0; i--) {
    const random = Math.floor(Math.random() * (i + 1));
    [memoryArray[i], memoryArray[random]] = [memoryArray[random], memoryArray[i]]
  }
  return memoryArray
}
