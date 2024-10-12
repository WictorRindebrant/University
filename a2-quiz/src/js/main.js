/* Wictor Rindebrant */
'use strict'
import { addToStorage, getTop5Storage, getStorage } from './modules/webstorage.js'

const firstURL = 'https://courselab.lnu.se/quiz/question/1'
let nextURL = firstURL
let respURL

const startpage = document.getElementById('startpage')
const questionpage = document.getElementById('questionpage')
const loserpage = document.getElementById('loserpage')
const winnerpage = document.getElementById('winnerpage')
const scoreboard = document.getElementById('scoreboard')
const progresspage = document.getElementById('progresspage')

const startbutton = document.getElementById('startbutton')
const answerbutton = document.getElementById('answerbutton')
const againbuttonlose = document.getElementById('againbuttonlose')
const againbuttonwin = document.getElementById('againbuttonwin')

let gameTimer // Timer for the game.
let gameTimerInterv // The game timer interval
let timer // The interval timer for the questions.
let nickname

/**
 * Hides everything that should not be displayed on the start page.
 */
function showStartpage () {
  hideElement(questionpage) /* Hides the questionpage */
  hideElement(loserpage) /* Hides the loserpage */
  hideElement(winnerpage) /* Hides the winnerpage */
  hideElement(scoreboard) /* Hides the scoreboard */
  hideElement(progresspage) /* Hides the progressbar */
  showElement(startpage) /* Hides the startpage */
}

/**
 * Hides everything that should not be displayed on the question page.
 */
function showQuestionpage () {
  hideElement(startpage) /* Hides the startpage */
  hideElement(loserpage) /* Hides the loserpage */
  hideElement(winnerpage) /* Hides the winnerpage */
  hideElement(scoreboard) /* Hides the scoreboard */
  showElement(questionpage) /* Hides the questionpage */
  showElement(progresspage) /* Hides the questionpage */
}

/**
 * Hides everything that should not be displayed on the loser page.
 */
function showLoserpage () {
  hideElement(startpage) /* Hides the startpage */
  hideElement(questionpage) /* Hides the questionpage */
  hideElement(winnerpage) /* Hides the winnerpage */
  hideElement(progresspage) /* Hides the progressbar */
  showElement(loserpage) /* Hides the loserpage */
  showElement(scoreboard) /* Hides the scoreboard */
  fixScoreboard()
}

/**
 * Hides everything that should not be displayed on the winner page.
 */
function showWinnerpage () {
  hideElement(startpage) /* Hides the startpage */
  hideElement(questionpage) /* Hides the questionpage */
  hideElement(loserpage) /* Hides the loserpage */
  hideElement(progresspage) /* Hides the progressbar */
  showElement(winnerpage) /* Hides the winnerpage */
  showElement(scoreboard) /* Hides the scoreboard */
  const winnername = document.getElementById('winnername')
  const winnertime = document.getElementById('winnertime')
  addToStorage(nickname.value, gameTimer)
  fixScoreboard()
  winnername.textContent = 'Well done ' + nickname.value + '!'
  winnertime.textContent = 'Your time was: ' + gameTimer + ' seconds'
}

/**
 * Getting the top 5 players from the stored scoreboard and display them.
 */
function fixScoreboard () {
  // Showing only top 5 scores
  const top5Scoreboard = getTop5Storage()
  const scoreboardmain = document.getElementById('scoreboardmain')
  scoreboardmain.innerHTML = ''
  const header3 = document.createElement('h3')
  header3.type = 'h3'
  header3.textContent = 'Scoreboard'
  scoreboardmain.appendChild(header3)

  let numbersb = 1
  top5Scoreboard.forEach(p => {
    const scoreText = document.createElement('text')
    scoreText.type = 'p'
    scoreText.textContent = numbersb + '. ' + p.name + ': ' + p.time + ' seconds'
    scoreboardmain.appendChild(scoreText)
    scoreboardmain.innerHTML += '<br>'
    numbersb++
  })

  // Showing all the scores.
  const showAll = document.createElement('showall')
  showAll.textContent = 'Show All Scores'
  showAll.style.color = 'blue'
  showAll.addEventListener('click', e => {
    const allScores = getStorage()
    scoreboardmain.innerHTML = ''
    const header3 = document.createElement('h3')
    header3.type = 'h3'
    header3.textContent = 'Scoreboard'
    scoreboardmain.appendChild(header3)

    let numbersb = 1
    allScores.forEach(p => {
      const scoreText = document.createElement('text')
      scoreText.type = 'p'
      scoreText.textContent = numbersb + '. ' + p.name + ': ' + p.time + ' seconds'
      scoreboardmain.appendChild(scoreText)
      scoreboardmain.innerHTML += '<br>'
      numbersb++
    })
  })
  scoreboardmain.appendChild(showAll)
}

/* The start buttons events */
startbutton.addEventListener('click', e => {
  nickname = document.getElementById('nickname')
  if (nickname.value === '') {
    nickname.style.borderColor = 'red'
  } else {
    nickname.style.borderColor = ''
    showQuestionpage()
    getQuestion(firstURL)
    startProgressbar()
    startGametimer()
  }
})

