import { userModel } from './../model/user_model.mjs'

export const userController = {}

// Get register page values.
userController.register = async (req, res) => {
  const username = req.session.username ?? null
  const password = req.session.password ?? null
  const passwordAgain = req.session.passwordAgain ?? null
  const flashMessage = req.session.flashMessage ?? null
  const flashMessageStatus = req.session.flashMessageStatus ?? null
  const user = req.session.user ?? null

  // Resets the sessions values.
  req.session.username = null
  req.session.password = null
  req.session.passwordAgain = null
  req.session.flashMessage = null
  req.session.flashMessageStatus = null

  const data = {
    username,
    password,
    passwordAgain,
    flashMessage,
    flashMessageStatus,
    user
  }

  if (user) {
    const errorData = userController.createErrorData('403', 'Logout before register new user')
    res.status(403).render('error', errorData)
  } else {
    res.render('register', data)
  }
}

// Post create new user.
userController.newuser = async (req, res) => {
  // Getting information from the html body.
  const username = req.body.username
  const password = req.body.password
  const passwordAgain = req.body.passwordAgain

  const data = {
    username,
    password
  }

  // Checking if the user should be created or not.
  if (password !== passwordAgain) {
    req.session.flashMessage = 'The Passwords are not the same!'
    req.session.flashMessageStatus = 'red'
    res.status(400).redirect('/register')
  } else if (username === '') {
    req.session.flashMessage = 'The Username needs to be filled in!'
    req.session.flashMessageStatus = 'red'
    res.status(400).redirect('/register')
  } else if (password.length < 5) {
    req.session.flashMessage = 'The Password needs to be atleast 5 long!'
    req.session.flashMessageStatus = 'red'
    res.status(400).redirect('/register')
  } else {
    const user = await userModel.add(data)
    if (user) {
      req.session.flashMessage = 'Account created! You can now login'
      req.session.flashMessageStatus = 'green'
      res.redirect('/register')
    } else {
      req.session.flashMessage = 'The Username already exist!'
      req.session.flashMessageStatus = 'red'
      res.status(400).redirect('/register')
    }
  }
}

// Get Login page values.
userController.login = async (req, res) => {
  const username = req.session.username ?? null
  const password = req.session.password ?? null
  const flashMessage = req.session.flashMessage ?? null
  const flashMessageStatus = req.session.flashMessageStatus ?? null
  const user = req.session.user ?? null

  // Resets the sessions values.
  req.session.username = null
  req.session.password = null
  req.session.flashMessage = null
  req.session.flashMessageStatus = null

  const data = {
    username,
    password,
    flashMessage,
    flashMessageStatus,
    user
  }

  if (user) {
    const errorData = userController.createErrorData('403', 'You are already logged in')
    res.status(403).render('error', errorData)
  } else {
    res.render('login', data)
  }
}

// Post Login a user.
userController.loginProcess = async (req, res) => {
  const username = req.body.username
  const password = req.body.password

  const data = {
    username,
    password
  }

  const user = await userModel.login(data)
  req.session.user = user

  if (user) {
    req.session.flashMessage = ('You are now logged in as: ' + req.session.user.username)
    req.session.flashMessageStatus = 'green'
    res.redirect('/snippets')
  } else {
    req.session.flashMessage = 'The Username or Password was wrong!'
    req.session.flashMessageStatus = 'red'
    res.status(400).redirect('/login')
  }
}

// Get Logout and sets user to null.
userController.logout = async (req, res) => {
  if (!req.session.user) {
    const errorData = userController.createErrorData('403', 'You are already logged out')
    res.status(403).render('error', errorData)
  } else {
    req.session.user = null
    res.redirect('/snippets')
  }
}

// Creates an array with error data that is being returned.
userController.createErrorData = (statusCode, errorMessage) => {
  const errorData = {
    statusCode,
    errorMessage
  }
  return errorData
}

// Resets the DB.
// userController.reset = async (req, res, next) => {
//   await userModel.reset()
//   next()
// }
