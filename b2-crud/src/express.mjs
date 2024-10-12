import express from 'express'
import session from 'express-session'
import logger from 'morgan'
import defRoute from './route/webpage_route.mjs'

const app = express()

app.set('view engine', 'ejs')

app.use(session({
  cookie: {
    maxAge: 600000
  },
  resave: false,
  saveUninitialized: true,
  secret: 'keyboard cat'
}))

app.set('views', 'src/views')
app.use('/views/css', express.static('src/views/css'))
app.use(logger('dev', { immediate: true }))
app.use(express.urlencoded({ extended: true }))
app.use(express.json())
app.use(express.static('public'))

app.use('/', defRoute)

export default (port = 3000) => {
  app.listen(port, () => {
    console.log(`Listening at port ${port}`)
  })
}
