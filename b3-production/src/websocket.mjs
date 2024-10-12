import WebSocket, { WebSocketServer } from 'ws'

const wsServer = new WebSocketServer({
  noServer: true,
  clientTracking: true
})

export default wsServer

wsServer.on('connection', (ws) => {
  // console.log('Connetion recived. Adding client.')

  ws.on('message', (data) => {
    // console.log(`ws got: ${data}`)

    wsServer.broadcastExceptSelf(ws, data)
  })

  ws.on('close', () => console.info('Client closed connection'))
  ws.on('error', console.error)
})

wsServer.broadcastExceptSelf = (ws, data) => {
  let clients = 0

  wsServer.clients.forEach((client) => {
    if (client.readyState === WebSocket.OPEN) {
      clients++
      client.send(data)
    }
  })
  // console.log(`Broadcasted data to ${clients} (${wsServer.clients.size}) clients.`)
}
