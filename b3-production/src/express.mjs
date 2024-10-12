import express from 'express'
import session from 'express-session'
import MemoryStoreModule from 'memorystore'
import logger from 'morgan'
import wsServer from './websocket.mjs'
import webRoute from './route/webserver_route.mjs'

const app = express()
const PORT = process.env.PORT || 3000

app.set('view engine', 'ejs')

const MemoryStore = MemoryStoreModule(session)

app.use(session({
  cookie: {
    secure: true,
    sameSite: 'none',
    maxAge: 6000000
  },
  store: new MemoryStore({
    checkPeriod: 86400000
  }),
  resave: false,
  saveUninitialized: true,
  secret: 'keyboard cat'
}))

app.set('views', 'src/views')
app.use('/views/css', express.static('src/views/css'))
app.use('/views/js', express.static('src/views/js'))
app.use('/views/img', express.static('src/views/img'))
app.use(logger('dev', { immediate: true }))
app.use(express.urlencoded({ extended: true }))
app.use(express.json())
app.use(express.static('public'))
app.use('/', webRoute)

export default () => {
  const server = app.listen(PORT, () => {
    console.log(`Listening at port ${PORT}`)
  })

  server.on('upgrade', (request, socket, head) => {
    wsServer.handleUpgrade(request, socket, head, socket => {
      wsServer.emit('connection', socket, request)
    })
  })
}
