/* Wictor Rindebrant */
'use strict'

let websocket

/**
 * Creates a websocket from given url and return it when called on.
 * @returns {WebSocket} Returns the created websocket
 */
export function webConnection () {
  websocket = new WebSocket('wss://courselab.lnu.se/message-app/socket')

  return websocket
}

/**
 * Sending a msg in JSON format to the server with usr and msg.
 * @param {string} usr The user that is sending the msg.
 * @param {string} msg The message to be sent to the server.
 */
export function webMsgSend (usr, msg) {
  if (!websocket || websocket.readState === 3) {
    console.log('The websocket is not connected to the server')
  } else {
    websocket.send(JSON.stringify({
      type: 'message',
      data: msg,
      username: usr,
      channel: 'The Channel',
      key: 'eDBE76deU7L0H9mEBgxUKVR0VCnq0XBd'
    }))
  }
}