/* The answer buttons events */
answerbutton.addEventListener('click', async e => {
  const input = document.getElementById('answerinput')
  if (input.value === '' || input.value === 'on') {
    showLoserpage()
  } else {
    const working = await sendQuestion()
    if (nextURL === undefined && working) {
      showWinnerpage()
      clearInterval(timer)
    } else if (!working) {
      showLoserpage()
      clearInterval(timer)
    } else {
      getQuestion(nextURL)
      restartProgressbar()
    }
  }
})

/* The again buttons events on the loser page */
againbuttonlose.addEventListener('click', e => {
  nextURL = firstURL
  showStartpage()
})

/* The again buttons events on the win page */
againbuttonwin.addEventListener('click', e => {
  nextURL = firstURL
  showStartpage()
})

/**
 * Function to hide an element.
 * @param {element} element is an element in the html document.
 */
function hideElement (element) {
  element.style.display = 'none'
}

/**
 * Function to show an element.
 * @param {element} element is an element in the html document.
 */
function showElement (element) {
  element.style.display = 'block'
}

/**
 * This function starts the game timer from 0.
 */
function startGametimer () {
  gameTimer = 0
  clearGametimer()
  gameTimerInterv = setInterval(timerTick, 1000)
  /**
   * Adds +1 to gametimer (which will be same as 1 sec).
   */
  function timerTick () {
    gameTimer++
  }
}

/**
 * Cleares the game timer interval.
 */
function clearGametimer () {
  clearInterval(gameTimerInterv)
}

/**
 * Function for the progressbar timer.
 */
function startProgressbar () {
  const progressbar = document.getElementById('progressbar')
  const progresslabel = document.getElementById('progresslabel')
  let width = 90
  progresslabel.innerHTML = 'Time remaining: ' + width / 10
  progressbar.style.width = width + '%'
  timer = setInterval(timerDec, 1000)

  /**
   * Makes the progress bar smaller for each tick.
   */
  function timerDec () {
    if (width <= 0 || questionpage.style.display === 'none') {
      showLoserpage()
      clearInterval(timer)
    } else {
      width -= 10
      progresslabel.innerHTML = 'Time remaining: ' + width / 10
      progressbar.style.width = width + '%'
    }
  }
}

/**
 * This function resets the progress bar timer and interval.
 */
function restartProgressbar () {
  clearInterval(timer)
  startProgressbar()
}

/**
 * Fetching an url and returns the data as response.
 * @param {string} url a link in the form of a string.
 * @returns {Response} response from the url.
 */
async function fetchQuestion (url) {
  const response = await fetch(url)
  return await (response.json())
}

/**
 * Showing the question from the url to the webpage.
 * @param {string} url a link in the form of a string.
 */
async function getQuestion (url) {
  const urlResponse = await fetchQuestion(url)
  const questionheader = document.getElementById('questionheader')
  const question = document.getElementById('question')
  const answerinput = document.getElementById('answerinput')
  questionheader.innerHTML = 'Question ' + urlResponse.id + ':'
  question.innerHTML = urlResponse.question
  answerinput.innerHTML = ''

  if ('alternatives' in urlResponse) {
    for (const alt in urlResponse.alternatives) {
      const radioOption = document.createElement('input')
      radioOption.type = 'radio'
      radioOption.name = 'alternatives'
      radioOption.id = alt
      radioOption.value = urlResponse.alternatives[alt]
      radioOption.classList = 'radioInput'
      answerinput.style.textAlign = 'left'
      answerinput.appendChild(radioOption)

      const radioLabel = document.createElement('label')
      radioLabel.htmlFor = urlResponse.alternatives[alt]
      radioLabel.textContent = urlResponse.alternatives[alt]
      radioLabel.classList = 'radioText'
      answerinput.appendChild(radioLabel)
      answerinput.innerHTML += '<br>'
    }
  } else {
    const radioOption = document.createElement('input')
    radioOption.type = 'text'
    radioOption.name = 'inputtext'
    radioOption.id = 'inputtext'
    answerinput.style.textAlign = 'center'
    answerinput.style.padding = '30px'
    answerinput.appendChild(radioOption)
  }
  respURL = urlResponse.nextURL
}

/**
 * Sends the answer to the web page and waits for response.
 * @returns {boolean} true if error code 200, else false.
 */
async function sendQuestion () {
  const nextUrlResponse = await fetchQuestion(nextURL)
  let answer

  let response
  if ('alternatives' in nextUrlResponse) {
    const radioOptions = document.getElementsByClassName('radioInput')

    for (let i = 0; i < radioOptions.length; i++) {
      if (radioOptions[i].checked) {
        answer = radioOptions[i].id

        response = await fetch(respURL, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ answer })
        })
        break
      }
    }
  } else {
    answer = document.getElementById('inputtext').value
    /* Submits the question */
    response = await fetch(respURL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ answer })
    })
  }

  if (response.status === 200) {
    const data = await response.json()
    nextURL = data.nextURL
    return true
  } else {
    showLoserpage()
    return false
  }
}

showStartpage()
