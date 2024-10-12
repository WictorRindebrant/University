import { newMessage } from './flashMessage.js'

console.log('Ready in websocket.js')

/**
 * Sends the given message to the function newMessage that is to the flashMessage.
 * @param {string} data the message that should be displayed.
 */
function outputLog (data) {
  newMessage(data)
}

const url = 'wss://' + window.location.hostname + window.location.pathname
const websocket = new WebSocket(url)

console.log(`Connecting to: ${url}`)

websocket.onopen = () => {
  console.log('The websocket is now open.')
}

websocket.onmessage = async (event) => {
  const text = await new Response(event.data).text()
  outputLog(`GitLab MSG: ${text}`)
}

websocket.onclose = () => {
  console.log('Websocket is now closed')
}
