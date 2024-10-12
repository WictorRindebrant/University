/* Wictor Rindebrant */
'use strict'

import chatImgUrl from '../img/toolbar/chat.png'

import { setElements } from '../js/modules/draggable.js'
import { layerOnTop, newWindow } from './modules/window.js'
import { webConnection, webMsgSend } from './modules/websocket.js'

const parentElement = document.getElementById('desktop')
const chatBtn = document.getElementById('chatBtn')
let user

chatBtn.addEventListener('click', e => {
  const dragWindow = createWindow()
  const startPage = dragWindow.children[0]
  const connectBtn = startPage.children[3]
  let gameTimerInterv
  let chatBox

  if (user === undefined) {
    connectBtn.addEventListener('click', e => {
      user = startPage.children[2].value
      connectFunc()
    })
  } else {
    connectBtn.addEventListener('click', e => {
      user = startPage.children[2].value
      connectFunc()
    })
    connectFunc()
  }

  /**
   * This function handles the connection for the chat.
   */
  function connectFunc () {
    const websocket = webConnection()
    chatBox = createChatWindow(dragWindow)
    startPage.style.display = 'none'

    // Disconnect
    chatBox.children[2].addEventListener('click', e => {
      dragWindow.removeChild(chatBox)
      startPage.style.display = 'block'
      user = undefined
      websocket.close()
    })

    // Send chat
    chatBox.children[0].addEventListener('keydown', (e) => {
      if (e.key === 'Enter' && user !== undefined) {
        webMsgSend(user, chatBox.children[0].value)
        chatBox.children[0].value = ''
      }
    })

    // Recieve msg.
    websocket.onmessage = (event) => {
      const responseData = JSON.parse(event.data)
      if (responseData.type === 'message' || responseData.type === 'notification') {
        createMsg(chatBox.children[1], responseData.username, responseData.data)
        chatBox.children[1].scrollTop = chatBox.children[1].scrollHeight
      }
    }

    // Event that happens when you close the connection.
    websocket.onclose = () => {
      createMsg(chatBox.children[1], 'The Server', 'You have been Disconnected! Try to login again.')
    }

    clearInterval(gameTimerInterv)
    gameTimerInterv = setInterval(timerTick, 1000)

    /**
     *
     */
    function timerTick () {
      if (user === undefined && startPage.style.display === 'none') {
        websocket.close() // Closes the web socket
        chatBox.children[3].style.display = 'block' // show diconnected text
      } else if (user !== undefined && chatBox.children[3].style.display === 'block') {
        dragWindow.removeChild(chatBox) // Remove old chat
        connectFunc() // Reconnect
        chatBox.children[3].style.display = 'none' // hide disconnected text
      }
    }
  }
})

/**
 * This function creates the main chat window and the elements
 * That should start in this window.
 * @param {HTMLElement} window the main window for the chat application.
 * @returns {HTMLElement} the chat box to store all the chats inside of.
 */
function createChatWindow (window) {
  const chatBox = document.createElement('div')
  chatBox.className = 'chatBox'
  chatBox.id = 'chatBox'

  const chatWindow = document.createElement('div')
  chatWindow.className = 'chatWindow'
  chatWindow.id = 'chatWindow'

  const chatMsgInput = document.createElement('input')
  chatMsgInput.className = 'chatMsgInput'
  chatMsgInput.id = 'chatMsgInput'

  const chatDisconnectBtn = document.createElement('button')
  chatDisconnectBtn.className = 'chatDisconnectBtn'
  chatDisconnectBtn.id = 'chatDisconnectBtn'
  chatDisconnectBtn.textContent = 'Disconnect'

  const disconnectWarning = document.createElement('p')
  disconnectWarning.className = 'disconnectWarning'
  disconnectWarning.id = 'disconnectWarning'
  disconnectWarning.textContent = 'DISCONNECTED'
  disconnectWarning.style.display = 'none'

  chatBox.appendChild(chatMsgInput)
  chatBox.appendChild(chatWindow)
  chatBox.appendChild(chatDisconnectBtn)
  chatBox.appendChild(disconnectWarning)
  window.appendChild(chatBox)
  return chatBox
}

/**
 * Creates the new msg and adds it to the chatWindow.
 * @param {HTMLElement} chatWindow Where all the chatmsg are stored and shown.
 * @param {string} user The user that is posting the msg.
 * @param {string} msg The msg that the user wants to post.
 */
export function createMsg (chatWindow, user, msg) {
  const chatMsgBox = document.createElement('div')
  chatMsgBox.className = 'chatMsgBox'
  chatMsgBox.id = 'chatMsgBox'

  const chatUser = document.createElement('h4')
  chatUser.className = 'chatUser'
  chatUser.id = 'chatUser'
  chatUser.textContent = user

  const chatMsg = document.createElement('p')
  chatMsg.className = 'chatMsg'
  chatMsg.id = 'chatMsg'
  chatMsg.textContent = msg

  chatMsgBox.appendChild(chatUser)
  chatMsgBox.appendChild(chatMsg)
  chatWindow.appendChild(chatMsgBox)
}

let idnumb = 0
/**
 * This function creates the whole chat window.
 * It also creates the elements that is started inside the chat window.
 * @returns {HTMLElement} The main chat window.
 */
function createWindow () {
  const dragWindow = document.createElement('div')
  dragWindow.id = 'dragwindow' + idnumb
  dragWindow.className = 'dragwindow'
  dragWindow.setAttribute('draggable', 'true')
  dragWindow.addEventListener('mousedown', e => {
    layerOnTop(dragWindow)
  })

  const dragHeader = document.createElement('header')
  dragHeader.id = 'dragheader' + idnumb
  dragHeader.className = 'dragheader'
  dragHeader.setAttribute('draggable', 'false')

  const img = document.createElement('img')
  img.src = chatImgUrl
  img.className = 'dragheadimg'

  const h3 = document.createElement('h3')
  h3.className = 'dragh3'
  h3.textContent = 'Chat'

  const closebutton = document.createElement('button')
  closebutton.className = 'dragclosebtn'
  closebutton.textContent = 'X'
  closebutton.addEventListener('click', e => {
    parentElement.removeChild(dragWindow)
  })

  const startPage = document.createElement('div')
  startPage.className = 'startPage'
  startPage.id = 'startPage'
  startPage.style.display = 'block'

  const startPageWelcome = document.createElement('h1')
  startPageWelcome.className = 'startPageWelcome'
  startPageWelcome.id = 'startPageWelcome'
  startPageWelcome.textContent = 'Welcome to LNU Chat!'

  const startPageName = document.createElement('h2')
  startPageName.className = 'startPageName'
  startPageName.id = 'startPageName'
  startPageName.textContent = 'Enter your name:'

  const startPageInput = document.createElement('input')
  startPageInput.className = 'startPageInput'
  startPageInput.id = 'startPageInput'

  const startPageButton = document.createElement('button')
  startPageButton.className = 'startPageButton'
  startPageButton.id = 'startPageButton'
  startPageButton.textContent = 'Connect'

  dragHeader.appendChild(img)
  dragHeader.appendChild(h3)
  dragHeader.appendChild(closebutton)
  startPage.appendChild(startPageWelcome)
  startPage.appendChild(startPageName)
  startPage.appendChild(startPageInput)
  startPage.appendChild(startPageButton)
  dragWindow.appendChild(startPage)
  dragWindow.appendChild(dragHeader)

  parentElement.appendChild(dragWindow)
  newWindow(dragWindow)
  layerOnTop(dragWindow)
  setElements(dragWindow)
  idnumb++
  return dragWindow
}
